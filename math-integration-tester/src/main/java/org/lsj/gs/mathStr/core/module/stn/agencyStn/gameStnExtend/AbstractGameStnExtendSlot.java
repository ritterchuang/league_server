package org.lsj.gs.mathStr.core.module.stn.agencyStn.gameStnExtend;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.mathStr.config.entity.AgencyStnConfig;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrReturnResult;
import org.lsj.gs.mathStr.core.module.stn.TemplateStn;
import org.lsj.gs.mathStr.core.module.stn.TemplateStnSlot;
import org.lsj.utils.MathUtil;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

// 抽象虎機遊戲統計者
public abstract class AbstractGameStnExtendSlot extends GameStnExtend {
    protected final TemplateStn totalStn; // 整體統計者

    public AbstractGameStnExtendSlot(AgencyStnConfig config) {
        super(config);
        this.totalStn = new TemplateStn();
    }

    // 更新統計資訊
    public void update(BoardGtrResult boardGtrResult) {
        // 1. 更新整體統計物件
        this.totalStn.update(boardGtrResult);
    }

    // 更新返還統計
    @Override
    public void updateReturnResult(BoardGtrReturnResult boardGtrReturnResult) {
        this.totalStn.updateReturnResult(boardGtrReturnResult);
    }


    //* 列印特殊事件 *//

    // 列印特殊事件 觸發率
    protected void printSpecialFeatureTriggerRate(TemplateStnSlot templateStnSlot) {
        Map<ConstMathSlot.SpecialFeatureType, Integer> specialFeatureCnt = templateStnSlot.getSpecialFeatureTypeToCountMap().entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldV, newV) -> oldV, LinkedHashMap::new));

        System.out.printf("%8s %n", "[ 特殊中獎率 ]");
        for (Map.Entry<ConstMathSlot.SpecialFeatureType, Integer> entry: specialFeatureCnt.entrySet()) {
            System.out.printf("%2s", entry.getKey());
            System.out.printf("%10f", MathUtil.divide(entry.getValue(), templateStnSlot.getTotalRoundPerGameState()));
            System.out.printf("%n");
        }
    }

    // 列印特殊事件 貢獻率
    protected void printSpecialFeatureRtp(TemplateStnSlot templateStnSlot) {
        Map<ConstMathSlot.SpecialFeatureType, Double> specialFeatureWin = templateStnSlot.getSpecialFeatureTypeToWinMap().entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldV, newV) -> oldV, LinkedHashMap::new));

        System.out.printf("%8s %n", "[ 特殊貢獻率 ]");
        for (Map.Entry<ConstMathSlot.SpecialFeatureType, Double> entry: specialFeatureWin.entrySet()) {
            System.out.printf("%2s", entry.getKey());
            System.out.printf("%10f", MathUtil.divide(
                    entry.getValue(),
                    MathUtil.multiply(templateStnSlot.getTotalRoundPerGameState(), MathUtil.divide(templateStnSlot.getTotalBet(), templateStnSlot.getTotalRound()))));
            System.out.printf("%n");
        }
    }


    //* 列印標誌 連線率*//

    // 依照指定次數計算連線率 TODO 之後優化防呆
    protected void printSymbolHitRateWithTargetTimes(Map<Integer, Map<Integer, Integer>> payComboIdToHitNumberCountMap, int times) {
        Map<Integer, Map<Integer, Integer>> sortHitNumberCountByPayComboIdByHitNumber = payComboIdToHitNumberCountMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(
                Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldV, newV) -> oldV, LinkedHashMap::new)), (oldV, newV) -> oldV, LinkedHashMap::new));

        // 2. 取得所有連線的最大連線數
        int maxHitNumber = sortHitNumberCountByPayComboIdByHitNumber.size() != 0
                ? sortHitNumberCountByPayComboIdByHitNumber.values().stream().mapToInt(
                entry -> entry.entrySet().stream().max(
                        Map.Entry.comparingByKey()).get().getKey()).max().getAsInt()
                : 5;

        // 3. 列印標頭
        this.printSymbolHitRateTitle(maxHitNumber);

        // 4. 列印 連線數 擊中率
        this.printSymbolHitRateDataWithTimes(maxHitNumber, sortHitNumberCountByPayComboIdByHitNumber, times);
    }

    // 列印標誌連線擊中率資訊
    private void printSymbolHitRateDataWithTimes(int maxHitNumber, Map<Integer, Map<Integer, Integer>> sortHitNumberCountByPayComboIdByHitNumber, int targetTimes) {
        for (Map.Entry<Integer, Map<Integer, Integer>> entry: sortHitNumberCountByPayComboIdByHitNumber.entrySet()) {
            System.out.printf("%2s", entry.getKey());
            for (int i = 0; i < maxHitNumber; i++) {
                if (entry.getValue().containsKey(i + 1)) {
                    System.out.printf("\t%10f", MathUtil.divide(entry.getValue().get(i + 1), targetTimes));
                }else {
                    System.out.printf("\t%10f", 0.0);
                }
                if (i == maxHitNumber - 1) {
                    System.out.printf("%n");
                }
            }
        }
    }

    // 依照指定次數計算貢獻率
    protected void printSymbolRtpWithTargetTimes(Map<Integer, Map<Integer, Double>> payComboIdToHitNumberWinMap, int targetTimes, double bet) {
        // 1. 整理資料[依照 payComboId, hitNumber 排序]
        Map<Integer, Map<Integer, Double>> sortHitNumberWinByPayComboIdByHitNumber = payComboIdToHitNumberWinMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(
                Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldV, newV) -> oldV, LinkedHashMap::new)), (oldV, newV) -> oldV, LinkedHashMap::new));


        // 2. 取得所有連線的最大連線數 TODO 之後優化防呆
        int maxHitNumber = sortHitNumberWinByPayComboIdByHitNumber.size() != 0
                ? sortHitNumberWinByPayComboIdByHitNumber.values().stream().mapToInt(
                entry -> entry.entrySet().stream().max(
                        Map.Entry.comparingByKey()).get().getKey()).max().getAsInt()
                : 5;

        // 3. 列印標頭
        this.printSymbolRtpTitle(maxHitNumber);

        // 4. 列印 連線數 貢獻率
        this.printSymbolRtpDataWithTimes(maxHitNumber, sortHitNumberWinByPayComboIdByHitNumber, targetTimes, bet);
    }

    private void printSymbolRtpDataWithTimes(int maxHitNumber, Map<Integer, Map<Integer, Double>> sortHitNumberWinByPayComboIdByHitNumber, int targetTimes, double bet) {
        for (Map.Entry<Integer, Map<Integer, Double>> entry: sortHitNumberWinByPayComboIdByHitNumber.entrySet()) {
            System.out.printf("%2s", entry.getKey());
            for (int i = 0; i < maxHitNumber; i++) {
                if (entry.getValue().containsKey(i + 1)) {
                    System.out.printf("\t%10f",
                            MathUtil.divide(
                                    entry.getValue().get(i + 1),
                                    MathUtil.multiply(targetTimes, bet)));
                }else {
                    System.out.printf("\t%10f", 0.0);
                }
                if (i == maxHitNumber - 1) {
                    System.out.printf("%n");
                }
            }
        }
    }


    //* 列印標頭 *//

    // 列進遊戲狀態標頭
    protected void printGameStateTitleInfo(int gameStateId, TemplateStnSlot templateStnSlot) {
        // 1. 印出子彈標題
        super.printTitle("狀態統計", gameStateId);

        // 2. 印出目標標題
        System.out.printf("\t%-6s \t%-6s \t%-6s \t%-6s \t%-6s \t%-6s %n", "平均場次", "觸發率", "總 RTP", "單局 RTP", "倍數標準差", "最大倍數");

        // 3. 印出 平均場次、觸發率
        double avgRound = MathUtil.divide(templateStnSlot.getTotalRoundPerGameState(), templateStnSlot.getTotalRound());
        double currentStateRtp = MathUtil.divide(templateStnSlot.getTotalWin(), templateStnSlot.getTotalBet());
        // 計算標準差
        System.out.printf("\t%8f \t%8f \t%8f \t%8f \t%8f \t%8f %n",
                avgRound,
                MathUtil.divide(templateStnSlot.getTotalRound(), this.totalStn.getTotalRound()),
                currentStateRtp,
                MathUtil.divide(
                        currentStateRtp,
                        avgRound),
                Math.sqrt(templateStnSlot.getTotalOddsSquare() / templateStnSlot.getTotalRound() - (templateStnSlot.getTotalOdds() / templateStnSlot.getTotalRound()) * (templateStnSlot.getTotalOdds() / templateStnSlot.getTotalRound())),
                templateStnSlot.getMaxOdds());
    }

    // 列印標至連線率 標頭
    private void printSymbolHitRateTitle(int maxHitNumber) {
        System.out.println("[ 標誌中獎率 ]");
        System.out.printf("\t");
        for (int hitNumber = 0; hitNumber < maxHitNumber; hitNumber++) {
            System.out.printf("\t%-8s", (hitNumber + 1) + "連線");
            if (hitNumber == maxHitNumber - 1) {
                System.out.printf("%n");
            }
        }
    }

    // 列印標誌連線貢獻率 標頭
    private void printSymbolRtpTitle(int maxHitNumber) {
        System.out.println("[ 標誌貢獻率 ]");
        System.out.printf("\t");
        for (int hitNumber = 0; hitNumber < maxHitNumber; hitNumber++) {
            System.out.printf("\t%-8s", (hitNumber + 1) + "連線");
            if (hitNumber == maxHitNumber - 1) {
                System.out.printf("%n");
            }
        }
    }
}

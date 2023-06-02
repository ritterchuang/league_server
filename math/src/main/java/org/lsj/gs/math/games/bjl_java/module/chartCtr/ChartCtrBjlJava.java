package org.lsj.gs.math.games.bjl_java.module.chartCtr;

import org.lsj.gs.math.core.baiRen.ConstMathBjl;
import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtilBaiRen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 新百家樂路圖計算器
public class ChartCtrBjlJava {
    private final int KEEP_ROUND = 60;
    private List<Object> chartList; // 路線圖
    private final ITableUtilBaiRen tableUtil; // 百人牌桌工具包介面

    public ChartCtrBjlJava(ITableUtilBaiRen tableUtil) {
        this.chartList = new ArrayList<>();
        this.tableUtil = tableUtil;
        this.chartList = this.generateChartList();
    }

    // 更新路線圖
    public void updateChart(int[] winBetAreaArray){
        if(this.chartList.size() >= this.KEEP_ROUND){
            this.chartList.remove(0);
        }

        this.chartList.add(this.calculateChartEnumBjl(winBetAreaArray).getCode());
    }

    // 取得路圖列表
    public List<Object> getChartList() {
        return chartList;
    }

    // 取得路圖陣列
    public int[] getChartArray(){
        return this.chartList.stream()
                .mapToInt(chartType -> (int)chartType)
                .toArray();
    }


    //* 產生初始路圖 *//

    // 產生路圖列表
    private List<Object> generateChartList() {
        // 1. 決定產出局數
        int randomRound = this.tableUtil.getNotSupportSetRngDataRandomUtil().getRandomIntWithAccuracy(this.KEEP_ROUND, ConstMathCommon.AccuracyType.A32768);

        // 2. 產生路圖
        return IntStream.range(0, randomRound)
                .mapToObj(roundIndex -> this.generateChart()).collect(Collectors.toList());
    }

    // 產生一局路圖 庄勝率 45% 閒勝率 45% 和勝率 10%
    private Object generateChart() {
        // 1. 決定獲勝押注類型
        ConstMathBjl.BetAreaTypeEnum betAreaTypeEnumBjlJava = ConstMathBjl.BetAreaTypeEnum.fromCode(
                this.tableUtil.getNotSupportSetRngDataRandomUtil().getArrayIndexByWeightWithAccuracy(
                        new ArrayList<>(){{
                            add(45);
                            add(45);
                            add(10);}},
                        ConstMathCommon.AccuracyType.A32768));

        // 2. 回傳莊家押注獲勝隨機結果 [庄8975 庄對 500 閒對 500 庄閒對 25]
        if (betAreaTypeEnumBjlJava.equals(ConstMathBjl.BetAreaTypeEnum.BANK)) {
            return this.generateBankTypeChartEnumType();
        }

        // 3. 回傳閒家押注獲勝隨機結果 [閒8975 庄對 500 閒對 500 庄閒對 25]
        if (betAreaTypeEnumBjlJava.equals(ConstMathBjl.BetAreaTypeEnum.PLAY)) {
            return this.generatePlayTypeChartEnumType();
        }

        // 4. 回傳和押注獲勝結果
        return ConstMathBjl.ChartEnum.TIE.getCode();
    }

    private int generateBankTypeChartEnumType() {
        List<ConstMathBjl.ChartEnum> chartEnumList = List.of(ConstMathBjl.ChartEnum.BANK_WIN, ConstMathBjl.ChartEnum.BANK_BP, ConstMathBjl.ChartEnum.BANK_PP, ConstMathBjl.ChartEnum.BANK_BP_PP);

        return chartEnumList.get(
                this.tableUtil.getNotSupportSetRngDataRandomUtil().getArrayIndexByWeightWithAccuracy(
                        new ArrayList<>(){{
                            add(8975);
                            add(500);
                            add(500);
                            add(25);}},
                        ConstMathCommon.AccuracyType.A32768)).getCode();
    }

    private int generatePlayTypeChartEnumType() {
        List<ConstMathBjl.ChartEnum> chartEnumBjlJavaList = List.of(ConstMathBjl.ChartEnum.PLAY_WIN, ConstMathBjl.ChartEnum.PLAY_BP, ConstMathBjl.ChartEnum.PLAY_PP, ConstMathBjl.ChartEnum.PLAY_BP_PP);

        return chartEnumBjlJavaList.get(
                this.tableUtil.getNotSupportSetRngDataRandomUtil().getArrayIndexByWeightWithAccuracy(
                        new ArrayList<>(){{
                            add(8975);
                            add(500);
                            add(500);
                            add(25);}},
                        ConstMathCommon.AccuracyType.A32768)).getCode();
    }


    //* 計算路圖 *//

    // 計算百家樂 路圖
    private ConstMathBjl.ChartEnum calculateChartEnumBjl(int[] winBetAreaIdArray) {
        List<Integer> winBetAreaIdList = Arrays.stream(winBetAreaIdArray).boxed().collect(Collectors.toList());

        // 和局
        if (winBetAreaIdList.contains(ConstMathBjl.BetAreaEnum.TIE.getCode())) {
            return ConstMathBjl.ChartEnum.TIE;
        }

        // 庄勝
        if (winBetAreaIdList.contains(ConstMathBjl.BetAreaEnum.BANK.getCode())
            && winBetAreaIdList.contains(ConstMathBjl.BetAreaEnum.BANK_PAIR.getCode())
            && winBetAreaIdList.contains(ConstMathBjl.BetAreaEnum.PLAY_PAIR.getCode())) {
            return ConstMathBjl.ChartEnum.BANK_BP_PP;
        }

        if (winBetAreaIdList.contains(ConstMathBjl.BetAreaEnum.BANK.getCode())
                && winBetAreaIdList.contains(ConstMathBjl.BetAreaEnum.BANK_PAIR.getCode())) {
            return ConstMathBjl.ChartEnum.BANK_BP;
        }

        if (winBetAreaIdList.contains(ConstMathBjl.BetAreaEnum.BANK.getCode())
                && winBetAreaIdList.contains(ConstMathBjl.BetAreaEnum.PLAY_PAIR.getCode())) {
            return ConstMathBjl.ChartEnum.BANK_PP;
        }

        if (winBetAreaIdList.contains(ConstMathBjl.BetAreaEnum.BANK.getCode())) {
            return ConstMathBjl.ChartEnum.BANK_WIN;
        }

        // 閒勝
        if (winBetAreaIdList.contains(ConstMathBjl.BetAreaEnum.PLAY.getCode())
                && winBetAreaIdList.contains(ConstMathBjl.BetAreaEnum.BANK_PAIR.getCode())
                && winBetAreaIdList.contains(ConstMathBjl.BetAreaEnum.PLAY_PAIR.getCode())) {
            return ConstMathBjl.ChartEnum.PLAY_BP_PP;
        }

        if (winBetAreaIdList.contains(ConstMathBjl.BetAreaEnum.PLAY.getCode())
                && winBetAreaIdList.contains(ConstMathBjl.BetAreaEnum.BANK_PAIR.getCode())) {
            return ConstMathBjl.ChartEnum.PLAY_BP;
        }

        if (winBetAreaIdList.contains(ConstMathBjl.BetAreaEnum.PLAY.getCode())
                && winBetAreaIdList.contains(ConstMathBjl.BetAreaEnum.PLAY_PAIR.getCode())) {
            return ConstMathBjl.ChartEnum.PLAY_PP;
        }

        if (winBetAreaIdList.contains(ConstMathBjl.BetAreaEnum.PLAY.getCode())) {
            return ConstMathBjl.ChartEnum.PLAY_WIN;
        }

        return ConstMathBjl.ChartEnum.INVALID;
    }

    // 重設
    public void reset() {}
}

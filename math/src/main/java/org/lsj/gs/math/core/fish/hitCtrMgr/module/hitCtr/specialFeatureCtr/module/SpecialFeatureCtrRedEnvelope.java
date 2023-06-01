package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.killCountCtr.IKillCountCtr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.RobotSpecialFeatureOddsInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.RobotSpecialFeatureResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.SpecialFeatureResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureCtrConfigExtend.SpecialFeatureCtrConfigExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureCtrConfigExtend.SpecialFeatureCtrConfigExtendRedEnvelope;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureResultExtend.SpecialFeatureResultExtendRedEnvelope;
import org.lsj.utils.MathUtil;

import java.util.*;
import java.util.stream.Collectors;

// 特殊事件紅包計算者
public class SpecialFeatureCtrRedEnvelope extends AbstractSpecialFeatureCtr implements ISpecialFeatureCtr {
    private final ConstMathFish.SpecialFeatureEnumType specialFeatureEnumType; // 特殊事件類型
    private final SpecialFeatureCtrConfigExtendRedEnvelope config; // 特殊事件紅包設定

    public SpecialFeatureCtrRedEnvelope(ConstMathFish.SpecialFeatureEnumType specialFeatureEnumType, SpecialFeatureCtrConfigExtend specialFeatureCtrConfigExtend, ITableUtil tableUtil) {
        super(specialFeatureCtrConfigExtend, tableUtil);
        this.specialFeatureEnumType = specialFeatureEnumType;
        this.config = (SpecialFeatureCtrConfigExtendRedEnvelope) specialFeatureCtrConfigExtend;
    }

    //* 計算者相關 *//
    // 取得特殊事件類型
    @Override
    public ConstMathFish.SpecialFeatureEnumType getSpecialFeatureType() {
        return specialFeatureEnumType;
    }


    //* 結果相關 *//
    // 計算真人特殊事件結果
    @Override
    public SpecialFeatureResult calculateSpecialFeatureResult(double totalRtp, double baseBet, IKillCountCtr killCountCtr) {
        // 1. 計算此次使用Rtp
        double usedSpecialFeatureRtp = MathUtil.multiply(totalRtp, this.config.getRtpUseRatio());

        // 2. 計算獲獎個數
        int awardCount = this.config.getAwardCountAndWeightArray()[0][
                this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(
                        Arrays.stream(this.config.getAwardCountAndWeightArray()[1]).boxed().collect(Collectors.toList()), ConstMathCommon.AccuracyType.MILLION)];

        // 3. 計算表演倍數列表
        List<Integer> showOddsList = this.calculateShowOddsList(awardCount);

        // 4. 計算獎項倍數
        int specialFeatureOdds = showOddsList.stream().mapToInt(i->i).sum();

        // 5. 計算觸發次數
        int killCount = killCountCtr.calculateKillCount(usedSpecialFeatureRtp, specialFeatureOdds, 1);

        // 6. 計算擊殺標誌
        boolean killFlag = killCount > 0;

        // 7. 計算單次得分
        double basicWin = MathUtil.moneyScale(MathUtil.multiply(specialFeatureOdds, baseBet));

        // 8. 計算總得分
        double totalWin = MathUtil.moneyScale(MathUtil.multiply(killCount, basicWin));

        // 9. 修正倍數表演結果
        this.adjustShowOddsList(showOddsList, this.config.getShowCount());

        // 11. 計算贏分表演資訊
        List<Double> winList = new ArrayList<>();
        showOddsList.forEach(odds -> winList.add(MathUtil.moneyScale(MathUtil.multiply(odds, baseBet))));

        // 12. 回傳包裝結果
        return this.packageSpecialFeatureResult(killFlag, killCount, basicWin, totalWin, awardCount, showOddsList, winList);
    }

    // 計算表演倍數列表
    private List<Integer> calculateShowOddsList(int awardCount) {
        // 1. 取得表演倍數陣列
        int[] resultArray = this.config.getAwardCountToAwardOddsListMap().get(awardCount).get(
                this.tableUtil.getMainRandomUtil().getArrayIndexByWeightWithAccuracy(
                        Arrays.stream(this.config.getAwardCountToAwardWeightArrayMap().get(awardCount)).boxed().collect(Collectors.toList()), ConstMathCommon.AccuracyType.MILLION)
        );

        // 2. 轉成列表
        List<Integer> result = Arrays.stream(resultArray).boxed().collect(Collectors.toList());

        // 3. 洗牌
        super.tableUtil.getMainRandomUtil().shuffleList(result);

        // 4. 回傳
        return result;
    }

    // 調整表演個數
    private void adjustShowOddsList(List<Integer> showOddsList, int showCount) {
        // 1. 滿足表演個數不調整
        if (showOddsList.size() == showCount) {
            return;
        }

        // 2. 過多表演個數，移除
        if (showOddsList.size() > showCount) {
            int removeTime = showOddsList.size() - showCount;
            for (int removeIndex = 0; removeIndex < removeTime; removeIndex++) {
                showOddsList.remove(showOddsList.size() - 1);
            }
            return;
        }

        // 3. 過少表演個數，添加
        int addTime = showCount - showOddsList.size();
        for (int addIndex = 0; addIndex < addTime; addIndex++) {
            // 3.1 決定表演倍數
            int showOdds = this.tableUtil.getMainRandomUtil().getRandomElement(Arrays.stream(this.config.getAwardOddsArray()).boxed().collect(Collectors.toList()), ConstMathCommon.AccuracyType.A32768);

            // 3.2 添加表演倍數
            showOddsList.add(showOdds);
        }
    }

    // 包裝結果
    private SpecialFeatureResult packageSpecialFeatureResult(boolean killFLag, int killCount, double basicWin, double totalWin, int awardCount, List<Integer> oddsCloneList, List<Double> winList) {
        // 1. 若無擊殺，包空殼
        if (!killFLag) {
            return new SpecialFeatureResult(false, 0.0, ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureResultExtendRedEnvelope());
        }

        // 2. 回傳包裝資料
        return new SpecialFeatureResult(true, totalWin, ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureResultExtendRedEnvelope(true, killCount, basicWin, totalWin,
                awardCount, oddsCloneList.stream().mapToInt(i -> i).toArray(), winList.stream().mapToDouble(i -> i).toArray()));
    }

    // 計算機器人特殊結果
    @Override
    public List<RobotSpecialFeatureResult> calculateRobotSpecialFeatureResultList(double robotTotalRtp) {
        return super.calculateRobotSpecialFeatureResultList(robotTotalRtp, this.calculateRobotSpecialFeatureOddsInfoList(), this.specialFeatureEnumType);
    }

    // 計算機器人特殊事件倍數資訊列表
    @Override
    public List<RobotSpecialFeatureOddsInfo> calculateRobotSpecialFeatureOddsInfoList() {
        // 1. 創建空間
        List<RobotSpecialFeatureOddsInfo> result = new ArrayList<>();

        // 2. 計算獲獎個數機率對應表
        Map<Integer, Double> awardCountProbMap = this.calculateAwardCountProbMap(this.config.getAwardCountAndWeightArray());

        // 3. 遍歷獲獎個數
        for (Map.Entry<Integer, Double> awardCountProbEntry : awardCountProbMap.entrySet()) {
            // 4. 計算獲獎倍數列表的權重索引與機率對應表
            Map<Integer, Double> awardOddsWeightIndexToProbMap = this.calculateAwardOddsWeightIndexToProbMap(
                    this.config.getAwardCountToAwardWeightArrayMap().get(awardCountProbEntry.getKey()));

            // 5. 組合機器人倍數資訊
            for (Map.Entry<Integer, Double> awardOddsProbMapEntry : awardOddsWeightIndexToProbMap.entrySet()){
                result.add(this.combineRobotSpecialFeatureOddsInfo(awardCountProbEntry, awardOddsProbMapEntry));
            }
        }

        // 4. 回傳
        return result;
    }

    // 組合機器人倍數資訊
    private RobotSpecialFeatureOddsInfo combineRobotSpecialFeatureOddsInfo(Map.Entry<Integer, Double> awardCountProbEntry, Map.Entry<Integer, Double> awardOddsProbMapEntry){
        // 1. 計算基本倍數
        int basicOdds = Arrays.stream(this.config.getAwardCountToAwardOddsListMap().get(awardCountProbEntry.getKey()).get(awardOddsProbMapEntry.getKey())).sum();

        // 2. 計算倍數出現率
        double oddsAppearProbability = MathUtil.multiply(awardCountProbEntry.getValue(), awardOddsProbMapEntry.getValue());

        return new RobotSpecialFeatureOddsInfo(basicOdds, oddsAppearProbability);
    }

    // 計算獲獎個數機率對應表
    private Map<Integer, Double> calculateAwardCountProbMap(int[][] awardCountAndWeightArray){
        Map<Integer, Double> result = new HashMap<>();

        // 1. 計算獲獎個數權重和
        int totalAwardCountWeight = Arrays.stream(awardCountAndWeightArray[1]).sum();

        // 2. 計算獲獎個數機率
        for (int awardCountIndex = 0; awardCountIndex < awardCountAndWeightArray[1].length; awardCountIndex++) {
            result.put(
                    awardCountAndWeightArray[0][awardCountIndex],
                    MathUtil.divide(awardCountAndWeightArray[1][awardCountIndex], totalAwardCountWeight)
            );
        }

        return result;
    }

    // 計算獲獎倍數列表的權重索引與機率對應表
    private Map<Integer, Double> calculateAwardOddsWeightIndexToProbMap(int[] awardWeightArray) {
        Map<Integer, Double> result = new HashMap<>();

        // 1. 計算獲獎倍數列表的權重和
        int totalAwardOddsWeight = Arrays.stream(awardWeightArray).sum();

        for (int awardWeightIndex = 0; awardWeightIndex < awardWeightArray.length; awardWeightIndex++) {
            result.put(
                    awardWeightIndex,
                    MathUtil.divide(awardWeightArray[awardWeightIndex], totalAwardOddsWeight)
            );
        }

        return result;
    }


    //* Rtp相關 *//
    // 計算扣除特殊事件剩餘的Rtp
    @Override
    public double calculateBasicAndAwardBulletRtp(double totalRtp) {
        // 1. 計算剩餘百分比
        double leftRatio = MathUtil.subtract(1.0, this.config.getRtpUseRatio());

        // 2. 計算剩餘Rtp
        return MathUtil.multiply(totalRtp, leftRatio);
    }
}

package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.module;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.RobotSpecialFeatureCombination;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.RobotSpecialFeatureOddsInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.RobotSpecialFeatureResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.SpecialFeatureResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureCtrConfigExtend.SpecialFeatureCtrConfigExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureResultExtend.SpecialFeatureResultExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureResultExtend.SpecialFeatureResultExtendInvalid;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureResultExtend.SpecialFeatureResultExtendRedEnvelope;
import org.lsj.utils.MathUtil;

import java.util.ArrayList;
import java.util.List;

// 抽象特殊事件處理者
public abstract class AbstractSpecialFeatureCtr implements ISpecialFeatureCtr {
    protected final SpecialFeatureCtrConfigExtend config; // 特殊事件設定
    protected final ITableUtil tableUtil; // 牌桌工具包

    public AbstractSpecialFeatureCtr(SpecialFeatureCtrConfigExtend specialFeatureCtrConfigExtend, ITableUtil tableUtil) {
        this.config = specialFeatureCtrConfigExtend;
        this.tableUtil = tableUtil;
    }

    //* 機器人結果相關 *//
    // 計算機器人結果
    protected List<RobotSpecialFeatureResult> calculateRobotSpecialFeatureResultList(double robotTotalRtp, List<RobotSpecialFeatureOddsInfo> robotSpecialFeatureOddsInfoList, ConstMathFish.SpecialFeatureEnumType specialFeatureEnumType) {
        // 1. 計算此次使用Rtp
        double usedSpecialFeatureRtp = MathUtil.multiply(robotTotalRtp, this.config.getRtpUseRatio());

        // 2. 計算所有得分組合
        List<RobotSpecialFeatureCombination> robotSpecialFeatureCombinationList = this.calculateSpecialFeatureAllCombinationResultList(robotSpecialFeatureOddsInfoList);

        // 3. 創建空殼
        List<RobotSpecialFeatureResult> result = new ArrayList<>();

        // 4. 添加觸發特殊事件結果
        result.addAll(this.calculateKillResult(usedSpecialFeatureRtp, robotSpecialFeatureCombinationList, specialFeatureEnumType));

        // 5. 添加未觸發特殊事件結果
        result.add(this.calculateNoKillResult(usedSpecialFeatureRtp, robotSpecialFeatureCombinationList, specialFeatureEnumType));

        // 6. 回傳
        return result;
    }

    // 計算 機器人特殊事件 所有倍數組合
    private List<RobotSpecialFeatureCombination> calculateSpecialFeatureAllCombinationResultList(List<RobotSpecialFeatureOddsInfo> robotSpecialFeatureOddsInfoList) {
        // 1. 創建空間
        List<RobotSpecialFeatureCombination> result = new ArrayList<>();

        // 2. 填寫資料
        for (int oddsIndex = 0; oddsIndex < robotSpecialFeatureOddsInfoList.size(); oddsIndex++) {
            // 2.1 取得特殊倍數
            int specialOdds = robotSpecialFeatureOddsInfoList.get(oddsIndex).getSpecialFeatureOdds();

            // 2.2 取得基礎得分
            double basicWin = MathUtil.moneyScale(MathUtil.multiply(specialOdds, 1.0));

            // 2.3 取得出現率
            double appearProbability = robotSpecialFeatureOddsInfoList.get(oddsIndex).getOddsAppearProbability();

            // 2.4 添加結果
            result.add(new RobotSpecialFeatureCombination(specialOdds, basicWin, appearProbability));
        }

        // 3. 回傳
        return result;
    }

    // 計算 擊殺 機器人特殊事件結果
    private List<RobotSpecialFeatureResult> calculateKillResult(double usedSpecialFeatureRtp, List<RobotSpecialFeatureCombination> robotSpecialFeatureCombinationList, ConstMathFish.SpecialFeatureEnumType specialFeatureEnumType) {
        // 1. 創建空間
        List<RobotSpecialFeatureResult> result = new ArrayList<>();

        // 2. 準備資料
        for (int combinationIndex = 0; combinationIndex < robotSpecialFeatureCombinationList.size(); combinationIndex++) {
            // 2.1 取得特殊得分
            double basicWin = robotSpecialFeatureCombinationList.get(combinationIndex).getBasicWin();

            // 2.2 取得出現並觸發率
            double appearAndTriggerProbability = robotSpecialFeatureCombinationList.get(combinationIndex).getAppearAndTriggerProbability(usedSpecialFeatureRtp);

            // 2.3 添加結果
            result.add(new RobotSpecialFeatureResult(
                    new SpecialFeatureResult(
                            true,
                            basicWin,
                            specialFeatureEnumType,
                            this.packageTriggerSpecialFeatureResultExtend(specialFeatureEnumType, basicWin)),
                            appearAndTriggerProbability));
        }

        // 3. 回傳
        return result;
    }

    // 計算 未擊殺 機器人特殊事件結果
    private RobotSpecialFeatureResult calculateNoKillResult(double usedSpecialFeatureRtp, List<RobotSpecialFeatureCombination> robotSpecialFeatureCombinationList, ConstMathFish.SpecialFeatureEnumType specialFeatureEnumType) {
        // 1. 計算無觸發機率
        double totalAppearAndNoTriggerProbability = this.calculateAppearAndNoTriggerProbability(usedSpecialFeatureRtp, robotSpecialFeatureCombinationList);

        // 2. 封裝結果
        return new RobotSpecialFeatureResult(new SpecialFeatureResult(false, 0.0, specialFeatureEnumType, this.packageNoTriggerSpecialFeatureResultExtend(specialFeatureEnumType)),
                totalAppearAndNoTriggerProbability);
    }

    // 包裝有觸發額外特殊事件
    private SpecialFeatureResultExtend packageTriggerSpecialFeatureResultExtend(ConstMathFish.SpecialFeatureEnumType specialFeatureEnumType, double basicWin) {
        switch (specialFeatureEnumType) {
            case RED_ENVELOPE: return new SpecialFeatureResultExtendRedEnvelope(true, 1, basicWin, basicWin, 0, new int[0], new double[0]);
            default: return new SpecialFeatureResultExtendInvalid();
        }
    }

    // 包裝無觸發額外特殊事件
    private SpecialFeatureResultExtend packageNoTriggerSpecialFeatureResultExtend(ConstMathFish.SpecialFeatureEnumType specialFeatureEnumType) {
        switch (specialFeatureEnumType) {
            case RED_ENVELOPE: return new SpecialFeatureResultExtendRedEnvelope();
            default: return new SpecialFeatureResultExtendInvalid();
        }
    }

    // 計算未擊殺機率
    private double calculateAppearAndNoTriggerProbability(double usedSpecialFeatureRtp, List<RobotSpecialFeatureCombination> robotSpecialFeatureCombinationList) {
        // 1. 創建空間
        double result = 0.0;

        // 2. 準備資料
        for (int combinationIndex = 0; combinationIndex < robotSpecialFeatureCombinationList.size(); combinationIndex++) {
            result = MathUtil.add(result,
                    robotSpecialFeatureCombinationList.get(combinationIndex).getAppearAndNoTriggerProbability(usedSpecialFeatureRtp));
        }

        return result;
    }
}
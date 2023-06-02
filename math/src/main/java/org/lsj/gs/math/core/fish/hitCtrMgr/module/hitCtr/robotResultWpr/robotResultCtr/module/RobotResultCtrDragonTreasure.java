package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.module;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendDragonTreasure;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.module.IAwardBulletGtr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.config.RobotResultCtrConfigExtendDragonTreasure;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.result.RobotBasicAndAwardBulletResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.result.RobotBasicOddsInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.module.ISpecialFeatureCtr;
import org.lsj.utils.MathUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 機器人結果計算者輪盤
public class RobotResultCtrDragonTreasure extends AbstractRobotResultCtr implements IRobotResultCtr {
    private final ConstMathFish.HitType hitType; // 機器人結果打擊類型
    private final RobotResultCtrConfigExtendDragonTreasure config; // 機器人結果打擊設定

    public RobotResultCtrDragonTreasure(ConstMathFish.HitType hitType, RobotResultCtrConfigExtendDragonTreasure robotResultCtrConfigExtendDragonTreasure, double robotRtp) {
        super(robotRtp);
        this.hitType = hitType;
        this.config = robotResultCtrConfigExtendDragonTreasure;
    }


    //* 機器人結果相關 *//
    // 計算機器人基本獎勵結果
    @Override
    public List<RobotBasicAndAwardBulletResult> calculateRobotBasicAndAwardBulletResult(double robotUsedRtp, BulletMgr bulletMgr, IAwardBulletGtr awardBulletGtr, ISpecialFeatureCtr specialFeatureCtr) {
        return super.calculateRobotBasicAndAwardBulletResultList(
                specialFeatureCtr.calculateBasicAndAwardBulletRtp(robotUsedRtp),
                this.getRobotBasicOddsInfoList(),
                awardBulletGtr.calculateRobotAwardBulletGtrResult(bulletMgr),
                awardBulletGtr.calculateNoKillResult(),
                this.hitType,
                this.getRobotHitResultExtend()
        );
    }
    

    //* 當前設定資訊相關 *//
    @Override
    public List<RobotBasicOddsInfo> getRobotBasicOddsInfoList() {
        // 1. 創建空間
        List<RobotBasicOddsInfo> result = new ArrayList<>();

        // 2. 計算倍數資訊
        // 2.1 計算顆數權重和
        int totalBallCountWeight = Arrays.stream(this.config.getDragonBallEndCountAndWeightArray()[1]).sum();

        // 2.2 遍歷所有顆數可能
        for (int ballCountIndex = 0; ballCountIndex < this.config.getDragonBallEndCountAndWeightArray()[0].length; ballCountIndex++) {
            // 2.3 取得此次顆數
            int ballCount = this.config.getDragonBallEndCountAndWeightArray()[0][ballCountIndex];

            // 2.4 取得此次顆數出現機率
            double ballAppearProbability = MathUtil.divide(this.config.getDragonBallEndCountAndWeightArray()[1][ballCountIndex], totalBallCountWeight);

            // 2.5 取得表演倍數列表
            List<int[]> showOddsArrayList = this.config.getEndBallCountToShowOddsListMap().get(ballCount);

            // 3. 計算表演倍數權重和
            int totalShowOddsWeight = Arrays.stream(this.config.getEndBallCountToShowOddsWeightArrayMap().get(ballCount)).sum();

            // 3.1 遍歷所有獎項可能
            for (int showOddsIndex = 0; showOddsIndex < showOddsArrayList.size(); showOddsIndex++) {
                // 3.2 取得此次表演倍數
                int[] showOddsArray = showOddsArrayList.get(showOddsIndex);

                // 3.3 取得此次表演倍數出現率
                double showOddsAppearProbability = MathUtil.divide(this.config.getEndBallCountToShowOddsWeightArrayMap().get(ballCount)[showOddsIndex], totalShowOddsWeight);

                // 4. 觸發神龍大獎
                if (Arrays.stream(showOddsArray).distinct().count() == this.config.getDragonBallOddsArray().length) {
                    result.addAll(this.calculateTriggerDragonRobotBasicOddsInfo(showOddsArray, ballAppearProbability, showOddsAppearProbability));
                }
                // 5. 沒觸發神龍大獎
                else {
                    result.add(this.calculateNoTriggerDragonRobotBasicOddsInfo(showOddsArray, ballAppearProbability, showOddsAppearProbability));
                }
            }
        }

        // 6. 回傳
        return result;
    }

    // 計算觸發神龍獎項資訊
    private List<RobotBasicOddsInfo> calculateTriggerDragonRobotBasicOddsInfo(int[] showOddsArray, double ballAppearProbability, double showOddsAppearProbability) {
        // 1. 創建空間
        List<RobotBasicOddsInfo> result = new ArrayList<>();

        // 2. 取得神龍倍數權重和
        int totalDragonMultiplierWeight = Arrays.stream(this.config.getDragonExtraMultiplierAndWeightArray()[1]).sum();

        // 3. 遍歷所有神龍倍數可能
        for (int dragonMultiplierIndex = 0; dragonMultiplierIndex < this.config.getDragonExtraMultiplierAndWeightArray()[0].length; dragonMultiplierIndex++) {
            // 3.1 取得此次神龍額外倍數出現率
            double dragonMultiplierAppearProbability = MathUtil.divide(this.config.getDragonExtraMultiplierAndWeightArray()[1][dragonMultiplierIndex], totalDragonMultiplierWeight);

            // 3.2 計算此次總倍數
            int totalOdds = (Arrays.stream(showOddsArray).sum()) * (showOddsArray.length) * (this.config.getDragonExtraMultiplierAndWeightArray()[0][dragonMultiplierIndex]);

            // 3.3 計算總出現率
            double appearProbability = MathUtil.multiply(MathUtil.multiply(ballAppearProbability, showOddsAppearProbability), dragonMultiplierAppearProbability);

            // 3.4 添加結果
            result.add(new RobotBasicOddsInfo(totalOdds, appearProbability));
        }

        // 4. 回傳
        return result;
    }

    // 計算未觸發神龍獎項資訊
    private RobotBasicOddsInfo calculateNoTriggerDragonRobotBasicOddsInfo(int[] showOddsArray, double ballAppearProbability, double showOddsAppearProbability) {
        // 1. 計算倍數
        int totalOdds = (Arrays.stream(showOddsArray).sum()) * (showOddsArray.length);

        // 2. 計算出現率
        double appearProbability = MathUtil.multiply(ballAppearProbability, showOddsAppearProbability);

        // 3. 添加結果
        return new RobotBasicOddsInfo(totalOdds, appearProbability);
    }

    // 取得機器人打擊額外資訊
    @Override
    public HitResultExtend getRobotHitResultExtend() {
        return new HitResultExtendDragonTreasure();
    }
}

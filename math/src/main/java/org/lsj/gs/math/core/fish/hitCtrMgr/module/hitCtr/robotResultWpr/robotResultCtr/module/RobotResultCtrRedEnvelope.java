package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.module;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendRedEnvelope;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.module.IAwardBulletGtr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.config.RobotResultCtrConfigExtendRedEnvelope;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.result.RobotBasicAndAwardBulletResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.result.RobotBasicOddsInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.module.ISpecialFeatureCtr;
import org.lsj.utils.MathUtil;

import java.util.*;

// 機器人結果計算者紅包
public class RobotResultCtrRedEnvelope extends AbstractRobotResultCtr implements IRobotResultCtr {
    private final ConstMathFish.HitType hitType; // 機器人結果打擊類型
    private final RobotResultCtrConfigExtendRedEnvelope config; // 機器人結果打擊設定

    public RobotResultCtrRedEnvelope(ConstMathFish.HitType hitType, RobotResultCtrConfigExtendRedEnvelope robotResultCtrConfigExtendRedEnvelope, double robotRtp) {
        super(robotRtp);
        this.hitType = hitType;
        this.config = robotResultCtrConfigExtendRedEnvelope;
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

    // 取得機器人基礎倍數資訊列表
    @Override
    public List<RobotBasicOddsInfo> getRobotBasicOddsInfoList() {
        // 1. 創建空間
        List<RobotBasicOddsInfo> result = new ArrayList<>();

        // 2. 計算獲獎個數機率對應表
        Map<Integer, Double> awardCountProbMap = this.calculateAwardCountProbMap(this.config.getAwardCountAndWeightArray());

        // 3. 遍歷獲獎個數
        for (Map.Entry<Integer, Double> awardCountProbEntry : awardCountProbMap.entrySet()) {
            // 4. 計算獲獎倍數列表的權重索引與機率對應表
            Map<Integer, Double> awardOddsWeightIndexToProbMap = this.calculateAwardOddsWeightIndexToProbMap(
                    this.config.getAwardCountToAwardWeightArrayMap().get(awardCountProbEntry.getKey()));

            // 5. 組合機器人倍數資訊
            for (Map.Entry<Integer, Double> awardOddsProbMapEntry : awardOddsWeightIndexToProbMap.entrySet()){
                result.add(this.combineRobotBasicOddsInfo(awardCountProbEntry, awardOddsProbMapEntry));
            }
        }

        // 6. 回傳
        return result;
    }

    // 組合機器人倍數資訊
    private RobotBasicOddsInfo combineRobotBasicOddsInfo(Map.Entry<Integer, Double> awardCountProbEntry, Map.Entry<Integer, Double> awardOddsProbMapEntry){
        // 1. 計算基本倍數
        int basicOdds = Arrays.stream(this.config.getAwardCountToAwardOddsListMap().get(awardCountProbEntry.getKey()).get(awardOddsProbMapEntry.getKey())).sum();

        // 2. 計算倍數出現率
        double oddsAppearProbability = MathUtil.multiply(awardCountProbEntry.getValue(), awardOddsProbMapEntry.getValue());

        return new RobotBasicOddsInfo(basicOdds, oddsAppearProbability);
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

    //* 當前設定資訊相關 *//
    // 取得機器人打擊額外資訊
    @Override
    public HitResultExtend getRobotHitResultExtend() {
        return new HitResultExtendRedEnvelope();
    }
}

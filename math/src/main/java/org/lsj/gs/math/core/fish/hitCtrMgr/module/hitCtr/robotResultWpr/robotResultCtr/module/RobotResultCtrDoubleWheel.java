package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.module;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendDoubleWheel;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.module.IAwardBulletGtr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.config.RobotResultCtrConfigExtendDoubleWheel;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.result.RobotBasicAndAwardBulletResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.result.RobotBasicOddsInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.module.ISpecialFeatureCtr;
import org.lsj.utils.MathUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 機器人結果計算者雙倍輪盤
public class RobotResultCtrDoubleWheel extends AbstractRobotResultCtr implements IRobotResultCtr {
    private final ConstMathFish.HitType hitType; // 機器人結果打擊類型
    private final RobotResultCtrConfigExtendDoubleWheel config; // 機器人結果打擊設定

    public RobotResultCtrDoubleWheel(ConstMathFish.HitType hitType, RobotResultCtrConfigExtendDoubleWheel robotResultCtrConfigExtendDoubleWheel, double robotRtp) {
        super(robotRtp);
        this.hitType = hitType;
        this.config = robotResultCtrConfigExtendDoubleWheel;
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

        // 2. 計算內圈倍數權重和
        int totalInSideWeight = Arrays.stream(this.config.getInSideShowOddsWeightArray()).sum();

        // 3. 添加倍數資訊
        for (int inSideOddsIndex = 0; inSideOddsIndex < this.config.getInSideShowOddsArray().length; inSideOddsIndex++) {
            // 3.1 計算內圈倍數
            int inSideOdds = this.config.getInSideShowOddsArray()[inSideOddsIndex];

            // 3.2 計算內圈出現率
            double inSideOddsAppearRate = MathUtil.divide(this.config.getInSideShowOddsWeightArray()[inSideOddsIndex], totalInSideWeight);

            // 3.3 取得外圈倍數權重列表
            int[] outSideOddsWeightArray = this.config.getInSideOddsToOutSideOddsWeightArrayMap().get(inSideOdds);

            // 3.3 計算外圈倍數權重和
            int totalOutSideWeight = Arrays.stream(outSideOddsWeightArray).sum();

            // 4. 計算結果
            for (int outSideOddsIndex = 0; outSideOddsIndex < this.config.getOutSideShowOddsArray().length; outSideOddsIndex++) {
                if (outSideOddsWeightArray[outSideOddsIndex] > 0) {
                    // 4.1 計算基礎倍數
                    int basicOdds = inSideOdds * this.config.getOutSideShowOddsArray()[outSideOddsIndex];

                    // 4.2 計算外圈出現率
                    double outSideOddsAppearRate = MathUtil.divide(outSideOddsWeightArray[outSideOddsIndex], totalOutSideWeight);

                    // 4.3 添加結果
                    result.add(new RobotBasicOddsInfo(basicOdds, MathUtil.multiply(inSideOddsAppearRate, outSideOddsAppearRate)));
                }
            }
        }

        // 4. 回傳
        return result;
    }


    //* 當前設定資訊相關 *//
    // 取得機器人打擊額外資訊
    @Override
    public HitResultExtend getRobotHitResultExtend() {
        return new HitResultExtendDoubleWheel();
    }
}

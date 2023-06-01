package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.module;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendTripleWheel;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.module.IAwardBulletGtr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.config.RobotResultCtrConfigExtendTripleWheel;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.result.RobotBasicAndAwardBulletResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.result.RobotBasicOddsInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.module.ISpecialFeatureCtr;
import org.lsj.utils.MathUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 機器人結果計算者雙倍輪盤
public class RobotResultCtrTripleWheel extends AbstractRobotResultCtr implements IRobotResultCtr {
    private final ConstMathFish.HitType hitType; // 機器人結果打擊類型
    private final RobotResultCtrConfigExtendTripleWheel config; // 機器人結果打擊設定

    public RobotResultCtrTripleWheel(ConstMathFish.HitType hitType, RobotResultCtrConfigExtendTripleWheel robotResultCtrConfigExtendTripleWheel, double robotRtp) {
        super(robotRtp);
        this.hitType = hitType;
        this.config = robotResultCtrConfigExtendTripleWheel;
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

        // 2. 計算外圈倍數權重和
        int firstTotalWeight = Arrays.stream(this.config.getShowFirstOddsWeightArray()).sum();

        // 3. 添加倍數資訊
        //處理外圈
        for (int firstOddsIndex = 0; firstOddsIndex < this.config.getFirstShowOddsArray().length; firstOddsIndex++) {
            // 3.1 計算外圈倍數
            int firstOdds = this.config.getFirstShowOddsArray()[firstOddsIndex];
            // 3.2 計算外圈出現率
            double firstOddsAppearRate = MathUtil.divide(this.config.getShowFirstOddsWeightArray()[firstOddsIndex], firstTotalWeight);
            // 3.3 計算中圈倍數權重和
            int secondTotalWeight = Arrays.stream(this.config.getShowSecondOddsWeightArray().get(firstOdds)).sum();

            //處理中圈
            for (int secondOddsIndex = 0; secondOddsIndex < this.config.getSecondShowOddsArray().length; secondOddsIndex++) {
                // 3.4 計算中圈倍數
                int secondOdds = this.config.getSecondShowOddsArray()[secondOddsIndex];
                // 3.5 計算中圈出現率
                double secondOddsAppearRate = MathUtil.divide(this.config.getShowSecondOddsWeightArray().get(firstOdds)[secondOddsIndex], secondTotalWeight);
                // 3.6 計算外*中圈倍數
                int firstAndSecondOdds = firstOdds * secondOdds;
                // 3.7 計算外*中圈組合出現率
                double firstAndSecondAppearRate = MathUtil.multiply(firstOddsAppearRate, secondOddsAppearRate);
                // 3.8 計算內圈倍數權重和
                int thirdTotalWeight = Arrays.stream(this.config.getShowThirdOddsWeightArray().get(firstAndSecondOdds).get(firstOdds)).sum();

                //處理內圈
                for (int thirdOddsIndex = 0; thirdOddsIndex < this.config.getThirdShowOddsArray().length; thirdOddsIndex++) {
                    // 3.9 計算內圈倍數
                    int thirdOdds = this.config.getSecondShowOddsArray()[thirdOddsIndex];
                    // 3.10 計算內圈出現率
                    double thirdOddsAppearRate = MathUtil.divide(this.config.getShowThirdOddsWeightArray().get(firstAndSecondOdds).get(firstOdds)[thirdOddsIndex], thirdTotalWeight);

                    // 4 計算結果
                    // 4.1 計算基礎倍數
                    int basicOdds = firstOdds * secondOdds * thirdOdds;
                    // 4.2 計算組合出現率
                    double oddsAppearRate = MathUtil.multiply(firstAndSecondAppearRate, thirdOddsAppearRate);
                    // 4.3 添加結果
                    result.add(new RobotBasicOddsInfo(basicOdds, oddsAppearRate));
                }
            }
        }
        // 5. 回傳
        return result;
    }

    //* 當前設定資訊相關 *//
    // 取得機器人打擊額外資訊
    @Override
    public HitResultExtend getRobotHitResultExtend() {
        return new HitResultExtendTripleWheel();
    }
}

package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.module;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendMultiTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.module.IAwardBulletGtr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.config.RobotResultCtrConfigExtendMultiTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.result.RobotBasicAndAwardBulletResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.result.RobotBasicOddsInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.module.ISpecialFeatureCtr;
import org.lsj.utils.MathUtil;

import java.util.ArrayList;
import java.util.List;

// 機器人結果計算者多重目標
public class RobotResultCtrMultiTarget extends AbstractRobotResultCtr implements IRobotResultCtr {
    private final ConstMathFish.HitType hitType; // 機器人結果打擊類型
    private final RobotResultCtrConfigExtendMultiTarget config; // 機器人結果打擊設定
    private final int robotHitResultOddsPointCount = 20; // 機器人打擊結果倍數的區間點數

    public RobotResultCtrMultiTarget(ConstMathFish.HitType hitType, RobotResultCtrConfigExtendMultiTarget robotResultCtrConfigExtendMultiTarget, double robotRtp) {
        super(robotRtp);
        this.hitType = hitType;
        this.config = robotResultCtrConfigExtendMultiTarget;
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
        // 1. 計算所有倍數可能
        int[] basicOddsArray = this.getBasicOddsArray();

        // 2. 平均種倍數出現率
        double appearProbability = MathUtil.divide(1, basicOddsArray.length);

        // 3. 封裝
        List<RobotBasicOddsInfo> result = new ArrayList<>();
        for (int oddsIndex = 0; oddsIndex < basicOddsArray.length; oddsIndex++) {
            result.add(new RobotBasicOddsInfo(basicOddsArray[oddsIndex], appearProbability));
        }

        // 4. 回傳
        return result;
    }

    // 取得基礎倍數列表
    private int[] getBasicOddsArray() {
        // 1. 創建空間
        List<Integer> result = new ArrayList<>();
        int lowerOdds = this.config.getRobotHitResultLimitBase().getLowerLimitBase();
        int upperOdds = this.config.getRobotHitResultLimitBase().getUpperLimitBase();

        // 2. 計算所有倍數
        for (int oddsIndex = 0; oddsIndex <= this.robotHitResultOddsPointCount; oddsIndex++) {
            int basicOdds = lowerOdds + (int) (MathUtil.divide((upperOdds - lowerOdds) * oddsIndex, this.robotHitResultOddsPointCount));
            result.add(basicOdds);
        }

        return result.stream().mapToInt(x -> x).toArray();
    }


    //* 當前設定資訊相關 *//
    // 取得機器人打擊額外資訊
    @Override
    public HitResultExtend getRobotHitResultExtend() {
        return new HitResultExtendMultiTarget();
    }
}

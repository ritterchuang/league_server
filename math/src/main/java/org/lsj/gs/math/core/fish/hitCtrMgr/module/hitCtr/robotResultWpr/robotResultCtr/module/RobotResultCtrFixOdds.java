package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.module;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendFixedOdds;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.module.IAwardBulletGtr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.config.RobotResultCtrConfigExtendFixOdds;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.result.RobotBasicAndAwardBulletResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.result.RobotBasicOddsInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.module.ISpecialFeatureCtr;

import java.util.ArrayList;
import java.util.List;

// 機器人結果計算者固定倍數
public class RobotResultCtrFixOdds extends AbstractRobotResultCtr implements IRobotResultCtr {
    private final ConstMathFish.HitType hitType; // 機器人結果打擊類型
    private final RobotResultCtrConfigExtendFixOdds config; // 機器人結果打擊設定

    public RobotResultCtrFixOdds(ConstMathFish.HitType hitType, RobotResultCtrConfigExtendFixOdds robotResultCtrConfigExtendFixOdds, double robotRtp) {
        super(robotRtp);
        this.hitType = hitType;
        this.config = robotResultCtrConfigExtendFixOdds;
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

        // 2. 添加倍數資訊
        result.add(new RobotBasicOddsInfo(this.config.getOdds(), 1.0));

        // 3. 回傳
        return result;
    }


    //* 當前設定資訊相關 *//
    // 取得機器人打擊額外資訊
    @Override
    public HitResultExtend getRobotHitResultExtend() {
        return new HitResultExtendFixedOdds();
    }
}

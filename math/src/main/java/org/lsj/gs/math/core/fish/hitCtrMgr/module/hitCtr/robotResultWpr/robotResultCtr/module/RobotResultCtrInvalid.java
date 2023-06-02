package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.module;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendInvalid;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.module.IAwardBulletGtr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.result.RobotBasicAndAwardBulletResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.result.RobotBasicOddsInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.module.ISpecialFeatureCtr;

import java.util.Collections;
import java.util.List;

// 機器人結果計算者非法
public class RobotResultCtrInvalid extends AbstractRobotResultCtr implements IRobotResultCtr{
    private final ConstMathFish.HitType hitType; // 機器人結果打擊類型

    public RobotResultCtrInvalid(ConstMathFish.HitType hitType, double robotRtp) {
        super(robotRtp);
        this.hitType = hitType;
    }


    //* 機器人結果相關 *//
    // 計算機器人基本獎勵結果
    @Override
    public List<RobotBasicAndAwardBulletResult> calculateRobotBasicAndAwardBulletResult(double robotUsedRtp, BulletMgr bulletMgr, IAwardBulletGtr awardBulletGtr, ISpecialFeatureCtr specialFeatureCtr) {
        return Collections.emptyList();
    }

    // 取得機器人基礎倍數資訊列表
    @Override
    public List<RobotBasicOddsInfo> getRobotBasicOddsInfoList() {
        return Collections.emptyList();
    }


    //* 當前設定資訊相關 *//
    // 取得機器人打擊額外資訊
    @Override
    public HitResultExtend getRobotHitResultExtend() {
        return new HitResultExtendInvalid();
    }
}

package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.module;

import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;
import org.lsj.gs.math.core.fish.clientHitResultPgr.IClientHitResultPgr;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.module.IAwardBulletGtr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.result.RobotBasicAndAwardBulletResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.result.RobotBasicOddsInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.RobotSpecialFeatureResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.module.ISpecialFeatureCtr;
import org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.client.robotHitResult.RobotPresentResult;

import java.util.List;

// 機器人結果計算者介面
public interface IRobotResultCtr {
    //* 機器人結果相關 *//
    // 計算機器人基本獎勵結果
    List<RobotBasicAndAwardBulletResult> calculateRobotBasicAndAwardBulletResult(double robotUsedRtp, BulletMgr bulletMgr, IAwardBulletGtr awardBulletGtr, ISpecialFeatureCtr specialFeatureCtr);

    // 計算機器人表演結果
    List<RobotPresentResult> calculateRobotPresentResultList(List<RobotBasicAndAwardBulletResult> robotBasicAndAwardBulletResultList, List<RobotSpecialFeatureResult> robotSpecialFeatureResultList, IClientHitResultPgr clientHitResultWpr);

    // 取得機器人基礎倍數資訊列表
    List<RobotBasicOddsInfo> getRobotBasicOddsInfoList();


    //* 當前設定資訊相關 *//
    // 取得機器人利潤率
    double getRobotRtp();

    // 取得機器人打擊額外資訊
    HitResultExtend getRobotHitResultExtend();
}

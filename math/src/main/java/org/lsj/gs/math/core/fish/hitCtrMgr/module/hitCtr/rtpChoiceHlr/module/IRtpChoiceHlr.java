package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.rtpChoiceHlr.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;

// Rtp 選擇處理者介面
public interface IRtpChoiceHlr {

    // 計算此次真人使用的Rtp
    double calculateUsedRtp(int bulletIndex, BulletMgr bulletMgr, ConstMathCommon.ShuffleType shuffleType, GameAdjustParameter gameAdjustParameter, double humanRtp);

    // 計算此次機器人使用的Rtp
    double calculateRobotUsedRtp(int bulletIndex, BulletMgr bulletMgr, double robotRtp);
}

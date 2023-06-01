package com.lx.gs.math.core.fish.hitCtrMgr.module.hitCtr;

import com.lx.gs.math.core.common.ConstMathCommon;
import com.lx.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import com.lx.gs.math.core.fish.bulletMgr.BulletMgr;
import com.lx.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import com.lx.gs.math.core.fish.clientHitResultPgr.IClientHitResultPgr;
import com.lx.gs.math.core.fish.hitCtrMgr.entity.client.ClientHit;
import com.lx.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;
import com.lx.gs.math.core.fish.hitCtrMgr.entity.client.hitTypeExtend.HitTypeExtend;
import com.lx.gs.math.core.fish.hitCtrMgr.entity.server.HitResult;
import com.lx.gs.math.core.fish.mathFishConfigHlr.entity.client.robotHitResult.RobotHitResult;

// 打擊計算器介面
public interface IHitCtr {
    // 取得真人打擊結果
    HitResult calculateHitResult(Bullet bullet, ClientTarget target, ClientHit hit, BulletMgr bulletMgr, ConstMathCommon.ShuffleType shuffleType, GameAdjustParameter gameAdjustParameter);

    // 取得機器人打擊結果
    RobotHitResult calculateRobotHitResult(int bulletIndex, BulletMgr bulletMgr, IClientHitResultPgr clientHitResultWpr);

    // 檢驗完整性
    boolean checkComplete(HitTypeExtend hitTypeExtend);
}

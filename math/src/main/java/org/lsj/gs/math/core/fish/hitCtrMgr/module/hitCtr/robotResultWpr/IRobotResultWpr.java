package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr;

import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;
import org.lsj.gs.math.core.fish.clientHitResultPgr.IClientHitResultPgr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.module.IAwardBulletGtr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.rtpChoiceHlr.module.IRtpChoiceHlr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.module.ISpecialFeatureCtr;
import org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.client.robotHitResult.RobotHitResult;

// 機器人結果包裝者介面
public interface IRobotResultWpr {

    // 計算機器人打擊結果
    RobotHitResult calculateRobotHitResult(int bulletIndex, BulletMgr bulletMgr, IClientHitResultPgr clientHitResultWpr, IAwardBulletGtr awardBulletGtr, ISpecialFeatureCtr specialFeatureCtr, IRtpChoiceHlr rtpChoiceHlr);
}

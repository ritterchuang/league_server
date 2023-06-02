package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.module;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.config.*;

// 機器人結果計算者工廠
public class RobotResultCtrFactory {

    // 創建機器人結果計算者
    public IRobotResultCtr createRobotResultCtr(ConstMathFish.HitType hitType, HitTypeConfigExtend hitTypeConfigExtend, double robotRtp) {
        switch (hitType) {
            case FIXED_ODDS: return new RobotResultCtrFixOdds(hitType, new RobotResultCtrConfigExtendFixOdds(hitTypeConfigExtend), robotRtp);
            case RANDOM_ODDS: return new RobotResultCtrRandomOdds(hitType, new RobotResultCtrConfigExtendRandomOdds(hitTypeConfigExtend), robotRtp);
            case MULTI_TARGET: return new RobotResultCtrMultiTarget(hitType, new RobotResultCtrConfigExtendMultiTarget(hitTypeConfigExtend), robotRtp);
            case WHEEL: return new RobotResultCtrWheel(hitType, new RobotResultCtrConfigExtendWheel(hitTypeConfigExtend), robotRtp);
            case RED_ENVELOPE: return new RobotResultCtrRedEnvelope(hitType, new RobotResultCtrConfigExtendRedEnvelope(hitTypeConfigExtend), robotRtp);
            case DRAGON_TREASURE: return new RobotResultCtrDragonTreasure(hitType, new RobotResultCtrConfigExtendDragonTreasure(hitTypeConfigExtend), robotRtp);
            case DOUBLE_WHEEL: return new RobotResultCtrDoubleWheel(hitType, new RobotResultCtrConfigExtendDoubleWheel(hitTypeConfigExtend), robotRtp);
            case TREASURE_BOX: return new RobotResultCtrTreasureBox(hitType, new RobotResultCtrConfigExtendTreasureBox(hitTypeConfigExtend), robotRtp);
            case REEL: return new RobotResultCtrReel(hitType, new RobotResultCtrConfigExtendReel(hitTypeConfigExtend), robotRtp);
            case TRIPLE_WHEEL: return new RobotResultCtrTripleWheel(hitType, new RobotResultCtrConfigExtendTripleWheel(hitTypeConfigExtend), robotRtp);
            default: return new RobotResultCtrInvalid(hitType, robotRtp);
        }
    }
}

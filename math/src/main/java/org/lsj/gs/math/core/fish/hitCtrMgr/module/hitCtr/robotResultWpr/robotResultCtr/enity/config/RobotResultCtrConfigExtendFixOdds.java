package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.config;

import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendFixedOdds;

// 機器人打擊結果計算者設定固定倍數
public class RobotResultCtrConfigExtendFixOdds extends RobotResultCtrConfigExtend{
    private final int odds; // 固定倍數

    public RobotResultCtrConfigExtendFixOdds(HitTypeConfigExtend HitTypeConfigExtend) {
        HitTypeConfigExtendFixedOdds hitTypeConfigExtendFixedOdds = (HitTypeConfigExtendFixedOdds)HitTypeConfigExtend;
        this.odds = hitTypeConfigExtendFixedOdds.getOdds();
    }

    public int getOdds() {
        return odds;
    }
}

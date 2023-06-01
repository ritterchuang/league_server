package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.config;

import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendRandomOdds;

// 機器人打擊結果計算者設定隨機倍數
public class RobotResultCtrConfigExtendRandomOdds extends RobotResultCtrConfigExtend{
    private final int[] oddsArray; // 倍數列表
    private final int[] oddsWeightArray; // 倍數權重

    public RobotResultCtrConfigExtendRandomOdds(HitTypeConfigExtend HitTypeConfigExtend) {
        HitTypeConfigExtendRandomOdds hitTypeConfigExtendRandomOdds = (HitTypeConfigExtendRandomOdds)HitTypeConfigExtend;
        this.oddsArray = hitTypeConfigExtendRandomOdds.getOddsArray();
        this.oddsWeightArray = hitTypeConfigExtendRandomOdds.getOddsWeightArray();
    }

    public int[] getOddsArray() {
        return oddsArray;
    }

    public int[] getOddsWeightArray() {
        return oddsWeightArray;
    }
}

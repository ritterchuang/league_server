package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.config;

import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendWheel;

// 機器人打擊結果計算者設定滾輪
public class RobotResultCtrConfigExtendWheel extends RobotResultCtrConfigExtend{
    private final int[] showOddsArray; // 表演倍數陣列
    private final int[] showOddsWeightArray; // 表演倍數權重列表

    public RobotResultCtrConfigExtendWheel(HitTypeConfigExtend HitTypeConfigExtend) {
        HitTypeConfigExtendWheel hitTypeConfigExtendWheel = (HitTypeConfigExtendWheel)HitTypeConfigExtend;
        this.showOddsArray = hitTypeConfigExtendWheel.getShowOddsArray();
        this.showOddsWeightArray = hitTypeConfigExtendWheel.getShowOddsWeightArray();
    }

    public int[] getShowOddsArray() {
        return showOddsArray;
    }

    public int[] getShowOddsWeightArray() {
        return showOddsWeightArray;
    }
}

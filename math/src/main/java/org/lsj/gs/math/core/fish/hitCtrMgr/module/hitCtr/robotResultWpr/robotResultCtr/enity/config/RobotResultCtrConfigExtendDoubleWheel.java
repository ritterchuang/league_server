package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.config;

import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendDoubleWheel;

import java.util.Map;

// 機器人打擊結果計算者設定滾輪
public class RobotResultCtrConfigExtendDoubleWheel extends RobotResultCtrConfigExtend{
    private final int[] inSideShowOddsArray; // 內圈表演倍數陣列
    private final int[] outSideShowOddsArray; // 外圈表演倍數陣列
    private final int[] inSideShowOddsWeightArray; // 內圈表演倍數權重列表
    private final Map<Integer, int[]> inSideOddsToOutSideOddsWeightArrayMap; // 外圈表演倍數權重

    public RobotResultCtrConfigExtendDoubleWheel(HitTypeConfigExtend HitTypeConfigExtend) {
        HitTypeConfigExtendDoubleWheel hitTypeConfigExtendDoubleWheel = (HitTypeConfigExtendDoubleWheel)HitTypeConfigExtend;
        this.inSideShowOddsArray = hitTypeConfigExtendDoubleWheel.getInSideShowOddsArray();
        this.outSideShowOddsArray = hitTypeConfigExtendDoubleWheel.getOutSideShowOddsArray();
        this.inSideShowOddsWeightArray = hitTypeConfigExtendDoubleWheel.getInSideShowOddsWeightArray();
        this.inSideOddsToOutSideOddsWeightArrayMap = hitTypeConfigExtendDoubleWheel.getInSideOddsToOutSideOddsWeightArrayMap();
    }

    public int[] getInSideShowOddsArray() {
        return inSideShowOddsArray;
    }

    public int[] getOutSideShowOddsArray() {
        return outSideShowOddsArray;
    }

    public int[] getInSideShowOddsWeightArray() {
        return inSideShowOddsWeightArray;
    }

    public Map<Integer, int[]> getInSideOddsToOutSideOddsWeightArrayMap() {
        return inSideOddsToOutSideOddsWeightArrayMap;
    }
}

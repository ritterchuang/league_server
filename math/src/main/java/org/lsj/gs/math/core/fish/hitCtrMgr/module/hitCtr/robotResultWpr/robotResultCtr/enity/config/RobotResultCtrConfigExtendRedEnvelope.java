package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.config;

import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendRedEnvelope;

import java.util.List;
import java.util.Map;

// 機器人打擊結果計算者設定滾輪
public class RobotResultCtrConfigExtendRedEnvelope extends RobotResultCtrConfigExtend{
    private final int showCount; // 表演長度
    private final int[] awardOddsArray; // 獲獎倍數列表
    private final int[][] awardCountAndWeightArray; // 獲獎個數與權重陣列
    private final Map<Integer, List<int[]>> awardCountToAwardOddsListMap; // <結束顆數, 獲獎倍數列表>
    private final Map<Integer, int[]> awardCountToAwardWeightArrayMap; // <結束顆數, 獲獎權重列表>

    public RobotResultCtrConfigExtendRedEnvelope(HitTypeConfigExtend HitTypeConfigExtend) {
        HitTypeConfigExtendRedEnvelope hitTypeConfigExtendRedEnvelope = (HitTypeConfigExtendRedEnvelope)HitTypeConfigExtend;
        this.showCount = hitTypeConfigExtendRedEnvelope.getShowCount();
        this.awardOddsArray = hitTypeConfigExtendRedEnvelope.getAwardOddsArray();
        this.awardCountAndWeightArray = hitTypeConfigExtendRedEnvelope.getAwardCountAndWeightArray();
        this.awardCountToAwardOddsListMap = hitTypeConfigExtendRedEnvelope.getAwardCountToAwardOddsListMap();
        this.awardCountToAwardWeightArrayMap = hitTypeConfigExtendRedEnvelope.getAwardCountToAwardWeightArrayMap();
    }

    public int getShowCount() {
        return showCount;
    }

    public int[] getAwardOddsArray() {
        return awardOddsArray;
    }

    public int[][] getAwardCountAndWeightArray() {
        return awardCountAndWeightArray;
    }

    public Map<Integer, List<int[]>> getAwardCountToAwardOddsListMap() {
        return awardCountToAwardOddsListMap;
    }

    public Map<Integer, int[]> getAwardCountToAwardWeightArrayMap() {
        return awardCountToAwardWeightArrayMap;
    }
}

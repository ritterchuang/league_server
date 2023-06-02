package org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend;

import java.util.List;
import java.util.Map;

// 紅包目標打擊設定
public class HitTypeConfigExtendRedEnvelope extends HitTypeConfigExtend{
    private final int showCount; // 表演長度
    private final int[] awardOddsArray; // 表演未獲獎倍數列表
    private final int[][] awardCountAndWeightArray; // 獲獎個數與權重陣列
    private final Map<Integer, List<int[]>> awardCountToAwardOddsListMap; // <獲獎個數, 獲獎倍數列表>
    private final Map<Integer, int[]> awardCountToAwardWeightArrayMap; // <獲獎個數, 獲獎倍數列表的權重列表>

    public HitTypeConfigExtendRedEnvelope(int showCount, int[] awardOddsArray, int[][] awardCountAndWeightArray, Map<Integer, List<int[]>> awardCountToAwardOddsListMap, Map<Integer, int[]> awardCountToAwardWeightArrayMap) {
        this.showCount = showCount;
        this.awardOddsArray = awardOddsArray;
        this.awardCountAndWeightArray = awardCountAndWeightArray;
        this.awardCountToAwardOddsListMap = awardCountToAwardOddsListMap;
        this.awardCountToAwardWeightArrayMap = awardCountToAwardWeightArrayMap;
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

package org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend;

import java.util.Map;

// 雙重輪盤目標打擊設定
public class HitTypeConfigExtendDoubleWheel extends HitTypeConfigExtend{
    private final int[] inSideShowOddsArray; // 內圈表演倍數陣列
    private final int[] outSideShowOddsArray; // 外圈表演倍數陣列
    private final int[] inSideShowOddsWeightArray; // 內圈表演倍數權重列表
    private final Map<Integer, int[]> inSideOddsToOutSideOddsWeightArrayMap; // 外圈表演倍數權重

    public HitTypeConfigExtendDoubleWheel(int[] inSideShowOddsArray, int[] outSideShowOddsArray, int[] inSideShowOddsWeightArray, Map<Integer, int[]> inSideOddsToOutSideOddsWeightArrayMap) {
        this.inSideShowOddsArray = inSideShowOddsArray;
        this.outSideShowOddsArray = outSideShowOddsArray;
        this.inSideShowOddsWeightArray = inSideShowOddsWeightArray;
        this.inSideOddsToOutSideOddsWeightArrayMap = inSideOddsToOutSideOddsWeightArrayMap;
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

package org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend;

import java.util.List;
import java.util.Map;

// 神龍尋珠打擊設定
public class HitTypeConfigExtendDragonTreasure extends HitTypeConfigExtend{
    private final int[] dragonBallOddsArray; // 龍珠倍數列表
    private final int[][] dragonBallEndCountAndWeightArray; // 神龍結束顆數與權重陣列
    private final Map<Integer, List<int[]>> endBallCountToShowOddsListMap; // <結束顆數, 龍珠表演陣列列表>
    private final Map<Integer, int[]> endBallCountToShowOddsWeightArrayMap; // <結束顆數, 龍珠表演陣列權重列表>
    private final int[][] dragonExtraMultiplierAndWeightArray; // 神龍額外倍數與權重陣列


    public HitTypeConfigExtendDragonTreasure(int[] dragonBallOddsArray, int[][] dragonBallEndCountAndWeightArray, Map<Integer, List<int[]>> endBallCountToShowOddsListMap, Map<Integer, int[]> endBallCountToShowOddsWeightArrayMap, int[][] dragonExtraMultiplierAndWeightArray) {
        this.dragonBallOddsArray = dragonBallOddsArray;
        this.dragonBallEndCountAndWeightArray = dragonBallEndCountAndWeightArray;
        this.endBallCountToShowOddsListMap = endBallCountToShowOddsListMap;
        this.endBallCountToShowOddsWeightArrayMap = endBallCountToShowOddsWeightArrayMap;
        this.dragonExtraMultiplierAndWeightArray = dragonExtraMultiplierAndWeightArray;
    }

    public int[] getDragonBallOddsArray() {
        return dragonBallOddsArray;
    }

    public int[][] getDragonBallEndCountAndWeightArray() {
        return dragonBallEndCountAndWeightArray;
    }

    public Map<Integer, List<int[]>> getEndBallCountToShowOddsListMap() {
        return endBallCountToShowOddsListMap;
    }

    public Map<Integer, int[]> getEndBallCountToShowOddsWeightArrayMap() {
        return endBallCountToShowOddsWeightArrayMap;
    }

    public int[][] getDragonExtraMultiplierAndWeightArray() {
        return dragonExtraMultiplierAndWeightArray;
    }
}

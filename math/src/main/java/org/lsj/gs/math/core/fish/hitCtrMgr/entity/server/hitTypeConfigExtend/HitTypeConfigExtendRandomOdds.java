package org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend;

// 隨機倍數打擊設定
public class HitTypeConfigExtendRandomOdds extends HitTypeConfigExtend{
    private final int[] oddsArray; // 倍數列表
    private final int[] oddsWeightArray; // 倍數權重

    public HitTypeConfigExtendRandomOdds(int[] oddsArray, int[] oddsWeightArray) {
        this.oddsArray = oddsArray;
        this.oddsWeightArray = oddsWeightArray;
    }

    public int[] getOddsArray() {
        return oddsArray;
    }

    public int[] getOddsWeightArray() {
        return oddsWeightArray;
    }
}

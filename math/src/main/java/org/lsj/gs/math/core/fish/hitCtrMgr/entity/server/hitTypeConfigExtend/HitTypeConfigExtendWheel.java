package org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend;

// 輪盤目標打擊設定
public class HitTypeConfigExtendWheel extends HitTypeConfigExtend{
    private final int[] showOddsArray; // 表演倍數陣列
    private final int[] showOddsWeightArray; // 表演倍數權重列表

    public HitTypeConfigExtendWheel(int[] showOddsArray, int[] showOddsWeightArray) {
        this.showOddsArray = showOddsArray;
        this.showOddsWeightArray = showOddsWeightArray;
    }

    public int[] getShowOddsArray() {
        return showOddsArray;
    }

    public int[] getShowOddsWeightArray() {
        return showOddsWeightArray;
    }
}

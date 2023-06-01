package org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend;

import java.util.Map;

// 三重輪盤目標打擊設定
public class HitTypeConfigExtendTripleWheel extends HitTypeConfigExtend{
    private final int[] firstShowOddsArray; // 外圈表演倍數列表
    private final int[] secondShowOddsArray; // 中圈表演倍數列表
    private final int[] thirdShowOddsArray; // 內圈表演倍數列表
    private final int[] showFirstOddsWeightArray; // 外圈倍數權重列表
    private final Map<Integer, int[]> showSecondOddsWeightArray; // 中圈倍數權重列表
    private final Map<Integer, Map<Integer,int[]>> showThirdOddsWeightArray; // 內圈倍數權重列表

    public HitTypeConfigExtendTripleWheel(int[] firstShowOddsArray, int[] secondShowOddsArray, int[] thirdShowOddsArray,int[] showFirstOddsWeightArray, Map<Integer, int[]> showSecondOddsWeightArray,Map<Integer, Map<Integer,int[]>> showThirdOddsWeightArray) {
        this.firstShowOddsArray = firstShowOddsArray;
        this.secondShowOddsArray = secondShowOddsArray;
        this.thirdShowOddsArray = thirdShowOddsArray;
        this.showFirstOddsWeightArray = showFirstOddsWeightArray;
        this.showSecondOddsWeightArray = showSecondOddsWeightArray;
        this.showThirdOddsWeightArray = showThirdOddsWeightArray;
    }

    public int[] getFirstShowOddsArray() {return firstShowOddsArray;}
    public int[] getSecondShowOddsArray() {
        return secondShowOddsArray;
    }
    public int[] getThirdShowOddsArray() { return thirdShowOddsArray; }
    public int[] getShowFirstOddsWeightArray() {
        return showFirstOddsWeightArray;
    }
    public Map<Integer, int[]> getShowSecondOddsWeightArray() {
        return showSecondOddsWeightArray;
    }
    public Map<Integer, Map<Integer,int[]>> getShowThirdOddsWeightArray() {
        return showThirdOddsWeightArray;
    }
}

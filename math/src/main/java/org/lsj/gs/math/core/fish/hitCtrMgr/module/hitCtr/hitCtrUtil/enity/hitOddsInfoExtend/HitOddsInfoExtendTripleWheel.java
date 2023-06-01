package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.hitOddsInfoExtend;

// 三重輪盤額外倍數資訊
public class HitOddsInfoExtendTripleWheel extends HitOddsInfoExtend{
    private final int firstOddsIndex; // 外圈倍數
    private final int secondOddsIndex; // 中圈倍數
    private final int thirdOddsIndex; // 內圈倍數
    public HitOddsInfoExtendTripleWheel(int firstOddsIndex, int secondOddsIndex, int thirdOddsIndex) {
        this.firstOddsIndex = firstOddsIndex;
        this.secondOddsIndex = secondOddsIndex;
        this.thirdOddsIndex = thirdOddsIndex;
    }
    public int getFirstOddsIndex() {
        return firstOddsIndex;
    }
    public int getSecondOddsIndex() {
        return secondOddsIndex;
    }
    public int getThirdOddsIndex() {
        return thirdOddsIndex;
    }
}

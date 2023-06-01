package org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend;

// 雙重輪盤目標客製處理結果
public class HitResultExtendTripleWheel extends HitResultExtend{
    private final int firstShowOddsIndex; // 外圈表演倍數位置
    private final int secondShowOddsIndex; // 中圈表演倍數位置
    private final int thirdShowOddsIndex; // 內圈表演倍數位置
    private final int firstShowOdds; // 外圈表演倍數
    private final int secondShowOdds; // 中圈表演倍數
    private final int thirdShowOdds; // 內圈表演倍數

    public HitResultExtendTripleWheel(int firstShowOddsIndex, int secondShowOddsIndex, int thirdShowOddsIndex, int firstShowOdds, int secondShowOdds, int thirdShowOdds) {
        this.firstShowOddsIndex = firstShowOddsIndex;
        this.secondShowOddsIndex = secondShowOddsIndex;
        this.thirdShowOddsIndex = thirdShowOddsIndex;
        this.firstShowOdds = firstShowOdds;
        this.secondShowOdds = secondShowOdds;
        this.thirdShowOdds = thirdShowOdds;
    }

    public HitResultExtendTripleWheel() {
        this.firstShowOddsIndex = 0;
        this.secondShowOddsIndex = 0;
        this.thirdShowOddsIndex = 0;
        this.firstShowOdds = 0;
        this.secondShowOdds = 0;
        this.thirdShowOdds = 0;
    }

    public int getFirstShowOddsIndex() {
        return firstShowOddsIndex;
    }
    public int getSecondShowOddsIndex() {
        return secondShowOddsIndex;
    }
    public int getThirdShowOddsIndex() {
        return thirdShowOddsIndex;
    }

    public int getFirstShowOdds() {
        return firstShowOdds;
    }
    public int getSecondShowOdds() {
        return secondShowOdds;
    }
    public int getThirdShowOdds() {
        return thirdShowOdds;
    }

}

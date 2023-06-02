package org.lsj.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.enity.displayResultExtend;

import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendTripleWheel;
import org.lsj.utils.MathUtil;

// 客製表演雙重輪盤
public class DisplayResultExtendTripleWheel extends AbstractDisplayResultExtendValue{
    private final int firstShowOddsIndex; // 內圈表演倍數位置
    private final int secondShowOddsIndex; // 外圈表演倍數位置
    private final int thirdShowOddsIndex; // 外圈表演倍數位置
    private final int firstShowOdds;  // 內圈表演倍數
    private final int secondShowOdds;  // 外圈表演倍數
    private final int thirdShowOdds;  // 外圈表演倍數

    public DisplayResultExtendTripleWheel(int killCount, double basicWin, HitResultExtendTripleWheel hitResultExtendTripleWheel) {
        super(killCount, basicWin, MathUtil.moneyScale(MathUtil.multiply(killCount, basicWin)));
        this.firstShowOddsIndex = hitResultExtendTripleWheel.getFirstShowOddsIndex();
        this.secondShowOddsIndex = hitResultExtendTripleWheel.getSecondShowOddsIndex();
        this.thirdShowOddsIndex = hitResultExtendTripleWheel.getThirdShowOddsIndex();
        this.firstShowOdds = hitResultExtendTripleWheel.getFirstShowOdds();
        this.secondShowOdds = hitResultExtendTripleWheel.getSecondShowOdds();
        this.thirdShowOdds = hitResultExtendTripleWheel.getThirdShowOdds();
    }

    public DisplayResultExtendTripleWheel(int killCount, double basicWin, double totalWin, int firstShowOddsIndex, int secondShowOddsIndex, int thirdShowOddsIndex, int firstShowOdds, int secondShowOdds, int thirdShowOdds) {
        super(killCount, basicWin, totalWin);
        this.firstShowOddsIndex = firstShowOddsIndex;
        this.secondShowOddsIndex = secondShowOddsIndex;
        this.thirdShowOddsIndex = thirdShowOddsIndex;
        this.firstShowOdds = firstShowOdds;
        this.secondShowOdds = secondShowOdds;
        this.thirdShowOdds = thirdShowOdds;
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

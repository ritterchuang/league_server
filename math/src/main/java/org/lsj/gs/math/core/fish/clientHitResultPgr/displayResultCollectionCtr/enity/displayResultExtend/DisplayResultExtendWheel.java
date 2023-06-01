package org.lsj.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.enity.displayResultExtend;

import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendWheel;
import org.lsj.utils.MathUtil;

// 客製表演輪盤
public class DisplayResultExtendWheel extends AbstractDisplayResultExtendValue{
    private final int showOddsIndex; // 表演倍數位置
    private final double showWin; // 表演分數
    private final int showOdds;  // 表演倍數

    public DisplayResultExtendWheel(int killCount, double basicWin, HitResultExtendWheel hitResultExtendWheel) {
        super(killCount, basicWin, MathUtil.moneyScale(MathUtil.multiply(killCount, basicWin)));
        this.showOddsIndex = hitResultExtendWheel.getShowOddsIndex();
        this.showWin = hitResultExtendWheel.getShowWin();
        this.showOdds = hitResultExtendWheel.getShowOdds();
    }

    public int getShowOddsIndex() {
        return showOddsIndex;
    }

    public double getShowWin() {
        return showWin;
    }

    public int getShowOdds() {
        return showOdds;
    }
}

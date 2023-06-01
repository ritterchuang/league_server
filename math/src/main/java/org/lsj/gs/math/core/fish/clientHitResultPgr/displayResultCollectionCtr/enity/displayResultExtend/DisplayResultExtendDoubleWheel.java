package org.lsj.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.enity.displayResultExtend;

import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendDoubleWheel;
import org.lsj.utils.MathUtil;

// 客製表演雙重輪盤
public class DisplayResultExtendDoubleWheel extends AbstractDisplayResultExtendValue{
    private final int inSideShowOddsIndex; // 內圈表演倍數位置
    private final int outSideShowOddsIndex; // 外圈表演倍數位置
    private final int inSideShowOdds;  // 內圈表演倍數
    private final int outSideShowOdds;  // 外圈表演倍數

    public DisplayResultExtendDoubleWheel(int killCount, double basicWin, HitResultExtendDoubleWheel hitResultExtendDoubleWheel) {
        super(killCount, basicWin, MathUtil.moneyScale(MathUtil.multiply(killCount, basicWin)));
        this.inSideShowOddsIndex = hitResultExtendDoubleWheel.getInSideShowOddsIndex();
        this.outSideShowOddsIndex = hitResultExtendDoubleWheel.getOutSideShowOddsIndex();
        this.inSideShowOdds = hitResultExtendDoubleWheel.getInSideShowOdds();
        this.outSideShowOdds = hitResultExtendDoubleWheel.getOutSideShowOdds();
    }

    public DisplayResultExtendDoubleWheel(int killCount, double basicWin, double totalWin, int inSideShowOddsIndex, int outSideShowOddsIndex, double inSideShowWin, double outSideShowWin, int inSideShowOdds, int outSideShowOdds) {
        super(killCount, basicWin, totalWin);
        this.inSideShowOddsIndex = inSideShowOddsIndex;
        this.outSideShowOddsIndex = outSideShowOddsIndex;
        this.inSideShowOdds = inSideShowOdds;
        this.outSideShowOdds = outSideShowOdds;
    }

    public int getInSideShowOddsIndex() {
        return inSideShowOddsIndex;
    }

    public int getOutSideShowOddsIndex() {
        return outSideShowOddsIndex;
    }

    public int getInSideShowOdds() {
        return inSideShowOdds;
    }

    public int getOutSideShowOdds() {
        return outSideShowOdds;
    }
}

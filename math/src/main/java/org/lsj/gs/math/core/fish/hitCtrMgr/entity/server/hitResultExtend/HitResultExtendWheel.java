package org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend;

// 輪盤目標客製處理結果
public class HitResultExtendWheel extends HitResultExtend{
    private final int showOddsIndex; // 初始表演倍數位置
    private final int showOdds; // 表演倍數
    private final double showWin; // 表演贏分

    public HitResultExtendWheel(int showOddsIndex, int showOdds, double showWin) {
        this.showOddsIndex = showOddsIndex;
        this.showOdds = showOdds;
        this.showWin = showWin;
    }

    public HitResultExtendWheel() {
        this.showOddsIndex = 0;
        this.showOdds = 0;
        this.showWin = 0.0;
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

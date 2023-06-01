package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.accumulateWinResultPgr;

// 累積得分結果
public class AccumulateWinResult {
    private final double beforeAccWin; // 轉前累積總得分
    private final double afterAccWin; // 轉後累積總得分

    public AccumulateWinResult() {
        this.beforeAccWin = 0.0;
        this.afterAccWin = 0.0;
    }

    public AccumulateWinResult(double beforeAccWin, double afterAccWin) {
        this.beforeAccWin = beforeAccWin;
        this.afterAccWin = afterAccWin;
    }

    public double getBeforeAccWin() {
        return beforeAccWin;
    }

    public double getAfterAccWin() {
        return afterAccWin;
    }
}

package org.lsj.gs.math.core.slot.accumulateWinCtr;

// 累積得分計算者結果
public class AccumulateWinCtrResult {
    private final double beforeAccWin; // 轉前累積總得分
    private final double afterAccWin; // 轉後累積總得分

    public AccumulateWinCtrResult() {
        this.beforeAccWin = 0.0;
        this.afterAccWin = 0.0;
    }

    public AccumulateWinCtrResult(double beforeAccWin, double afterAccWin) {
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

package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundResult;

// 客端局額外結果父類別
public class RoundResultExtend {
    protected double totalWin; // 總得分

    public RoundResultExtend(double totalWin) {
        this.totalWin = totalWin;
    }

    public RoundResultExtend() {
        this.totalWin = 0.0;
    }

    public double getTotalWin() {
        return totalWin;
    }
}

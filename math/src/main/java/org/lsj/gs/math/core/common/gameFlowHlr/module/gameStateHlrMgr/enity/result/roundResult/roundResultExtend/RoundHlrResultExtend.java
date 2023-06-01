package org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.roundResultExtend;

// 遊戲局額外結果
public class RoundHlrResultExtend {
    protected final double totalWin; // 總得分

    public RoundHlrResultExtend(double totalWin) {
        this.totalWin = totalWin;
    }

    public double getTotalWin() {
        return totalWin;
    }
}

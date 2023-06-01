package com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.enity;

// 消除額外結果
public class CascadeResultExtend {
    private final double totalWin; // 總得分

    public CascadeResultExtend(double totalWin) {
        this.totalWin = totalWin;
    }

    public double getTotalWin() {
        return totalWin;
    }
}

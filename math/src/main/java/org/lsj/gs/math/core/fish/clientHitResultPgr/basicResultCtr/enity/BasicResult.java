package org.lsj.gs.math.core.fish.clientHitResultPgr.basicResultCtr.enity;

// 魚基本結果
public class BasicResult {
    private final boolean killFlag; // 擊殺標誌
    private final int killCount; // 擊殺次數
    private final double basicWin; // 魚基本贏分

    public BasicResult(boolean killFlag, int killCount, double basicWin) {
        this.killFlag = killFlag;
        this.killCount = killCount;
        this.basicWin = basicWin;
    }

    public BasicResult() {
        this.killFlag = false;
        this.killCount = 0;
        this.basicWin = 0.0;
    }

    public boolean isKillFlag() {
        return killFlag;
    }

    public int getKillCount() {
        return killCount;
    }

    public double getBasicWin() {
        return basicWin;
    }
}

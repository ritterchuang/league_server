package org.lsj.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.enity.displayResultExtend;

// 客製表演抽象父類別有值
public class AbstractDisplayResultExtendValue extends DisplayResultExtend{
    protected final int killCount;    // 擊殺次數
    protected final double basicWin;  // 單次得分
    protected final double totalWin;  // 總得分

    public AbstractDisplayResultExtendValue(int killCount, double basicWin, double totalWin) {
        this.killCount = killCount;
        this.basicWin = basicWin;
        this.totalWin = totalWin;
    }

    public int getKillCount() {
        return killCount;
    }

    public double getBasicWin() {
        return basicWin;
    }

    public double getTotalWin() {
        return totalWin;
    }
}

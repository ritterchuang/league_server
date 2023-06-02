package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureResultExtend;

// 特殊事件抽象父類別
public class AbstractSpecialFeatureResultExtendValue extends SpecialFeatureResultExtend {
    private final boolean killFlag; // 擊殺標誌
    private final int killCount; // 擊殺次數
    private final double basicWin; // 單次特殊事件得分
    private final double totalWin; // 總贏分

    public AbstractSpecialFeatureResultExtendValue(boolean killFlag, int killCount, double basicWin, double totalWin) {
        this.killFlag = killFlag;
        this.killCount = killCount;
        this.basicWin = basicWin;
        this.totalWin = totalWin;
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

    public double getTotalWin() {
        return totalWin;
    }
}

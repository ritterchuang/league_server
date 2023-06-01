package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureResultExtend;

// 特殊事件紅包結果
public class SpecialFeatureResultExtendRedEnvelope extends AbstractSpecialFeatureResultExtendValue {
    private final int awardCount; // 獲獎個數
    private final int[] showOddsArray; // 表演倍數列表
    private final double[] showWinArray; // 表演贏分列表

    public SpecialFeatureResultExtendRedEnvelope(boolean killFlag, int killCount, double basicWin, double totalWin, int awardCount, int[] showOddsArray, double[] showWinArray) {
        super(killFlag, killCount, basicWin, totalWin);
        this.awardCount = awardCount;
        this.showWinArray = showWinArray;
        this.showOddsArray = showOddsArray;
    }

    // 未觸發特殊事件
    public SpecialFeatureResultExtendRedEnvelope() {
        super(false, 0, 0.0, 0.0);
        this.awardCount = 0;
        this.showWinArray = new double[0];
        this.showOddsArray = new int[0];
    }

    public int getAwardCount() {
        return awardCount;
    }

    public double[] getShowWinArray() {
        return showWinArray;
    }

    public int[] getShowOddsArray() {
        return showOddsArray;
    }
}



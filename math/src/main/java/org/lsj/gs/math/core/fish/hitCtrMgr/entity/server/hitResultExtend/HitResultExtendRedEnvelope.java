package org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend;

// 紅包目標客製處理結果
public class HitResultExtendRedEnvelope extends HitResultExtend{
    private final int awardCount; // 獲獎個數
    private final int[] showOddsArray; // 表演倍數列表
    private final double[] showWinArray; // 表演贏分列表

    public HitResultExtendRedEnvelope(int awardCount, int[] showOddsArray, double[] showWinArray) {
        this.awardCount = awardCount;
        this.showOddsArray = showOddsArray;
        this.showWinArray = showWinArray;
    }

    // 未獲得獎項
    public HitResultExtendRedEnvelope() {
        this.awardCount = 0;
        this.showOddsArray = new int[0];
        this.showWinArray = new double[0];
    }

    public int getAwardCount() {
        return awardCount;
    }

    public int[] getShowOddsArray() {
        return showOddsArray;
    }

    public double[] getShowWinArray() {
        return showWinArray;
    }
}

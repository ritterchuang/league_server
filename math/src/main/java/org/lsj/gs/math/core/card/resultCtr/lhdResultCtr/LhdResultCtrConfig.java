package org.lsj.gs.math.core.card.resultCtr.lhdResultCtr;

// 龍虎結果計算器設定
public class LhdResultCtrConfig {
    private final int areaCount; // 區域個數
    private final int feeType; // 手續費類型
    private final double gameRate; // 手續費率 百分位

    public LhdResultCtrConfig(int areaCount, int feeType, double gameRate) {
        this.areaCount = areaCount;
        this.feeType = feeType;
        this.gameRate = gameRate;
    }

    public int getAreaCount() {
        return areaCount;
    }

    public int getFeeType() {
        return feeType;
    }

    public double getGameRate() {
        return gameRate;
    }
}

package org.lsj.gs.math.core.card.resultCtr.bjlResultCtr;

import java.util.Map;

// 百家結果計算器設定
public class BjlBaiRenResultCtrConfig {
    private final int betAreaCount; // 下注區域個數
    private final int feeType; // 手續費類型
    private final double gameRate; // 手續費率 百分位
    private final Map<Integer, Double> betAreaRateMap; // 押注賠率表

    public BjlBaiRenResultCtrConfig(int betAreaCount, int feeType, double gameRate, Map<Integer, Double> betAreaRateMap) {
        this.betAreaCount = betAreaCount;
        this.feeType = feeType;
        this.gameRate = gameRate;
        this.betAreaRateMap = betAreaRateMap;
    }

    public int getBetAreaCount() {
        return betAreaCount;
    }

    public int getFeeType() {
        return feeType;
    }

    public double getGameRate() {
        return gameRate;
    }

    public Map<Integer, Double> getBetAreaRateMap() {
        return betAreaRateMap;
    }
}

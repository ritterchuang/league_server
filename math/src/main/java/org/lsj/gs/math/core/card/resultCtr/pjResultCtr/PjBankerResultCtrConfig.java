package org.lsj.gs.math.core.card.resultCtr.pjResultCtr;

// 牌九結果計算器設定
public class PjBankerResultCtrConfig {
    private final int maxUser; // 最大玩家數
    private final double baseScore; // 底注
    private final int feeType; // 手續費類型
    private final double gameRate; // 手續費率

    public PjBankerResultCtrConfig(int maxUser, double baseScore, int feeType, double gameRate) {
        this.maxUser = maxUser;
        this.baseScore = baseScore;
        this.feeType = feeType;
        this.gameRate = gameRate;
    }

    public int getMaxUser() {
        return maxUser;
    }

    public double getBaseScore() {
        return baseScore;
    }

    public int getFeeType() {
        return feeType;
    }

    public double getGameRate() {
        return gameRate;
    }
}

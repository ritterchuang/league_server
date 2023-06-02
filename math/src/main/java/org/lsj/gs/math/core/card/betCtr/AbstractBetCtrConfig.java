package org.lsj.gs.math.core.card.betCtr;

public abstract class AbstractBetCtrConfig {
    protected final int maxUser; // 最大玩家數
    protected final double baseScore; // 底注

    public AbstractBetCtrConfig(int maxUser, double baseScore) {
        this.maxUser = maxUser;
        this.baseScore = baseScore;
    }

    public int getMaxUser() {
        return maxUser;
    }

    public double getBaseScore() {
        return baseScore;
    }
}

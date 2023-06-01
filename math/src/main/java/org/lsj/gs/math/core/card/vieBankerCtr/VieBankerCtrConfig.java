package org.lsj.gs.math.core.card.vieBankerCtr;

import org.lsj.gs.math.core.card.ConstMathCard;

// 搶莊計算器設定
public class VieBankerCtrConfig {
    private final ConstMathCard.BankerType bankerType; // 搶莊方式
    private final int maxUser; // 最大玩家數
    private final double baseScore; // 底注

    public VieBankerCtrConfig(ConstMathCard.BankerType bankerType, int maxUser, double baseScore) {
        this.bankerType = bankerType;
        this.maxUser = maxUser;
        this.baseScore = baseScore;
    }

    public ConstMathCard.BankerType getBankerType() {
        return bankerType;
    }

    public int getMaxUser() {
        return maxUser;
    }

    public double getBaseScore() {
        return baseScore;
    }
}

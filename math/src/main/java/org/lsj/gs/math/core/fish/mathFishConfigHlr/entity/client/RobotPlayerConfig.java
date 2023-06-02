package org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.client;

// 機器人玩家設定
public class RobotPlayerConfig {
    private final double beginMoneyLowerLimit; // 起始金額下限
    private final double beginMoneyUpperLimit; // 起始金額上限

    public RobotPlayerConfig(double beginMoneyLowerLimit, double beginMoneyUpperLimit) {
        this.beginMoneyLowerLimit = beginMoneyLowerLimit;
        this.beginMoneyUpperLimit = beginMoneyUpperLimit;
    }

    public double getBeginMoneyLowerLimit() {
        return beginMoneyLowerLimit;
    }

    public double getBeginMoneyUpperLimit() {
        return beginMoneyUpperLimit;
    }
}

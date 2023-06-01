package org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.server;

// 機器人起始金額上下限倍數
public class RobotBeginMoneyLimitBase {
    private final double lowerLimitBase; // 機器人起始金額下限倍數
    private final double upperLimitBase; // 機器人起始金額上限倍數

    public RobotBeginMoneyLimitBase(double lowerLimitBase, double upperLimitBase) {
        this.lowerLimitBase = lowerLimitBase;
        this.upperLimitBase = upperLimitBase;
    }

    public double getLowerLimitBase() {
        return lowerLimitBase;
    }

    public double getUpperLimitBase() {
        return upperLimitBase;
    }
}

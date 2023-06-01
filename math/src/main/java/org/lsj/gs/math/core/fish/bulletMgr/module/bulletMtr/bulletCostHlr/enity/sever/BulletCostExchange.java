package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.sever;

// 子彈成本換算
public class BulletCostExchange {
    private final double baseCost; // 基底成本
    private final double baseBet; // 基底押注

    public BulletCostExchange(double baseCost, double baseBet) {
        this.baseCost = baseCost;
        this.baseBet = baseBet;
    }

    public double getBaseCost() {
        return baseCost;
    }

    public double getBaseBet() {
        return baseBet;
    }
}

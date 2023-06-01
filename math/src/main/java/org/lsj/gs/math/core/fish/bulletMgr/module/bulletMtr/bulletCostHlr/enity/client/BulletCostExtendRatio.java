package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.client;

// 等比例子彈成本訊息
public class BulletCostExtendRatio extends BulletCostExtend {
    private final double cost; // 子彈成本

    // 原始建構子提供JSON解析用
    public BulletCostExtendRatio() {
        this.cost = 0.0;
    }

    public BulletCostExtendRatio(double cost) {
        this.cost = cost;
    }

    // 檢驗完整性
    @Override
    public boolean checkComplete() {
        // 1. 檢驗子彈成本恆正性
        if(!(this.cost > 0)){
            return false;
        }

        return true;
    }

    public double getCost() {
        return cost;
    }
}

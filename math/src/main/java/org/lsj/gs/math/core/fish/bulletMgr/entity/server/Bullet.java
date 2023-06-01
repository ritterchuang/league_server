package org.lsj.gs.math.core.fish.bulletMgr.entity.server;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.client.BulletCostExtend;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.enity.client.BulletTypeExtend;

// 子彈資訊
public class Bullet {
    private final int bulletIndex; // 子彈編碼
    private final ConstMathFish.BulletType bulletType; // 子彈類型
    private final BulletTypeExtend bulletTypeExtend; // 客製子彈類型訊息
    private final ConstMathFish.BulletCostType bulletCostType; // 子彈成本類型
    private final BulletCostExtend bulletCostExtend; // 客製子彈訊息

    private final double cost; // 子彈成本
    private final double bet; // 子彈押注

    public Bullet(int bulletIndex, ConstMathFish.BulletType bulletType, BulletTypeExtend bulletTypeExtend, ConstMathFish.BulletCostType bulletCostType, BulletCostExtend bulletCostExtend, double cost, double bet) {
        this.bulletIndex = bulletIndex;
        this.bulletType = ConstMathFish.BulletType.INVALID;
        this.bulletTypeExtend = null;
        this.bulletCostType = ConstMathFish.BulletCostType.INVALID;
        this.bulletCostExtend = bulletCostExtend;
        this.cost = cost;
        this.bet = bet;
    }

    public int getBulletIndex() {
        return bulletIndex;
    }

    public ConstMathFish.BulletType getBulletType() {
        return bulletType;
    }

    public BulletTypeExtend getBulletTypeExtend() {
        return bulletTypeExtend;
    }

    public ConstMathFish.BulletCostType getBulletCostType() {
        return bulletCostType;
    }

    public BulletCostExtend getBulletCostExtend() {
        return bulletCostExtend;
    }

    public double getCost() {
        return cost;
    }

    public double getBet() {
        return bet;
    }
}

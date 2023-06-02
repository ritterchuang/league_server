package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.sever.bulletCostConfigExtend;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.sever.BulletCostExchange;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.sever.bulletCostListConfigExtend.BulletCostListConfigExtend;

// 客製子彈成本類型設定
public class BulletCostConfigExtendRatio extends BulletCostConfigExtend{
    private final ConstMathFish.BulletCostListType bulletCostListType; // 子彈成本列表類型
    private final BulletCostListConfigExtend config; // 客製子彈成本列表類型設定
    private final BulletCostExchange bulletCostExchange; // 子彈成本換算

    public BulletCostConfigExtendRatio(ConstMathFish.BulletCostListType bulletCostListType, BulletCostListConfigExtend bulletCostListConfigExtend, BulletCostExchange bulletCostExchange) {
        this.bulletCostListType = bulletCostListType;
        this.config = bulletCostListConfigExtend;
        this.bulletCostExchange = bulletCostExchange;
    }

    public ConstMathFish.BulletCostListType getBulletCostListType() {
        return bulletCostListType;
    }

    public BulletCostListConfigExtend getBulletCostListConfigExtend() {
        return config;
    }

    public BulletCostExchange getBulletCostExchange() {
        return bulletCostExchange;
    }
}

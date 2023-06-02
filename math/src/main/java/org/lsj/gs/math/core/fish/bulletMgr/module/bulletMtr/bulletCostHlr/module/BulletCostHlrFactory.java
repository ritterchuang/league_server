package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.module;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.sever.bulletCostConfigExtend.BulletCostConfigExtend;

// 子彈成本處理者工廠
public class BulletCostHlrFactory {

    // 建立子彈成本處理者
    public IBulletCostHlr createBulletCostHlr(double base, ConstMathFish.BulletCostType bulletCostType, BulletCostConfigExtend bulletCostConfigExtend) {
        switch (bulletCostType) {
            case RATIO: return new BulletCostHlrRatio(base, bulletCostType, bulletCostConfigExtend);
            case FREE: return new BulletCostHlrFree(base, bulletCostType, bulletCostConfigExtend);
            default: return new BulletCostHlrInvalid(bulletCostType);
        }
    }
}

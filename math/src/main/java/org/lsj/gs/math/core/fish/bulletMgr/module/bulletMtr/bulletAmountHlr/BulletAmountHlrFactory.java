package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletAmountHlr;

import org.lsj.gs.math.core.fish.ConstMathFish;

// 子彈數量處理者工廠
public class BulletAmountHlrFactory {

    // 建立子彈數量處理者
    public IBulletAmountHlr createBulletAmountHlr(ConstMathFish.BulletAmountType bulletAmountType) {
        switch (bulletAmountType) {
            case FINITE: return new BulletAmountHlrFinite(bulletAmountType);
            case INFINITE: return new BulletAmountHlrInFinite(bulletAmountType);
            default: return new BulletAmountHlrInvalid(bulletAmountType);
        }
    }
}

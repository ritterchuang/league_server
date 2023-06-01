package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.module;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.enity.sever.BulletConfigExtend;

// 子彈類別處理者工廠
public class BulletTypeHlrFactory {

    // 建立子彈類型處理者
    public IBulletTypeHlr createBulletTypeHlr(ConstMathFish.BulletType bulletType, BulletConfigExtend bulletConfigExtend) {
        switch (bulletType) {
            case NORMAL: return new BulletTypeHlrNormal(bulletType, bulletConfigExtend);
            default: return new BulletTypeHlrInvalid(bulletType);
        }
    }
}

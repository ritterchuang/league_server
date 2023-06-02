package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.module.bulletCostListCtr;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.sever.bulletCostListConfigExtend.BulletCostListConfigExtend;

// 子彈成本列表計算器工廠
public class BulletCostListCtrFactory {

    // 建立子彈成本列表計算者
    public IBulletCostListCtr createBulletCostListCtr(ConstMathFish.BulletCostListType bulletCostListType, BulletCostListConfigExtend bulletCostListConfigExtend) {
        switch(bulletCostListType){
            case TEN_MULTI_TEN: return new BulletCostListCtrTenMultiTen(bulletCostListConfigExtend);
            case TEN: return new BulletCostListCtrTen(bulletCostListConfigExtend);
            default: return new BulletCostListCtrInvalid();
        }
    }
}

package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.module.awardBulletCtr;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrConfigExtend.awardBulletCtrConfigExtend.AwardBulletCtrConfigExtend;

// 獎勵子彈計算者工廠
public class AwardBulletCtrFactory {

    // 創建獎勵子彈計算者
    public IAwardBulletCtr createAwardBulletCtr(ConstMathFish.AwardBulletCtrType awardBulletCtrType, AwardBulletCtrConfigExtend awardBulletCtrConfigExtend, ITableUtil tableUtil) {
        switch (awardBulletCtrType) {
            case NORMAL: return new AwardBulletCtrNormal(awardBulletCtrType, awardBulletCtrConfigExtend, tableUtil);
            default: return new AwardBulletCtrInvalid(ConstMathFish.AwardBulletCtrType.INVALID);
        }
    }
}

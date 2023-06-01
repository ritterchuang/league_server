package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.module;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrConfigExtend.AwardBulletGtrConfigExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrConfigExtend.AwardBulletGtrConfigExtendNone;

// 子彈生產者工廠
public class AwardBulletGtrFactory {

    // 建立獎勵子彈生產者
    public IAwardBulletGtr createAwardBulletGtr(ConstMathFish.AwardBulletType awardBulletType, AwardBulletGtrConfigExtend awardBulletGtrConfigExtend, ITableUtil tableUtil) {
        switch (awardBulletType) {
            case NONE: return new AwardBulletGtrNone(awardBulletType, awardBulletGtrConfigExtend, tableUtil);
            case ONE_TYPE: return new AwardBulletGtrOneType(awardBulletType, awardBulletGtrConfigExtend, tableUtil);
            default: return new AwardBulletGtrInvalid(ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(), tableUtil);
        }
    }
}

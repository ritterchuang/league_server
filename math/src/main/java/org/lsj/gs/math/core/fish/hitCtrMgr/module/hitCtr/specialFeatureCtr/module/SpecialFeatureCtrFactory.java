package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.module;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureCtrConfigExtend.SpecialFeatureCtrConfigExtend;

// 特殊事件計算者工廠
public class SpecialFeatureCtrFactory {

    // 建立特殊事件計算者
    public ISpecialFeatureCtr createSpecialFeatureCtr(ConstMathFish.SpecialFeatureEnumType specialFeatureEnumType, SpecialFeatureCtrConfigExtend specialFeatureCtrConfigExtend, ITableUtil tableUtil) {
        switch (specialFeatureEnumType) {
            case NONE: return new SpecialFeatureCtrNone(specialFeatureEnumType, specialFeatureCtrConfigExtend);
            case RED_ENVELOPE: return new SpecialFeatureCtrRedEnvelope(specialFeatureEnumType, specialFeatureCtrConfigExtend, tableUtil);
            default: return new SpecialFeatureCtrInvalid(specialFeatureEnumType, specialFeatureCtrConfigExtend);
        }
    }
}

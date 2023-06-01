package org.lsj.gs.math.core.slot.specialFeatureHlrMgr.module;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrConfig;

// 特殊事件處理者工廠
public class SpecialFeatureHlrFactory {

    // 創建特殊事件處理者
    public ISpecialFeatureHlr create(SpecialFeatureHlrConfig specialFeatureHlrConfig, ITableUtil tableUtil) {
        switch (specialFeatureHlrConfig.getSpecialFeatureConfig().getSpecialFeatureType()) {
            case SF_01: return new SpecialFeatureHlr_01(specialFeatureHlrConfig, tableUtil);
            case SF_02: return new SpecialFeatureHlr_02(specialFeatureHlrConfig, tableUtil);
            case SF_03: return new SpecialFeatureHlr_03(specialFeatureHlrConfig, tableUtil);
            case SF_04: return new SpecialFeatureHlr_04(specialFeatureHlrConfig, tableUtil);
            case SF_05: return new SpecialFeatureHlr_05(specialFeatureHlrConfig, tableUtil);
            case SF_06: return new SpecialFeatureHlr_06(specialFeatureHlrConfig, tableUtil);
            case SF_07: return new SpecialFeatureHlr_07(specialFeatureHlrConfig, tableUtil);
            case SF_08: return new SpecialFeatureHlr_08(specialFeatureHlrConfig, tableUtil);
            case SF_09: return new SpecialFeatureHlr_09(specialFeatureHlrConfig, tableUtil);
            case SF_10: return new SpecialFeatureHlr_10(specialFeatureHlrConfig, tableUtil);
            case SF_11: return new SpecialFeatureHlr_11(specialFeatureHlrConfig, tableUtil);
            case SF_12: return new SpecialFeatureHlr_12(specialFeatureHlrConfig, tableUtil);
            case SF_13: return new SpecialFeatureHlr_13(specialFeatureHlrConfig, tableUtil);
            default: return new SpecialFeatureHlr_Invalid();
        }
    }
}

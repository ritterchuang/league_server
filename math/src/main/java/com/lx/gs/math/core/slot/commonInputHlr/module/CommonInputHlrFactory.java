package com.lx.gs.math.core.slot.commonInputHlr.module;

import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.core.slot.commonInputHlr.ICommonInputHlr;
import com.lx.gs.math.core.slot.commonInputHlr.enity.config.CommonInputHlrConfig;

// 公用輸入處理者工廠
public class CommonInputHlrFactory {

    // 創建公用輸入處理者 TODO Default
    public ICommonInputHlr create(CommonInputHlrConfig commonInputHlrConfig, ITableUtil tableUtil) {
        switch (commonInputHlrConfig.getCommonInputType()) {
            default: return new CommonInputHlrNone(commonInputHlrConfig, tableUtil);
        }
    }
}

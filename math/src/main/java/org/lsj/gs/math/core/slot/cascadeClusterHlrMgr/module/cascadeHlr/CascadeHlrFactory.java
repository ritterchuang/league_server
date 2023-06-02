package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.accumulateWinCtr.AccumulateWinCtr;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.config.CascadeHlrConfig;

// 消除處理者工廠
public class CascadeHlrFactory{

    // 創建 消除處理者
    public ICascadeHlr create(CascadeHlrConfig cascadeHlrConfig, AccumulateWinCtr accumulateWinCtr, ITableUtil tableUtil) {
        switch (cascadeHlrConfig.getCascadeType()) {
            case MJHL: return new CascadeHlrMjhl(cascadeHlrConfig, accumulateWinCtr, tableUtil);
            case DGRY: return new CascadeHlrDgry(cascadeHlrConfig, accumulateWinCtr, tableUtil);
            case CJWP: return new CascadeHlrCjwp(cascadeHlrConfig, accumulateWinCtr, tableUtil);
            default: return new CascadeHlrDefault(cascadeHlrConfig, accumulateWinCtr, tableUtil);
        }
    }
}

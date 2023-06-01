package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.accumulateWinCtr.AccumulateWinCtr;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.config.CascadeClusterHlrConfig;

// 消除集合處理者工廠
public class CascadeClusterHlrFactory {

    // TODO default
    public ICascadeClusterHlr create(CascadeClusterHlrConfig cascadeClusterHlrConfig, AccumulateWinCtr accumulateWinCtr, ITableUtil tableUtil) {
        switch (cascadeClusterHlrConfig.getCascadeClusterType()) {
            case MJHL: return new CascadeClusterHlrMjhl(cascadeClusterHlrConfig, accumulateWinCtr, tableUtil);
            case DGRY: return new CascadeClusterHlrDgry(cascadeClusterHlrConfig, accumulateWinCtr, tableUtil);
            case CJWP: return new CascadeClusterHlrCjwp(cascadeClusterHlrConfig, accumulateWinCtr, tableUtil);
            default: return new CascadeClusterHlrDefault();
        }
    }
}

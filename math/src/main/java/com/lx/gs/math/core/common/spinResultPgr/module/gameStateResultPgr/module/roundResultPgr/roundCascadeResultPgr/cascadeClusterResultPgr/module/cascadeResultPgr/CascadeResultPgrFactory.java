package com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr;

import com.lx.gs.math.core.common.spinResultPgr.enity.pgrConfig.CascadeResultPgrConfig;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.accumulateWinResultPgr.AccumulateWinResultPgr;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.progressResultPgr.ProgressResultPgr;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;

// 消除結果包裝者工廠
public class CascadeResultPgrFactory {

    public ICascadeResultPgr create(CascadeResultPgrConfig cascadeResultPgrConfig, ProgressResultPgr progressResultPgr, AccumulateWinResultPgr accumulateWinResultPgr, ITableUtil tableUtil) {
        switch (cascadeResultPgrConfig.getConfig().getCascadeType()) {
            case MJHL: return new CascadeResultPgrMjhl(cascadeResultPgrConfig, progressResultPgr, accumulateWinResultPgr, tableUtil);
            case DGRY: return new CascadeResultPgrDgry(cascadeResultPgrConfig, progressResultPgr, accumulateWinResultPgr, tableUtil);
            case CJWP: return new CascadeResultPgrCjwp(cascadeResultPgrConfig, progressResultPgr, accumulateWinResultPgr, tableUtil);
            default: return new CascadeResultPgrDefault();
        }
    }
}

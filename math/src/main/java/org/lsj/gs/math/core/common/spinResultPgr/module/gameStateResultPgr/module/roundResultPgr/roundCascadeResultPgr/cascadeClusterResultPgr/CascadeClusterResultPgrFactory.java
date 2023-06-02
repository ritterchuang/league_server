package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr;

import org.lsj.gs.math.core.common.spinResultPgr.enity.pgrConfig.CascadeClusterResultPgrConfig;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.accumulateWinResultPgr.AccumulateWinResultPgr;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.progressResultPgr.ProgressResultPgr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

// 消除結果集合包裝者工廠
public class CascadeClusterResultPgrFactory {

    public ICascadeClusterResultPgr create(CascadeClusterResultPgrConfig cascadeClusterResultPgrConfig, ProgressResultPgr progressResultPgr, AccumulateWinResultPgr accumulateWinResultPgr, ITableUtil tableUtil) {
        switch (cascadeClusterResultPgrConfig.getCascadeClusterType()) {
            case MJHL: return new CascadeClusterResultPgrMjhl(cascadeClusterResultPgrConfig, progressResultPgr, accumulateWinResultPgr, tableUtil);
            case DGRY: return new CascadeClusterResultPgrDgry(cascadeClusterResultPgrConfig, progressResultPgr, accumulateWinResultPgr, tableUtil);
            case CJWP: return new CascadeClusterResultPgrCjwp(cascadeClusterResultPgrConfig, progressResultPgr, accumulateWinResultPgr, tableUtil);
            default: return new CascadeClusterResultPgrDefault();
        }
    }
}

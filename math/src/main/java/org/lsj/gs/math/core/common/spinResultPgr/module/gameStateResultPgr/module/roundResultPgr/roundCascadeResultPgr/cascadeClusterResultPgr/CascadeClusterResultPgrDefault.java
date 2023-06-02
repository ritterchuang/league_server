package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr;

import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.enity.CascadeClusterResult;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.result.CascadeClusterHlrResult;

// 消除結果集合包裝者 預設
public class CascadeClusterResultPgrDefault implements ICascadeClusterResultPgr{
    @Override
    public CascadeClusterResult packageCascadeClusterResult(CascadeClusterHlrResult cascadeClusterHlrResult) {
        return null;
    }
}

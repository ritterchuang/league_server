package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr;

import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.enity.CascadeResult;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;

import java.util.List;

// 消除結果包裝者 預設
public class CascadeResultPgrDefault implements ICascadeResultPgr{
    // 包裝消除結果列表
    public List<CascadeResult> packageCascadeResultList(List<CascadeHlrResult> cascadeHlrResultList) {
        return null;
    }
}

package com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr;

import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.enity.CascadeResult;
import com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;

import java.util.List;

// 消除結果包裝者介面
public interface ICascadeResultPgr {

    List<CascadeResult> packageCascadeResultList(List<CascadeHlrResult> cascadeHlrResultList);
}

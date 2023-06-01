package com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.module;

import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.enity.CascadeResultExtend;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.ScreenResult;
import com.lx.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;

// 消除結果包裝者介面
public interface ICascadeScreenResultPgr {

    ScreenResult calculateCascadeScreenResult(ScreenResult lastScreenResult, CascadeResultExtend lastCascadeResultExtend, ScreenGtrResult currentScreenGtrResult);
}

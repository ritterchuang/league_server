package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.module;

import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundCascadeResultPgr.cascadeClusterResultPgr.module.cascadeResultPgr.enity.CascadeResultExtend;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.ScreenResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;

// 消除結果包裝者介面
public interface ICascadeScreenResultPgr {

    ScreenResult calculateCascadeScreenResult(ScreenResult lastScreenResult, CascadeResultExtend lastCascadeResultExtend, ScreenGtrResult currentScreenGtrResult);
}

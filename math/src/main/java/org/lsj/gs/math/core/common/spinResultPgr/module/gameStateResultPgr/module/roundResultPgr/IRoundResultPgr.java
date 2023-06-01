package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.roundResultPgr.roundResult.RoundResult;

// 客製局結果包裝者介面
public interface IRoundResultPgr {

    RoundResult packageRoundResult(RoundHlrResult roundHlrResult); // 包裝客端局結果
}

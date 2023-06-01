package com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr;

import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.enity.GameStateResult;

// 遊戲狀態結果包裝者介面
public interface IGameStateResultPgr {

    GameStateResult packageGameStateResult(GameStateHlrResult gameStateHlrResult);
}

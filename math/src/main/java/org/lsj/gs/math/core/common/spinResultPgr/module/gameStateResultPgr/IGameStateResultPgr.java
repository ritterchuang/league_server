package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.enity.GameStateResult;

// 遊戲狀態結果包裝者介面
public interface IGameStateResultPgr {

    GameStateResult packageGameStateResult(GameStateHlrResult gameStateHlrResult);
}

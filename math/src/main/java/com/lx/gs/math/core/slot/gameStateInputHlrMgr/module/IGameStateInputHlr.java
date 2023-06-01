package com.lx.gs.math.core.slot.gameStateInputHlrMgr.module;

import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import com.lx.gs.math.core.slot.gameStateInputHlrMgr.enity.result.GameStateInputResult;

import java.util.List;

// 遊戲輸入處理者
public interface IGameStateInputHlr {

    GameStateInputResult calculateGameStateInputResult(List<RoundHlrResult> roundHlrResultList);
}

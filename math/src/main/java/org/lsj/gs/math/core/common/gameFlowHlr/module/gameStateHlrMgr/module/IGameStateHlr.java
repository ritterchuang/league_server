package org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;

// 遊戲狀態處理者
public interface IGameStateHlr {

    // 計算遊戲狀態處理結果
    GameStateHlrResult calculateGameStateHlrResult(int gameStateResultIndex,
                                                   ConstMathSlot.ReelRtpType reelRtpType,
                                                   SpinRequest spinRequest,
                                                   GameStateHlrResult beforeGameStateHlrResult);
}

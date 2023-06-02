package org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity;

import org.lsj.gs.math.core.common.gameFlowHlr.enity.result.GameFlowHlrResult;

// 遊戲下注紀錄結果老虎機介面
public interface IGameBetLogResultSlot extends IGameBetLogResult{
    GameFlowHlrResult getGameFlowHlrResult(); // 取得遊戲流程處理者結果
}

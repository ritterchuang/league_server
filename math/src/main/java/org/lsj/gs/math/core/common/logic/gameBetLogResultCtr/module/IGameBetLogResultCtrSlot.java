package org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module;

import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gameFlowHlr.enity.result.GameFlowHlrResult;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultSlot;

// 下注記錄計算器老虎機介面
public interface IGameBetLogResultCtrSlot extends IGameBetLogResultCtr {
    IGameBetLogResultSlot calculateGameBetLogResultSlot(String roundId, UidScore uidScore, GameFlowHlrResult gameFlowHlrResult); // 計算下注紀錄結果
}

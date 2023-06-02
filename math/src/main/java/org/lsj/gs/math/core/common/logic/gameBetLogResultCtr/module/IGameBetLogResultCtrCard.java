package org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module;

import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultCard;

// 下注記錄計算器棋牌介面
public interface IGameBetLogResultCtrCard extends IGameBetLogResultCtr {

    IGameBetLogResultCard calculateGameBetLogResultCard(String roundId, UidScore uidScore); // 計算下注紀錄結果
}

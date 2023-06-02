package org.lsj.gs.math.core.common.logic;

import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultCard;

// 卡牌邏輯介面
public interface ILogicCard extends ILogic{
    IGameBetLogResultCard getGameBetLogResultCard(UidScore uidScore); // 取得遊戲結果
}

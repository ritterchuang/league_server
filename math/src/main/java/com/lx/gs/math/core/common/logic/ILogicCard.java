package com.lx.gs.math.core.common.logic;

import com.lx.gs.math.core.common.gameAdjust.entity.UidScore;
import com.lx.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultCard;

// 卡牌邏輯介面
public interface ILogicCard extends ILogic{
    IGameBetLogResultCard getGameBetLogResultCard(UidScore uidScore); // 取得遊戲結果
}

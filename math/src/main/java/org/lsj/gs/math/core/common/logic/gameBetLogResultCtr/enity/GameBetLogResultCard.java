package org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity;

import org.lsj.db.GameBetLogObj;

import java.util.List;

// 遊戲下注紀錄結果棋牌
public class GameBetLogResultCard extends AbstractGameBetLogResult implements IGameBetLogResultCard {

    public GameBetLogResultCard(GameBetLogObj gameBetLogObj, List<Object> detail, String personControlLog, List<Integer> randomNumberUsedList) {
        super(gameBetLogObj, detail, personControlLog, randomNumberUsedList);
    }
}

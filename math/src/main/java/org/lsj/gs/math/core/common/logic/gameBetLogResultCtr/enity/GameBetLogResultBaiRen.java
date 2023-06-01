package org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity;

import org.lsj.db.GameBetLogObj;

import java.util.List;

// 遊戲下注紀錄結果百人
public class GameBetLogResultBaiRen extends AbstractGameBetLogResult implements IGameBetLogResultBaiRen {

    public GameBetLogResultBaiRen(GameBetLogObj gameBetLogObj, List<Object> detail, String personControlLog, List<Integer> randomNumberUsedList) {
        super(gameBetLogObj, detail, personControlLog, randomNumberUsedList);
    }
}

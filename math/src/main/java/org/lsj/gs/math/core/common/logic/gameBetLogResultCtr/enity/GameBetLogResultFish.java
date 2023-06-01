package org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity;

import org.lsj.db.GameBetLogObj;

import java.util.List;

// 遊戲下注紀錄結果魚機
public class GameBetLogResultFish extends AbstractGameBetLogResult implements IGameBetLogResultFish {
    private final boolean recordDataBase; // 是否紀錄DB

    public GameBetLogResultFish(GameBetLogObj gameBetLogObj, List<Object> detail, String personControlLog, List<Integer> randomNumberUsedList, boolean recordDataBase) {
        super(gameBetLogObj, detail, personControlLog, randomNumberUsedList);
        this.recordDataBase = recordDataBase;
    }

    @Override
    public boolean isRecordDataBase() {
        return this.recordDataBase;
    }

}

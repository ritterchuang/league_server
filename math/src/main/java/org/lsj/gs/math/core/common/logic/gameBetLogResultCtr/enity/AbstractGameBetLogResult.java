package org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity;

import org.lsj.db.GameBetLogObj;

import java.util.List;

// 遊戲下注紀錄結果
public abstract class AbstractGameBetLogResult implements IGameBetLogResult{
    protected final GameBetLogObj gameBetLogObj; // 下注記錄物件
    protected final List<Object> detail; // 遊戲詳細資訊
    protected final String personControlLog; // 玩家調控資訊
    protected final List<Integer> randomNumberUsedList; // 使用過的隨機亂數列表

    public AbstractGameBetLogResult(GameBetLogObj gameBetLogObj, List<Object> detail, String personControlLog, List<Integer> randomNumberUsedList) {
        this.gameBetLogObj = gameBetLogObj;
        this.detail = detail;
        this.personControlLog = personControlLog;
        this.randomNumberUsedList = randomNumberUsedList;
    }

    @Override
    public GameBetLogObj getGameBetLogObj() {
        return gameBetLogObj;
    }

    @Override
    public List<Object> getDetail() {
        return detail;
    }

    @Override
    public String getPersonControlLog() {
        return personControlLog;
    }

    @Override
    public List<Integer> getRandomNumberUsedList() {
        return randomNumberUsedList;
    }
}

package com.lx.gs.mathStr.core.entity.playStr;

import com.lx.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResult;
import com.lx.gs.mathStr.core.entity.playStr.gameResultExtend.GameResultExtend;

// 局結果
public class BoardGtrResult {
    private final StnResult stnResult; // 統計結果
    private final IGameBetLogResult gameBetLogResult; // 遊戲結果
    private final GameResultExtend gameResultExtend; // 客製化結果

    public BoardGtrResult(StnResult stnResult, IGameBetLogResult gameBetLogResult, GameResultExtend gameResultExtend) {
        this.stnResult = stnResult;
        this.gameBetLogResult = gameBetLogResult;
        this.gameResultExtend = gameResultExtend;
    }

    public StnResult getStnResult() {
        return stnResult;
    }

    public IGameBetLogResult getGameBetLogResult() {
        return gameBetLogResult;
    }

    public GameResultExtend getGameResultExtend() {
        return gameResultExtend;
    }
}

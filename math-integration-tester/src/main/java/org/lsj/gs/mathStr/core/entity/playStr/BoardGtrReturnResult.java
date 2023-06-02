package org.lsj.gs.mathStr.core.entity.playStr;

import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultFish;

import java.util.Optional;

// 局返還結果
public class BoardGtrReturnResult {
    private final StnResult stnResult; // 統計結果
    private final Optional<IGameBetLogResultFish> returnResult; // 返還結果

    public BoardGtrReturnResult(StnResult stnResult, Optional<IGameBetLogResultFish> returnResult) {
        this.stnResult = stnResult;
        this.returnResult = returnResult;
    }

    public StnResult getStnResult() {
        return stnResult;
    }

    public Optional<IGameBetLogResultFish> getReturnResult() {
        return returnResult;
    }
}

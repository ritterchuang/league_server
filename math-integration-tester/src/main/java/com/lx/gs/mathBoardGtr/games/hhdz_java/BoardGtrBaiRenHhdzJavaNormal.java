package com.lx.gs.mathBoardGtr.games.hhdz_java;

import com.lx.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResult;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import com.lx.gs.mathStr.config.entity.ControlAlgorithmConfig;
import com.lx.gs.mathStr.core.entity.GamePlayerSimulation;
import com.lx.gs.mathStr.core.entity.playStr.BoardGtrResult;
import com.lx.gs.mathStr.core.entity.playStr.gameResultExtend.baiRen.GameResultExtend105HhdzJava;
import com.lx.gs.mathStr.core.module.poolCltr.PoolCtr;
import com.lx.gs.state.BaiRenGameState;

// 一般新紅黑大戰局產生器
public class BoardGtrBaiRenHhdzJavaNormal extends AbstractBoardGtrBaiRenHhdzJava {
    public BoardGtrBaiRenHhdzJavaNormal(GamePlayerSimulation player, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
        super(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
    }

    @Override
    public BoardGtrResult calculateOneBoardResult() {
        // 1. 更新至 遊戲開始 狀態
        super.table.updateGameState(BaiRenGameState.GAME_BEGIN, 25.0);
        super.humanOnRecvBetMsg();

        // 2. 更新至 結算 狀態
        super.table.updateGameState(BaiRenGameState.GAME_END, 0.0);

        // 3. 取得結果
        IGameBetLogResult gameBetLogResult = super.table.getResult();

        // 4. 計算客製結果
        GameResultExtend105HhdzJava gameResultExtend = this.calculateGameResultExtend();

        // 5. 回傳局結果
        return new BoardGtrResult(super.calculateStatisticResult(), gameBetLogResult, gameResultExtend);
    }

    // 強制置遊戲開始 TODO test 會用到
    public void updateGameBeginState(){
        super.table.updateGameState(BaiRenGameState.GAME_BEGIN, 25.0);
    }

    private GameResultExtend105HhdzJava calculateGameResultExtend() {
        return new GameResultExtend105HhdzJava(
                super.table.getLogic().getStackMap(),
                super.table.getLogic().getPlayerAreaBetArray(this.table.getLogic().getHumanChairIndex())
        );
    }
}

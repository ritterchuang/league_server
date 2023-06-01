package com.lx.gs.mathBoardGtr.games.mybjl_java;

import com.lx.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResult;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import com.lx.gs.mathStr.config.entity.ControlAlgorithmConfig;
import com.lx.gs.mathStr.core.entity.GamePlayerSimulation;
import com.lx.gs.mathStr.core.entity.playStr.BoardGtrResult;
import com.lx.gs.mathStr.core.entity.playStr.gameResultExtend.baiRen.GameResultExtend129MybjlJava;
import com.lx.gs.mathStr.core.module.poolCltr.PoolCtr;
import com.lx.gs.state.BaiRenGameState;

// 一般新免傭百家樂局產生器
public class BoardGtrBaiRenMybjlJavaNormal extends AbstractBoardGtrBaiRenMybjlJava {
    public BoardGtrBaiRenMybjlJavaNormal(GamePlayerSimulation player, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
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
        GameResultExtend129MybjlJava gameResultExtend = this.calculateGameResultExtend();

        // 5. 回傳局結果
        return new BoardGtrResult(super.calculateStatisticResult(), gameBetLogResult, gameResultExtend);
    }

    private GameResultExtend129MybjlJava calculateGameResultExtend() {
        return new GameResultExtend129MybjlJava(
                super.table.getLogic().getStackMap(),
                super.table.getLogic().getPlayerAreaBetArray(this.table.getLogic().getHumanChairIndex()),
                super.table.getLogic().calculateWinAreaArray(),
                super.table.getLogic().calculateReturnAreaArray()
        );
    }
}

package org.lsj.gs.mathBoardGtr.games.bjl_java;

import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResult;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathStr.config.entity.ControlAlgorithmConfig;
import org.lsj.gs.mathStr.core.entity.GamePlayerSimulation;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.baiRen.GameResultExtend109BjlJava;
import org.lsj.gs.mathStr.core.module.poolCltr.PoolCtr;
import org.lsj.gs.state.BaiRenGameState;

// 一般新百家樂戰局產生器
public class BoardGtrBaiRenBjlJavaNormal extends AbstractBoardGtrBaiRenBjlJava {
    public BoardGtrBaiRenBjlJavaNormal(GamePlayerSimulation player, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
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
        GameResultExtend109BjlJava gameResultExtend = this.calculateGameResultExtend();

        // 5. 回傳局結果
        return new BoardGtrResult(super.calculateStatisticResult(), gameBetLogResult, gameResultExtend);
    }

    private GameResultExtend109BjlJava calculateGameResultExtend() {
        return new GameResultExtend109BjlJava(
                super.table.getLogic().getStackMap(),
                super.table.getLogic().getPlayerAreaBetArray(this.table.getLogic().getHumanChairIndex()),
                super.table.getLogic().calculateWinAreaArray(),
                super.table.getLogic().calculateReturnAreaArray()
        );
    }
}

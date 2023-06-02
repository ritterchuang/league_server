package org.lsj.gs.mathBoardGtr.games.brnn_java;

import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.NiuStackCommon;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResult;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathStr.config.entity.ControlAlgorithmConfig;
import org.lsj.gs.mathStr.core.entity.GamePlayerSimulation;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.baiRen.GameResultExtend110BrnnJava;
import org.lsj.gs.mathStr.core.module.poolCltr.PoolCtr;
import org.lsj.gs.state.BaiRenGameState;

import java.util.Map;
import java.util.stream.Collectors;

// 一般新百人牛牛局產生器
public class BoardGtrBaiRenBrnnJavaNormal extends AbstractBoardGtrBaiRenBrnnJava {
    public BoardGtrBaiRenBrnnJavaNormal(GamePlayerSimulation player, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
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
        GameResultExtend110BrnnJava gameResultExtend = this.calculateGameResultExtend();

        // 5. 回傳局結果
        return new BoardGtrResult(super.calculateStatisticResult(), gameBetLogResult, gameResultExtend);
    }

    private GameResultExtend110BrnnJava calculateGameResultExtend() {
        return new GameResultExtend110BrnnJava(
                super.table.getLogic().getStackMap().entrySet().stream().collect(Collectors.toMap(
                        Map.Entry::getKey, entry -> (NiuStackCommon) entry.getValue()
                )),
                super.table.getLogic().getPlayerAreaBetArray(this.table.getLogic().getHumanChairIndex()),
                super.table.getLogic().calculateAreaWinLossResult()
        );
    }
}

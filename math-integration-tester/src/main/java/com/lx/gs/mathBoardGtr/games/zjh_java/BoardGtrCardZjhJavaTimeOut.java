package com.lx.gs.mathBoardGtr.games.zjh_java;

import com.lx.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResult;
import com.lx.gs.math.games.qznn_java.entity.ConstQznnJava;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import com.lx.gs.mathStr.config.entity.ControlAlgorithmConfig;
import com.lx.gs.mathStr.core.entity.GamePlayerSimulation;
import com.lx.gs.mathStr.core.entity.playStr.BoardGtrResult;
import com.lx.gs.mathStr.core.entity.playStr.gameResultExtend.GameResultExtend;
import com.lx.gs.mathStr.core.module.poolCltr.PoolCtr;

// 超時新炸金花局產生器
public class BoardGtrCardZjhJavaTimeOut extends AbstractBoardGtrCardZjhJava {
    public BoardGtrCardZjhJavaTimeOut(GamePlayerSimulation player, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
        super(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
    }

    @Override
    public BoardGtrResult calculateOneBoardResult() {
        // 1. 等待開始
        super.table.switchState(ConstQznnJava.StateEnumQznnJava.WAIT_BEGIN.getCode());

        // 2. 遊戲開始
        super.table.switchState(ConstQznnJava.StateEnumQznnJava.GAME_BEGIN.getCode());

        // 3. 搶莊狀態
        super.robotOnMessageVieBanker();
        super.table.getStateMgr().getStateMap().get(ConstQznnJava.StateEnumQznnJava.VIE_BANKER.getCode()).onTimeout();
        if(super.table.getStateMgr().getCurrentStateId() == ConstQznnJava.StateEnumQznnJava.VIE_BANKER.getCode()){
            super.table.switchState(ConstQznnJava.StateEnumQznnJava.VIE_BANKER.getCode());
        }

        // 4. 下注狀態
        super.robotOnMessageBet();
        super.table.getStateMgr().getStateMap().get(ConstQznnJava.StateEnumQznnJava.BET.getCode()).onTimeout();
        super.table.switchState(ConstQznnJava.StateEnumQznnJava.BET.getCode());

        // 5. 發牌狀態
        super.table.switchState(ConstQznnJava.StateEnumQznnJava.DEAL_CARD.getCode());

        // 6. 選牌狀態
        super.robotOnMessageSelect();
        super.table.getStateMgr().getStateMap().get(ConstQznnJava.StateEnumQznnJava.SELECT.getCode()).onTimeout();
        super.table.switchState(ConstQznnJava.StateEnumQznnJava.SELECT.getCode());

        // 7. 取得結果
        IGameBetLogResult gameBetLogResult = table.getResult();

        // 8. 比牌狀態
        super.table.switchState(ConstQznnJava.StateEnumQznnJava.COMPARE.getCode());

        // 9. 結算狀態
        super.table.switchState(ConstQznnJava.StateEnumQznnJava.GAME_END.getCode());

        // 10. 回傳局結果
        return new BoardGtrResult(super.calculateStatisticResult(), gameBetLogResult, new GameResultExtend());
    }
}

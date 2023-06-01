package com.lx.gs.mathBoardGtr.games.qzpj_java;

import com.lx.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResult;
import com.lx.gs.math.games.qzpj_java.entity.ConstQzpjJava;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import com.lx.gs.mathStr.config.entity.ControlAlgorithmConfig;
import com.lx.gs.mathStr.core.entity.GamePlayerSimulation;
import com.lx.gs.mathStr.core.entity.playStr.BoardGtrResult;
import com.lx.gs.mathStr.core.entity.playStr.gameResultExtend.GameResultExtend;
import com.lx.gs.mathStr.core.module.poolCltr.PoolCtr;

// 超時新搶莊牌九局產生器
public class BoardGtrCardQzpjJavaTimeOut extends AbstractBoardGtrCardQzpjJava {
    public BoardGtrCardQzpjJavaTimeOut(GamePlayerSimulation player, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
        super(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
    }

    @Override
    public BoardGtrResult calculateOneBoardResult() {
        // 1. 等待開始
        super.table.switchState(ConstQzpjJava.StateEnumQzpjJava.WAIT_BEGIN.getCode());

        // 2. 遊戲開始
        super.table.switchState(ConstQzpjJava.StateEnumQzpjJava.GAME_BEGIN.getCode());

        // 3. 搶莊狀態
        super.robotOnMessageVieBanker();
        super.table.getStateMgr().getStateMap().get(ConstQzpjJava.StateEnumQzpjJava.VIE_BANKER.getCode()).onTimeout();
        if(super.table.getStateMgr().getCurrentStateId() == ConstQzpjJava.StateEnumQzpjJava.VIE_BANKER.getCode()){
            super.table.switchState(ConstQzpjJava.StateEnumQzpjJava.VIE_BANKER.getCode());
        }

        // 4. 下注狀態
        super.robotOnMessageBet();
        super.table.getStateMgr().getStateMap().get(ConstQzpjJava.StateEnumQzpjJava.BET.getCode()).onTimeout();
        super.table.switchState(ConstQzpjJava.StateEnumQzpjJava.BET.getCode());

        // 5. 發牌狀態
        super.table.switchState(ConstQzpjJava.StateEnumQzpjJava.DEAL_CARD.getCode());

        // 6. 取得結果
        IGameBetLogResult gameBetLogResult = table.getResult();

        // 7. 比牌狀態
        super.table.switchState(ConstQzpjJava.StateEnumQzpjJava.COMPARE.getCode());

        // 8. 結算狀態
        super.table.switchState(ConstQzpjJava.StateEnumQzpjJava.GAME_END.getCode());

        // 9. 回傳局結果
        return new BoardGtrResult(super.calculateStatisticResult(), gameBetLogResult, new GameResultExtend());
    }
}

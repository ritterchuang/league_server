package org.lsj.gs.mathBoardGtr.games.qznn_ksz_java;

import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResult;
import org.lsj.gs.math.games.qznn_ksz_java.entity.ConstQznnKszJava;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathStr.config.entity.ControlAlgorithmConfig;
import org.lsj.gs.mathStr.core.entity.GamePlayerSimulation;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.GameResultExtend;
import org.lsj.gs.mathStr.core.module.poolCltr.PoolCtr;

// 超時新看三張搶莊牛牛局產生器
public class BoardGtrCardQznnKszJavaTimeOut extends AbstractBoardGtrCardQznnKszJava {
    public BoardGtrCardQznnKszJavaTimeOut(GamePlayerSimulation player, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
        super(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
    }

    @Override
    public BoardGtrResult calculateOneBoardResult() {
        // 1. 等待開始
        super.table.switchState(ConstQznnKszJava.StateEnumQznnKszJava.WAIT_BEGIN.getCode());

        // 2. 遊戲開始
        super.table.switchState(ConstQznnKszJava.StateEnumQznnKszJava.GAME_BEGIN.getCode());

        // 3. 發牌狀態
        super.table.switchState(ConstQznnKszJava.StateEnumQznnKszJava.DEAL_CARD.getCode());

        // 4. 搶莊狀態
        super.robotOnMessageVieBanker();
        super.table.getStateMgr().getStateMap().get(ConstQznnKszJava.StateEnumQznnKszJava.VIE_BANKER.getCode()).onTimeout();
        if(super.table.getStateMgr().getCurrentStateId() == ConstQznnKszJava.StateEnumQznnKszJava.VIE_BANKER.getCode()){
            super.table.switchState(ConstQznnKszJava.StateEnumQznnKszJava.VIE_BANKER.getCode());
        }

        // 5. 下注狀態
        super.robotOnMessageBet();
        super.table.getStateMgr().getStateMap().get(ConstQznnKszJava.StateEnumQznnKszJava.BET.getCode()).onTimeout();
        if(super.table.getStateMgr().getCurrentStateId() == ConstQznnKszJava.StateEnumQznnKszJava.BET.getCode()){
            super.table.switchState(ConstQznnKszJava.StateEnumQznnKszJava.BET.getCode());
        }

        // 6. 選牌狀態
        super.robotOnMessageSelect();
        super.table.getStateMgr().getStateMap().get(ConstQznnKszJava.StateEnumQznnKszJava.SELECT.getCode()).onTimeout();
        if(super.table.getStateMgr().getCurrentStateId() == ConstQznnKszJava.StateEnumQznnKszJava.SELECT.getCode()){
            super.table.switchState(ConstQznnKszJava.StateEnumQznnKszJava.SELECT.getCode());
        }

        // 7. 取得結果
        IGameBetLogResult gameBetLogResult = table.getResult();

        // 8. 比牌狀態
        super.table.switchState(ConstQznnKszJava.StateEnumQznnKszJava.COMPARE.getCode());

        // 9. 結算狀態
        super.table.switchState(ConstQznnKszJava.StateEnumQznnKszJava.GAME_END.getCode());

        // 10. 回傳局結果
        return new BoardGtrResult(super.calculateStatisticResult(), gameBetLogResult, new GameResultExtend());
    }
}

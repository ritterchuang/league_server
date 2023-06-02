package org.lsj.gs.mathBoardGtr.games.qznn_java;

import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResult;
import org.lsj.gs.math.games.qznn_java.entity.ConstQznnJava;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathStr.config.entity.ControlAlgorithmConfig;
import org.lsj.gs.mathStr.core.entity.GamePlayerSimulation;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.GameResultExtend;
import org.lsj.gs.mathStr.core.module.poolCltr.PoolCtr;

// 超時新搶莊牛牛局產生器
public class BoardGtrCardQznnJavaTimeOut extends AbstractBoardGtrCardQznnJava {
    public BoardGtrCardQznnJavaTimeOut(GamePlayerSimulation player, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
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

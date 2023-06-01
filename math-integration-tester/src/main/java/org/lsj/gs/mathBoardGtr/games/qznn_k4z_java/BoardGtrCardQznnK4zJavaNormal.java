package org.lsj.gs.mathBoardGtr.games.qznn_k4z_java;

import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResult;
import org.lsj.gs.math.games.qznn_k4z_java.entity.ConstQznnK4zJava;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathStr.config.entity.ControlAlgorithmConfig;
import org.lsj.gs.mathStr.core.entity.GamePlayerSimulation;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.card.GameResultExtend126QznnK4zJava;
import org.lsj.gs.mathStr.core.module.poolCltr.PoolCtr;

import java.util.Map;

// 一般看四張搶莊牛牛局產生器
public class BoardGtrCardQznnK4zJavaNormal extends AbstractBoardGtrCardQznnK4zJava {
    public BoardGtrCardQznnK4zJavaNormal(GamePlayerSimulation player, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
        super(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
    }

    @Override
    public BoardGtrResult calculateOneBoardResult() {
        // 1. 等待開始
        super.table.switchState(ConstQznnK4zJava.StateEnumQznnK4zJava.WAIT_BEGIN.getCode());

        // 2. 遊戲開始
        super.table.switchState(ConstQznnK4zJava.StateEnumQznnK4zJava.GAME_BEGIN.getCode());

        // 3. 發牌狀態
        super.table.switchState(ConstQznnK4zJava.StateEnumQznnK4zJava.DEAL_CARD.getCode());

        // 4. 搶莊狀態
        super.humanOnMessageVieBanker();
        super.robotOnMessageVieBanker();
        if(super.table.getStateMgr().getCurrentStateId() == ConstQznnK4zJava.StateEnumQznnK4zJava.VIE_BANKER.getCode()){
            super.table.switchState(ConstQznnK4zJava.StateEnumQznnK4zJava.VIE_BANKER.getCode());
        }

        // 5. 下注狀態
        super.humanOnMessageBet();
        super.robotOnMessageBet();
        if(super.table.getStateMgr().getCurrentStateId() == ConstQznnK4zJava.StateEnumQznnK4zJava.BET.getCode()){
            super.table.switchState(ConstQznnK4zJava.StateEnumQznnK4zJava.BET.getCode());
        }

        // 6. 選牌狀態
        super.humanOnMessageSelect();
        super.robotOnMessageSelect();
        if(super.table.getStateMgr().getCurrentStateId() == ConstQznnK4zJava.StateEnumQznnK4zJava.SELECT.getCode()){
            super.table.switchState(ConstQznnK4zJava.StateEnumQznnK4zJava.SELECT.getCode());
        }

        // 7. 取得結果
        IGameBetLogResult gameBetLogResult = table.getResult();

        // 8. 比牌狀態
        super.table.switchState(ConstQznnK4zJava.StateEnumQznnK4zJava.COMPARE.getCode());

        // 9. 結算狀態
        super.table.switchState(ConstQznnK4zJava.StateEnumQznnK4zJava.GAME_END.getCode());

        // 10. 計算客製結果
        GameResultExtend126QznnK4zJava gameResultExtend = this.calculateGameResultExtend();

        // 11. 回傳局結果
        return new BoardGtrResult(super.calculateStatisticResult(), gameBetLogResult, gameResultExtend);
    }

    // 計算客製結果
    private GameResultExtend126QznnK4zJava calculateGameResultExtend() {
        // 1. 計算真人位置
        int humanChairIndex = super.table.getLogic().getHumanChairIndex();

        // 2. 計算莊家位置
        int bankerChairIndex = super.table.getLogic().getBankerChairIndex();

        // 3. 計算真人搶莊倍數
        int humanVieBankerRate = super.table.getLogic().getVieRateArray()[humanChairIndex];

        // 4. 計算真人搶閒倍數
        int humanBetRate = super.table.getLogic().getBetResultArray()[humanChairIndex];

        // 5. 計算玩家牌型
        int[] niuStackArray = super.table.getLogic().getAllPlayerSelectTypeArray();

        // 6. 計算所有得分
        Map<Integer, UidScore> uidScoreMap = super.table.getLogic().calculateUidScoreMap();

        return new GameResultExtend126QznnK4zJava(
                humanChairIndex,
                bankerChairIndex,
                humanVieBankerRate,
                humanBetRate,
                niuStackArray,
                uidScoreMap
        );
    }
}

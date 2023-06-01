package org.lsj.gs.mathBoardGtr.games.lznn_java;

import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResult;
import org.lsj.gs.math.games.lznn_java.entity.ConstLznnJava;
import org.lsj.gs.math.games.lznn_java.entity.NiuStackLznnJava;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathStr.config.entity.ControlAlgorithmConfig;
import org.lsj.gs.mathStr.core.entity.GamePlayerSimulation;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.card.GameResultExtend127LznnJava;
import org.lsj.gs.mathStr.core.module.poolCltr.PoolCtr;

import java.util.Map;

// 一般搶莊牛牛局產生器
public class BoardGtrCardLznnJavaNormal extends AbstractBoardGtrCardLznnJava {
    public BoardGtrCardLznnJavaNormal(GamePlayerSimulation player, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
        super(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
    }

    @Override
    public BoardGtrResult calculateOneBoardResult() {
        // 1. 等待開始
        super.table.switchState(ConstLznnJava.StateEnumLznnJava.WAIT_BEGIN.getCode());

        // 2. 遊戲開始
        super.table.switchState(ConstLznnJava.StateEnumLznnJava.GAME_BEGIN.getCode());

        // 3. 搶莊狀態
        super.humanOnMessageVieBanker();
        super.robotOnMessageVieBanker();
        if(super.table.getStateMgr().getCurrentStateId() == ConstLznnJava.StateEnumLznnJava.VIE_BANKER.getCode()){
            super.table.switchState(ConstLznnJava.StateEnumLznnJava.VIE_BANKER.getCode());
        }

        // 4. 下注狀態
        super.humanOnMessageBet();
        super.robotOnMessageBet();
        super.table.switchState(ConstLznnJava.StateEnumLznnJava.BET.getCode());

        // 5. 發牌狀態
        super.table.switchState(ConstLznnJava.StateEnumLznnJava.DEAL_CARD.getCode());

        // 6. 選牌狀態
        super.humanOnMessageSelect();
        super.robotOnMessageSelect();
        super.table.switchState(ConstLznnJava.StateEnumLznnJava.SELECT.getCode());

        // 7. 取得結果
        IGameBetLogResult gameBetLogResult = table.getResult();

        // 8. 比牌狀態
        super.table.switchState(ConstLznnJava.StateEnumLznnJava.COMPARE.getCode());

        // 9. 結算狀態
        super.table.switchState(ConstLznnJava.StateEnumLznnJava.GAME_END.getCode());

        // 10. 計算客製結果
        GameResultExtend127LznnJava gameResultExtend = this.calculateGameResultExtend();

        // 11. 回傳局結果
        return new BoardGtrResult(super.calculateStatisticResult(), gameBetLogResult, gameResultExtend);
    }

    // 計算客製結果
    private GameResultExtend127LznnJava calculateGameResultExtend() {
        // 1. 計算真人位置
        int humanChairIndex = super.table.getLogic().getHumanChairIndex();

        // 2. 計算莊家位置
        int bankerChairIndex = super.table.getLogic().getBankerChairIndex();

        // 3. 計算真人搶莊倍數
        int humanVieBankerRate = super.table.getLogic().getVieRateArray()[humanChairIndex];

        // 4. 計算真人搶閒倍數
        int humanBetRate = super.table.getLogic().getBetResultArray()[humanChairIndex];

        // 5. 計算玩家牌型
        NiuStackLznnJava niuStack = super.table.getLogic().getHumanPlayerSelectResult();

        // 6. 計算所有得分
        Map<Integer, UidScore> uidScoreMap = super.table.getLogic().calculateUidScoreMap();

        return new GameResultExtend127LznnJava(
                humanChairIndex,
                bankerChairIndex,
                humanVieBankerRate,
                humanBetRate,
                niuStack,
                uidScoreMap
                );
    }
}

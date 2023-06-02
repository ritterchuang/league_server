package org.lsj.gs.mathBoardGtr.games.tbnn_java;

import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResult;
import org.lsj.gs.math.games.tbnn_java.entity.ConstTbnnJava;
import org.lsj.gs.math.games.tbnn_java.entity.NiuStackTbnnJava;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathStr.config.entity.ControlAlgorithmConfig;
import org.lsj.gs.mathStr.core.entity.GamePlayerSimulation;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.card.GameResultExtend125TbnnJava;
import org.lsj.gs.mathStr.core.module.poolCltr.PoolCtr;

import java.util.Map;

// 一般通比牛牛局產生器
public class BoardGtrCardTbnnJavaNormal extends AbstractBoardGtrCardTbnnJava {
    public BoardGtrCardTbnnJavaNormal(GamePlayerSimulation player, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
        super(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
    }

    @Override
    public BoardGtrResult calculateOneBoardResult() {
        // 1. 等待開始
        super.table.switchState(ConstTbnnJava.StateEnumTbnnJava.WAIT_BEGIN.getCode());

        // 2. 遊戲開始
        super.table.switchState(ConstTbnnJava.StateEnumTbnnJava.GAME_BEGIN.getCode());

        // 3. 下注狀態
        super.humanOnMessageBet();
        super.robotOnMessageBet();
        super.table.switchState(ConstTbnnJava.StateEnumTbnnJava.BET.getCode());

        // 4. 發牌狀態
        super.table.switchState(ConstTbnnJava.StateEnumTbnnJava.DEAL_CARD.getCode());

        // 5. 選牌狀態
        super.humanOnMessageSelect();
        super.robotOnMessageSelect();
        super.table.switchState(ConstTbnnJava.StateEnumTbnnJava.SELECT.getCode());

        // 6. 取得結果
        IGameBetLogResult gameBetLogResult = table.getResult();

        // 7. 比牌狀態
        super.table.switchState(ConstTbnnJava.StateEnumTbnnJava.COMPARE.getCode());

        // 8. 結算狀態
        super.table.switchState(ConstTbnnJava.StateEnumTbnnJava.GAME_END.getCode());

        // 9. 計算客製結果
        GameResultExtend125TbnnJava gameResultExtend = this.calculateGameResultExtend();

        // 10. 回傳局結果
        return new BoardGtrResult(super.calculateStatisticResult(), gameBetLogResult, gameResultExtend);
    }

    // 計算客製結果
    private GameResultExtend125TbnnJava calculateGameResultExtend() {
        // 1. 計算真人位置
        int humanChairIndex = super.table.getLogic().getHumanChairIndex();

        // 2. 計算真人搶閒倍數
        int humanBetRate = super.table.getLogic().getBetResultArray()[humanChairIndex];

        // 3. 計算玩家牌型
        NiuStackTbnnJava niuStack = super.table.getLogic().getHumanPlayerSelectResult();

        // 4. 計算所有得分
        Map<Integer, UidScore> uidScoreMap = super.table.getLogic().calculateUidScoreMap();

        return new GameResultExtend125TbnnJava(
                humanChairIndex,
                humanBetRate,
                niuStack,
                uidScoreMap
                );
    }
}

package org.lsj.gs.mathBoardGtr.games.ebg_java;

import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResult;
import org.lsj.gs.math.games.ebg_java.entity.ConstEbgJava;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathStr.config.entity.ControlAlgorithmConfig;
import org.lsj.gs.mathStr.core.entity.GamePlayerSimulation;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.card.GameResultExtend114EbgJava;
import org.lsj.gs.mathStr.core.module.poolCltr.PoolCtr;

import java.util.Map;

// 一般搶莊二八槓局產生器
public class BoardGtrCardEbgJavaNormal extends AbstractBoardGtrCardEbgJava {
    public BoardGtrCardEbgJavaNormal(GamePlayerSimulation player, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
        super(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
    }

    @Override
    public BoardGtrResult calculateOneBoardResult() {
        // 1. 等待開始
        super.table.switchState(ConstEbgJava.StateEnumEbgJava.WAIT_BEGIN.getCode());

        // 2. 遊戲開始
        super.table.switchState(ConstEbgJava.StateEnumEbgJava.GAME_BEGIN.getCode());

        // 3. 搶莊狀態
        super.humanOnMessageVieBanker();
        super.robotOnMessageVieBanker();
        if(super.table.getStateMgr().getCurrentStateId() == ConstEbgJava.StateEnumEbgJava.VIE_BANKER.getCode()){
            super.table.switchState(ConstEbgJava.StateEnumEbgJava.VIE_BANKER.getCode());
        }

        // 4. 下注狀態
        super.humanOnMessageBet();
        super.robotOnMessageBet();
        super.table.switchState(ConstEbgJava.StateEnumEbgJava.BET.getCode());

        // 5. 發牌狀態
        super.table.switchState(ConstEbgJava.StateEnumEbgJava.DEAL_CARD.getCode());

        // 6. 取得結果
        IGameBetLogResult gameBetLogResult = table.getResult();

        // 7. 比牌狀態
        super.table.switchState(ConstEbgJava.StateEnumEbgJava.COMPARE.getCode());

        // 8. 結算狀態
        super.table.switchState(ConstEbgJava.StateEnumEbgJava.GAME_END.getCode());

        // 9. 計算客製結果
        GameResultExtend114EbgJava gameResultExtend = this.calculateGameResultExtend();

        // 10. 回傳局結果
        return new BoardGtrResult(super.calculateStatisticResult(), gameBetLogResult, gameResultExtend);
    }

    // 計算客製結果
    private GameResultExtend114EbgJava calculateGameResultExtend() {
        // 1. 計算真人位置
        int humanChairIndex = super.table.getLogic().getHumanChairIndex();

        // 2. 計算莊家位置
        int bankerChairIndex = super.table.getLogic().getBankerChairIndex();

        // 3. 計算真人搶莊倍數
        int humanVieBankerRate = super.table.getLogic().getVieRateArray()[humanChairIndex];

        // 4. 計算真人搶閒倍數
        int humanBetRate = super.table.getLogic().getBetResultArray()[humanChairIndex];

        // 5. 計算玩家牌型
        int[] ebgStackArray = super.table.getLogic().getAllPlayerSelectTypeArray();

        // 6. 計算所有得分
        Map<Integer, UidScore> uidScoreMap = super.table.getLogic().calculateUidScoreMap();

        return new GameResultExtend114EbgJava(
                humanChairIndex,
                bankerChairIndex,
                humanVieBankerRate,
                humanBetRate,
                ebgStackArray,
                uidScoreMap
                );
    }
}

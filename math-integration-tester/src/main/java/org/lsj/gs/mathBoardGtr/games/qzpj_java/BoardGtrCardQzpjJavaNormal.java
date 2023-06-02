package org.lsj.gs.mathBoardGtr.games.qzpj_java;

import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResult;
import org.lsj.gs.math.games.qzpj_java.entity.ConstQzpjJava;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathStr.config.entity.ControlAlgorithmConfig;
import org.lsj.gs.mathStr.core.entity.GamePlayerSimulation;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.card.GameResultExtend122QzpjJava;
import org.lsj.gs.mathStr.core.module.poolCltr.PoolCtr;

import java.util.Map;

// 一般搶莊牌九局產生器
public class BoardGtrCardQzpjJavaNormal extends AbstractBoardGtrCardQzpjJava {
    public BoardGtrCardQzpjJavaNormal(GamePlayerSimulation player, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
        super(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
    }

    @Override
    public BoardGtrResult calculateOneBoardResult() {
        // 1. 等待開始
        super.table.switchState(ConstQzpjJava.StateEnumQzpjJava.WAIT_BEGIN.getCode());

        // 2. 遊戲開始
        super.table.switchState(ConstQzpjJava.StateEnumQzpjJava.GAME_BEGIN.getCode());

        // 3. 搶莊狀態
        super.humanOnMessageVieBanker();
        super.robotOnMessageVieBanker();
        if(super.table.getStateMgr().getCurrentStateId() == ConstQzpjJava.StateEnumQzpjJava.VIE_BANKER.getCode()){
            super.table.switchState(ConstQzpjJava.StateEnumQzpjJava.VIE_BANKER.getCode());
        }

        // 4. 下注狀態
        super.humanOnMessageBet();
        super.robotOnMessageBet();
        super.table.switchState(ConstQzpjJava.StateEnumQzpjJava.BET.getCode());

        // 5. 發牌狀態
        super.table.switchState(ConstQzpjJava.StateEnumQzpjJava.DEAL_CARD.getCode());

        // 6. 取得結果
        IGameBetLogResult gameBetLogResult = table.getResult();

        // 7. 比牌狀態
        super.table.switchState(ConstQzpjJava.StateEnumQzpjJava.COMPARE.getCode());

        // 8. 結算狀態
        super.table.switchState(ConstQzpjJava.StateEnumQzpjJava.GAME_END.getCode());

        // 9. 計算客製結果
        GameResultExtend122QzpjJava gameResultExtend = this.calculateGameResultExtend();

        // 10. 回傳局結果
        return new BoardGtrResult(super.calculateStatisticResult(), gameBetLogResult, gameResultExtend);
    }

    // 計算客製結果
    private GameResultExtend122QzpjJava calculateGameResultExtend() {
        // 1. 計算真人位置
        int humanChairIndex = super.table.getLogic().getHumanChairIndex();

        // 2. 計算莊家位置
        int bankerChairIndex = super.table.getLogic().getBankerChairIndex();

        // 3. 計算真人搶莊倍數
        int humanVieBankerRate = super.table.getLogic().getVieRateArray()[humanChairIndex];

        // 4. 計算真人搶閒倍數
        int humanBetRate = super.table.getLogic().getBetResultArray()[humanChairIndex];

        // 5. 計算玩家牌型
        int[] pjStackArray = super.table.getLogic().getAllPlayerSelectTypeArray();

        // 6. 計算所有得分
        Map<Integer, UidScore> uidScoreMap = super.table.getLogic().calculateUidScoreMap();

        return new GameResultExtend122QzpjJava(
                humanChairIndex,
                bankerChairIndex,
                humanVieBankerRate,
                humanBetRate,
                pjStackArray,
                uidScoreMap
                );
    }
}

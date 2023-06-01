package org.lsj.gs.skillTreeGtr.games.modelSkill.entity.tablePhase;

import org.lsj.gs.skillTreeGtr.core.entity.tablePhase.AbstractTablePhase;

import java.util.Arrays;

// 模板 牌局階段
public class TablePhaseModelSkill extends AbstractTablePhase {
    private final String key; // 鑑值
    private final int[] cardPointArray; // 牌點數 [0] = 真人; [1] = 機器人;
    private final int turn; // 執行權; 0: 真人可執行; 1: 機器人;
    private final int turnCount; // 執行次數
    private final int[] actionArray; // 執行動作列表; 0: 棄牌; 1: 比牌; 2: 加注;
    private final int baseScore; // 底注
    private final int validBet; // 有效押注
    private final boolean gameEndFlag; // 遊戲結束標誌
    private final int[] scoreArray; // 遊戲得分; [0] = 真人; [1] = 機器人;

    // 解析用建構子
    public TablePhaseModelSkill() {
        this.key = "null";
        this.cardPointArray = null;
        this.turn = -1;
        this.turnCount = -1;
        this.actionArray = null;
        this.baseScore = -1;
        this.validBet = -1;
        this.gameEndFlag = true;
        this.scoreArray = null;
    }

    public TablePhaseModelSkill(
            String key,
            int[] cardPointArray,
            int turn,
            int turnCount,
            int[] actionArray,
            int baseScore,
            int validBet,
            boolean gameEndFlag,
            int[] scoreArray) {
        this.key = key;
        this.cardPointArray = cardPointArray;
        this.turn = turn;
        this.turnCount = turnCount;
        this.actionArray = actionArray;
        this.baseScore = baseScore;
        this.validBet = validBet;
        this.gameEndFlag = gameEndFlag;
        this.scoreArray = scoreArray;
    }

    public String getKey() {
        return key;
    }

    public int[] getCardPointArray() {
        return cardPointArray;
    }

    public int getTurn() {
        return turn;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public int[] getActionArray() {
        return actionArray;
    }

    public int getBaseScore() {
        return baseScore;
    }

    public int getValidBet() {
        return validBet;
    }

    public boolean isGameEndFlag() {
        return gameEndFlag;
    }

    public int[] getScoreArray() {
        return scoreArray;
    }

    @Override
    public String toString() {
        return "key: " + this.key + "; " +
                "cardPoint: " + this.cardPointArray[0] + ":" + this.cardPointArray[1] + "; " +
                "turn: " + this.turn + "; " +
                "turn count: " + this.turnCount + "; " +
                "actionArray: " + Arrays.toString(this.actionArray) + "; " +
                "baseScore: " + this.baseScore + "; " +
                "validBet: " + this.validBet + "; " +
                "gameEndFlag: " + this.gameEndFlag + "; " +
                "scoreArray: " + Arrays.toString(this.scoreArray) + "; ";
    }
}

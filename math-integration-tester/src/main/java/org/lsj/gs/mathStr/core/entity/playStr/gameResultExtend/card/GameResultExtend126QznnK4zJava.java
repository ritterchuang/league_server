package org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.card;

import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;

import java.util.Map;

// 新看四張搶莊牛牛客製遊戲結果
public class GameResultExtend126QznnK4zJava extends GameResultExtendBanker {
    private final int[] niuStackArray; // 所有牌型; [座位] = 牌型;

    public GameResultExtend126QznnK4zJava(int humanChairIndex, int bankerChairIndex, int humanVieBankerRate, int humanBetRate, int[] niuStackArray, Map<Integer, UidScore> uidScoreMap) {
        super(humanChairIndex, bankerChairIndex, humanVieBankerRate, humanBetRate, uidScoreMap);
        this.niuStackArray = niuStackArray;
    }

    public int[] getNiuStackArray() {
        return niuStackArray;
    }
}

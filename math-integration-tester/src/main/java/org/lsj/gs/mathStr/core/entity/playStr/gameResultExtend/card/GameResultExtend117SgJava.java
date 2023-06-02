package org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.card;

import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;

import java.util.Map;

// 新三公客製遊戲結果
public class GameResultExtend117SgJava extends GameResultExtendBanker {
    private final int[] sgStackArray; // 所有牌型; [座位] = 牌型;

    public GameResultExtend117SgJava(int humanChairIndex, int bankerChairIndex, int humanVieBankerRate, int humanBetRate, int[] sgStackArray, Map<Integer, UidScore> uidScoreMap) {
        super(humanChairIndex, bankerChairIndex, humanVieBankerRate, humanBetRate, uidScoreMap);
        this.sgStackArray = sgStackArray;
    }

    public int[] getSgStackArray() {
        return sgStackArray;
    }
}

package org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.card;

import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;

import java.util.Map;

// 新搶莊二八槓客製遊戲結果
public class GameResultExtend114EbgJava extends GameResultExtendBanker {
    private final int[] ebgStackArray; // 所有牌型; [座位] = 牌型;

    public GameResultExtend114EbgJava(int humanChairIndex, int bankerChairIndex, int humanVieBankerRate, int humanBetRate, int[] ebgStackArray, Map<Integer, UidScore> uidScoreMap) {
        super(humanChairIndex, bankerChairIndex, humanVieBankerRate, humanBetRate, uidScoreMap);
        this.ebgStackArray = ebgStackArray;
    }

    public int[] getEbgStackArray() {
        return ebgStackArray;
    }
}

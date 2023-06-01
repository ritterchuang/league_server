package org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.card;

import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;

import java.util.Map;

// 新搶莊牌九客製遊戲結果
public class GameResultExtend122QzpjJava extends GameResultExtendBanker {
    private final int[] pjStackArray; // 所有牌型; [座位] = 牌型;

    public GameResultExtend122QzpjJava(int humanChairIndex, int bankerChairIndex, int humanVieBankerRate, int humanBetRate, int[] pjStackArray, Map<Integer, UidScore> uidScoreMap) {
        super(humanChairIndex, bankerChairIndex, humanVieBankerRate, humanBetRate, uidScoreMap);
        this.pjStackArray = pjStackArray;
    }

    public int[] getPjStackArray() {
        return pjStackArray;
    }
}

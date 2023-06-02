package org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.card;

import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.games.qznn_ksz_java.entity.NiuStackQznnKszJava;

import java.util.Map;

// 新看三張搶莊牛牛客製遊戲結果
public class GameResultExtend115QznnKszJava extends GameResultExtendBanker {
    private final NiuStackQznnKszJava niuStack; // 玩家牌型

    public GameResultExtend115QznnKszJava(int humanChairIndex, int bankerChairIndex, int humanVieBankerRate, int humanBetRate, NiuStackQznnKszJava niuStack, Map<Integer, UidScore> uidScoreMap) {
        super(humanChairIndex, bankerChairIndex, humanVieBankerRate, humanBetRate, uidScoreMap);
        this.niuStack = niuStack;
    }

    public NiuStackQznnKszJava getNiuStack() {
        return niuStack;
    }
}

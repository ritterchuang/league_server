package org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.card;

import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.games.qznn_java.entity.NiuStackQznnJava;

import java.util.Map;

// 新搶莊牛牛客製遊戲結果
public class GameResultExtend113QznnJava extends GameResultExtendBanker {
    private final NiuStackQznnJava niuStack; // 玩家牌型

    public GameResultExtend113QznnJava(int humanChairIndex, int bankerChairIndex, int humanVieBankerRate, int humanBetRate, NiuStackQznnJava niuStack, Map<Integer, UidScore> uidScoreMap) {
        super(humanChairIndex, bankerChairIndex, humanVieBankerRate, humanBetRate, uidScoreMap);
        this.niuStack = niuStack;
    }

    public NiuStackQznnJava getNiuStack() {
        return niuStack;
    }
}

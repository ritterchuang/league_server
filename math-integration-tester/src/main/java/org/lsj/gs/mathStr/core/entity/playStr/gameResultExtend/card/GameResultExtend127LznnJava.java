package org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.card;

import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.games.lznn_java.entity.NiuStackLznnJava;

import java.util.Map;

// 新賴子牛牛客製遊戲結果
public class GameResultExtend127LznnJava extends GameResultExtendBanker {
    private final NiuStackLznnJava niuStack; // 玩家牌型

    public GameResultExtend127LznnJava(int humanChairIndex, int bankerChairIndex, int humanVieBankerRate, int humanBetRate, NiuStackLznnJava niuStack, Map<Integer, UidScore> uidScoreMap) {
        super(humanChairIndex, bankerChairIndex, humanVieBankerRate, humanBetRate, uidScoreMap);
        this.niuStack = niuStack;
    }

    public NiuStackLznnJava getNiuStack() {
        return niuStack;
    }
}

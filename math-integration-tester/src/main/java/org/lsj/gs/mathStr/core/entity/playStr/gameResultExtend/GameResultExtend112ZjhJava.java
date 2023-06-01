package org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend;

import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.games.zjh_java.entity.NiuStackZjhJava;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.card.GameResultExtendBanker;

import java.util.Map;

// 新炸金花客製遊戲結果
public class GameResultExtend112ZjhJava extends GameResultExtendBanker {
    private final NiuStackZjhJava niuStack; // 玩家牌型

    public GameResultExtend112ZjhJava(int humanChairIndex, int bankerChairIndex, int humanVieBankerRate, int humanBetRate, NiuStackZjhJava niuStack, Map<Integer, UidScore> uidScoreMap) {
        super(humanChairIndex, bankerChairIndex, humanVieBankerRate, humanBetRate, uidScoreMap);
        this.niuStack = niuStack;
    }

    public NiuStackZjhJava getNiuStack() {
        return niuStack;
    }
}

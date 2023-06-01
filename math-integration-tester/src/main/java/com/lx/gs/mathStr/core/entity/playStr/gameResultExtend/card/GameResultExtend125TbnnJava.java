package com.lx.gs.mathStr.core.entity.playStr.gameResultExtend.card;

import com.lx.gs.math.core.common.gameAdjust.entity.UidScore;
import com.lx.gs.math.games.tbnn_java.entity.NiuStackTbnnJava;

import java.util.Map;

// 新通比牛牛客製遊戲結果
public class GameResultExtend125TbnnJava extends GameResultExtendTonbi {
    private final NiuStackTbnnJava niuStack; // 玩家牌型

    public GameResultExtend125TbnnJava(int humanChairIndex, int humanBetRate, NiuStackTbnnJava niuStack, Map<Integer, UidScore> uidScoreMap) {
        super(humanChairIndex, humanBetRate, uidScoreMap);
        this.niuStack = niuStack;
    }

    public NiuStackTbnnJava getNiuStack() {
        return niuStack;
    }
}

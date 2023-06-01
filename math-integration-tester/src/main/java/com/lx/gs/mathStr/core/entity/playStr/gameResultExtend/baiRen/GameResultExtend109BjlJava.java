package com.lx.gs.mathStr.core.entity.playStr.gameResultExtend.baiRen;

import com.lx.gs.math.core.card.stackCtr.bjlStackCtr.BjlStack;

import java.util.Map;

// 新百家樂客製遊戲結果
public class GameResultExtend109BjlJava extends GameResultExtendBaiRenBj {
    public GameResultExtend109BjlJava(Map<Integer, BjlStack> stackMap, int[] playerAreaBetArray, int[] winAreaArray, int[] returnAreaArray) {
        super(stackMap, playerAreaBetArray, winAreaArray, returnAreaArray);
    }

}

package org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.baiRen;

import org.lsj.gs.math.core.card.stackCtr.bjlStackCtr.BjlStack;

import java.util.Map;

// 新免傭百家樂客製遊戲結果
public class GameResultExtend129MybjlJava extends GameResultExtendBaiRenBj {

    public GameResultExtend129MybjlJava(Map<Integer, BjlStack> stackMap, int[] playerAreaBetArray, int[] winAreaArray, int[] returnAreaArray) {
        super(stackMap, playerAreaBetArray, winAreaArray, returnAreaArray);
    }
}

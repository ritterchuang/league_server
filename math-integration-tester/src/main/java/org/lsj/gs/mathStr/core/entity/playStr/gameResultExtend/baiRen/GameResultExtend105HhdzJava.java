package org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.baiRen;

import org.lsj.gs.math.core.card.stackCtr.jhStackCtr.JhStack;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.GameResultExtend;

import java.util.Map;

// 新紅黑大戰客製遊戲結果
public class GameResultExtend105HhdzJava extends GameResultExtend {
    private final Map<Integer, JhStack> stackMap; // 區域牌型對應表
    private final int[] playerAreaBetArray; // 取得指定玩家押注區域金額陣列

    public GameResultExtend105HhdzJava(Map<Integer, JhStack> stackMap, int[] playerAreaBetArray) {
        this.stackMap = stackMap;
        this.playerAreaBetArray = playerAreaBetArray;
    }

    public Map<Integer, JhStack> getStackMap() {
        return stackMap;
    }

    public int[] getPlayerAreaBetArray() {
        return playerAreaBetArray;
    }
}

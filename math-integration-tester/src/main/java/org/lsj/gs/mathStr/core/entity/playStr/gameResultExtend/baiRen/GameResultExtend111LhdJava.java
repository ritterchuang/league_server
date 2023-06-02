package org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.baiRen;

import org.lsj.gs.math.core.card.stackCtr.lhStackCtr.LhStack;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.GameResultExtend;

import java.util.Map;

// 新龍虎鬥客製遊戲結果
public class GameResultExtend111LhdJava extends GameResultExtend {
    private final Map<Integer, LhStack> stackMap; // 龍虎牌型
    private final int[] playerAreaBetArray; // 取得指定玩家押注區域金額陣列

    public GameResultExtend111LhdJava(Map<Integer, LhStack> stackMap, int[] playerAreaBetArray) {
        this.stackMap = stackMap;
        this.playerAreaBetArray = playerAreaBetArray;
    }

    public Map<Integer, LhStack> getStackMap() {
        return stackMap;
    }

    public int[] getPlayerAreaBetArray() {
        return playerAreaBetArray;
    }
}

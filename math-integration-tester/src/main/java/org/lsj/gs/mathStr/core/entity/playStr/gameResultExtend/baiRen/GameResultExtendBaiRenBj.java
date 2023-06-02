package org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.baiRen;

import org.lsj.gs.math.core.card.stackCtr.bjlStackCtr.BjlStack;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.GameResultExtend;

import java.util.Map;

// 新百家客製遊戲結果
public class GameResultExtendBaiRenBj extends GameResultExtend {
    private final Map<Integer, BjlStack> stackMap; // 區域牌型對應表
    private final int[] playerAreaBetArray; // 取得指定玩家押注區域金額陣列
    private final int[] winAreaArray; // 獲勝押注區域
    private final int[] returnAreaArray; // 返還押注區域

    public GameResultExtendBaiRenBj(Map<Integer, BjlStack> stackMap, int[] playerAreaBetArray, int[] winAreaArray, int[] returnAreaArray) {
        this.stackMap = stackMap;
        this.playerAreaBetArray = playerAreaBetArray;
        this.winAreaArray = winAreaArray;
        this.returnAreaArray = returnAreaArray;
    }

    public Map<Integer, BjlStack> getStackMap() {
        return stackMap;
    }

    public int[] getPlayerAreaBetArray() {
        return playerAreaBetArray;
    }

    public int[] getWinAreaArray() {
        return winAreaArray;
    }

    public int[] getReturnAreaArray() {
        return returnAreaArray;
    }
}

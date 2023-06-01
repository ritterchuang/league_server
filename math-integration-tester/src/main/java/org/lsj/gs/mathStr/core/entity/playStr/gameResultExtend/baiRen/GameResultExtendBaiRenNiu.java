package org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.baiRen;

import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.NiuStackCommon;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.GameResultExtend;

import java.util.Map;

// 新超級牛牛客製遊戲結果
public class GameResultExtendBaiRenNiu extends GameResultExtend {
    private final Map<Integer, NiuStackCommon> stackMap; // 區域牌型對應表
    private final int[] playerAreaBetArray; // 取得指定玩家押注區域金額陣列
    private final int[] winLossArray; // 下注區域輸贏陣列

    public GameResultExtendBaiRenNiu(Map<Integer, NiuStackCommon> stackMap, int[] playerAreaBetArray, int[] winLossArray) {
        this.stackMap = stackMap;
        this.playerAreaBetArray = playerAreaBetArray;
        this.winLossArray = winLossArray;
    }

    public Map<Integer, NiuStackCommon> getStackMap() {
        return stackMap;
    }

    public int[] getPlayerAreaBetArray() {
        return playerAreaBetArray;
    }

    public int[] getWinLossArray() {
        return winLossArray;
    }
}

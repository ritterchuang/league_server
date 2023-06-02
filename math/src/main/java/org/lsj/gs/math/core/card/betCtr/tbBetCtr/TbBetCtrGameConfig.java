package org.lsj.gs.math.core.card.betCtr.tbBetCtr;

import java.util.List;

// 下注計算器遊戲設定
public class TbBetCtrGameConfig {
    private final List<Integer> betList; // 下注列表

    public TbBetCtrGameConfig(List<Integer> betList) {
        this.betList = betList;
    }

    public List<Integer> getBetList() {
        return betList;
    }
}

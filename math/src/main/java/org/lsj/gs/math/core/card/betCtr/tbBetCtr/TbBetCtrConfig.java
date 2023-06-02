package org.lsj.gs.math.core.card.betCtr.tbBetCtr;

import org.lsj.gs.math.core.card.betCtr.AbstractBetCtrConfig;

import java.util.List;

// 通比下注計算器遊戲設定
public class TbBetCtrConfig extends AbstractBetCtrConfig {
    private final List<Integer> betList; // 下注列表

    public TbBetCtrConfig(int maxUser, double baseScore, List<Integer> betList) {
        super(maxUser, baseScore);
        this.betList = betList;
    }

    public List<Integer> getBetList() {
        return betList;
    }
}

package org.lsj.gs.math.core.card.stackCtr.sgStackCtr;

// 三公牌型計算器設定
public class SgStackCtrConfig {
    private final int maxUser; // 最大玩家數

    public SgStackCtrConfig(int maxUser) {
        this.maxUser = maxUser;
    }

    public int getMaxUser() {
        return maxUser;
    }
}

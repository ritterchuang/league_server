package org.lsj.gs.math.core.card.stackCtr.ebgStackCtr;

// 二八槓牌型計算器設定
public class EbgStackCtrConfig {
    private final int maxUser; // 最大玩家數

    public EbgStackCtrConfig(int maxUser) {
        this.maxUser = maxUser;
    }

    public int getMaxUser() {
        return maxUser;
    }
}

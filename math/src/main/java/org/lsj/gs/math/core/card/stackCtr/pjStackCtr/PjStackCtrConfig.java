package org.lsj.gs.math.core.card.stackCtr.pjStackCtr;

// 牌九牌型計算器設定
public class PjStackCtrConfig {
    private final int maxUser; // 最大玩家數

    public PjStackCtrConfig(int maxUser) {
        this.maxUser = maxUser;
    }

    public int getMaxUser() {
        return maxUser;
    }
}

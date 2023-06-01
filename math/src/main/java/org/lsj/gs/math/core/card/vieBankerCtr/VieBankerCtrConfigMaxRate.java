package org.lsj.gs.math.core.card.vieBankerCtr;

import org.lsj.gs.math.core.card.ConstMathCard;

// 搶莊計算器設定 給定最大倍數
public class VieBankerCtrConfigMaxRate extends VieBankerCtrConfig{
    private final int minVieRate; // 搶莊倍數列表
    private final int maxVieRate; // 搶莊倍數攜帶金額門檻列表
    private final int vieRateGroup; // 搶莊切割組數

    public VieBankerCtrConfigMaxRate(ConstMathCard.BankerType bankerType, int maxUser, double baseScore, int minVieRate, int maxVieRate, int vieRateGroup) {
        super(bankerType, maxUser, baseScore);
        this.minVieRate = minVieRate;
        this.maxVieRate = maxVieRate;
        this.vieRateGroup = vieRateGroup;
    }

    public int getMinVieRate() {
        return minVieRate;
    }

    public int getMaxVieRate() {
        return maxVieRate;
    }

    public int getVieRateGroup() {
        return vieRateGroup;
    }
}

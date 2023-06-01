package org.lsj.gs.math.core.card.vieBankerCtr;

import org.lsj.gs.math.core.card.ConstMathCard;

// 搶莊計算器遊戲設定
public class VieBankerCtrGameConfigMaxRate extends VieBankerCtrGameConfig{
    private final int minVieRate; // 搶莊倍數列表
    private final int maxVieRate; // 搶莊倍數攜帶金額門檻列表
    private final int vieRateGroup; // 搶莊切割組數

    public VieBankerCtrGameConfigMaxRate(ConstMathCard.BankerType bankerType, int minVieRate, int maxVieRate, int vieRateGroup) {
        super(bankerType);
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

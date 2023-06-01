package org.lsj.gs.math.core.card.vieBankerCtr;

import org.lsj.gs.math.core.card.ConstMathCard;

// 搶莊計算器遊戲設定
public class VieBankerCtrGameConfig {
    private final ConstMathCard.BankerType bankerType;

    public VieBankerCtrGameConfig(ConstMathCard.BankerType bankerType) {
        this.bankerType = bankerType;
    }

    public ConstMathCard.BankerType getBankerType() {
        return bankerType;
    }
}

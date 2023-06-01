package org.lsj.gs.math.core.card.betCtr.qzBetCtr;

import org.lsj.gs.math.core.card.ConstMathCard;

// 下注計算器遊戲設定 BetType02
public class QzBetCtrGameConfigBetType02 extends QzBetCtrGameConfig {
    private final int minRate; // 下注最小倍數
    private final int thresholdValue; // 分母門檻

    public QzBetCtrGameConfigBetType02(ConstMathCard.QzBetType qzBetType, int minRate, int thresholdValue) {
        super(qzBetType);
        this.minRate = minRate;
        this.thresholdValue = thresholdValue;
    }

    public int getMinRate() {
        return minRate;
    }

    public int getThresholdValue() {
        return thresholdValue;
    }
}

package org.lsj.gs.math.core.card.betCtr.qzBetCtr;

import org.lsj.gs.math.core.card.ConstMathCard;

// 下注計算器設定
public class QzBetCtrConfigBetType02 extends QzBetCtrConfig {
    private final int minRate; // 下注最小倍數
    private final int thresholdValue; // 分母門檻

    public QzBetCtrConfigBetType02(ConstMathCard.QzBetType qzBetType, int maxUser, double baseScore, int minRate, int thresholdValue) {
        super(qzBetType, maxUser, baseScore);
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

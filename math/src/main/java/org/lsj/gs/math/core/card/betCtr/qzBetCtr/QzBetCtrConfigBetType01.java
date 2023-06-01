package org.lsj.gs.math.core.card.betCtr.qzBetCtr;

import org.lsj.gs.math.core.card.ConstMathCard;

// 下注計算器設定
public class QzBetCtrConfigBetType01 extends QzBetCtrConfig {
    private final int maxRate; // 下注最大倍數
    private final int minRate; // 下注最小倍數
    private final int specifyRate; // 特殊倍數
    private final int thresholdValue; // 分母門檻

    public QzBetCtrConfigBetType01(ConstMathCard.QzBetType qzBetType, int maxUser, double baseScore, int maxRate, int minRate, int specifyRate, int thresholdValue) {
        super(qzBetType, maxUser, baseScore);
        this.maxRate = maxRate;
        this.minRate = minRate;
        this.specifyRate = specifyRate;
        this.thresholdValue = thresholdValue;
    }

    public int getMaxRate() {
        return maxRate;
    }

    public int getMinRate() {
        return minRate;
    }

    public int getSpecifyRate() {
        return specifyRate;
    }

    public int getThresholdValue() {
        return thresholdValue;
    }
}

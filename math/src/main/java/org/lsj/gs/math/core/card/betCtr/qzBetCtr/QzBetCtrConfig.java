package org.lsj.gs.math.core.card.betCtr.qzBetCtr;

import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.card.betCtr.AbstractBetCtrConfig;

// 搶莊下注計算器設定
public class QzBetCtrConfig extends AbstractBetCtrConfig {
    private final ConstMathCard.QzBetType qzBetType; // 下注類型

    public QzBetCtrConfig(ConstMathCard.QzBetType qzBetType, int maxUser, double baseScore) {
        super(maxUser, baseScore);
        this.qzBetType = qzBetType;
    }

    public ConstMathCard.QzBetType getBetType() {
        return qzBetType;
    }
}

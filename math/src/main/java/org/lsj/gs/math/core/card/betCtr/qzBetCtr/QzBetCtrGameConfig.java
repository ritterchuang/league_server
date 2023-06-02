package org.lsj.gs.math.core.card.betCtr.qzBetCtr;

import org.lsj.gs.math.core.card.ConstMathCard;

// 下注計算器遊戲設定
public class QzBetCtrGameConfig {
    private final ConstMathCard.QzBetType qzBetType; // 下注類型

    public QzBetCtrGameConfig(ConstMathCard.QzBetType qzBetType) {
        this.qzBetType = qzBetType;
    }

    public ConstMathCard.QzBetType getBetType() {
        return qzBetType;
    }
}

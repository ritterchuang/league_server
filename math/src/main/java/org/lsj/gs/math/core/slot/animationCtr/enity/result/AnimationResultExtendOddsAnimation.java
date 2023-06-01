package org.lsj.gs.math.core.slot.animationCtr.enity.result;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 客製動畫額外結果單一倍數動畫
public class AnimationResultExtendOddsAnimation extends AnimationResultExtend{
    private final ConstMathSlot.OddsWinType oddsWinType; // 獎項倍數類型

    public AnimationResultExtendOddsAnimation(ConstMathSlot.OddsWinType oddsWinType) {
        this.oddsWinType = oddsWinType;
    }

    public ConstMathSlot.OddsWinType getOddsWinType() {
        return oddsWinType;
    }
}

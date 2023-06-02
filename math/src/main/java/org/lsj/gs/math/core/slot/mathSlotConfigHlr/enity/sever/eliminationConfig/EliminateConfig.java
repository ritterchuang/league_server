package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.eliminationConfig;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 消除方式設定
public class EliminateConfig {
    private final ConstMathSlot.EliminationType eliminationType; // 消除方式類型
    private final EliminationConfigExtend eliminationConfigExtend; // 消除方式額外設定

    public EliminateConfig(ConstMathSlot.EliminationType eliminationType, EliminationConfigExtend eliminationConfigExtend) {
        this.eliminationType = eliminationType;
        this.eliminationConfigExtend = eliminationConfigExtend;
    }

    public ConstMathSlot.EliminationType getEliminationType() {
        return eliminationType;
    }

    public EliminationConfigExtend getEliminationConfigExtend() {
        return eliminationConfigExtend;
    }
}

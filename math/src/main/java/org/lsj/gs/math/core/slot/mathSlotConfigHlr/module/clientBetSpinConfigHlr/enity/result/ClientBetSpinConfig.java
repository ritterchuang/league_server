package org.lsj.gs.math.core.slot.mathSlotConfigHlr.module.clientBetSpinConfigHlr.enity.result;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 押注玩法設定
public class ClientBetSpinConfig {
    private final ConstMathSlot.BetSpinType betSpinType; // 投注玩法類型
    private final ClientBetSpinConfigExtend betSpinConfigExtend; // 客製投注玩法額外設定

    public ClientBetSpinConfig(ConstMathSlot.BetSpinType betSpinType, ClientBetSpinConfigExtend betSpinConfigExtend) {
        this.betSpinType = betSpinType;
        this.betSpinConfigExtend = betSpinConfigExtend;
    }

    public ConstMathSlot.BetSpinType getBetSpinType() {
        return betSpinType;
    }

    public ClientBetSpinConfigExtend getBetSpinConfigExtend() {
        return betSpinConfigExtend;
    }
}

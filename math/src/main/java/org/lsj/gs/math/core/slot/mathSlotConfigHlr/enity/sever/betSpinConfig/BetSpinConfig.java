package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.betSpinConfig;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.paymentConfig.PaymentConfig;

// 成本計算方式
public class BetSpinConfig {
    private final ConstMathSlot.BetSpinType betSpinType; // 押注玩法類型
    private final BetSpinConfigExtend betSpinConfigExtend; // 押注玩法額外設定
    private final PaymentConfig paymentConfig; // 成本設定

    public BetSpinConfig(ConstMathSlot.BetSpinType betSpinType, BetSpinConfigExtend betSpinConfigExtend, PaymentConfig paymentConfig) {
        this.betSpinType = betSpinType;
        this.betSpinConfigExtend = betSpinConfigExtend;
        this.paymentConfig = paymentConfig;
    }

    public ConstMathSlot.BetSpinType getBetSpinType() {
        return betSpinType;
    }

    public BetSpinConfigExtend getBetSpinConfigExtend() {
        return betSpinConfigExtend;
    }

    public PaymentConfig getPaymentConfig() {
        return paymentConfig;
    }
}

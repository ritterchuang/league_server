package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.paymentConfig;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 收費設置
public class PaymentConfig {
    private final ConstMathSlot.PaymentType paymentType;
    private final PaymentConfigExtend paymentConfigExtend;

    public PaymentConfig(ConstMathSlot.PaymentType paymentType, PaymentConfigExtend paymentConfigExtend) {
        this.paymentType = paymentType;
        this.paymentConfigExtend = paymentConfigExtend;
    }

    public ConstMathSlot.PaymentType getPaymentType() {
        return paymentType;
    }

    public PaymentConfigExtend getPaymentConfigExtend() {
        return paymentConfigExtend;
    }
}

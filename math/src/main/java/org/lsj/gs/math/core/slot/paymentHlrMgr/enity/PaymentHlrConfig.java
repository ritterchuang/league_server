package org.lsj.gs.math.core.slot.paymentHlrMgr.enity;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.paymentConfig.PaymentConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.paymentConfig.PaymentConfigExtend;

import java.util.List;

// 收費設置
public class PaymentHlrConfig {
    private final List<Double> creditList; // 成本列表
    private final double base; // 房間底注
    private final ConstMathSlot.PaymentType paymentType; // 成本類型
    private final PaymentConfigExtend paymentConfigExtend; // 成本額外設定

    public PaymentHlrConfig(List<Double> creditList, double base, PaymentConfig paymentConfig) {
        this.creditList = creditList;
        this.base = base;
        this.paymentType = paymentConfig.getPaymentType();
        this.paymentConfigExtend = paymentConfig.getPaymentConfigExtend();
    }

    public List<Double> getCreditList() {
        return creditList;
    }

    public double getBase() {
        return base;
    }

    public ConstMathSlot.PaymentType getPaymentType() {
        return paymentType;
    }

    public PaymentConfigExtend getPaymentConfigExtend() {
        return paymentConfigExtend;
    }
}

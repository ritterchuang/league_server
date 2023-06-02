package org.lsj.gs.math.core.slot.paymentHlrMgr.enity;

import org.lsj.gs.math.core.slot.ConstMathSlot;

import java.util.Map;

// 成本處理器管理者設定
public class PaymentHlrMgrConfig {
    private final Map<ConstMathSlot.BetType, Map<ConstMathSlot.SpinType, PaymentHlrConfig>> betSpinTypeToPaymentHlrConfig; // <押注類型, <玩法類型, 成本計算設定>> 對應表

    public PaymentHlrMgrConfig(Map<ConstMathSlot.BetType, Map<ConstMathSlot.SpinType, PaymentHlrConfig>> betSpinTypeToPaymentHlrConfig) {
        this.betSpinTypeToPaymentHlrConfig = betSpinTypeToPaymentHlrConfig;
    }

    public Map<ConstMathSlot.BetType, Map<ConstMathSlot.SpinType, PaymentHlrConfig>> getBetSpinTypeToPaymentHlrConfig() {
        return betSpinTypeToPaymentHlrConfig;
    }
}

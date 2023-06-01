package com.lx.gs.math.core.slot.mathSlotConfigHlr.module;

import com.lx.gs.math.core.slot.ConstMathSlot;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.module.clientBetSpinConfigHlr.enity.result.ClientBetSpinConfig;
import com.lx.gs.math.core.slot.paymentHlrMgr.PaymentHlrMgr;

// 客端押注玩法設定處理者介面
public interface IClientBetSpinConfigHlr {

    // 計算 客端押注玩法設定
    ClientBetSpinConfig calculateClientBetSpinConfig(ConstMathSlot.BetSpinType betSpinType,
                                                     ConstMathSlot.BetType betType,
                                                     ConstMathSlot.SpinType spinType,
                                                     PaymentHlrMgr paymentHlrMgr);
}

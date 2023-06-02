package org.lsj.gs.math.core.slot.mathSlotConfigHlr.module;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.module.clientBetSpinConfigHlr.enity.result.ClientBetSpinConfig;
import org.lsj.gs.math.core.slot.paymentHlrMgr.PaymentHlrMgr;

// 客端押注玩法設定處理者介面
public interface IClientBetSpinConfigHlr {

    // 計算 客端押注玩法設定
    ClientBetSpinConfig calculateClientBetSpinConfig(ConstMathSlot.BetSpinType betSpinType,
                                                     ConstMathSlot.BetType betType,
                                                     ConstMathSlot.SpinType spinType,
                                                     PaymentHlrMgr paymentHlrMgr);
}

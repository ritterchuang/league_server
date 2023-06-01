package org.lsj.gs.math.core.slot.mathSlotConfigHlr.module.clientBetSpinConfigHlr.module;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.module.IClientBetSpinConfigHlr;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.module.clientBetSpinConfigHlr.enity.config.ClientBetSpinConfigHlrConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.module.clientBetSpinConfigHlr.enity.result.ClientBetSpinConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.module.clientBetSpinConfigHlr.enity.result.ClientBetSpinConfigExtendNoneNormal;
import org.lsj.gs.math.core.slot.paymentHlrMgr.PaymentHlrMgr;

// 客端押注玩法設定處理者
public class ClientBetSpinConfigHlrNoneNormal implements IClientBetSpinConfigHlr {
    private final ClientBetSpinConfigHlrConfig config; // 客端押注玩法設定處理者設定檔
    private final ITableUtil tableUtil; // 牌桌工具包

    public ClientBetSpinConfigHlrNoneNormal(ClientBetSpinConfigHlrConfig config, ITableUtil tableUtil) {
        this.config = config;
        this.tableUtil = tableUtil;
    }

    // 計算 客端押注玩法設定
    @Override
    public ClientBetSpinConfig calculateClientBetSpinConfig(ConstMathSlot.BetSpinType betSpinType,
                                                            ConstMathSlot.BetType betType,
                                                            ConstMathSlot.SpinType spinType,
                                                            PaymentHlrMgr paymentHlrMgr) {

        return new ClientBetSpinConfig(betSpinType,
                new ClientBetSpinConfigExtendNoneNormal(paymentHlrMgr.getCreditBetBoxList(betType, spinType)));
    }
}

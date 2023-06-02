package org.lsj.gs.math.core.slot.paymentHlrMgr.module;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.paymentHlrMgr.enity.PaymentHlrConfig;

// 成本處理者工廠
public class PaymentHlrFactory {

    // 創建成本處理者 TODO Default
    public IPaymentHlr create(PaymentHlrConfig paymentHlrConfig, ITableUtil tableUtil) {
        switch (paymentHlrConfig.getPaymentType()) {
            default: return new PaymentHlrRatio(paymentHlrConfig, tableUtil);
        }
    }
}

package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig;

import java.util.List;

// 賠率表設定
public class PayTableConfig {
    private final List<PayCombo> payComboList; // 標誌賠率表列表

    public PayTableConfig(List<PayCombo> payComboList) {
        this.payComboList = payComboList;
    }

    public List<PayCombo> getPayComboList() {
        return payComboList;
    }
}

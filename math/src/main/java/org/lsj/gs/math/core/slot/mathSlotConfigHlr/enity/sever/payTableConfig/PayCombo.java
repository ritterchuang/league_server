package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig;

import java.util.List;

// 標誌賠率表
public class PayCombo {
    private final List<Integer> symbolIdList; // 標誌識別碼列表 [index] = 標誌
    private final List<Integer> payOddsList; // 賠率列表 [連線數] = 賠率

    public PayCombo(List<Integer> symbolIdList, List<Integer> payOddsList) {
        this.symbolIdList = symbolIdList;
        this.payOddsList = payOddsList;
    }

    // 判斷是否為混搭
    public boolean isMix() {
        return symbolIdList.size() > 1;
    }

    public List<Integer> getSymbolIdList() {
        return symbolIdList;
    }

    public List<Integer> getPayOddsList() {
        return payOddsList;
    }
}

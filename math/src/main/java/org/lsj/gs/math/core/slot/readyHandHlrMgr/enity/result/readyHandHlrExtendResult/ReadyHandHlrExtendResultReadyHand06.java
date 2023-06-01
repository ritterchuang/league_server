package org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.readyHandHlrExtendResult;

import java.util.List;

// 聽牌處理者額外結果 ReadyHand06
public class ReadyHandHlrExtendResultReadyHand06 extends ReadyHandHlrExtendResult{
    private final List<Integer> targetSymbolIdList; // 聽牌列表
    private final int columnIndex; // 聽牌輪軸

    public ReadyHandHlrExtendResultReadyHand06(List<Integer> targetSymbolIdList, int columnIndex) {
        this.targetSymbolIdList = targetSymbolIdList;
        this.columnIndex = columnIndex;
    }

    public List<Integer> getTargetSymbolIdList() {
        return targetSymbolIdList;
    }

    public int getColumnIndex() {
        return columnIndex;
    }
}

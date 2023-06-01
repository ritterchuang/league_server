package org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.readyHandHlrExtendResult;

import java.util.List;

// 聽牌處理者額外結果 ReadyHand04
public class ReadyHandHlrExtendResultReadyHand04 extends ReadyHandHlrExtendResult{
    private final List<Integer> targetSymbolIdList; // 聽牌列表
    private final int columnIndex; // 聽牌輪軸

    public ReadyHandHlrExtendResultReadyHand04(List<Integer> targetSymbolIdList, int columnIndex) {
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

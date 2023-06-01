package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.readyHandResultPgr.enity;

import java.util.List;

// 聽牌額外結果 聽牌類型01
public class ReadyHandResultExtend01 extends ReadyHandResultExtend{
    private List<Integer> symbolIdList; // 標誌列表
    private int columnIndex; // 表演輪軸

    public ReadyHandResultExtend01(List<Integer> symbolIdList, int columnIndex) {
        this.symbolIdList = symbolIdList;
        this.columnIndex = columnIndex;
    }

    public List<Integer> getSymbolIdList() {
        return symbolIdList;
    }

    public int getColumnIndex() {
        return columnIndex;
    }
}

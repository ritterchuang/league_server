package org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.addSymbolCtrResultExtend;

import java.util.List;

// 新增標誌計算者額外結果 一般
public class AddSymbolCtrResult{
    private final List<List<Integer>> addSymbolList; // 新增標誌列表 [col][掉落順序] = symbolId
    private final List<Integer> usedSymbolCountOnScreen; // 新畫面使用幾個新增標誌 [column] = 使用個數

    public AddSymbolCtrResult(List<List<Integer>> addSymbolList, List<Integer> usedSymbolCountOnScreen) {
        this.addSymbolList = addSymbolList;
        this.usedSymbolCountOnScreen = usedSymbolCountOnScreen;
    }

    public List<List<Integer>> getAddSymbolList() {
        return addSymbolList;
    }

    public List<Integer> getUsedSymbolCountOnScreen() {
        return usedSymbolCountOnScreen;
    }
}

package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig;

import java.util.List;

// 客製輪帶表額外設定無滾輪權重
public class ReelConfigExtendNonReelWeight extends ReelConfigExtend {
    private final List<List<Integer>> symbolWeightList; // 標誌權重表 [column][symbolId] = weight

    public ReelConfigExtendNonReelWeight(List<List<Integer>> symbolWeightList) {
        this.symbolWeightList = symbolWeightList;
    }

    public List<List<Integer>> getSymbolWeightList() {
        return symbolWeightList;
    }
}

package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig;

import java.util.List;

// 客製輪帶表額外設定相依滾輪 [目前相同Y的標誌，高度需一樣]
public class ReelConfigExtendReelMegaDependent extends ReelConfigExtend {
    private final List<List<MegaSymbolBox>> reelMegaSymbolBoxList; // MegaSymbol輪帶表
    private final List<Integer> combineReelList; // 同步滾輪列表[-1 代表跟前面一起轉]

    public ReelConfigExtendReelMegaDependent(List<List<MegaSymbolBox>> reelMegaSymbolBoxList, List<Integer> combineReelList) {
        this.reelMegaSymbolBoxList = reelMegaSymbolBoxList;
        this.combineReelList = combineReelList;
    }

    public List<List<MegaSymbolBox>> getReelMegaSymbolBoxList() {
        return reelMegaSymbolBoxList;
    }

    public List<Integer> getCombineReelList() {
        return combineReelList;
    }
}

package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig;

import java.util.List;

// 客製輪帶表額外設定相依滾輪
public class ReelConfigExtendReelDependent extends ReelConfigExtend {
    private final List<List<Integer>> reelStripList; // 輪帶表 [column][輪帶位置] = symbolId

    public ReelConfigExtendReelDependent(List<List<Integer>> reelStripList) {
        this.reelStripList = reelStripList;
    }

    public List<List<Integer>> getReelStripList() {
        return reelStripList;
    }
}

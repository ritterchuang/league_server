package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig;

import java.util.List;

/*
* 畫面位置
* 0 3 6 9 12
* 1 4 7 10 13
* 2 5 8 11 14
*
* */
// 客製輪帶表額外設定獨立滾輪
public class ReelConfigExtendReelIndependent extends ReelConfigExtend {
    private final List<List<Integer>> reelStripList; // 輪帶表 [畫面位置][輪帶位置] = symbolId

    public ReelConfigExtendReelIndependent(List<List<Integer>> reelStripList) {
        this.reelStripList = reelStripList;
    }

    public List<List<Integer>> getReelStripList() {
        return reelStripList;
    }
}

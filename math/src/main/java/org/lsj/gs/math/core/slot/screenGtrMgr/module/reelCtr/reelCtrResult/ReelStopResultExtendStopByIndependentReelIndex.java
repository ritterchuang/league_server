package org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult;

import java.util.List;

// 客製滾輪結果 獨立滾輪
public class ReelStopResultExtendStopByIndependentReelIndex extends ReelStopResultExtend {
    private final List<Integer> reelStopIndexList; // 輪軸滾停指標列表 [index] = stopIndex
                                                   // [index / column = rowIndex]
                                                   // [index % column = columnIndex]


    public ReelStopResultExtendStopByIndependentReelIndex(DampCtrResult dampCtrResult, List<Integer> reelStopIndexList) {
        super(dampCtrResult);
        this.reelStopIndexList = reelStopIndexList;
    }
}

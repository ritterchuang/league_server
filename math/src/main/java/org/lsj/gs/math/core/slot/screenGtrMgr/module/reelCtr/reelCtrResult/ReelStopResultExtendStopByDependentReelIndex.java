package org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult;

import java.util.List;

// 客製滾輪結果 相依滾輪
public class ReelStopResultExtendStopByDependentReelIndex extends ReelStopResultExtend {
    private final List<Integer> reelStopIndexList; // 輪軸滾停指標列表 [column] = stopIndex

    public ReelStopResultExtendStopByDependentReelIndex(DampCtrResult dampCtrResult, List<Integer> reelStopIndexList) {
        super(dampCtrResult);
        this.reelStopIndexList = reelStopIndexList;
    }

    public List<Integer> getReelStopIndexList() {
        return reelStopIndexList;
    }
}

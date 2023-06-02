package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.clientReelResult;

import java.util.List;

// 客端滾輪結果相依滾輪
public class ClientReelStopResultExtendStopByDependentReelIndex extends ClientReelStopResultExtend {
    private final List<Integer> reelStopIndexList; // 輪軸滾停指標列表 [column] = stopIndex

    public ClientReelStopResultExtendStopByDependentReelIndex(List<Integer> reelStopIndexList) {
        this.reelStopIndexList = reelStopIndexList;
    }

    public List<Integer> getReelStopIndexList() {
        return reelStopIndexList;
    }
}

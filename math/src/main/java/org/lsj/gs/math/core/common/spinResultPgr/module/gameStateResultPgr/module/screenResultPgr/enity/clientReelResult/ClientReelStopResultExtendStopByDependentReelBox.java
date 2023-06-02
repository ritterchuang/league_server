package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.clientReelResult;

import org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.StopBox;

import java.util.List;

// 客端滾輪結果 相依滾輪停輪物件
public class ClientReelStopResultExtendStopByDependentReelBox extends ClientReelStopResultExtend {
    private final List<StopBox> reelStopBoxList; // 輪軸滾停指標列表 [column] = stopBox

    public ClientReelStopResultExtendStopByDependentReelBox(List<StopBox> reelStopBoxList) {
        this.reelStopBoxList = reelStopBoxList;
    }

    public List<StopBox> getReelStopBoxList() {
        return reelStopBoxList;
    }
}

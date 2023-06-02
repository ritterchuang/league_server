package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity.clientReelResult;

import org.lsj.gs.math.core.common.spinResultPgr.ConstPgrSlot;

// 客製輪帶表結果
public class ClientReelResult {
    private final int reelId; // 輪帶表識別碼
    private final ConstPgrSlot.ClientReelStopType reelStopType; // 客端滾輪類型
    private final ClientReelStopResultExtend reelStopResultExtend; // 客端滾輪額外結果

    public ClientReelResult(int reelId, ConstPgrSlot.ClientReelStopType reelStopType, ClientReelStopResultExtend reelStopResultExtend) {
        this.reelId = reelId;
        this.reelStopType = reelStopType;
        this.reelStopResultExtend = reelStopResultExtend;
    }

    public int getReelId() {
        return reelId;
    }

    public ConstPgrSlot.ClientReelStopType getReelStopType() {
        return reelStopType;
    }

    public ClientReelStopResultExtend getReelStopResultExtend() {
        return reelStopResultExtend;
    }
}

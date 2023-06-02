package org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 客端輪帶表結果
public class ReelCtrResult {
    private final int reelId; // 輪帶表識別碼
    private final ConstMathSlot.ReelStopType reelStopType; // 滾輪類型
    private final ReelStopResultExtend reelStopResultExtend; // 客製滾輪額外結果

    public ReelCtrResult(int reelId, ConstMathSlot.ReelStopType reelStopType, ReelStopResultExtend reelStopResultExtend) {
        this.reelId = reelId;
        this.reelStopType = reelStopType;
        this.reelStopResultExtend = reelStopResultExtend;
    }

    public int getReelId() {
        return reelId;
    }

    public ConstMathSlot.ReelStopType getReelStopType() {
        return reelStopType;
    }

    public ReelStopResultExtend getReelStopResultExtend() {
        return reelStopResultExtend;
    }
}

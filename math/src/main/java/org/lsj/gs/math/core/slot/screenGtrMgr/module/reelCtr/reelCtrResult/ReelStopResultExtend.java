package org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult;

// 客端客製滾輪結果
public class ReelStopResultExtend {
    private final DampCtrResult dampCtrResult; // 破框資訊

    public ReelStopResultExtend(DampCtrResult dampCtrResult) {
        this.dampCtrResult = dampCtrResult;
    }

    public DampCtrResult getDampCtrResult() {
        return dampCtrResult;
    }
}

package org.lsj.gs.math.core.slot.progressHlrMgr.enity.result;

// 系統遊戲進度額外資訊觸發局
public class ProgressHlrResultExtendTriggerRound extends ProgressHlrResultExtend {
    private final RoundProgress roundProgress; // 局進度

    public ProgressHlrResultExtendTriggerRound(RoundProgress roundProgress) {
        this.roundProgress = roundProgress;
    }

    public RoundProgress getRoundProgress() {
        return roundProgress;
    }
}

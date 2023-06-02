package org.lsj.gs.math.core.slot.progressHlrMgr.enity.result;

// 系統遊戲進度額外資訊局
public class ProgressHlrResultExtendRound extends ProgressHlrResultExtend {
    private final RoundProgress roundProgress; // 局進度

    public ProgressHlrResultExtendRound(RoundProgress roundProgress) {
        this.roundProgress = roundProgress;
    }

    public RoundProgress getRoundProgress() {
        return roundProgress;
    }
}

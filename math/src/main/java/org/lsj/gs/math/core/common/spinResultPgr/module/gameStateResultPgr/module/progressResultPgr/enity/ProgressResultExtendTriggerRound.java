package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.progressResultPgr.enity;

import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.RoundProgress;

// 客製遊戲進度額外資訊 觸發局
public class ProgressResultExtendTriggerRound extends ProgressResultExtend {
    private final RoundProgress roundProgress; // 局進度

    public ProgressResultExtendTriggerRound(RoundProgress roundProgress) {
        this.roundProgress = roundProgress;
    }

    public RoundProgress getRoundProgress() {
        return roundProgress;
    }
}

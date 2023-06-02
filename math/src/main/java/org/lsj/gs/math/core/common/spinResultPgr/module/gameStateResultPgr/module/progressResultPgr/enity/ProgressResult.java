package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.progressResultPgr.enity;

import org.lsj.gs.math.core.slot.ConstMathSlot;

public class ProgressResult {
    private final boolean maxProgress; // 是否達最大場次
    private final ConstMathSlot.ClientProgressType progressType; // 遊戲進度類型
    private final ProgressResultExtend progressResultExtend; // 客製遊戲進度額外結果

    public ProgressResult(boolean maxProgress, ConstMathSlot.ClientProgressType progressType, ProgressResultExtend progressResultExtend) {
        this.maxProgress = maxProgress;
        this.progressType = progressType;
        this.progressResultExtend = progressResultExtend;
    }

    public boolean isMaxProgress() {
        return maxProgress;
    }

    public ConstMathSlot.ClientProgressType getProgressType() {
        return progressType;
    }

    public ProgressResultExtend getProgressResultExtend() {
        return progressResultExtend;
    }
}

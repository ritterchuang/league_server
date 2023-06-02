package org.lsj.gs.math.core.slot.progressHlrMgr.enity.result;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 系統遊戲進度結果
public class ProgressHlrResult {
    private final boolean maxProgress; // 是否達最大場次
    private final ConstMathSlot.ProgressType progressType; // 遊戲進度類型
    private final ProgressHlrResultExtend progressHlrResultExtend; // 客製遊戲進度額外結果

    public ProgressHlrResult(boolean maxProgress, ConstMathSlot.ProgressType progressType, ProgressHlrResultExtend progressHlrResultExtend) {
        this.maxProgress = maxProgress;
        this.progressType = progressType;
        this.progressHlrResultExtend = progressHlrResultExtend;
    }

    public ProgressHlrResult() {
        this.maxProgress = false;
        this.progressType = ConstMathSlot.ProgressType.INVALID;
        this.progressHlrResultExtend = new ProgressHlrResultExtend();
    }

    public boolean isMaxProgress() {
        return maxProgress;
    }

    public ConstMathSlot.ProgressType getProgressType() {
        return progressType;
    }

    public ProgressHlrResultExtend getProgressHlrResultExtend() {
        return progressHlrResultExtend;
    }
}

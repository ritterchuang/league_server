package org.lsj.gs.math.core.slot.progressHlrMgr.enity.result;

// 系統遊戲進度額外資訊 消除
public class ProgressHlrResultExtendCascade extends ProgressHlrResultExtend {
    private final CascadeProgress cascadeProgress; // 局進度

    public ProgressHlrResultExtendCascade(CascadeProgress cascadeProgress) {
        this.cascadeProgress = cascadeProgress;
    }

    public CascadeProgress getCascadeProgress() {
        return cascadeProgress;
    }
}

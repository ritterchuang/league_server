package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.progressResultPgr.enity;

import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.CascadeProgress;

// 客製遊戲進度額外資訊局
public class ProgressResultExtendCascade extends ProgressResultExtend {
    private final CascadeProgress cascadeProgress; // 消除進度

    public ProgressResultExtendCascade(CascadeProgress cascadeProgress) {
        this.cascadeProgress = cascadeProgress;
    }

    public CascadeProgress getCascadeProgress() {
        return cascadeProgress;
    }
}

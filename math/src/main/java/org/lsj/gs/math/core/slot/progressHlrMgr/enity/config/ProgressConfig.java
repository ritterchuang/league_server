package org.lsj.gs.math.core.slot.progressHlrMgr.enity.config;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 遊戲進度設定
public class ProgressConfig {
    private final ConstMathSlot.ProgressType progressType; // 遊戲進度類型
    private final ProgressConfigExtend progressConfigExtend; // 遊戲進度額外設定

    public ProgressConfig(ConstMathSlot.ProgressType progressType, ProgressConfigExtend progressConfigExtend) {
        this.progressType = progressType;
        this.progressConfigExtend = progressConfigExtend;
    }

    public ConstMathSlot.ProgressType getProgressType() {
        return progressType;
    }

    public ProgressConfigExtend getProgressConfigExtend() {
        return progressConfigExtend;
    }
}

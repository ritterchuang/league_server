package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 畫面設定
public class FrameConfig {
    private final ConstMathSlot.FrameType frameType; // 畫面類型
    private final FrameConfigExtend frameConfigExtend; // 客製畫面類型額外設定

    public FrameConfig(ConstMathSlot.FrameType frameType, FrameConfigExtend frameConfigExtend) {
        this.frameType = frameType;
        this.frameConfigExtend = frameConfigExtend;
    }

    public ConstMathSlot.FrameType getFrameType() {
        return frameType;
    }

    public FrameConfigExtend getFrameConfigExtend() {
        return frameConfigExtend;
    }
}

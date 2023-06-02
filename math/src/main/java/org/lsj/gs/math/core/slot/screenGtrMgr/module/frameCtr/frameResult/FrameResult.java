package org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 畫面結果
public class FrameResult {
    private final ConstMathSlot.FrameType frameType; // 畫面類型
    private final FrameResultExtend frameResultExtend; // 畫面額外結果

    public FrameResult(ConstMathSlot.FrameType frameType, FrameResultExtend frameResultExtend) {
        this.frameType = frameType;
        this.frameResultExtend = frameResultExtend;
    }

    public ConstMathSlot.FrameType getFrameType() {
        return frameType;
    }

    public FrameResultExtend getFrameResultExtend() {
        return frameResultExtend;
    }
}

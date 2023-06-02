package org.lsj.gs.math.core.slot.screenGtrMgr.enity;

import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfig;

// 畫面計算器設定檔
public class FrameCtrConfig {
    private final FrameConfig frameConfig; // 畫面設定

    public FrameCtrConfig(FrameConfig frameConfig) {
        this.frameConfig = frameConfig;
    }

    public FrameConfig getFrameConfig() {
        return frameConfig;
    }
}

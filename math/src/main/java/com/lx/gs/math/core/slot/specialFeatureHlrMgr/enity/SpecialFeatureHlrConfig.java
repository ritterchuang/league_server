package com.lx.gs.math.core.slot.specialFeatureHlrMgr.enity;

import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.specialFeatureConfig.SpecialFeatureConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;

// 特殊事件處理者設定
public class SpecialFeatureHlrConfig {
    private final FrameConfig frameConfig; // 畫面設定
    private final SymbolConfig symbolConfig; // 標誌設定
    private final SpecialFeatureConfig specialFeatureConfig; // 特殊事件設定

    public SpecialFeatureHlrConfig(FrameConfig frameConfig, SymbolConfig symbolConfig, SpecialFeatureConfig specialFeatureConfig) {
        this.frameConfig = frameConfig;
        this.symbolConfig = symbolConfig;
        this.specialFeatureConfig = specialFeatureConfig;
    }

    public FrameConfig getFrameConfig() {
        return frameConfig;
    }

    public SymbolConfig getSymbolConfig() {
        return symbolConfig;
    }

    public SpecialFeatureConfig getSpecialFeatureConfig() {
        return specialFeatureConfig;
    }
}

package org.lsj.gs.math.core.slot.screenGtrMgr.enity;

import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.dampConfig.DampConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;

// 畫面生產設定
public class ScreenGtrConfig {
    private final FrameConfig frameConfig; // 畫面設定
    private final SymbolConfig symbolConfig; // 標誌設定
    private final ReelConfig reelConfig; // 輪帶表設定
    private final DampConfig dampConfig; // 破框設定

    public ScreenGtrConfig(FrameConfig frameConfig, SymbolConfig symbolConfig, ReelConfig reelConfig, DampConfig dampConfig) {
        this.frameConfig = frameConfig;
        this.symbolConfig = symbolConfig;
        this.reelConfig = reelConfig;
        this.dampConfig = dampConfig;
    }

    public ReelCtrConfig generateReelCtrConfig() {
        return new ReelCtrConfig(this.reelConfig, this.dampConfig);
    }

    public FrameCtrConfig generateFrameCtrConfig() {
        return new FrameCtrConfig(this.frameConfig);
    }
}

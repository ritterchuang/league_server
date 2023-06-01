package org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.config;

import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.readyHandConfig.ReadyHandConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;

// 聽牌處理器設定
public class ReadyHandHlrConfig {
    private final FrameConfig frameConfig; // 畫面設定
    private final SymbolConfig symbolConfig; // 標誌設定
    private final ReadyHandConfig readyHandConfig; // 聽牌設定

    public ReadyHandHlrConfig(FrameConfig frameConfig, SymbolConfig symbolConfig, ReadyHandConfig readyHandConfig) {
        this.frameConfig = frameConfig;
        this.symbolConfig = symbolConfig;
        this.readyHandConfig = readyHandConfig;
    }

    public FrameConfig getFrameConfig() {
        return frameConfig;
    }

    public SymbolConfig getSymbolConfig() {
        return symbolConfig;
    }

    public ReadyHandConfig getReadyHandConfig() {
        return readyHandConfig;
    }
}

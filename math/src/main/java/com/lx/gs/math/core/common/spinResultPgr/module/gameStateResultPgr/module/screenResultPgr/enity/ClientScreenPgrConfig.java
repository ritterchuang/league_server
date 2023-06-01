package com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.screenResultPgr.enity;

import com.lx.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientScreenConfig.ClientScreenConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.dampConfig.DampConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfig;

// 客端畫面處理者設定
public class ClientScreenPgrConfig {
    private final ClientScreenConfig clientScreenConfig; // 客端畫面顯示設定

    private final FrameConfig frameConfig; // 畫面設定
    private final ReelConfig reelConfig; // 輪帶表設定
    private final DampConfig dampConfig; // 破框設定

    public ClientScreenPgrConfig(ClientScreenConfig clientScreenConfig, FrameConfig frameConfig, ReelConfig reelConfig, DampConfig dampConfig) {
        this.clientScreenConfig = clientScreenConfig;
        this.dampConfig = dampConfig;
        this.frameConfig = frameConfig;
        this.reelConfig = reelConfig;
    }

    public ClientScreenConfig getClientScreenConfig() {
        return clientScreenConfig;
    }

    public FrameConfig getFrameConfig() {
        return frameConfig;
    }

    public ReelConfig getReelConfig() {
        return reelConfig;
    }

    public DampConfig getDampConfig() {
        return dampConfig;
    }
}

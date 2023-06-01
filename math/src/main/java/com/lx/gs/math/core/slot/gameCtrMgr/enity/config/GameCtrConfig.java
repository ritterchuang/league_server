package com.lx.gs.math.core.slot.gameCtrMgr.enity.config;

import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.gameConfig.GameConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayTableConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;

// 畫面算分者設定
public class GameCtrConfig {
    private final FrameConfig frameConfig; // 畫面設定
    private final SymbolConfig symbolConfig; // 標誌設定
    private final PayTableConfig payTableConfig; // 賠率設定
    private final GameConfig gameConfig; // 算分設定

    public GameCtrConfig(FrameConfig frameConfig, SymbolConfig symbolConfig, PayTableConfig payTableConfig, GameConfig gameConfig) {
        this.frameConfig = frameConfig;
        this.symbolConfig = symbolConfig;
        this.payTableConfig = payTableConfig;
        this.gameConfig = gameConfig;
    }

    public FrameConfig getFrameConfig() {
        return frameConfig;
    }

    public SymbolConfig getSymbolConfig() {
        return symbolConfig;
    }

    public PayTableConfig getPayTableConfig() {
        return payTableConfig;
    }

    public GameConfig getGameConfig() {
        return gameConfig;
    }
}

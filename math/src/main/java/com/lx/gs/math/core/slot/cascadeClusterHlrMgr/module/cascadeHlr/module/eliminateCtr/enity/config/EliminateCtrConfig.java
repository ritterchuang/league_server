package com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.module.eliminateCtr.enity.config;

import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.eliminationConfig.EliminateConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;

// 消除計算者設定 TODO
public class EliminateCtrConfig {
    private final SymbolConfig symbolConfig; // 標誌設定
    private final EliminateConfig eliminateConfig; // 消除方式設定

    public EliminateCtrConfig(SymbolConfig symbolConfig, EliminateConfig eliminateConfig) {
        this.symbolConfig = symbolConfig;
        this.eliminateConfig = eliminateConfig;
    }

    public SymbolConfig getSymbolConfig() {
        return symbolConfig;
    }

    public EliminateConfig getEliminationConfig() {
        return eliminateConfig;
    }
}

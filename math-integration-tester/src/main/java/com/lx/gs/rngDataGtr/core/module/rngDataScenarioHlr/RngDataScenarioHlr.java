package com.lx.gs.rngDataGtr.core.module.rngDataScenarioHlr;

import com.lx.gs.rngDataGtr.config.entity.RngDataScenarioHlrConfig;

// 亂數情境處理器
public class RngDataScenarioHlr {
    private final RngDataScenarioHlrConfig rngDataScenarioHlrConfig; // 亂數情境處理器設定

    public RngDataScenarioHlr(RngDataScenarioHlrConfig rngDataScenarioHlrConfig) {
        this.rngDataScenarioHlrConfig = rngDataScenarioHlrConfig;
    }

    public int getRunBoardLimit(){
        return this.rngDataScenarioHlrConfig.getRunBoardLimit();
    }
}

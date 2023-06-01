package com.lx.gs.mathStr.config.resources.scenarioHlr;

import com.lx.gs.mathStr.config.entity.ScenarioHlrConfig;

// 情境處理器設定 10萬場
public class ScenarioHlrR01B100T {
    public ScenarioHlrConfig create(){
        return new ScenarioHlrConfig(100000);
    }
}

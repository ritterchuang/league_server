package com.lx.gs.mathStr.config.resources.scenarioHlr;

import com.lx.gs.mathStr.config.entity.ScenarioHlrConfig;

// 情境處理器設定 100萬場
public class ScenarioHlrR02B1M {
    public ScenarioHlrConfig create(){
        return new ScenarioHlrConfig(1000000);
    }
}

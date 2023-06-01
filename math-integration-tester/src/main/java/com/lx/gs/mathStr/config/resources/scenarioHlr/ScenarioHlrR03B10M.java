package com.lx.gs.mathStr.config.resources.scenarioHlr;

import com.lx.gs.mathStr.config.entity.ScenarioHlrConfig;

// 情境處理器設定 1000萬場
public class ScenarioHlrR03B10M {
    public ScenarioHlrConfig create(){
        return new ScenarioHlrConfig(10_000_000);
    }
}

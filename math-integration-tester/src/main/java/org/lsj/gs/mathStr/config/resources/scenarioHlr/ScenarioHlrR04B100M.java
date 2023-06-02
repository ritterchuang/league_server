package org.lsj.gs.mathStr.config.resources.scenarioHlr;

import org.lsj.gs.mathStr.config.entity.ScenarioHlrConfig;

// 情境處理器設定 1億場
public class ScenarioHlrR04B100M {
    public ScenarioHlrConfig create(){
        return new ScenarioHlrConfig(100_000_000);
    }
}

package org.lsj.gs.rngDataGtr.config.entity;

// 亂數情境處理器設定
public class RngDataScenarioHlrConfig {
    private final int runBoardLimit; // 執行場次

    public RngDataScenarioHlrConfig(int runBoardLimit) {
        this.runBoardLimit = runBoardLimit;
    }

    public int getRunBoardLimit() {
        return runBoardLimit;
    }
}

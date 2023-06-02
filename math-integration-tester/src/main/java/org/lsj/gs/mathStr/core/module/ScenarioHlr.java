package org.lsj.gs.mathStr.core.module;

import org.lsj.gs.mathStr.config.entity.ScenarioHlrConfig;

// 情境處理器
public class ScenarioHlr {
    private final ScenarioHlrConfig config; // 情境處理器設定
    private int playedBoardCount; // 已執行遊戲場次

    public ScenarioHlr(ScenarioHlrConfig config) {
        this.config = config;
        this.playedBoardCount = 0;
    }

    // 更新已執行遊戲場次
    public void updatePlayedBoardCount(int playedBoardCount){
        this.playedBoardCount += playedBoardCount;
    }

    // 取得剩餘可執行遊戲場次
    public int getRemainBoardCount(){
        return (this.config.getSimulationBoard() - this.playedBoardCount);
    }

    public ScenarioHlrConfig getConfig() {
        return config;
    }

    public int getPlayedBoardCount() {
        return playedBoardCount;
    }
}

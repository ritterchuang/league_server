package org.lsj.gs.mathStr.config.entity;

// 模擬情境設定
public class ScenarioHlrConfig {
    private final int simulationBoard; // 總模擬場次

    public ScenarioHlrConfig(int simulationBoard){ this.simulationBoard = simulationBoard;}

    public int getSimulationBoard() {
        return simulationBoard;
    }
}

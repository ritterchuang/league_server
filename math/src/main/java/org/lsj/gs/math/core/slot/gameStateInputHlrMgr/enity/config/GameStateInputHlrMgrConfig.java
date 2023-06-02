package org.lsj.gs.math.core.slot.gameStateInputHlrMgr.enity.config;

import java.util.Map;

// 遊戲輸入處理者管理器設定
public class GameStateInputHlrMgrConfig {
    private Map<Integer, GameStateInputConfig> conditionIdToGameStateInputConfigMap; // 遊戲輸入處理者設定對應表

    public GameStateInputHlrMgrConfig(Map<Integer, GameStateInputConfig> conditionIdToGameStateInputConfigMap) {
        this.conditionIdToGameStateInputConfigMap = conditionIdToGameStateInputConfigMap;
    }

    public Map<Integer, GameStateInputConfig> getConditionIdToGameStateInputConfigMap() {
        return conditionIdToGameStateInputConfigMap;
    }
}

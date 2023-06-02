package org.lsj.gs.math.core.slot.gameCtrMgr.enity.config;

import java.util.Map;

// 遊戲算分管理器設定
public class GameCtrMgrConfig {
    private final Map<Integer, GameCtrConfig> conditionIdToGameCtrConfigMap; // 遊戲ID、算分設定對應表

    public GameCtrMgrConfig(Map<Integer, GameCtrConfig> conditionIdToGameCtrConfigMap) {
        this.conditionIdToGameCtrConfigMap = conditionIdToGameCtrConfigMap;
    }

    public Map<Integer, GameCtrConfig> getConditionIdToGameCtrConfigMap() {
        return conditionIdToGameCtrConfigMap;
    }
}

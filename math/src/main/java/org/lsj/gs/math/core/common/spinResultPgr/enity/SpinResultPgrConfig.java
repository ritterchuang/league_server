package org.lsj.gs.math.core.common.spinResultPgr.enity;

import org.lsj.gs.math.core.common.spinResultPgr.enity.pgrConfig.ClientGameStateResultPgrConfig;

import java.util.Map;

// 押注結果包裝者設定
public class SpinResultPgrConfig {
    private final Map<Integer, ClientGameStateResultPgrConfig> conditionIdToGameStateResultPgrConfigMap; // <狀態ID, 遊戲狀態結果包裝者設定> 對應表

    public SpinResultPgrConfig(Map<Integer, ClientGameStateResultPgrConfig> conditionIdToGameStateResultPgrConfigMap) {
        this.conditionIdToGameStateResultPgrConfigMap = conditionIdToGameStateResultPgrConfigMap;
    }

    public Map<Integer, ClientGameStateResultPgrConfig> getConditionIdToGameStateResultPgrConfigMap() {
        return conditionIdToGameStateResultPgrConfigMap;
    }
}

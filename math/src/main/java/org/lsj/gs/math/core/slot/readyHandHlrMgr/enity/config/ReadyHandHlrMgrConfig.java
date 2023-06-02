package org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.config;

import java.util.Map;

// 聽牌處理器設定
public class ReadyHandHlrMgrConfig {
    private final Map<Integer, ReadyHandHlrConfigCluster> conditionIdToReadyHandHlrConfigClusterMap; // <條件ID, 聽牌處理設定集合> 對應表

    public ReadyHandHlrMgrConfig(Map<Integer, ReadyHandHlrConfigCluster> conditionIdToReadyHandHlrConfigClusterMap) {
        this.conditionIdToReadyHandHlrConfigClusterMap = conditionIdToReadyHandHlrConfigClusterMap;
    }

    public Map<Integer, ReadyHandHlrConfigCluster> getConditionIdToReadyHandHlrConfigClusterMap() {
        return conditionIdToReadyHandHlrConfigClusterMap;
    }
}

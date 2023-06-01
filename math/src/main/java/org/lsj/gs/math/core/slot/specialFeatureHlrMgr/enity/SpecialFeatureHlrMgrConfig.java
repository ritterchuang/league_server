package org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity;

import java.util.Map;

// 特殊事件處理者管理器設定
public class SpecialFeatureHlrMgrConfig {
    private final Map<Integer, SpecialFeatureHlrConfigCluster> conditionIdToSpecialFeatureHlrConfigClusterMap; // <條件ID, 特殊事件處理設定集合> 對應表

    public SpecialFeatureHlrMgrConfig(Map<Integer, SpecialFeatureHlrConfigCluster> conditionIdToSpecialFeatureHlrConfigClusterMap) {
        this.conditionIdToSpecialFeatureHlrConfigClusterMap = conditionIdToSpecialFeatureHlrConfigClusterMap;
    }

    public Map<Integer, SpecialFeatureHlrConfigCluster> getConditionIdToSpecialFeatureHlrConfigClusterMap() {
        return conditionIdToSpecialFeatureHlrConfigClusterMap;
    }
}

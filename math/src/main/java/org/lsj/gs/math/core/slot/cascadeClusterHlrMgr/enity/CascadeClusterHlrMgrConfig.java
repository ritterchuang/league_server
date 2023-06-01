package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.enity;

import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.config.CascadeClusterHlrConfig;

import java.util.Map;

// 消除集合處理者管理器設定
public class CascadeClusterHlrMgrConfig {
    private final Map<Integer, CascadeClusterHlrConfig> conditionIdToCascadeClusterHlrConfigMap; // 遊戲ID、消除集合處理者設定

    public CascadeClusterHlrMgrConfig(Map<Integer, CascadeClusterHlrConfig> conditionIdToCascadeClusterHlrConfigMap) {
        this.conditionIdToCascadeClusterHlrConfigMap = conditionIdToCascadeClusterHlrConfigMap;
    }

    public Map<Integer, CascadeClusterHlrConfig> getConditionIdToCascadeClusterHlrConfigMap() {
        return conditionIdToCascadeClusterHlrConfigMap;
    }
}

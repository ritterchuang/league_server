package org.lsj.gs.math.core.slot.progressHlrMgr.enity.config;

import java.util.Map;

// 遊戲進度處理器管理者設定
public class ProgressHlrMgrConfig {
    private final Map<Integer, ProgressConfig> conditionIdToProgressConfigMap; // 遊戲進度設定對應表

    public ProgressHlrMgrConfig(Map<Integer, ProgressConfig> conditionIdToProgressConfigMap) {
        this.conditionIdToProgressConfigMap = conditionIdToProgressConfigMap;
    }

    public Map<Integer, ProgressConfig> getConditionIdToProgressConfigMap() {
        return conditionIdToProgressConfigMap;
    }
}

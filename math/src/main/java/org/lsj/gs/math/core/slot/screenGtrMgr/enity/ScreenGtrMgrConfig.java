package org.lsj.gs.math.core.slot.screenGtrMgr.enity;

import java.util.Map;

// 遊戲畫面生產者管理器設定
public class ScreenGtrMgrConfig {
    private final Map<Integer, ScreenGtrConfig> conditionIdToScreenGtrConfigMap; // 遊戲ID、畫面生產器設定對應表

    public ScreenGtrMgrConfig(Map<Integer, ScreenGtrConfig> conditionIdToScreenGtrConfigMap) {
        this.conditionIdToScreenGtrConfigMap = conditionIdToScreenGtrConfigMap;
    }

    public Map<Integer, ScreenGtrConfig> getConditionIdToScreenGtrConfigMap() {
        return conditionIdToScreenGtrConfigMap;
    }
}

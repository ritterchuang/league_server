package org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity;

import java.util.Map;

// 虎機詳細記錄處理者設定
public class SlotDetailHlrMgrConfig {
    private final Map<Integer, SlotDetailHlrConfig> conditionIdToSlotDetailCtrConfigMap; // <條件ID, 虎機詳細記錄計算者設定> 對應表

    public SlotDetailHlrMgrConfig(Map<Integer, SlotDetailHlrConfig> conditionIdToSlotDetailCtrConfigMap) {
        this.conditionIdToSlotDetailCtrConfigMap = conditionIdToSlotDetailCtrConfigMap;
    }

    public Map<Integer, SlotDetailHlrConfig> getConditionIdToSlotDetailCtrConfigMap() {
        return conditionIdToSlotDetailCtrConfigMap;
    }
}

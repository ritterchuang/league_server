package org.lsj.gs.math.core.slot.progressHlrMgr.enity.config;

import org.lsj.gs.math.core.slot.ConstMathSlot;

import java.util.Map;

// 遊戲進度額外設定觸發局
public class ProgressConfigExtendTriggerRound extends ProgressConfigExtend{
    private final Map<ConstMathSlot.TriggerEvent, Integer> eventToDefaultRoundMap; // 初始場次對應表
    private final  Map<ConstMathSlot.TriggerEvent, Integer> eventToAddRoundMap; // 增加場次對應表
    private final int maxRound; // 最大場次

    public ProgressConfigExtendTriggerRound(
            Map<ConstMathSlot.TriggerEvent, Integer> eventToDefaultRoundMap,
            Map<ConstMathSlot.TriggerEvent, Integer> eventToAddRoundMap,
            int maxRound) {
        this.eventToDefaultRoundMap = eventToDefaultRoundMap;
        this.eventToAddRoundMap = eventToAddRoundMap;
        this.maxRound = maxRound;
    }

    public Map<ConstMathSlot.TriggerEvent, Integer> getEventToDefaultRoundMap() {
        return eventToDefaultRoundMap;
    }

    public Map<ConstMathSlot.TriggerEvent, Integer> getEventToAddRoundMap() {
        return eventToAddRoundMap;
    }

    public int getMaxRound() {
        return maxRound;
    }
}

package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.specialFeatureConfig;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 特殊事件設定
public class SpecialFeatureConfig {
    private final ConstMathSlot.SpecialFeatureType specialFeatureType; // 特殊事件觸發類型
    private final ConstMathSlot.TriggerEvent triggerEvent; // 特殊事件 pattern
    private final int basePay; // 基本倍數

    public SpecialFeatureConfig(ConstMathSlot.SpecialFeatureType specialFeatureType, ConstMathSlot.TriggerEvent triggerEvent, int basePay) {
        this.specialFeatureType = specialFeatureType;
        this.triggerEvent = triggerEvent;
        this.basePay = basePay;
    }

    public ConstMathSlot.SpecialFeatureType getSpecialFeatureType() {
        return specialFeatureType;
    }

    public ConstMathSlot.TriggerEvent getTriggerEvent() {
        return triggerEvent;
    }

    public int getBasePay() {
        return basePay;
    }
}

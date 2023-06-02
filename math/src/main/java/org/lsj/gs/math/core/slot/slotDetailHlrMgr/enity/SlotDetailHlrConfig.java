package org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 虎機詳細記錄計算者設定
public class SlotDetailHlrConfig {
    private final ConstMathSlot.SlotDetailType slotDetailType; // 虎機詳細記錄類型
    private final SlotDetailHlrConfigExtend slotDetailHlrConfigExtend; // 虎機詳細記錄計算者額外設定

    public SlotDetailHlrConfig(ConstMathSlot.SlotDetailType slotDetailType, SlotDetailHlrConfigExtend slotDetailHlrConfigExtend) {
        this.slotDetailType = slotDetailType;
        this.slotDetailHlrConfigExtend = slotDetailHlrConfigExtend;
    }

    public ConstMathSlot.SlotDetailType getSlotDetailType() {
        return slotDetailType;
    }

    public SlotDetailHlrConfigExtend getSlotDetailHlrConfigExtend() {
        return slotDetailHlrConfigExtend;
    }
}

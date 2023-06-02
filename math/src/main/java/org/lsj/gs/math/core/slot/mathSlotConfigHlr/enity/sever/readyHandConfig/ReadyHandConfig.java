package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.readyHandConfig;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 聽牌設定
public class ReadyHandConfig {
    private ConstMathSlot.ReadyHandType readyHandType; // 聽牌類型
    private ConstMathSlot.ReadyHandLimitType readyHandLimitType; // 聽牌限制類型

    public ReadyHandConfig(ConstMathSlot.ReadyHandType readyHandType, ConstMathSlot.ReadyHandLimitType readyHandLimitType) {
        this.readyHandType = readyHandType;
        this.readyHandLimitType = readyHandLimitType;
    }

    public ConstMathSlot.ReadyHandType getReadyHandType() {
        return readyHandType;
    }

    public ConstMathSlot.ReadyHandLimitType getReadyHandLimitType() {
        return readyHandLimitType;
    }
}

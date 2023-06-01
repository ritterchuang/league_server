package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.dampConfig;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 破框設定
public class DampConfig {
    private final ConstMathSlot.DampType upperDampType; // 上破框類型
    private final ConstMathSlot.DampType lowerDampType; // 下破框類型

    public DampConfig(ConstMathSlot.DampType upperDampType, ConstMathSlot.DampType lowerDampType) {
        this.upperDampType = upperDampType;
        this.lowerDampType = lowerDampType;
    }

    public ConstMathSlot.DampType getUpperDampType() {
        return upperDampType;
    }

    public ConstMathSlot.DampType getLowerDampType() {
        return lowerDampType;
    }
}

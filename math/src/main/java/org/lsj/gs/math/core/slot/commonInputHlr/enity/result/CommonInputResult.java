package org.lsj.gs.math.core.slot.commonInputHlr.enity.result;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 共用資料結果 TODO 用到再檢查正確性
public class CommonInputResult {
    private final ConstMathSlot.CommonInputType commonInputType; // 通用輸入類型
    private final CommonInputResultExtend commonInputResultExtend; // 通用輸入結果

    public CommonInputResult(ConstMathSlot.CommonInputType commonInputType, CommonInputResultExtend commonInputResultExtend) {
        this.commonInputType = commonInputType;
        this.commonInputResultExtend = commonInputResultExtend;
    }

    public CommonInputResult() {
        this.commonInputType = ConstMathSlot.CommonInputType.DEFAULT;
        this.commonInputResultExtend = new CommonInputResultExtendDefault();
    }

    public ConstMathSlot.CommonInputType getCommonInputType() {
        return commonInputType;
    }

    public CommonInputResultExtend getCommonInputResultExtend() {
        return commonInputResultExtend;
    }
}

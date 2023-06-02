package org.lsj.gs.math.core.slot.commonInputHlr.enity.config;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 通用輸入設定
public class CommonInputConfig {
    private final ConstMathSlot.CommonInputType commonInputType; // 通用輸入類型
    private final CommonInputConfigExtend configExtend; // 通用輸入額外設定

    public CommonInputConfig(ConstMathSlot.CommonInputType commonInputType, CommonInputConfigExtend configExtend) {
        this.commonInputType = commonInputType;
        this.configExtend = configExtend;
    }

    public ConstMathSlot.CommonInputType getCommonInputType() {
        return commonInputType;
    }

    public CommonInputConfigExtend getConfigExtend() {
        return configExtend;
    }
}

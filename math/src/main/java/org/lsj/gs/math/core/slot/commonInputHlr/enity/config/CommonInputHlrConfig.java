package org.lsj.gs.math.core.slot.commonInputHlr.enity.config;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 通用輸入處理者設定
public class CommonInputHlrConfig {
    private final ConstMathSlot.CommonInputType commonInputType; // 共同輸入類型
    private final CommonInputConfigExtend commonInputConfigExtend; // 共同輸入額外設定

    public CommonInputHlrConfig(CommonInputConfig commonInputConfig) {
        this.commonInputType = commonInputConfig.getCommonInputType();
        this.commonInputConfigExtend = commonInputConfig.getConfigExtend();
    }

    public ConstMathSlot.CommonInputType getCommonInputType() {
        return commonInputType;
    }

    public CommonInputConfigExtend getCommonInputConfigExtend() {
        return commonInputConfigExtend;
    }
}

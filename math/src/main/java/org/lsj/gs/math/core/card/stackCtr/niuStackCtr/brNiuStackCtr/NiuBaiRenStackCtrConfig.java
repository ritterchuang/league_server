package org.lsj.gs.math.core.card.stackCtr.niuStackCtr.brNiuStackCtr;

import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;

import java.util.Map;

// 牛型計算器設定
public class NiuBaiRenStackCtrConfig {
    private final Map<ConstNiu.NiuTypeEnumCommon, Integer> niuTypeToRateConfig; // 牛型設定

    public NiuBaiRenStackCtrConfig(Map<ConstNiu.NiuTypeEnumCommon, Integer> niuTypeToRateConfig) {
        this.niuTypeToRateConfig = niuTypeToRateConfig;
    }

    public Map<ConstNiu.NiuTypeEnumCommon, Integer> getNiuTypeToRateConfig() {
        return niuTypeToRateConfig;
    }
}

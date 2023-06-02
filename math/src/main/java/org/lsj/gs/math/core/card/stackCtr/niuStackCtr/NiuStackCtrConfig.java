package org.lsj.gs.math.core.card.stackCtr.niuStackCtr;

import java.util.Map;

// 牛型計算器設定
public class NiuStackCtrConfig {
    private final int maxUser; // 最大玩家數
    private final Map<ConstNiu.NiuTypeEnumCommon, Integer> niuTypeRateConfig; // 牛型設定

    public NiuStackCtrConfig(int maxUser, Map<ConstNiu.NiuTypeEnumCommon, Integer> niuTypeRateConfig) {
        this.maxUser = maxUser;
        this.niuTypeRateConfig = niuTypeRateConfig;
    }

    public int getMaxUser() {
        return maxUser;
    }

    public Map<ConstNiu.NiuTypeEnumCommon, Integer> getNiuTypeRateConfig() {
        return niuTypeRateConfig;
    }
}

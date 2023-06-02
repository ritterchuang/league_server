package org.lsj.gs.math.core.card.stackCtr.niuStackCtr;

import java.util.Map;

// 牛型計算器遊戲設定
public class NiuStackCtrGameConfig {
    private final Map<ConstNiu.NiuTypeEnumCommon, Integer> niuTypeRateConfig; // 牛型設定 <牛型識別碼, 牛型倍數>

    public NiuStackCtrGameConfig(Map<ConstNiu.NiuTypeEnumCommon, Integer> niuTypeRateConfig) {
        this.niuTypeRateConfig = niuTypeRateConfig;
    }

    public Map<ConstNiu.NiuTypeEnumCommon, Integer> getNiuTypeRateConfig() {
        return niuTypeRateConfig;
    }
}

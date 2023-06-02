package org.lsj.gs.math.core.card.resultCtr.niuResultCtr;

import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;

import java.util.Map;

// 抽象牛牛結果計算器遊戲設定
public abstract class AbstractNiuResultCtrGameConfig {
    private final Map<ConstNiu.NiuTypeEnumCommon, Integer> niuTypeRateConfig; // 牛型賠率設定

    public AbstractNiuResultCtrGameConfig(Map<ConstNiu.NiuTypeEnumCommon, Integer> niuTypeRateConfig) {
        this.niuTypeRateConfig = niuTypeRateConfig;
    }

    public Map<ConstNiu.NiuTypeEnumCommon, Integer> getNiuTypeRateConfig() {
        return niuTypeRateConfig;
    }
}

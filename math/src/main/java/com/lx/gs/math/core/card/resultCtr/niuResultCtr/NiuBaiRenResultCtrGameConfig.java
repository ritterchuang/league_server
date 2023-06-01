package com.lx.gs.math.core.card.resultCtr.niuResultCtr;

import com.lx.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;

import java.util.Map;

// 百人牛牛結果計算器遊戲設定
public class NiuBaiRenResultCtrGameConfig extends AbstractNiuResultCtrGameConfig{

    public NiuBaiRenResultCtrGameConfig(Map<ConstNiu.NiuTypeEnumCommon, Integer> niuTypeRateConfig) {
        super(niuTypeRateConfig);
    }
}

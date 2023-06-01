package org.lsj.gs.math.core.card.resultCtr.niuResultCtr;

import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;

import java.util.Map;

// 通比牛牛結果計算器遊戲設定
public class NiuTonbiResultCtrGameConfig extends AbstractNiuResultCtrGameConfig{

    public NiuTonbiResultCtrGameConfig(Map<ConstNiu.NiuTypeEnumCommon, Integer> niuTypeRateConfig) {
        super(niuTypeRateConfig);
    }
}

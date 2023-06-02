package org.lsj.gs.math.core.card.resultCtr.niuResultCtr;

import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;

import java.util.Map;

// 搶莊牛牛結果計算器遊戲設定
public class NiuBankerResultCtrGameConfig extends AbstractNiuResultCtrGameConfig{

    public NiuBankerResultCtrGameConfig(Map<ConstNiu.NiuTypeEnumCommon, Integer> niuTypeRateConfig) {
        super(niuTypeRateConfig);
    }
}

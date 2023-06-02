package org.lsj.gs.math.core.card.resultCtr.niuResultCtr;

import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;

import java.util.Map;

// 搶莊牛牛結果計算器設定
public class NiuBankerResultCtrConfig extends AbstractNiuResultCtrConfig{

    public NiuBankerResultCtrConfig(int maxUser, double baseScore, int feeType, double gameRate, Map<ConstNiu.NiuTypeEnumCommon, Integer> niuTypeRateConfig) {
        super(maxUser, baseScore, feeType, gameRate, niuTypeRateConfig);
    }
}

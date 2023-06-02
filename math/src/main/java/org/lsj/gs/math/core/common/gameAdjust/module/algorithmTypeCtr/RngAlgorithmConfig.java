package org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr;

import org.lsj.gs.math.core.common.ConstMathCommon;

// 亂數產生器演算法設定
public class RngAlgorithmConfig {
    private final ConstMathCommon.PoolControlType poolControlType; // 水池控制類型
    private final ConstMathCommon.DealType dealType; // 開牌類型
    private final ConstMathCommon.ShuffleType shuffleType; // 發牌類型
    private final ConstMathCommon.PersonAdjustType personAdjustType; // 個人控類型

    public RngAlgorithmConfig(ConstMathCommon.PoolControlType poolControlType, ConstMathCommon.DealType dealType, ConstMathCommon.ShuffleType shuffleType, ConstMathCommon.PersonAdjustType personAdjustType) {
        this.poolControlType = poolControlType;
        this.dealType = dealType;
        this.shuffleType = shuffleType;
        this.personAdjustType = personAdjustType;
    }

    public ConstMathCommon.PoolControlType getPoolControlType() {
        return poolControlType;
    }

    public ConstMathCommon.DealType getDealType() {
        return dealType;
    }

    public ConstMathCommon.ShuffleType getShuffleType() {
        return shuffleType;
    }

    public ConstMathCommon.PersonAdjustType getPersonAdjustType() {
        return personAdjustType;
    }
}

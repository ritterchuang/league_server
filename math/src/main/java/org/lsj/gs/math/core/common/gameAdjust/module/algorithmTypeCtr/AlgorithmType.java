package org.lsj.gs.math.core.common.gameAdjust.module.algorithmTypeCtr;

import org.lsj.gs.math.core.common.ConstMathCommon;

// 演算法類型
public class AlgorithmType {
    private final ConstMathCommon.PersonAdjustType personAdjustType; // 個人控類型
    private final ConstMathCommon.PoolControlType poolControlType; // 水池控制類型
    private final ConstMathCommon.DealType dealType; // 開牌類型
    private final ConstMathCommon.ShuffleType shuffleType; // 發牌類型

    public AlgorithmType(ConstMathCommon.PersonAdjustType personAdjustType, ConstMathCommon.PoolControlType poolControlType, ConstMathCommon.DealType dealType, ConstMathCommon.ShuffleType shuffleType) {
        this.personAdjustType = personAdjustType;
        this.poolControlType = poolControlType;
        this.dealType = dealType;
        this.shuffleType = shuffleType;
    }

    public ConstMathCommon.PersonAdjustType getPersonAdjustType() {
        return personAdjustType;
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
}

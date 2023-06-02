package org.lsj.gs.math.core.common.gameAdjust.entity;

import org.lsj.gs.math.core.common.ConstMathCommon;

// 遊戲調控設定
public class GameAdjustConfig {
    private final ConstMathCommon.PoolControlType poolControlType; // 水池控制類型
    private final ConstMathCommon.DealType dealType; // 開牌類型
    private final ConstMathCommon.ShuffleType shuffleType; // 發牌類型

    public GameAdjustConfig(ConstMathCommon.PoolControlType poolControlType, ConstMathCommon.DealType dealType, ConstMathCommon.ShuffleType shuffleType) {
        this.poolControlType = poolControlType;
        this.dealType = dealType;
        this.shuffleType = shuffleType;
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

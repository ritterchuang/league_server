package com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.readyHandResultPgr;

import com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.readyHandResultPgr.enity.ReadyHandResultExtend;
import com.lx.gs.math.core.slot.ConstMathSlot;

// 聽牌結果
public class ReadyHandResult {
    private final ConstMathSlot.ReadyHandType readyHandType; // 聽牌類型
    private final ReadyHandResultExtend readyHandResultExtend; // 聽牌額外結果

    public ReadyHandResult(ConstMathSlot.ReadyHandType readyHandType, ReadyHandResultExtend readyHandResultExtend) {
        this.readyHandType = readyHandType;
        this.readyHandResultExtend = readyHandResultExtend;
    }

    public ConstMathSlot.ReadyHandType getReadyHandType() {
        return readyHandType;
    }

    public ReadyHandResultExtend getReadyHandResultExtend() {
        return readyHandResultExtend;
    }
}

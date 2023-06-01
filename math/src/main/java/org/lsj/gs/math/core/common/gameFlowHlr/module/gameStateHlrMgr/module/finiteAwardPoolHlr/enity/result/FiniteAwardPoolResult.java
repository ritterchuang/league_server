package org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.result;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 有限表演結果
public class FiniteAwardPoolResult {
    private final ConstMathSlot.FiniteAwardPoolType finiteAwardPoolType; // 有限表演類型
    private final FiniteAwardPoolResultExtend finiteAwardPoolResultExtend; // 有限表演額外結果

    public FiniteAwardPoolResult(ConstMathSlot.FiniteAwardPoolType finiteAwardPoolType, FiniteAwardPoolResultExtend finiteAwardPoolResultExtend) {
        this.finiteAwardPoolType = finiteAwardPoolType;
        this.finiteAwardPoolResultExtend = finiteAwardPoolResultExtend;
    }

    public ConstMathSlot.FiniteAwardPoolType getFiniteAwardPoolType() {
        return finiteAwardPoolType;
    }

    public FiniteAwardPoolResultExtend getFiniteAwardPoolResultExtend() {
        return finiteAwardPoolResultExtend;
    }
}

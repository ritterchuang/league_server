package com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr;

import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity.result.FiniteAwardPoolResult;
import com.lx.gs.math.core.slot.ConstMathSlot;

// 倍數表演處理者
public interface IFiniteAwardPoolHlr {

    // 計算倍數表演結果
    FiniteAwardPoolResult calculateFiniteAwardResult(ConstMathSlot.ReelRtpType reelRtpType);
}

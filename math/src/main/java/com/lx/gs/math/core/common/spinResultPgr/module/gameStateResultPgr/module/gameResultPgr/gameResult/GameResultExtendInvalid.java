package com.lx.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr.gameResult;

import com.lx.gs.math.core.slot.ConstMathSlot;

// 客端客製算分額外結果非法
public class GameResultExtendInvalid extends GameResultExtend {

    public GameResultExtendInvalid() {
        super(ConstMathSlot.GameHitDirectionType.INVALID);
    }
}

package com.lx.gs.math.core.common.gameAdjust.module.poolControlHlr;

import com.lx.gs.math.core.common.gameAdjust.entity.GameAdjustResult;
import com.lx.gs.math.core.common.gameAdjust.module.dealCtr.IDealCtr;
import com.lx.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import com.lx.gs.math.core.common.poolCtr.module.PoolCtr;

// 不使用水池控制處理器
public class PoolControlHlrNone extends AbstractPoolControlHlr {
    public PoolControlHlrNone(PoolCtr poolCtr) {
        super(poolCtr);
    }

    // 控制
    @Override
    public void control(IDealCtr dealCtr, GameAdjustParameter gameAdjustParameter) {
        // 1. 開牌
        GameAdjustResult gameAdjustResult = dealCtr.deal(gameAdjustParameter);

        // 2. 應用
        dealCtr.applyPreGameResult(gameAdjustResult.getPreGameResult());
    }
}

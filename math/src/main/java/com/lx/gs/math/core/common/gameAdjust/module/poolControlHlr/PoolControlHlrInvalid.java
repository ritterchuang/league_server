package com.lx.gs.math.core.common.gameAdjust.module.poolControlHlr;

import com.lx.gs.math.core.common.gameAdjust.entity.GameAdjustResult;
import com.lx.gs.math.core.common.gameAdjust.module.dealCtr.IDealCtr;
import com.lx.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import com.lx.gs.math.core.common.poolCtr.module.PoolCtr;

// 無效水池控制處理器
public class PoolControlHlrInvalid extends AbstractPoolControlHlr {
    public PoolControlHlrInvalid(PoolCtr poolCtr) {
        super(poolCtr);
    }

    // 控制 防呆為不控制
    @Override
    public void control(IDealCtr dealCtr, GameAdjustParameter gameAdjustParameter) {
        // 1. 開牌
        GameAdjustResult gameAdjustResult = dealCtr.deal(gameAdjustParameter);

        // 2. 應用
        dealCtr.applyPreGameResult(gameAdjustResult.getPreGameResult());
    }
}

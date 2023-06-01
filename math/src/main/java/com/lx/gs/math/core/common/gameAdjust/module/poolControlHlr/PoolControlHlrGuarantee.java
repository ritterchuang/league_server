package com.lx.gs.math.core.common.gameAdjust.module.poolControlHlr;

import com.lx.gs.math.core.common.gameAdjust.module.dealCtr.IDealCtr;
import com.lx.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import com.lx.gs.math.core.common.poolCtr.module.PoolCtr;

// 保證水池控制處理器
public class PoolControlHlrGuarantee extends AbstractPoolControlHlr {
    public PoolControlHlrGuarantee(PoolCtr poolCtr) {
        super(poolCtr);
    }

    // 控制
    @Override
    public void control(IDealCtr dealCtr, GameAdjustParameter gameAdjustParameter) {
        super.controlGuarantee(dealCtr, gameAdjustParameter);
    }
}

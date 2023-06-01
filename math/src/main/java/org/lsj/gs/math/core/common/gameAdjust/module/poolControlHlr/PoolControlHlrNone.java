package org.lsj.gs.math.core.common.gameAdjust.module.poolControlHlr;

import org.lsj.gs.math.core.common.gameAdjust.entity.GameAdjustResult;
import org.lsj.gs.math.core.common.gameAdjust.module.dealCtr.IDealCtr;
import org.lsj.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;

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

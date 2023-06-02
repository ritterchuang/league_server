package org.lsj.gs.math.core.common.gameAdjust.module.poolControlHlr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.module.dealCtr.IDealCtr;
import org.lsj.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;

// 個人控優先保證水池控制處理器
public class PoolControlHlrPersonAdjustPreferGuarantee extends AbstractPoolControlHlr {
    public PoolControlHlrPersonAdjustPreferGuarantee(PoolCtr poolCtr) {
        super(poolCtr);
    }

    @Override
    public void control(IDealCtr dealCtr, GameAdjustParameter gameAdjustParameter) {
        // 1. 個人控優先控保底
        if (!gameAdjustParameter.getPersonAdjustType().equals(ConstMathCommon.PersonAdjustType.NORMAL)) {
            super.controlGuaranteeBottom(dealCtr, gameAdjustParameter);
            return;
        }

        // 2. 保證
        super.controlGuarantee(dealCtr, gameAdjustParameter);
    }
}

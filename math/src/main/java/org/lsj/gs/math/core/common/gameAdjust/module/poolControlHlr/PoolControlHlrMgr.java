package org.lsj.gs.math.core.common.gameAdjust.module.poolControlHlr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;

// 水池控制處理器管理者
public class PoolControlHlrMgr {
    private final PoolCtr poolCtr; // 水池控制器

    public PoolControlHlrMgr(PoolCtr poolCtr) {
        this.poolCtr = poolCtr;
    }

    // 建立水池控制處理器
    public AbstractPoolControlHlr createPoolControlHlr(ConstMathCommon.PoolControlType poolControlType) {
        switch(poolControlType){
            case NONE: return new PoolControlHlrNone(this.poolCtr);
            case GUARANTEE: return new PoolControlHlrGuarantee(this.poolCtr);
            case PERSON_ADJUST_PREFER_GUARANTEE: return new PoolControlHlrPersonAdjustPreferGuarantee(this.poolCtr);
            default: return new PoolControlHlrInvalid(this.poolCtr);
        }
    }

    // 重設
    public void reset() {}
}

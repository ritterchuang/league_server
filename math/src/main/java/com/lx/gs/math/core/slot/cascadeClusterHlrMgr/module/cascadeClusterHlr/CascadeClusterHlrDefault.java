package com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr;

import com.lx.gs.math.core.slot.ConstMathSlot;
import com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.result.CascadeClusterHlrResult;
import com.lx.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;

// 消除集合處理者 預設
public class CascadeClusterHlrDefault implements ICascadeClusterHlr{

    @Override
    public CascadeClusterHlrResult calculateCascadeClusterHlrResult(SpinRequest spinRequest, ConstMathSlot.ReelRtpType reelRtpType) {
        return null;
    }
}

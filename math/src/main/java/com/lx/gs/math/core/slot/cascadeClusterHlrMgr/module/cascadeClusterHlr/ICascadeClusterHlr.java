package com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr;

import com.lx.gs.math.core.slot.ConstMathSlot;
import com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.result.CascadeClusterHlrResult;
import com.lx.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;

// 消除集合處理者介面
public interface ICascadeClusterHlr {

    CascadeClusterHlrResult calculateCascadeClusterHlrResult(SpinRequest spinRequest, ConstMathSlot.ReelRtpType reelRtpType); // 計算消除集合結果
}

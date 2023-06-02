package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.result.CascadeClusterHlrResult;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;

// 消除集合處理者介面
public interface ICascadeClusterHlr {

    CascadeClusterHlrResult calculateCascadeClusterHlrResult(SpinRequest spinRequest, ConstMathSlot.ReelRtpType reelRtpType); // 計算消除集合結果
}

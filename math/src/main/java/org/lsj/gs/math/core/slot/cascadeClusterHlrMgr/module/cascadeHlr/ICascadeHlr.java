package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;

import java.util.List;

// 消除處理者介面
public interface ICascadeHlr {

    List<CascadeHlrResult> calculateCascadeHlrResultList(SpinRequest spinRequest, ConstMathSlot.ReelRtpType reelRtpType); // 計算消除處理者結果列表
}

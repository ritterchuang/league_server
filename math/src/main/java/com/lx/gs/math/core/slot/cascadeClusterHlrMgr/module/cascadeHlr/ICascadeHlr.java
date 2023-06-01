package com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr;

import com.lx.gs.math.core.slot.ConstMathSlot;
import com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import com.lx.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;

import java.util.List;

// 消除處理者介面
public interface ICascadeHlr {

    List<CascadeHlrResult> calculateCascadeHlrResultList(SpinRequest spinRequest, ConstMathSlot.ReelRtpType reelRtpType); // 計算消除處理者結果列表
}

package com.lx.gs.math.core.slot.specialFeatureHlrMgr.module;

import com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import com.lx.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import com.lx.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import com.lx.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResult;

import java.util.List;
import java.util.Optional;

// 特殊事件處理者介面
public interface ISpecialFeatureHlr {

    Optional<SpecialFeatureHlrResult> calculateSpecialFeatureHlrResult(SpinRequest spinRequest, ScreenGtrResult screenGtrResult, List<CascadeHlrResult> cascadeHlrResultList);

}

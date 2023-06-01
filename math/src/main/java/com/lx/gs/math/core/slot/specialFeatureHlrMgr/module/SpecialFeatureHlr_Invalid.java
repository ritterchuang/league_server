package com.lx.gs.math.core.slot.specialFeatureHlrMgr.module;

import com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import com.lx.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import com.lx.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import com.lx.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResult;

import java.util.List;
import java.util.Optional;

// 預設特殊事件處理氣
public class SpecialFeatureHlr_Invalid implements ISpecialFeatureHlr{

    public SpecialFeatureHlr_Invalid() {
    }

    // 計算特殊事件結果
    @Override
    public Optional<SpecialFeatureHlrResult> calculateSpecialFeatureHlrResult(SpinRequest spinRequest, ScreenGtrResult screenGtrResult, List<CascadeHlrResult> cascadeHlrResultList) {
        return Optional.empty();
    }

}

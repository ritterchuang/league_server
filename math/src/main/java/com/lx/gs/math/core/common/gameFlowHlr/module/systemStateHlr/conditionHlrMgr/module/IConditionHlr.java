package com.lx.gs.math.core.common.gameFlowHlr.module.systemStateHlr.conditionHlrMgr.module;

import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import com.lx.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;

// 條件計算者介面
public interface IConditionHlr {

    // 檢查條件
    boolean checkCondition(SpinRequest spinRequest, GameStateHlrResult gameStateHlrResult);
}

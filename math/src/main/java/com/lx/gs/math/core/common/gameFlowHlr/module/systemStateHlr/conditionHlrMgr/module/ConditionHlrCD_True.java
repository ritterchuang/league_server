package com.lx.gs.math.core.common.gameFlowHlr.module.systemStateHlr.conditionHlrMgr.module;

import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import com.lx.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;

// 條件計算者True
public class ConditionHlrCD_True implements IConditionHlr {

    @Override
    public boolean checkCondition(SpinRequest spinRequest, GameStateHlrResult gameStateHlrResult) {
        return true;
    }
}

package com.lx.gs.math.core.common.gameFlowHlr.module.systemStateHlr.conditionHlrMgr.module;

import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import com.lx.gs.math.core.slot.ConstMathSlot;
import com.lx.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import com.lx.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResult;

import java.util.List;

// 條件計算者02 [最後一場結果滿足Trigger06]
public class ConditionHlrCD_02 implements IConditionHlr {
    private final List<ConstMathSlot.TriggerEvent> triggerEventList; // 觸發事件列表

    public ConditionHlrCD_02() {
        this.triggerEventList = List.of(
                ConstMathSlot.TriggerEvent.TRIGGER_06
               );
    }

    // 檢查條件
    @Override
    public boolean checkCondition(SpinRequest spinRequest, GameStateHlrResult gameStateHlrResult) {
        // 1. 判斷是否為空陣列
        if (gameStateHlrResult.getRoundHlrResultList().isEmpty()) {
            return false;
        }

        // 2. 取出特殊事件處理者列表
        List<SpecialFeatureHlrResult> specialFeatureHlrResultList = gameStateHlrResult.getRoundHlrResultList().get(gameStateHlrResult.getRoundHlrResultList().size() - 1).getSpecialFeatureHlrResultCluster().getSpecialFeatureHlrResultList();

        // 3. 檢查是否有滿足觸發事件列表
        return specialFeatureHlrResultList.stream().
                anyMatch(specialFeatureHlrResult -> this.triggerEventList.contains(specialFeatureHlrResult.getTriggerEvent()));
    }
}

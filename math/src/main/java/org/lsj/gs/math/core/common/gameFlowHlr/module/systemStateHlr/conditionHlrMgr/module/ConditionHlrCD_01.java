package org.lsj.gs.math.core.common.gameFlowHlr.module.systemStateHlr.conditionHlrMgr.module;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResult;

import java.util.List;

// 條件計算者01 [最後一場結果滿足 Trigger01 - Trigger05 其中一項]
public class ConditionHlrCD_01 implements IConditionHlr {
    private final List<ConstMathSlot.TriggerEvent> triggerEventList; // 觸發事件列表

    public ConditionHlrCD_01() {
        this.triggerEventList = List.of(
                ConstMathSlot.TriggerEvent.TRIGGER_01,
                ConstMathSlot.TriggerEvent.TRIGGER_02,
                ConstMathSlot.TriggerEvent.TRIGGER_03,
                ConstMathSlot.TriggerEvent.TRIGGER_04,
                ConstMathSlot.TriggerEvent.TRIGGER_05);
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

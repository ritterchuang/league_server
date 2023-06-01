package org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity;

import org.lsj.gs.math.core.slot.ConstMathSlot;

import java.util.List;

// 特殊事件處理者結果
public class SpecialFeatureHlrResult {
    private final ConstMathSlot.SpecialFeatureType specialFeatureType; // 特殊事件類型
    private final ConstMathSlot.TriggerEvent triggerEvent; // 觸發事件
    private final List<List<Boolean>> screenHitPosition; // 中獎畫面
    private final List<List<Boolean>> dampScreenHitPosition; // 破框中獎畫面
    private final double totalWin; // 特殊事件得分

    public SpecialFeatureHlrResult(ConstMathSlot.SpecialFeatureType specialFeatureType, ConstMathSlot.TriggerEvent triggerEvent, List<List<Boolean>> screenHitPosition, List<List<Boolean>> dampScreenHitPosition, double totalWin) {
        this.specialFeatureType = specialFeatureType;
        this.triggerEvent = triggerEvent;
        this.screenHitPosition = screenHitPosition;
        this.dampScreenHitPosition = dampScreenHitPosition;
        this.totalWin = totalWin;
    }

    public ConstMathSlot.SpecialFeatureType getSpecialFeatureType() {
        return specialFeatureType;
    }

    public ConstMathSlot.TriggerEvent getTriggerEvent() {
        return triggerEvent;
    }

    public List<List<Boolean>> getScreenHitPosition() {
        return screenHitPosition;
    }

    public List<List<Boolean>> getDampScreenHitPosition() {
        return dampScreenHitPosition;
    }

    public double getTotalWin() {
        return totalWin;
    }
}

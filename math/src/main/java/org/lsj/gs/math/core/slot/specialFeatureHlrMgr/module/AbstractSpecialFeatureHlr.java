package org.lsj.gs.math.core.slot.specialFeatureHlrMgr.module;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.commonUtil.DampScreenHitPositionUtil;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.DampCtrResult;

import java.util.List;

// 抽象特殊事件處理者
public abstract class AbstractSpecialFeatureHlr{
    protected List<ConstMathSlot.SymbolAttribute> targetSymbolAttributeList; // 目標標誌列表
    protected DampScreenHitPositionUtil dampScreenHitPositionUtil; // 破框畫面工具包

    public AbstractSpecialFeatureHlr(List<ConstMathSlot.SymbolAttribute> targetSymbolAttributeList) {
        this.targetSymbolAttributeList = targetSymbolAttributeList;
        this.dampScreenHitPositionUtil = new DampScreenHitPositionUtil();
    }

    // 是否為目標標誌
    protected boolean isTargetSymbolAttribute(ConstMathSlot.SymbolAttribute symbolAttribute) {
        return this.targetSymbolAttributeList.contains(symbolAttribute);
    }

    // 計算破框擊中畫面
    protected List<List<Boolean>> calculateDampScreenHitPosition(List<List<Boolean>> screenPosition, DampCtrResult dampCtrResult) {
        return this.dampScreenHitPositionUtil.calculateDampScreenHitPosition(screenPosition, dampCtrResult);
    }
}

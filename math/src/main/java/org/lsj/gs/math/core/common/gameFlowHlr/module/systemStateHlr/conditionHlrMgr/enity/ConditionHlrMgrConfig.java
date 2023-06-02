package org.lsj.gs.math.core.common.gameFlowHlr.module.systemStateHlr.conditionHlrMgr.enity;

import org.lsj.gs.math.core.slot.ConstMathSlot;

import java.util.List;

// 條件處理者設定
public class ConditionHlrMgrConfig {
    private final List<List<ConstMathSlot.Condition>> conditionList; // 狀態列表 [i][j] = 狀態 i 到狀態 j 所需要的條件 [從Initial 開始]

    public ConditionHlrMgrConfig(List<List<ConstMathSlot.Condition>> conditionList) {
        this.conditionList = conditionList;
    }

    public List<List<ConstMathSlot.Condition>> getConditionList() {
        return conditionList;
    }
}

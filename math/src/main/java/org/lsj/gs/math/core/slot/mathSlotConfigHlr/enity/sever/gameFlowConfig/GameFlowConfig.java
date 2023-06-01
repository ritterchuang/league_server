package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.gameFlowConfig;

import org.lsj.gs.math.core.slot.ConstMathSlot;

import java.util.List;

// 遊戲流程設定
public class GameFlowConfig {
    private final List<List<ConstMathSlot.Condition>> conditionList; // 狀態列表 [i][j] = 狀態 i 到狀態 j 所需要的條件 [從Initial 開始]

    public GameFlowConfig(List<List<ConstMathSlot.Condition>> conditionList) {
        this.conditionList = conditionList;
    }

    public List<List<ConstMathSlot.Condition>> getConditionList() {
        return conditionList;
    }
}

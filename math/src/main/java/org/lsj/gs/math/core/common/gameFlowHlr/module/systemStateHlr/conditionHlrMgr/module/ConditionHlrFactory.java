package org.lsj.gs.math.core.common.gameFlowHlr.module.systemStateHlr.conditionHlrMgr.module;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 條件計算者工廠
public class ConditionHlrFactory {

    // 創建條件處理者
    public IConditionHlr create(ConstMathSlot.Condition condition) {
        switch (condition) {
            case CD_TRUE: return new ConditionHlrCD_True();
            case CD_01: return new ConditionHlrCD_01();
            case CD_02: return new ConditionHlrCD_02();
            case CD_BASIC_BET: return new ConditionHlrCD_BASIC_BET();
            case CD_BUY_FEATURE_01: return new ConditionHlrCD_BUY_FEATURE_01();
            case CD_BUY_FEATURE_02: return new ConditionHlrCD_BUY_FEATURE_02();
            default: return new ConditionHlrCD_False();
        }
    }
}

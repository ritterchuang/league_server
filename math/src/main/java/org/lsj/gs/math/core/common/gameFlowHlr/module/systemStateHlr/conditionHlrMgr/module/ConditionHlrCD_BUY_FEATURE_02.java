package org.lsj.gs.math.core.common.gameFlowHlr.module.systemStateHlr.conditionHlrMgr.module;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;

// 條件計算者 BUY FEATURE 02 押注類型與玩法類型符合 BUY_FEATURE_02
public class ConditionHlrCD_BUY_FEATURE_02 implements IConditionHlr {
    private final ConstMathSlot.BetType betType = ConstMathSlot.BetType.NONE; // 押注類型
    private final ConstMathSlot.SpinType spinType = ConstMathSlot.SpinType.BUYFEATURE02; // 玩法類型

    public ConditionHlrCD_BUY_FEATURE_02() {

    }

    // 檢查條件
    @Override
    public boolean checkCondition(SpinRequest spinRequest, GameStateHlrResult gameStateHlrResult) {
        //todo 加測試
        // 1. 防呆如果不是空陣列則回傳false
        if (!gameStateHlrResult.getRoundHlrResultList().isEmpty()) {
            return false;
        }

        if(spinRequest.getBetType() != this.betType){
            return false;
        }

        if(spinRequest.getSpinType() != this.spinType){
            return false;
        }

        return true;
    }
}

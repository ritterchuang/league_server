package org.lsj.gs.math.core.common.gameAdjust.entity;

import org.lsj.gs.math.core.common.gameFlowHlr.enity.result.GameFlowHlrResult;

// 虎機預遊戲結果
public class PreGameAdjustResultSlot extends PreGameAdjustResult {
    private final GameFlowHlrResult gameFlowHlrResult; // 虎機流程結果

    public PreGameAdjustResultSlot(GameFlowHlrResult gameFlowHlrResult) {
        this.gameFlowHlrResult = gameFlowHlrResult;
    }

    public GameFlowHlrResult getGameFlowHlrResult() {
        return gameFlowHlrResult;
    }
}

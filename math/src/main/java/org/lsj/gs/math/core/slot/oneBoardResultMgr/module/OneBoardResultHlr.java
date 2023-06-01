package org.lsj.gs.math.core.slot.oneBoardResultMgr.module;

import org.lsj.gs.math.core.common.gameAdjust.entity.PreGameAdjustResult;
import org.lsj.gs.math.core.common.gameAdjust.entity.PreGameAdjustResultSlot;
import org.lsj.gs.math.core.common.gameFlowHlr.enity.result.GameFlowHlrResult;
import org.lsj.gs.math.core.common.table.entity.message.fish.GamePlayerResult;
import org.lsj.gs.math.core.common.table.entity.message.slot.ClientSpinRequest;
import org.lsj.gs.math.core.slot.oneBoardResultMgr.enity.OneBoardResult;

// 一局結果處理者
public class OneBoardResultHlr {
    private final OneBoardResult oneBoardResult; // 一局結果

    public OneBoardResultHlr() {
        this.oneBoardResult = new OneBoardResult();
    }

    // 清除記錄
    public void clear() {
        this.oneBoardResult.clear();
    }

    // 取得遊戲流程結果
    public GameFlowHlrResult getGameFlowHlrResult() {
        return ((PreGameAdjustResultSlot)this.getPreGameResult()).getGameFlowHlrResult();
    }

    public String getRoundId() {
        return this.oneBoardResult.getRoundId();
    }

    public void setRoundId(String roundId) {
        this.oneBoardResult.setRoundId(roundId);
    }

    public ClientSpinRequest getClientSpinRequest() {
        return this.oneBoardResult.getClientSpinRequest();
    }

    public void setClientSpinRequest(ClientSpinRequest clientSpinRequest) {
        this.oneBoardResult.setClientSpinRequest(clientSpinRequest);
    }

    public PreGameAdjustResult getPreGameResult() {
        return this.oneBoardResult.getPreGameResult();
    }

    public void setPreGameResult(PreGameAdjustResult preGameAdjustResult) {
        this.oneBoardResult.setPreGameResult(preGameAdjustResult);
    }

    public GamePlayerResult getAfterSpinBetGamePlayerResult() {
        return this.oneBoardResult.getAfterSpinBetGamePlayerResult();
    }

    public void setAfterSpinGamePlayerResult(GamePlayerResult afterSpinBetGamePlayerResult) {
        this.oneBoardResult.setAfterSpinBetGamePlayerResult(afterSpinBetGamePlayerResult);
    }
}

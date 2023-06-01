package org.lsj.gs.math.core.slot.oneBoardResultMgr.module;

import org.lsj.gs.math.core.common.gameAdjust.entity.PreGameAdjustResult;
import org.lsj.gs.math.core.common.gameAdjust.entity.PreGameAdjustResultSlot;
import org.lsj.gs.math.core.common.gameFlowHlr.enity.result.GameFlowHlrResult;
import org.lsj.gs.math.core.slot.oneBoardResultMgr.enity.LastPlayedProgressResult;
import org.lsj.gs.math.core.slot.oneBoardResultMgr.enity.SlotCutReturnProgressResult;

import java.util.Objects;

// 斷線重連處理者
public class SlotCutReturnHlr {
    private LastPlayedProgressResult lastPlayedProgressResult; // 上次撥放進度

    public SlotCutReturnHlr() {
        this.lastPlayedProgressResult = null;
    }

    // 檢查客端傳入參數
    public boolean isValidLastProgressResult(LastPlayedProgressResult lastPlayedProgressResult, PreGameAdjustResult preGameAdjustResult) {
        // 1. 若為空值，回傳非法
        if (Objects.isNull(lastPlayedProgressResult) || Objects.isNull(preGameAdjustResult)) {
            return false;
        }

        // 2. 傳入 stateIndex 錯誤，回傳非法
        if (lastPlayedProgressResult.getLastPlayedStateIndex() < 0
                || lastPlayedProgressResult.getLastPlayedStateIndex() >= ((PreGameAdjustResultSlot) preGameAdjustResult).getGameFlowHlrResult().getGameStateHlrResultList().size()) {
            return false;
        }

        // 3. 傳入 roundIndex 錯誤，回傳非法
        if (lastPlayedProgressResult.getLastPlayedRoundIndex() < 0
                || lastPlayedProgressResult.getLastPlayedRoundIndex() >= ((PreGameAdjustResultSlot) preGameAdjustResult).getGameFlowHlrResult().getGameStateHlrResultList().get(lastPlayedProgressResult.getLastPlayedStateIndex()).getRoundHlrResultList().size()) {
            return false;
        }

        return true;
    }

    // 檢查客端是否表演完
    public boolean isFinishedPlay(LastPlayedProgressResult lastPlayedProgressResult, GameFlowHlrResult gameFlowHlrResult) {
        // 1. 是否為最後 state
        if (lastPlayedProgressResult.getLastPlayedStateIndex() != gameFlowHlrResult.getGameStateHlrResultList().size() - 1) {
            return false;
        }

        // 2. 是否為最後 round
        if (lastPlayedProgressResult.getLastPlayedRoundIndex()
                != gameFlowHlrResult.getGameStateHlrResultList().get(lastPlayedProgressResult.getLastPlayedStateIndex()).getRoundHlrResultList().size() - 1) {
            return false;
        }

        return true;
    }

    // 是否需要斷線重連
    public boolean isNeedCutReturn(PreGameAdjustResult preGameAdjustResult) {
        return Objects.isNull(preGameAdjustResult) == false;
    }

    // 計算斷線重連進度 [參數正確才會到這]
    public SlotCutReturnProgressResult calculateSlotCutReturnProgressResult(GameFlowHlrResult gameFlowHlrResult) {
        // 1. 從未播過，從頭開始
        if (Objects.isNull(this.lastPlayedProgressResult)) {
            return new SlotCutReturnProgressResult(0,0);
        }

        // 1. 若該 state 還能撥，直接撥下一局
        if (this.lastPlayedProgressResult.getLastPlayedRoundIndex()
                < gameFlowHlrResult.getGameStateHlrResultList().get(lastPlayedProgressResult.getLastPlayedStateIndex()).getRoundHlrResultList().size() - 1) {
            return new SlotCutReturnProgressResult(this.lastPlayedProgressResult.getLastPlayedStateIndex(), this.lastPlayedProgressResult.getLastPlayedRoundIndex() + 1);
        }

        // 2. 該 state 撥玩，換新state 第一局
        return new SlotCutReturnProgressResult(this.lastPlayedProgressResult.getLastPlayedStateIndex() + 1, 0);
    }

    // 更新客端撥放進度
    public void updateLastPlayedProgressResult(LastPlayedProgressResult lastPlayedProgressResult) {
        this.setLastPlayedProgressResult(lastPlayedProgressResult);
    }

    // 清空記錄資訊
    public void clear() {
        this.lastPlayedProgressResult = null;
    }

    public LastPlayedProgressResult getLastPlayedProgressResult() {
        return lastPlayedProgressResult;
    }

    public void setLastPlayedProgressResult(LastPlayedProgressResult lastPlayedProgressResult) {
        this.lastPlayedProgressResult = lastPlayedProgressResult;
    }
}

package org.lsj.gs.math.core.slot.oneBoardResultMgr.enity;

import org.lsj.gs.math.core.common.spinResultPgr.enity.SpinResult;
import org.lsj.gs.math.core.common.table.entity.message.fish.GamePlayerResult;
import org.lsj.gs.math.core.common.table.entity.message.slot.ClientSpinRequest;

// 虎機斷線重連資訊
public class SlotCutReturn {
    private final String roundId; // 局號
    private final ClientSpinRequest clientSpinRequest; // 押注請求
    private final SpinResult spinResult; // 押注結果
    private final GamePlayerResult afterSpinBetGamePlayerResult; // 玩家下注後餘額
    private final SlotCutReturnProgressResult slotCutReturnProgressResult; // 斷線重連表演進度

    public SlotCutReturn(String roundId, ClientSpinRequest clientSpinRequest, SpinResult spinResult, GamePlayerResult afterSpinBetGamePlayerResult, SlotCutReturnProgressResult slotCutReturnProgressResult) {
        this.roundId = roundId;
        this.clientSpinRequest = clientSpinRequest;
        this.spinResult = spinResult;
        this.afterSpinBetGamePlayerResult = afterSpinBetGamePlayerResult;
        this.slotCutReturnProgressResult = slotCutReturnProgressResult;
    }

    public String getRoundId() {
        return roundId;
    }

    public ClientSpinRequest getClientSpinRequest() {
        return clientSpinRequest;
    }

    public SpinResult getSpinResult() {
        return spinResult;
    }

    public GamePlayerResult getAfterSpinBetGamePlayerResult() {
        return afterSpinBetGamePlayerResult;
    }

    public SlotCutReturnProgressResult getSlotCutReturnProgressResult() {
        return slotCutReturnProgressResult;
    }
}

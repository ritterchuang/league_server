package org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.readyHandHlrExtendResult.ReadyHandHlrExtendResult;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.readyHandHlrExtendResult.ReadyHandHlrExtendResultDefault;

// 聽牌處理者結果
public class ReadyHandHlrResult {
    private final ConstMathSlot.ReadyHandType readyHandType; // 聽牌類型
    private final ReadyHandHlrExtendResult readyHandHlrExtendResult; // 聽牌額外資訊

    public ReadyHandHlrResult(ConstMathSlot.ReadyHandType readyHandType, ReadyHandHlrExtendResult readyHandHlrExtendResult) {
        this.readyHandType = readyHandType;
        this.readyHandHlrExtendResult = readyHandHlrExtendResult;
    }

    public ReadyHandHlrResult() {
        this.readyHandType = ConstMathSlot.ReadyHandType.DEFAULT;
        this.readyHandHlrExtendResult = new ReadyHandHlrExtendResultDefault();
    }

    public ConstMathSlot.ReadyHandType getReadyHandType() {
        return readyHandType;
    }

    public ReadyHandHlrExtendResult getReadyHandHlrExtendResult() {
        return readyHandHlrExtendResult;
    }
}

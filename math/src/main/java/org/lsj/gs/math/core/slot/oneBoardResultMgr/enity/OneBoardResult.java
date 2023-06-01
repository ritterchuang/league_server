package org.lsj.gs.math.core.slot.oneBoardResultMgr.enity;

import org.lsj.gs.math.core.common.gameAdjust.entity.PreGameAdjustResult;
import org.lsj.gs.math.core.common.table.entity.message.fish.GamePlayerResult;
import org.lsj.gs.math.core.common.table.entity.message.slot.ClientSpinRequest;
import org.lsj.utils.StringUtil;

// 一局相關資訊
public class OneBoardResult {
    private String roundId; // 局號
    private ClientSpinRequest clientSpinRequest; // 客端請求
    private PreGameAdjustResult preGameAdjustResult; // 一局預結果
    private GamePlayerResult afterSpinBetGamePlayerResult; // 押注前餘額

    public void clear() {
        this.roundId = StringUtil.getInstance().getEmptyString();
        this.clientSpinRequest = null;
        this.preGameAdjustResult = null;
        this.afterSpinBetGamePlayerResult = null;
    }

    public String getRoundId() {
        return roundId;
    }

    public void setRoundId(String roundId) {
        this.roundId = roundId;
    }

    public ClientSpinRequest getClientSpinRequest() {
        return clientSpinRequest;
    }

    public void setClientSpinRequest(ClientSpinRequest clientSpinRequest) {
        this.clientSpinRequest = clientSpinRequest;
    }

    public PreGameAdjustResult getPreGameResult() {
        return preGameAdjustResult;
    }

    public void setPreGameResult(PreGameAdjustResult preGameAdjustResult) {
        this.preGameAdjustResult = preGameAdjustResult;
    }

    public GamePlayerResult getAfterSpinBetGamePlayerResult() {
        return afterSpinBetGamePlayerResult;
    }

    public void setAfterSpinBetGamePlayerResult(GamePlayerResult afterSpinBetGamePlayerResult) {
        this.afterSpinBetGamePlayerResult = afterSpinBetGamePlayerResult;
    }
}

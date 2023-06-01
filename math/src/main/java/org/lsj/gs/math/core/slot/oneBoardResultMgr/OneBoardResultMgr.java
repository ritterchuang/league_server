package org.lsj.gs.math.core.slot.oneBoardResultMgr;

import org.lsj.gs.math.core.AbstractModule;
import org.lsj.gs.math.core.common.gameAdjust.entity.PreGameAdjustResult;
import org.lsj.gs.math.core.common.gameFlowHlr.enity.result.GameFlowHlrResult;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.spinResultPgr.SpinResultPgr;
import org.lsj.gs.math.core.common.table.entity.message.fish.GamePlayerResult;
import org.lsj.gs.math.core.common.table.entity.message.slot.ClientSpinRequest;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.oneBoardResultMgr.enity.LastPlayedProgressResult;
import org.lsj.gs.math.core.slot.oneBoardResultMgr.enity.SlotCutReturn;
import org.lsj.gs.math.core.slot.oneBoardResultMgr.module.OneBoardResultHlr;
import org.lsj.gs.math.core.slot.oneBoardResultMgr.module.SlotCutReturnHlr;

import java.util.Optional;

// 一局結果管理者
public class OneBoardResultMgr extends AbstractModule {
    private final OneBoardResultHlr oneBoardResultHlr; // 一局結果處理者
    private final SlotCutReturnHlr slotCutReturnHlr; // 斷線重連處理者

    public OneBoardResultMgr(OneBoardResultHlr oneBoardResultHlr, SlotCutReturnHlr slotCutReturnHlr, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(gamePlayerHlr, poolCtr, tableUtil);
        this.oneBoardResultHlr = oneBoardResultHlr;
        this.slotCutReturnHlr = slotCutReturnHlr;
    }

    //* 設定相關 *//
    // 設定局號
    public void setRoundId(String roundId) {
        this.oneBoardResultHlr.setRoundId(roundId);
    }

    // 設定客端押注資訊
    public void setClientSpinRequest(ClientSpinRequest clientSpinRequest) {
        this.oneBoardResultHlr.setClientSpinRequest(clientSpinRequest);
    }

    // 設定預調控結果
    public void setPreGameAdjustResult(PreGameAdjustResult preGameAdjustResult) {
        this.oneBoardResultHlr.setPreGameResult(preGameAdjustResult);
    }

    // 設定遊戲前餘額
    public void setAfterSpinBetGamePlayerResult(GamePlayerResult afterSpinBetGamePlayerResult) {
        this.oneBoardResultHlr.setAfterSpinGamePlayerResult(afterSpinBetGamePlayerResult);
    }

    // 取得押注后餘額
    public GamePlayerResult getAfterSpinBetGamePlayerResult() {
        return this.oneBoardResultHlr.getAfterSpinBetGamePlayerResult();
    }

    //* 取得資訊相關 *//
    // 取得遊戲結果
    public GameFlowHlrResult getGameFlowHlrResult() {
        return this.oneBoardResultHlr.getGameFlowHlrResult();
    }

    // 取得押注資訊
    public ClientSpinRequest getCurrentSpinRequest() {
        return this.oneBoardResultHlr.getClientSpinRequest();
    }

    // 取得斷線重連資訊
    public Optional<SlotCutReturn> getSlotCutReturn(SpinResultPgr spinResultPgr) {
        // 1. 無須斷線重連回傳空
        if (!this.slotCutReturnHlr.isNeedCutReturn(this.oneBoardResultHlr.getPreGameResult()))
            return Optional.empty();

        // 2. 回傳斷線重連資訊
        return Optional.of(new SlotCutReturn(
                this.oneBoardResultHlr.getRoundId(),
                this.oneBoardResultHlr.getClientSpinRequest(),
                spinResultPgr.packageSpinResult(this.getGameFlowHlrResult()),
                this.oneBoardResultHlr.getAfterSpinBetGamePlayerResult(),
                this.slotCutReturnHlr.calculateSlotCutReturnProgressResult(this.getGameFlowHlrResult())));
    }


    //* 更新相關 *//
    // 清空資訊
    @Override
    public void reset() {
        this.oneBoardResultHlr.clear();
        this.slotCutReturnHlr.clear();
    }

    // 更新玩家撥放進度
    public void updateLastPlayedProgressResult(LastPlayedProgressResult lastPlayedProgressResult) {
        // 1. 檢查參數
        if (!this.slotCutReturnHlr.isValidLastProgressResult(lastPlayedProgressResult, this.oneBoardResultHlr.getPreGameResult())) {
            return;
        }

        // 2. 未表演完，更新
        if (!this.slotCutReturnHlr.isFinishedPlay(lastPlayedProgressResult, this.getGameFlowHlrResult())) {
            this.slotCutReturnHlr.updateLastPlayedProgressResult(lastPlayedProgressResult);
            return;
        }

        // 3. 已表演完，清空資訊
        this.reset();
    }
}

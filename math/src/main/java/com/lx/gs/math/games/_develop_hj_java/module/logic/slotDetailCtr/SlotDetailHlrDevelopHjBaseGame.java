package com.lx.gs.math.games._develop_hj_java.module.logic.slotDetailCtr;

import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import com.lx.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import com.lx.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetail;
import com.lx.gs.math.core.slot.slotDetailHlrMgr.module.ISlotDetailHlr;

import java.util.Collections;
import java.util.List;

// 虎機詳細記錄計算者 開發虎機 基本遊戲 TODO
public class SlotDetailHlrDevelopHjBaseGame implements ISlotDetailHlr {

    // 計算虎機詳細記錄
    @Override
    public List<SlotDetail> calculateSlotDetail(SpinRequest spinRequest, GameStateHlrResult gameStateHlrResult) {
        return Collections.emptyList();
    }

}
package com.lx.gs.math.core.slot.slotDetailHlrMgr.module;

import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import com.lx.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import com.lx.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetail;

import java.util.List;

// 虎機詳細記錄計算者
public interface ISlotDetailHlr {

    List<SlotDetail> calculateSlotDetail(SpinRequest spinRequest, GameStateHlrResult gameStateHlrResult); // 計算虎機詳細記錄
}

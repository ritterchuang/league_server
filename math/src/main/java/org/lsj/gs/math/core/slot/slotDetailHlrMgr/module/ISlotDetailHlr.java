package org.lsj.gs.math.core.slot.slotDetailHlrMgr.module;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetail;

import java.util.List;

// 虎機詳細記錄計算者
public interface ISlotDetailHlr {

    List<SlotDetail> calculateSlotDetail(SpinRequest spinRequest, GameStateHlrResult gameStateHlrResult); // 計算虎機詳細記錄
}

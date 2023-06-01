package com.lx.gs.math.core.slot.slotDetailHlrMgr;

import com.lx.gs.math.core.common.gameFlowHlr.enity.result.GameFlowHlrResult;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import com.lx.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetail;
import com.lx.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetailHlrMgrConfig;
import com.lx.gs.math.core.slot.slotDetailHlrMgr.module.ISlotDetailHlr;
import com.lx.gs.math.core.slot.slotDetailHlrMgr.module.SlotDetailHlrFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 虎機詳細記錄處理者
public class SlotDetailHlrMgr {
    private final SlotDetailHlrMgrConfig config; // 虎機詳細記錄處理者設定
    private final ITableUtil tableUtil; // 牌桌工具包
    private final Map<Integer, ISlotDetailHlr> conditionIdToSlotDetailCtrMap; // <條件ID, 虎機詳細記錄處理者> 對應表

    public SlotDetailHlrMgr(SlotDetailHlrMgrConfig slotDetailHlrMgrConfig, ITableUtil tableUtil) {
        this.config = slotDetailHlrMgrConfig;
        this.tableUtil = tableUtil;
        this.conditionIdToSlotDetailCtrMap = this.creatConditionIdToSlotDetailCtrMap();
    }


    // 取得詳細記錄列表
    public List<SlotDetail> getSlotDetailList(SpinRequest spinRequest, GameFlowHlrResult gameFlowHlrResult) {
        // 1. 宣告空間
        List<SlotDetail> result = new ArrayList<>();

        // 2. 取得遊戲狀態處理者結果列表
        List<GameStateHlrResult> gameStateHlrResultList = gameFlowHlrResult.getGameStateHlrResultList();

        // 3. 由各自 SlotDetailCtr 取得詳細記錄
        gameStateHlrResultList.forEach(hlrResult -> result.addAll(this.conditionIdToSlotDetailCtrMap.get(hlrResult.getConditionId()).calculateSlotDetail(spinRequest, hlrResult)));

        // 4. 回傳
        return result;
    }

    // 創建 虎機詳細記錄計算者對應表
    private Map<Integer, ISlotDetailHlr> creatConditionIdToSlotDetailCtrMap() {
        Map<Integer, ISlotDetailHlr> result = new HashMap<>();

        this.config.getConditionIdToSlotDetailCtrConfigMap().forEach(
                (key, value) -> result.put(key, new SlotDetailHlrFactory().create(value, this.tableUtil)));

        return result;
    }
}

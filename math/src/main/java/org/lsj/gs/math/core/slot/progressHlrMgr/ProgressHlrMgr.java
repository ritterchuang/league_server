package org.lsj.gs.math.core.slot.progressHlrMgr;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.config.ProgressHlrMgrConfig;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.progressHlrMgr.module.IProgressHlr;
import org.lsj.gs.math.core.slot.progressHlrMgr.module.ProgressHlrFactory;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 遊戲進度處理者管理器
public class ProgressHlrMgr {
    private final ProgressHlrMgrConfig config; // 設定檔
    private final ITableUtil tableUtil; // 牌桌工具包
    private final Map<Integer, IProgressHlr> progressHlrMap; // <條件索引, 進度處理者> 對應表

    public ProgressHlrMgr(ProgressHlrMgrConfig config, ITableUtil tableUtil) {
        this.config = config;
        this.tableUtil = tableUtil;
        this.progressHlrMap = this.createProgressHlrMap();
    }

    public int getDefaultRound(int conditionId, GameStateHlrResult beforeGameStateHlrResult) {
        return this.progressHlrMap.get(conditionId).getDefaultRound(beforeGameStateHlrResult);
    }

    public ProgressHlrResult getProgressHlrResult(int conditionId, int roundIndex, GameStateHlrResult beforeGameStateHlrResult, List<RoundHlrResult> preRoundHlrResultList, SpecialFeatureHlrResultCluster specialFeatureHlrResultCluster) {
        return this.progressHlrMap.get(conditionId).calculateProgressHlrResult(roundIndex, beforeGameStateHlrResult, preRoundHlrResultList, specialFeatureHlrResultCluster);
    }

    // 創建進度處理者對應表
    private Map<Integer, IProgressHlr> createProgressHlrMap() {
        Map<Integer, IProgressHlr> result = new HashMap<>();

        this.config.getConditionIdToProgressConfigMap().forEach(
                (key, value) -> result.put(key, new ProgressHlrFactory().create(value, this.tableUtil)));

        return result;
    }
}

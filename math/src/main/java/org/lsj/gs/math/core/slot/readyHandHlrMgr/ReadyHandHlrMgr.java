package org.lsj.gs.math.core.slot.readyHandHlrMgr;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.config.ReadyHandHlrConfigCluster;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.config.ReadyHandHlrMgrConfig;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResult;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResultCluster;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.enity.result.ReadyHandHlrResultUnionCluster;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.module.IReadyHandHlr;
import org.lsj.gs.math.core.slot.readyHandHlrMgr.module.ReadyHandHlrFactory;

import java.util.*;

// 聽牌處理器管理者 [避免 mega 聽牌]
public class ReadyHandHlrMgr {
    private final ReadyHandHlrMgrConfig config; // 設定檔
    private final ITableUtil tableUtil; // 牌桌工具包
    private final Map<Integer, List<IReadyHandHlr>> conditionIdToReadyHandHlrListMap; // 聽牌處理器對應表 <遊戲狀態ID, 聽牌處理器>

    public ReadyHandHlrMgr(ReadyHandHlrMgrConfig config, ITableUtil tableUtil) {
        this.config = config;
        this.tableUtil = tableUtil;
        this.conditionIdToReadyHandHlrListMap = this.createConditionIdToReadyHandHlrListMap();
    }

    // 取得聽牌聯集結果
    public ReadyHandHlrResultUnionCluster getReadyHandHlrResultUnionCluster(int conditionId, List<List<Integer>> screenSymbol, ProgressHlrResult progressHlrResult) {
        // 1. 計算特殊事件結果列表
        List<ReadyHandHlrResultCluster> readyHandHlrResultClusterList = this.calculateReadyHandHlrResultClusterList(conditionId, screenSymbol, progressHlrResult);

        // 2. 聯集所有結果
        List<ReadyHandHlrResult> unionReadyHandHlrResult = this.calculateUnionReadyHandHlrResult(readyHandHlrResultClusterList);
                
        // 3. 回傳
        return new ReadyHandHlrResultUnionCluster(unionReadyHandHlrResult);
    }

    // 取得聽牌計算者結果列表
    private List<ReadyHandHlrResult> calculateUnionReadyHandHlrResult(List<ReadyHandHlrResultCluster> readyHandHlrResultClusterList) {
       // 1. 若無集合列表，回傳空
       if (readyHandHlrResultClusterList.isEmpty()) {
           return Collections.emptyList();
       }

       // 2. 取得所有集合的第一個 非 default 資料
       List<ReadyHandHlrResult> result = new ArrayList<>();
       for (int columnIndex = 0; columnIndex < readyHandHlrResultClusterList.get(0).getReadyHandHlrResultList().size(); columnIndex++) {
            for (int clusterIndex = 0; clusterIndex < readyHandHlrResultClusterList.size(); clusterIndex++) {
                ReadyHandHlrResult readyHandHlrResult = readyHandHlrResultClusterList.get(clusterIndex).getReadyHandHlrResultList().get(columnIndex);

                if (readyHandHlrResult.getReadyHandType().equals(ConstMathSlot.ReadyHandType.DEFAULT) == false) {
                    result.add(readyHandHlrResult);
                    break;
                }
            }
        }

       // 3. 回傳結果
       return result;
    }

    // 計算 聽牌結果群集列表
    private List<ReadyHandHlrResultCluster> calculateReadyHandHlrResultClusterList(int conditionId, List<List<Integer>> screenSymbol, ProgressHlrResult progressHlrResult) {
        // 1. 取得特殊事件處理者列表
        List<IReadyHandHlr> readyHandHlrList = this.conditionIdToReadyHandHlrListMap.get(conditionId);

        // 2. 計算特殊事件結果
        List<ReadyHandHlrResultCluster> result = new ArrayList<>();
        readyHandHlrList.forEach(iReadyHandHlr -> iReadyHandHlr.calculateReadyHandHlrResultCluster(screenSymbol, progressHlrResult).ifPresent(result::add));

        // 3. 回傳
        return result;
    }

    // 創建 ConditionId, 聽牌處理者列表 對應表
    private Map<Integer, List<IReadyHandHlr>> createConditionIdToReadyHandHlrListMap() {
        Map<Integer, List<IReadyHandHlr>> result = new HashMap<>();

        this.config.getConditionIdToReadyHandHlrConfigClusterMap().keySet().forEach(conditionId -> result.put(conditionId, this.createReadyHandHlrList(this.config.getConditionIdToReadyHandHlrConfigClusterMap().get(conditionId))));

        return result;
    }

    // 創建聽牌處理者列表
    private List<IReadyHandHlr> createReadyHandHlrList(ReadyHandHlrConfigCluster readyHandHlrConfigCluster) {
        List<IReadyHandHlr> result = new ArrayList<>();

        readyHandHlrConfigCluster.getReadyHandHlrConfigList().forEach(config -> result.add(new ReadyHandHlrFactory().create(config, this.tableUtil)));

        return result;
    }
}

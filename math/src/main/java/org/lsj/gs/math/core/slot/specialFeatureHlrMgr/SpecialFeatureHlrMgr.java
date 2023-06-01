package org.lsj.gs.math.core.slot.specialFeatureHlrMgr;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrConfigCluster;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrMgrConfig;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResult;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.module.ISpecialFeatureHlr;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.module.SpecialFeatureHlrFactory;
import org.lsj.utils.MathUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 遊戲特殊事件處理器管理器
public class SpecialFeatureHlrMgr {
    private final SpecialFeatureHlrMgrConfig config; // 設定檔
    private final ITableUtil tableUtil; // 牌桌工具包
    private final Map<Integer, List<ISpecialFeatureHlr>> conditionIdToSpecialFeatureHlrListMap; // 特殊事件處理者Map

    public SpecialFeatureHlrMgr(SpecialFeatureHlrMgrConfig config, ITableUtil tableUtil) {
        this.config = config;
        this.tableUtil = tableUtil;
        this.conditionIdToSpecialFeatureHlrListMap = this.createConditionIdToSpecialFeatureHlrListMap();
    }

    // 計算特殊事件處理結果集合
    public SpecialFeatureHlrResultCluster getSpecialFeatureHlrResultCluster(int conditionId, SpinRequest spinRequest, ScreenGtrResult screenGtrResult) {
        // 1. 計算特殊事件結果列表
        List<SpecialFeatureHlrResult> specialFeatureHlrResultList = this.calculateSpecialFeatureHlrResultList(conditionId, spinRequest, screenGtrResult, new ArrayList<>());

        // 2. 計算總得分
        double totalWin = this.calculateTotalWin(specialFeatureHlrResultList);

        // 3. 回傳
        return new SpecialFeatureHlrResultCluster(MathUtil.moneyScale(totalWin), specialFeatureHlrResultList);
    }

    // 計算特殊事件處理結果集合(消除)
    public SpecialFeatureHlrResultCluster getSpecialFeatureHlrResultClusterCascade(int conditionId, SpinRequest spinRequest, ScreenGtrResult screenGtrResult, List<CascadeHlrResult> cascadeHlrResultList) {
        // 1. 計算特殊事件結果列表
        List<SpecialFeatureHlrResult> specialFeatureHlrResultList = this.calculateSpecialFeatureHlrResultList(conditionId, spinRequest, screenGtrResult, cascadeHlrResultList);

        // 2. 計算總得分
        double totalWin = this.calculateTotalWin(specialFeatureHlrResultList);

        // 3. 回傳
        return new SpecialFeatureHlrResultCluster(MathUtil.moneyScale(totalWin), specialFeatureHlrResultList);
    }

    // 計算總得分
    private double calculateTotalWin(List<SpecialFeatureHlrResult> specialFeatureHlrResultList) {
        double result = 0.0;

        for (int resultIndex = 0; resultIndex < specialFeatureHlrResultList.size(); resultIndex++) {
            result = MathUtil.add(result, specialFeatureHlrResultList.get(resultIndex).getTotalWin());
        }

        return result;
    }

    // 計算特殊事件處理結果列表
    private List<SpecialFeatureHlrResult> calculateSpecialFeatureHlrResultList(int conditionId, SpinRequest spinRequest, ScreenGtrResult screenGtrResult, List<CascadeHlrResult> cascadeHlrResultList) {
        // 1. 取得特殊事件處理者列表
        List<ISpecialFeatureHlr> specialFeatureHlrList = this.conditionIdToSpecialFeatureHlrListMap.get(conditionId);

        // 2. 計算特殊事件結果
        List<SpecialFeatureHlrResult> result = new ArrayList<>();
        specialFeatureHlrList.forEach(iSpecialFeatureHlr -> iSpecialFeatureHlr.calculateSpecialFeatureHlrResult(spinRequest, screenGtrResult, cascadeHlrResultList).ifPresent(result::add));

        // 3. 回傳
        return result;
    }

    // 創建 ConditionId, 特殊事件處理者列表 對應表
    private Map<Integer, List<ISpecialFeatureHlr>> createConditionIdToSpecialFeatureHlrListMap() {
        Map<Integer, List<ISpecialFeatureHlr>> result = new HashMap<>();

        this.config.getConditionIdToSpecialFeatureHlrConfigClusterMap().keySet().forEach(conditionId -> result.put(conditionId, this.createSpecialFeatureHlrList(this.config.getConditionIdToSpecialFeatureHlrConfigClusterMap().get(conditionId))));

        return result;
    }

    // 創建特殊事件處理者列表
    private List<ISpecialFeatureHlr> createSpecialFeatureHlrList(SpecialFeatureHlrConfigCluster specialFeatureHlrConfigCluster) {
        List<ISpecialFeatureHlr> result = new ArrayList<>();

        specialFeatureHlrConfigCluster.getSpecialFeatureHlrConfigList().forEach(config -> result.add(new SpecialFeatureHlrFactory().create(config, this.tableUtil)));

        return result;
    }
}

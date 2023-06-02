package org.lsj.gs.math.core.slot.cascadeClusterHlrMgr;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.accumulateWinCtr.AccumulateWinCtr;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.enity.CascadeClusterHlrMgrConfig;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.CascadeClusterHlrFactory;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.ICascadeClusterHlr;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeClusterHlr.enity.result.CascadeClusterHlrResult;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;

import java.util.HashMap;
import java.util.Map;

// 消除集合處理者管理器
public class CascadeClusterHlrMgr {
    private final CascadeClusterHlrMgrConfig config; // 設定檔
    private final Map<Integer, ICascadeClusterHlr> conditionIdToCascadeClusterHlrMap; // 消除集合處理者 map
    private final ITableUtil tableUtil; // 牌桌工具包

    public CascadeClusterHlrMgr(
            CascadeClusterHlrMgrConfig config,
            AccumulateWinCtr accumulateWinCtr,
            ITableUtil tableUtil) {
        this.config = config;
        this.conditionIdToCascadeClusterHlrMap = this.createCascadeClusterHlrMap(config, accumulateWinCtr, tableUtil);
        this.tableUtil = tableUtil;
    }

    // 計算消除集合結果
    public CascadeClusterHlrResult getCascadeClusterHlrResult(int conditionId, SpinRequest spinRequest, ConstMathSlot.ReelRtpType reelRtpType) {
        return this.conditionIdToCascadeClusterHlrMap.get(conditionId).calculateCascadeClusterHlrResult(spinRequest, reelRtpType);
    }

    // 計算消除集合處理者對應表
    private Map<Integer, ICascadeClusterHlr> createCascadeClusterHlrMap(CascadeClusterHlrMgrConfig config, AccumulateWinCtr accumulateWinCtr, ITableUtil tableUtil) {

        Map<Integer, ICascadeClusterHlr> result = new HashMap<>();

        CascadeClusterHlrFactory cascadeClusterHlrFactory = new CascadeClusterHlrFactory();

        config.getConditionIdToCascadeClusterHlrConfigMap().forEach(
                (key, value) -> result.put(key, cascadeClusterHlrFactory.create(value, accumulateWinCtr, tableUtil))
        );

        return result;
    }
}

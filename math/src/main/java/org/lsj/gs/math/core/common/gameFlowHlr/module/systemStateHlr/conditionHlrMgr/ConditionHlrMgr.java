package org.lsj.gs.math.core.common.gameFlowHlr.module.systemStateHlr.conditionHlrMgr;

import org.lsj.gs.math.core.AbstractModule;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.systemStateHlr.conditionHlrMgr.enity.ConditionHlrMgrConfig;
import org.lsj.gs.math.core.common.gameFlowHlr.module.systemStateHlr.conditionHlrMgr.module.ConditionHlrFactory;
import org.lsj.gs.math.core.common.gameFlowHlr.module.systemStateHlr.conditionHlrMgr.module.IConditionHlr;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 條件檢查者
public class ConditionHlrMgr extends AbstractModule {
    private final ConditionHlrMgrConfig config; // 設定檔
    private final Map<ConstMathSlot.Condition, IConditionHlr> conditionHlrMap; // 條件處理者

    public ConditionHlrMgr(ConditionHlrMgrConfig config, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(gamePlayerHlr, poolCtr, tableUtil);
        this.config = config;
        this.conditionHlrMap = this.createConditionHlrMap();
    }

    // 計算滿足條件 ConditionId 列表
    public List<Integer> getConditionIdCandidateList(SpinRequest spinRequest, GameStateHlrResult gameStateHlrResult, Map<Integer, ConstMathSlot.Condition> nextConditionIdMap) {
        return nextConditionIdMap.entrySet().stream().filter(
                entry -> this.conditionHlrMap.get(entry.getValue()).checkCondition(spinRequest, gameStateHlrResult)).
                map(Map.Entry::getKey).collect(Collectors.toList());
    }

    // 創建條件計算者對應表
    private Map<ConstMathSlot.Condition, IConditionHlr> createConditionHlrMap() {
        Map<ConstMathSlot.Condition, IConditionHlr> result = new HashMap<>();

        for (int indexOne = 0; indexOne < this.config.getConditionList().size(); indexOne++) {
            for (int indexTwo = 0; indexTwo < this.config.getConditionList().get(indexOne).size(); indexTwo++) {
                ConstMathSlot.Condition condition = this.config.getConditionList().get(indexOne).get(indexTwo);
                if (result.containsKey(condition) == false) {
                    result.put(condition, new ConditionHlrFactory().create(condition));
                }
            }
        }

        return result;
    }

    @Override
    public void reset() {

    }
}

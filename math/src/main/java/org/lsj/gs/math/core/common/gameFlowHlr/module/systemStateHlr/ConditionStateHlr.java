package org.lsj.gs.math.core.common.gameFlowHlr.module.systemStateHlr;

import org.lsj.gs.math.core.AbstractModule;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.systemStateHlr.conditionHlrMgr.ConditionHlrMgr;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.utils.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

// 條件狀態處理者
public class ConditionStateHlr extends AbstractModule {
    private static final Logger LOG = LoggerFactory.getLogger(ConditionStateHlr.class);
    private final ConditionStateHlrConfig config; // 條件狀態處理者設定
    private int currentConditionId; // 當前條件狀態識別碼

    public ConditionStateHlr(ConditionStateHlrConfig config, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(gamePlayerHlr, poolCtr, tableUtil);
        this.config = config;
    }

    // 更新當前遊戲狀態
    public void updateCurrentConditionId(SpinRequest spinRequest, GameStateHlrResult gameStateHlrResult, ConditionHlrMgr conditionHlrMgr) {
        // 1. 列出可以轉移狀態列表
        Map<Integer, ConstMathSlot.Condition> nextConditionIdMap = this.config.getConditionTable().get(this.currentConditionId);

        // 2. 由 條件狀態處理者 計算出可以轉移列表
        List<Integer> nextConditionIdList = conditionHlrMgr.getConditionIdCandidateList(spinRequest, gameStateHlrResult, nextConditionIdMap);

        // 3. 符合個數0，設定BoardEndId [當為初始，且符合數0，並記錄日誌]
        if (nextConditionIdList.size() == 0 && this.currentConditionId == this.config.getBoardInitialConditionId()) {
            LOG.error("{} condition table error, initial to end", LogUtil.getLogPrefix(super.gamePlayerHlr.getHumanGamePlayer().getSession(), super.gamePlayerHlr.getHumanGamePlayer().getUid()));
            this.setCurrentConditionId(this.config.getBoardEndConditionId());
            return;
        }

        // 4. 符合個數0，設定BoardEndId
        if (nextConditionIdList.size() == 0) {
            this.setCurrentConditionId(this.config.getBoardEndConditionId());
            return;
        }

        // 5. 符合個數1，更新該ID
        if (nextConditionIdList.size() == 1) {
            this.setCurrentConditionId(nextConditionIdList.get(0));
            return;
        }

        // 6. 符合個數大於2，給定第一個並記錄錯誤日誌
        if (nextConditionIdList.size() > 1) {
            LOG.error("{} condition table error, multiple condition", LogUtil.getLogPrefix(super.gamePlayerHlr.getHumanGamePlayer().getSession(), super.gamePlayerHlr.getHumanGamePlayer().getUid()));
            this.setCurrentConditionId(nextConditionIdList.get(0));
            return;
        }

    }

    // 判斷是否為最後State
    public boolean isBoardEnd() {
        return this.currentConditionId == this.config.getBoardEndConditionId();
    }


    // 取得當前遊戲狀態ID
    public int getCurrentConditionId() {
        return currentConditionId;
    }

    // 設定當前遊戲狀態ID
    public void setCurrentConditionId(int currentGameStateId) {
        this.currentConditionId = currentGameStateId;
    }

    // 設定初始遊戲狀態ID
    public void setInitialConditionId() {
        this.currentConditionId = this.config.getBoardInitialConditionId();
    }

    @Override
    public void reset() {

    }
}

package org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateHlrMgrConfig;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.GameStateHlrFactory;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.IGameStateHlr;
import org.lsj.gs.math.core.common.logic.logicSlot.ILogicSlot;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtilSlot;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// 遊戲狀態處理者管理器
public class GameStateHlrMgr {
    private final GameStateHlrMgrConfig config; // 設定檔
    private final ILogicSlot logicSlot; // 虎機邏輯
    private final ITableUtilSlot tableUtilSlot; // 虎機工具包
    private final Map<Integer, IGameStateHlr> gameStateHlrMap; // <條件桌子狀態索引, 遊戲狀態處理者> 對應表

    public GameStateHlrMgr(GameStateHlrMgrConfig gameStateHlrMgrConfig, ILogicSlot logicSlot, ITableUtilSlot tableUtilSlot) {
        this.config = gameStateHlrMgrConfig;
        this.logicSlot = logicSlot;
        this.tableUtilSlot = tableUtilSlot;
        this.gameStateHlrMap = this.createGameStateHlrMap();
    }

    // 計算遊戲狀態處理者結果
    public GameStateHlrResult getGameStateHlrResult(int currentConditionId, int gameStateResultIndex, ConstMathSlot.ReelRtpType reelRtpType, SpinRequest spinRequest, Optional<GameStateHlrResult> previousGameStateResult) {
        // 1. 若為初始狀態，回傳空殼
        if (currentConditionId == this.config.getBoardInitialConditionId() && previousGameStateResult.isEmpty()) {
            return new GameStateHlrResult();
        }

        // 2. 取得對應 IGameStateHlr 來計算結果
        return this.gameStateHlrMap.get(currentConditionId).calculateGameStateHlrResult(gameStateResultIndex - 1, reelRtpType, spinRequest, previousGameStateResult.get());
    }

    // 創建處理者對應表
    private Map<Integer, IGameStateHlr> createGameStateHlrMap() {
        Map<Integer, IGameStateHlr> result = new HashMap<>();

        for (int gameStateId = 0; gameStateId < this.config.getGameStateConfigList().size(); gameStateId++) {
            result.put(gameStateId + 1, new GameStateHlrFactory().create(
                    gameStateId + 1,
                    gameStateId,
                    this.config.getGameStateConfigList().get(gameStateId),
                    this.logicSlot,
                    this.tableUtilSlot));
        }

        return result;
    }
}

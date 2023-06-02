package org.lsj.gs.math.core.common.gameFlowHlr.enity.config;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateConfigCluster;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateHlrMgrConfig;
import org.lsj.gs.math.core.common.gameFlowHlr.module.systemStateHlr.ConditionStateHlrConfig;
import org.lsj.gs.math.core.common.gameFlowHlr.module.systemStateHlr.conditionHlrMgr.enity.ConditionHlrMgrConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.gameFlowConfig.GameFlowConfig;

// 遊戲處理者設定
public class GameFlowHlrConfig {
    private final GameFlowConfig gameFlowConfig; // 遊戲流程設定
    private final GameStateConfigCluster gameStateConfigCluster; // 遊戲狀態設定集合
    private final int boardInitialConditionId; // 局初始遊戲狀態Id
    private final int boardEndConditionId; // 局結束遊戲狀態Id

    public GameFlowHlrConfig(GameFlowConfig gameFlowConfig, GameStateConfigCluster gameStateConfigCluster) {
        this.gameFlowConfig = gameFlowConfig;
        this.gameStateConfigCluster = gameStateConfigCluster;
        this.boardInitialConditionId = 0;
        this.boardEndConditionId = gameFlowConfig.getConditionList().size();
    }

    // 取得系統處理者設定
    public ConditionStateHlrConfig createConditionStateHlrConfig() {
        return new ConditionStateHlrConfig(this.gameStateConfigCluster.getGameStateConfigList(), this.gameFlowConfig, this.boardInitialConditionId, this.boardEndConditionId);
    }

    // 取得跳件處理者設定
    public ConditionHlrMgrConfig createConditionHlrMgrConfig() {
        return new ConditionHlrMgrConfig(this.gameFlowConfig.getConditionList());
    }

    // 取得遊戲狀態處理器管理者設定
    public GameStateHlrMgrConfig createGameStateHlrMgrConfig() {
        return new GameStateHlrMgrConfig(this.gameStateConfigCluster.getGameStateConfigList(), this.boardInitialConditionId);
    }

    public GameFlowConfig getGameFlowConfig() {
        return gameFlowConfig;
    }

    public GameStateConfigCluster getGameStateConfigCluster() {
        return gameStateConfigCluster;
    }

    public int getBoardInitialConditionId() {
        return boardInitialConditionId;
    }

    public int getBoardEndConditionId() {
        return boardEndConditionId;
    }
}

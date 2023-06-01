package org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config;

import java.util.List;

// 遊戲狀態處理者管理器設定
public class GameStateHlrMgrConfig {
    private final List<GameStateConfig> gameStateConfigList; // 遊戲狀態設定檔列表
    private final int boardInitialConditionId; // 局初始遊戲狀態Id

    public GameStateHlrMgrConfig(List<GameStateConfig> gameStateConfigList, int boardInitialConditionId) {
        this.gameStateConfigList = gameStateConfigList;
        this.boardInitialConditionId = boardInitialConditionId;
    }

    public List<GameStateConfig> getGameStateConfigList() {
        return gameStateConfigList;
    }

    public int getBoardInitialConditionId() {
        return boardInitialConditionId;
    }
}

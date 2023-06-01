package com.lx.gs.math.core.slot.gameStateInputHlrMgr.module;

import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.core.slot.gameStateInputHlrMgr.enity.config.GameStateInputConfig;

// 遊戲輸入處理者工廠
public class GameStateInputHlrFactory {

    // 創建遊戲輸入處理者介面 TODO Default
    public IGameStateInputHlr create(GameStateInputConfig gameStateInputConfig, ITableUtil tableUtil) {
        switch (gameStateInputConfig.getGameStateInputType()) {
            default: return new GameStateStateInputHlrNone(gameStateInputConfig, tableUtil);
        }
    }
}

package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr;

import org.lsj.gs.math.core.common.spinResultPgr.enity.pgrConfig.ClientGameStateResultPgrConfig;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

// 遊戲狀態結果包裝者工廠
public class GameStateResultPgrFactory {

    // TODO 目前尚未需要多型
    public IGameStateResultPgr create(ClientGameStateResultPgrConfig clientGameStateResultPgrConfig, ITableUtil tableUtil) {
        switch (clientGameStateResultPgrConfig.getGameStateType()) {
            default: return new GameStateResultPgrDefault(clientGameStateResultPgrConfig, tableUtil);
        }
    }
}

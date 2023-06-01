package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.gameResultPgr;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

// 算分結果包裝者工廠
public class GameResultPgrFactory {

    // TODO CONNECT
    public IGameResultPgr create(GameResultPgrConfig config, ITableUtil tableUtil) {
        switch (config.getGameConfig().getGameHitType()) {
            case LINE_GAME: return new LineGameResultPgr(config, tableUtil);
            case WAY_GAME:
            default: return new WayGameResultPgr(config, tableUtil);
        }
    }
}

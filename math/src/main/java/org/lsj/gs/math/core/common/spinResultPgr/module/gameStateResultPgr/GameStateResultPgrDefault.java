package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr;

import org.lsj.gs.math.core.common.spinResultPgr.enity.pgrConfig.ClientGameStateResultPgrConfig;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

// 遊戲狀態結果包裝者 預設
public class GameStateResultPgrDefault extends AbstractGameStateResultPgr{

    public GameStateResultPgrDefault(ClientGameStateResultPgrConfig config, ITableUtil tableUtil) {
        super(config, tableUtil);
    }
}

package org.lsj.gs.math.core.common.spinResultPgr.module.gameStateResultPgr.module.resultPgrUtil;

import org.lsj.gs.math.core.common.spinResultPgr.config.enity.clientRoundConfig.clientSymbolHitConfig.ClientSymbolHitConfig;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

// 客端畫面處理者工廠
public class ClientSymbolHitPgrFactory {

    // 創建客端畫面處理者 TODO Default
    public IClientSymbolHitPgr create(ClientSymbolHitConfig clientSymbolHitConfig, ITableUtil tableUtil) {
        switch (clientSymbolHitConfig.getSymbolHitType()) {
            case POSITION_ID: return new ClientSymbolHitPgrPositionId(clientSymbolHitConfig, tableUtil);
            default: return new ClientSymbolHitPgrPositionBoolean(clientSymbolHitConfig, tableUtil);
        }
    }
}

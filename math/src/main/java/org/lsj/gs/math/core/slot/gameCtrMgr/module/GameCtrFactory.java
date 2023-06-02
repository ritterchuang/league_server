package org.lsj.gs.math.core.slot.gameCtrMgr.module;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.config.GameCtrConfig;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrConnect.GameCtrConnect;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrLine.GameCtrLine;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrWay.GameCtrWay;

// 遊戲計算者工廠
public class GameCtrFactory {

    // 創建遊戲計算者 TODO Default
    public IGameCtr create(GameCtrConfig gameCtrConfig, ITableUtil tableUtil) {
        switch (gameCtrConfig.getGameConfig().getGameHitType()) {
            case WAY_GAME: return new GameCtrWay(gameCtrConfig, tableUtil);
            case LINE_GAME: return new GameCtrLine(gameCtrConfig, tableUtil);
            case CONNECT_GAME: return new GameCtrConnect(gameCtrConfig, tableUtil);
            default: return new GameCtrInvalid();
        }
    }

}

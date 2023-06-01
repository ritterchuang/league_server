package com.lx.gs.mathBoardGtr.core.fish.fishUtil.clientBulletGtr;

import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.gameTypeConfigExtend.GameTypeConfigExtendFish;

// 客製子彈生產者工廠
public class ClientBulletGtrFactory {

    // 產出客製子彈生產者
    public IClientBulletGtr createClientBulletGtr(GameTypeConfigExtendFish gameTypeConfigExtendFish, ITableUtil tableUtil) {

        // 回傳子彈生產者
        switch (gameTypeConfigExtendFish.getPlayTypeFishConfigExtend().getPlayCostType()) {
            case FIX_COST: return new ClientBulletGtrFixBet();
            case RANDOM_COST: return new ClientBulletGtrRandomBet(tableUtil);
            default: return null;
        }
    }
}

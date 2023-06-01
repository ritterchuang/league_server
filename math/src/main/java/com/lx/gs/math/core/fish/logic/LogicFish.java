package com.lx.gs.math.core.fish.logic;

import com.lx.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import com.lx.gs.math.config.entity.tableGameConfig.TableGameConfigFish;
import com.lx.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import com.lx.gs.math.core.common.logic.AbstractLogicFish;
import com.lx.gs.math.core.common.logic.ILogicFish;
import com.lx.gs.math.core.common.logic.gameBetLogResultCtr.module.IGameBetLogResultCtrFish;
import com.lx.gs.math.core.common.poolCtr.module.PoolCtr;
import com.lx.gs.math.core.common.table.module.ITableBase;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;

// 捕魚邏輯
public class LogicFish extends AbstractLogicFish implements ILogicFish {
    public LogicFish(ITableBase table,
                     TableFieldConfig tableFieldConfig,
                     TableGameConfigFish tableGameConfig,
                     GamePlayerHlr gamePlayerHlr,
                     PoolCtr poolCtr,
                     IGameBetLogResultCtrFish gameBetLogResultCtrFish,
                     ITableUtil tableUtil) {
        // 初始化
        super(  table,
                tableFieldConfig,
                tableGameConfig,
                gamePlayerHlr,
                poolCtr,
                gameBetLogResultCtrFish,
                tableUtil);
    }
}

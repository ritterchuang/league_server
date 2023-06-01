package com.lx.gs.math.games.tbnn_java.module.robotLogic;

import com.lx.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import com.lx.gs.math.core.common.logic.AbstractLogicTb;
import com.lx.gs.math.core.common.poolCtr.module.PoolCtr;
import com.lx.gs.math.core.common.robotLogic.entity.RobotLogicConfigTonbi;
import com.lx.gs.math.core.common.robotLogic.module.AbstractRobotLogicTonbi;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;

// 通比牛牛機器人邏輯
public class RobotLogicTbnnJava extends AbstractRobotLogicTonbi {
    public RobotLogicTbnnJava(GamePlayerHlr gamePlayerHlr,
                              PoolCtr poolCtr,
                              ITableUtil tableUtil,
                              RobotLogicConfigTonbi config,
                              AbstractLogicTb logic){
        super(gamePlayerHlr, tableUtil, config, logic);
    }
}

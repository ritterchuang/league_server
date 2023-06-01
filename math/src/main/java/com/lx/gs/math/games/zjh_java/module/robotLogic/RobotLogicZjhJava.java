package com.lx.gs.math.games.zjh_java.module.robotLogic;

import com.lx.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import com.lx.gs.math.core.common.logic.AbstractLogicQz;
import com.lx.gs.math.core.common.poolCtr.module.PoolCtr;
import com.lx.gs.math.core.common.robotLogic.entity.RobotLogicConfigBanker;
import com.lx.gs.math.core.common.robotLogic.module.AbstractRobotLogicBanker;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;

// 炸金花機器人邏輯
public class RobotLogicZjhJava extends AbstractRobotLogicBanker {
    public RobotLogicZjhJava(GamePlayerHlr gamePlayerHlr,
                             PoolCtr poolCtr,
                             ITableUtil tableUtil,
                             RobotLogicConfigBanker config,
                             AbstractLogicQz logic){
        super(gamePlayerHlr, tableUtil, config, logic);
    }
}

package com.lx.gs.math.games.qznn_ksz_java.module.robotLogic;

import com.lx.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import com.lx.gs.math.core.common.logic.AbstractLogicQz;
import com.lx.gs.math.core.common.poolCtr.module.PoolCtr;
import com.lx.gs.math.core.common.robotLogic.entity.RobotLogicConfigBanker;
import com.lx.gs.math.core.common.robotLogic.module.AbstractRobotLogicBanker;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;

// 看三張搶莊牛牛機器人邏輯
public class RobotLogicQznnKszJava extends AbstractRobotLogicBanker {
    public RobotLogicQznnKszJava(GamePlayerHlr gamePlayerHlr,
                                 PoolCtr poolCtr,
                                 ITableUtil tableUtil,
                                 RobotLogicConfigBanker config,
                                 AbstractLogicQz logic){
        super(gamePlayerHlr, tableUtil, config, logic);
    }
}

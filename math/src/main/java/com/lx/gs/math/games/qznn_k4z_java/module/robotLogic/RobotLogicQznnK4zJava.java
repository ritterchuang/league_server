package com.lx.gs.math.games.qznn_k4z_java.module.robotLogic;

import com.lx.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import com.lx.gs.math.core.common.logic.AbstractLogicQz;
import com.lx.gs.math.core.common.poolCtr.module.PoolCtr;
import com.lx.gs.math.core.common.robotLogic.entity.RobotLogicConfigBanker;
import com.lx.gs.math.core.common.robotLogic.module.AbstractRobotLogicBanker;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;

// 新看四張搶莊牛牛機器人邏輯
public class RobotLogicQznnK4zJava extends AbstractRobotLogicBanker {
    public RobotLogicQznnK4zJava(GamePlayerHlr gamePlayerHlr,
                                 PoolCtr poolCtr,
                                 ITableUtil tableUtil,
                                 RobotLogicConfigBanker config,
                                 AbstractLogicQz logic){
        super(gamePlayerHlr, tableUtil, config, logic);
    }
}

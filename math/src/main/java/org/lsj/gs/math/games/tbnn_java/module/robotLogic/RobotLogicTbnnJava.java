package org.lsj.gs.math.games.tbnn_java.module.robotLogic;

import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.logic.AbstractLogicTb;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.robotLogic.entity.RobotLogicConfigTonbi;
import org.lsj.gs.math.core.common.robotLogic.module.AbstractRobotLogicTonbi;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

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

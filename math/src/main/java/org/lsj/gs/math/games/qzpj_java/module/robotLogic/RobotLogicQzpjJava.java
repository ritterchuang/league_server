package org.lsj.gs.math.games.qzpj_java.module.robotLogic;

import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.logic.AbstractLogicQz;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.robotLogic.entity.RobotLogicConfigBanker;
import org.lsj.gs.math.core.common.robotLogic.module.AbstractRobotLogicBanker;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;

// 搶莊牌九機器人邏輯
public class RobotLogicQzpjJava extends AbstractRobotLogicBanker {
    public RobotLogicQzpjJava(GamePlayerHlr gamePlayerHlr,
                              PoolCtr poolCtr,
                              ITableUtil tableUtil,
                              RobotLogicConfigBanker config,
                              AbstractLogicQz logic){
        super(gamePlayerHlr, tableUtil, config, logic);
    }
}

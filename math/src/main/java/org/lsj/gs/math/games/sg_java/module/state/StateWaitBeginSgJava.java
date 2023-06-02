package org.lsj.gs.math.games.sg_java.module.state;

import org.lsj.gs.math.games.sg_java.TableSgJava;
import org.lsj.gs.math.games.sg_java.module.gameAdjust.GameAdjustSgJava;
import org.lsj.gs.math.games.sg_java.module.logic.LogicSgJava;
import org.lsj.gs.math.games.sg_java.module.robotLogic.RobotLogicSgJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 新三公等待開始狀態
public class StateWaitBeginSgJava extends AbstractStateSgJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateWaitBeginSgJava.class);
    public StateWaitBeginSgJava(TableSgJava table, LogicSgJava logic, RobotLogicSgJava aiLogic, GameAdjustSgJava gameAdjust, int stateId) {
        super(table, logic, aiLogic, gameAdjust, stateId);
    }

    @Override
    public void onEnter() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());

        // 1. 傳送玩家資訊
        super.table.sendUserList();

        // 2. 傳送玩家金額
        super.table.sendHumanUpdateScore();
    }

    @Override
    public void onLeave() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());
        // 清空定時器
        super.table.getTableTimer().cancel();
    }

    @Override
    public void onTimeout() {
        super.iLeave();
    }
}

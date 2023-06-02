package org.lsj.gs.math.games.zjh_java.module.state;

import org.lsj.gs.math.games.zjh_java.TableZjhJava;
import org.lsj.gs.math.games.zjh_java.module.gameAdjust.GameAdjustZjhJava;
import org.lsj.gs.math.games.zjh_java.module.logic.LogicZjhJava;
import org.lsj.gs.math.games.zjh_java.module.robotLogic.RobotLogicZjhJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 等待開始狀態
public class StateWaitBeginZjhJava extends AbstractStateZjhJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateWaitBeginZjhJava.class);
    public StateWaitBeginZjhJava(TableZjhJava table, LogicZjhJava logic, RobotLogicZjhJava aiLogic, GameAdjustZjhJava gameAdjust, int stateId) {
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

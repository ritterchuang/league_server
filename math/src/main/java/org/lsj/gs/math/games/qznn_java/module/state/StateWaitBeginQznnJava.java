package org.lsj.gs.math.games.qznn_java.module.state;

import org.lsj.gs.math.games.qznn_java.TableQznnJava;
import org.lsj.gs.math.games.qznn_java.module.gameAdjust.GameAdjustQznnJava;
import org.lsj.gs.math.games.qznn_java.module.logic.LogicQznnJava;
import org.lsj.gs.math.games.qznn_java.module.robotLogic.RobotLogicQznnJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 等待開始狀態
public class StateWaitBeginQznnJava extends AbstractStateQznnJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateWaitBeginQznnJava.class);
    public StateWaitBeginQznnJava(TableQznnJava table, LogicQznnJava logic, RobotLogicQznnJava aiLogic, GameAdjustQznnJava gameAdjust, int stateId) {
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

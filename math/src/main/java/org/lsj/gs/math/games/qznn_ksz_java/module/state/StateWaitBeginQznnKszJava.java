package org.lsj.gs.math.games.qznn_ksz_java.module.state;

import org.lsj.gs.math.games.qznn_ksz_java.TableQznnKszJava;
import org.lsj.gs.math.games.qznn_ksz_java.module.gameAdjust.GameAdjustQznnKszJava;
import org.lsj.gs.math.games.qznn_ksz_java.module.logic.LogicQznnKszJava;
import org.lsj.gs.math.games.qznn_ksz_java.module.robotLogic.RobotLogicQznnKszJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 等待開始狀態
public class StateWaitBeginQznnKszJava extends AbstractStateQznnKszJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateWaitBeginQznnKszJava.class);
    public StateWaitBeginQznnKszJava(TableQznnKszJava table, LogicQznnKszJava logic, RobotLogicQznnKszJava aiLogic, GameAdjustQznnKszJava gameAdjust, int stateId) {
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

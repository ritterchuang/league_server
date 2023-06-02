package org.lsj.gs.math.games.brnn_java.module.state;

import org.lsj.gs.math.games.brnn_java.TableBrnnJava;
import org.lsj.gs.math.games.brnn_java.module.gameAdjust.GameAdjustBrnnJava;
import org.lsj.gs.math.games.brnn_java.module.logic.LogicBrnnJava;
import org.lsj.gs.math.games.brnn_java.module.robotLogic.RobotLogicBrnnJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 等待開始狀態
public class StateWaitBeginBrnnJava extends AbstractStateBrnnJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateWaitBeginBrnnJava.class);
    public StateWaitBeginBrnnJava(TableBrnnJava table, LogicBrnnJava logic, RobotLogicBrnnJava aiLogic, GameAdjustBrnnJava gameAdjust, int stateId) {
        super(table, logic, aiLogic, gameAdjust, stateId);
    }

    @Override
    public void onEnter() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());
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

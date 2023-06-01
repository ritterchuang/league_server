package org.lsj.gs.math.games.cjnn_java.module.state;

import org.lsj.gs.math.games.cjnn_java.TableCjnnJava;
import org.lsj.gs.math.games.cjnn_java.module.gameAdjust.GameAdjustCjnnJava;
import org.lsj.gs.math.games.cjnn_java.module.logic.LogicCjnnJava;
import org.lsj.gs.math.games.cjnn_java.module.robotLogic.RobotLogicCjnnJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 等待開始狀態
public class StateWaitBeginCjnnJava extends AbstractStateCjnnJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateWaitBeginCjnnJava.class);
    public StateWaitBeginCjnnJava(TableCjnnJava table, LogicCjnnJava logic, RobotLogicCjnnJava aiLogic, GameAdjustCjnnJava gameAdjust, int stateId) {
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

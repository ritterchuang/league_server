package org.lsj.gs.math.games.bjl_java.module.state;

import org.lsj.gs.math.games.bjl_java.TableBjlJava;
import org.lsj.gs.math.games.bjl_java.module.gameAdjust.GameAdjustBjlJava;
import org.lsj.gs.math.games.bjl_java.module.logic.LogicBjlJava;
import org.lsj.gs.math.games.bjl_java.module.robotLogic.RobotLogicBjlJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 等待開始狀態
public class StateWaitBeginBjlJava extends AbstractStateBjlJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateWaitBeginBjlJava.class);
    public StateWaitBeginBjlJava(TableBjlJava table, LogicBjlJava logic, RobotLogicBjlJava aiLogic, GameAdjustBjlJava gameAdjust, int stateId) {
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

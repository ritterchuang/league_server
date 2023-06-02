package org.lsj.gs.math.games.lhd_java.module.state;

import org.lsj.gs.math.games.lhd_java.TableLhdJava;
import org.lsj.gs.math.games.lhd_java.module.gameAdjust.GameAdjustLhdJava;
import org.lsj.gs.math.games.lhd_java.module.logic.LogicLhdJava;
import org.lsj.gs.math.games.lhd_java.module.robotLogic.RobotLogicLhdJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 等待開始狀態
public class StateWaitBeginLhdJava extends AbstractStateLhdJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateWaitBeginLhdJava.class);
    public StateWaitBeginLhdJava(TableLhdJava table, LogicLhdJava logic, RobotLogicLhdJava aiLogic, GameAdjustLhdJava gameAdjust, int stateId) {
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

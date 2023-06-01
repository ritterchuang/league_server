package org.lsj.gs.math.games.mybjl_java.module.state;

import org.lsj.gs.math.games.mybjl_java.TableMybjlJava;
import org.lsj.gs.math.games.mybjl_java.module.gameAdjust.GameAdjustMybjlJava;
import org.lsj.gs.math.games.mybjl_java.module.logic.LogicMybjlJava;
import org.lsj.gs.math.games.mybjl_java.module.robotLogic.RobotLogicMybjlJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 等待開始狀態
public class StateWaitBeginMybjlJava extends AbstractStateMybjlJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateWaitBeginMybjlJava.class);
    public StateWaitBeginMybjlJava(TableMybjlJava table, LogicMybjlJava logic, RobotLogicMybjlJava aiLogic, GameAdjustMybjlJava gameAdjust, int stateId) {
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

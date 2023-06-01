package org.lsj.gs.math.games.hhdz_java.module.state;

import org.lsj.gs.math.games.hhdz_java.TableHhdzJava;
import org.lsj.gs.math.games.hhdz_java.module.gameAdjust.GameAdjustHhdzJava;
import org.lsj.gs.math.games.hhdz_java.module.logic.LogicHhdzJava;
import org.lsj.gs.math.games.hhdz_java.module.robotLogic.RobotLogicHhdzJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 等待開始狀態
public class StateWaitBeginHhdzJava extends AbstractStateHhdzJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateWaitBeginHhdzJava.class);
    public StateWaitBeginHhdzJava(TableHhdzJava table, LogicHhdzJava logic, RobotLogicHhdzJava aiLogic, GameAdjustHhdzJava gameAdjust, int stateId) {
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

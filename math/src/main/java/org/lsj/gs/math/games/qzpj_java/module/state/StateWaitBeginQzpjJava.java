package org.lsj.gs.math.games.qzpj_java.module.state;

import org.lsj.gs.math.games.qzpj_java.TableQzpjJava;
import org.lsj.gs.math.games.qzpj_java.module.gameAdjust.GameAdjustQzpjJava;
import org.lsj.gs.math.games.qzpj_java.module.logic.LogicQzpjJava;
import org.lsj.gs.math.games.qzpj_java.module.robotLogic.RobotLogicQzpjJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 等待開始狀態
public class StateWaitBeginQzpjJava extends AbstractStateQzpjJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateWaitBeginQzpjJava.class);
    public StateWaitBeginQzpjJava(TableQzpjJava table, LogicQzpjJava logic, RobotLogicQzpjJava aiLogic, GameAdjustQzpjJava gameAdjust, int stateId) {
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

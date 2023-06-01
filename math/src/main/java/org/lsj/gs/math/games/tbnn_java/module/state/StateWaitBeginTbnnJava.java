package org.lsj.gs.math.games.tbnn_java.module.state;

import org.lsj.gs.math.games.tbnn_java.TableTbnnJava;
import org.lsj.gs.math.games.tbnn_java.module.gameAdjust.GameAdjustTbnnJava;
import org.lsj.gs.math.games.tbnn_java.module.logic.LogicTbnnJava;
import org.lsj.gs.math.games.tbnn_java.module.robotLogic.RobotLogicTbnnJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 等待開始狀態
public class StateWaitBeginTbnnJava extends AbstractStateTbnnJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateWaitBeginTbnnJava.class);
    public StateWaitBeginTbnnJava(TableTbnnJava table, LogicTbnnJava logic, RobotLogicTbnnJava aiLogic, GameAdjustTbnnJava gameAdjust, int stateId) {
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

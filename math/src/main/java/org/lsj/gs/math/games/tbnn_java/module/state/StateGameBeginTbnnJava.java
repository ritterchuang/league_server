package org.lsj.gs.math.games.tbnn_java.module.state;

import org.lsj.gs.math.core.common.table.entity.message.core.StcUpdateLogId;
import org.lsj.gs.math.games.tbnn_java.TableTbnnJava;
import org.lsj.gs.math.games.tbnn_java.entity.ConstTbnnJava;
import org.lsj.gs.math.games.tbnn_java.module.gameAdjust.GameAdjustTbnnJava;
import org.lsj.gs.math.games.tbnn_java.module.logic.LogicTbnnJava;
import org.lsj.gs.math.games.tbnn_java.module.robotLogic.RobotLogicTbnnJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

// 遊戲開始狀態
public class StateGameBeginTbnnJava extends AbstractStateTbnnJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateGameBeginTbnnJava.class);
    public StateGameBeginTbnnJava(TableTbnnJava table, LogicTbnnJava logic, RobotLogicTbnnJava aiLogic, GameAdjustTbnnJava gameAdjust, int stateId) {
        super(table, logic, aiLogic, gameAdjust, stateId);
    }

    @Override
    public void onEnter() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());
        // 1. 新增遊戲日誌
        super.addLogGameBegin(super.logic);

        // 2. 新增遊戲開始詳細日誌
        super.logic.addDetailGameBegin();

        // 3. 傳送局號
        LOG.info(super.table.getTableLogTitle() + " message: {}", new StcUpdateLogId(super.table.getRoundId()));
        super.table.sendRoundLogId();

        // 4. 設定開局動畫定時器
        super.table.getTableTimer().schedule(new TimerTaskTimeOut(this), ConstTbnnJava.TimeEnumTbnnJava.GAME_BEGIN.getCode(), ConstTbnnJava.TimeEnumTbnnJava.GAME_BEGIN.getMilliTimeSec());
    }

    @Override
    public void onLeave() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());
        // 清空定時器
        super.table.getTableTimer().cancel();
    }

    @Override
    public void onTimeout() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());
        super.iLeave();
    }

    // 超時定時器
    public class TimerTaskTimeOut extends TimerTask {
        private final StateGameBeginTbnnJava state;

        public TimerTaskTimeOut(StateGameBeginTbnnJava state){
            this.state = state;
        }

        @Override
        public void run() {
            this.state.onTimeout();
        }
    }
}

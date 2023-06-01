package org.lsj.gs.math.games.sg_java.module.state;

import org.lsj.gs.math.core.common.table.entity.message.core.StcUpdateLogId;
import org.lsj.gs.math.games.sg_java.TableSgJava;
import org.lsj.gs.math.games.sg_java.entity.ConstSgJava;
import org.lsj.gs.math.games.sg_java.module.gameAdjust.GameAdjustSgJava;
import org.lsj.gs.math.games.sg_java.module.logic.LogicSgJava;
import org.lsj.gs.math.games.sg_java.module.robotLogic.RobotLogicSgJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

// 新三公遊戲開始狀態
public class StateGameBeginSgJava extends AbstractStateSgJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateGameBeginSgJava.class);
    public StateGameBeginSgJava(TableSgJava table, LogicSgJava logic, RobotLogicSgJava aiLogic, GameAdjustSgJava gameAdjust, int stateId) {
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
        super.table.getTableTimer().schedule(new TimerTaskTimeOut(this), ConstSgJava.TimeEnumSgJava.GAME_BEGIN.getCode(), ConstSgJava.TimeEnumSgJava.GAME_BEGIN.getMilliTimeSec());
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
        private final StateGameBeginSgJava state;

        public TimerTaskTimeOut(StateGameBeginSgJava state){
            this.state = state;
        }

        @Override
        public void run() {
            this.state.onTimeout();
        }
    }
}

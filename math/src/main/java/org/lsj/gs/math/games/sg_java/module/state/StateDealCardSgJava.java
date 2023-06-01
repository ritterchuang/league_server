package org.lsj.gs.math.games.sg_java.module.state;

import org.lsj.gs.math.games.sg_java.TableSgJava;
import org.lsj.gs.math.games.sg_java.entity.ConstSgJava;
import org.lsj.gs.math.games.sg_java.entity.detail.DetailDealHand;
import org.lsj.gs.math.games.sg_java.entity.message.StcDealCardSgJava;
import org.lsj.gs.math.games.sg_java.module.gameAdjust.GameAdjustSgJava;
import org.lsj.gs.math.games.sg_java.module.logic.LogicSgJava;
import org.lsj.gs.math.games.sg_java.module.robotLogic.RobotLogicSgJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

// 新三公發牌狀態
public class StateDealCardSgJava extends AbstractStateSgJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateDealCardSgJava.class);
    private final int DEAL_COUNT_PER_PLAYER = 3; // 每人發牌數
    public StateDealCardSgJava(TableSgJava table, LogicSgJava logic, RobotLogicSgJava aiLogic, GameAdjustSgJava gameAdjust, int stateId) {
        super(table, logic, aiLogic, gameAdjust, stateId);
    }

    @Override
    public void onEnter() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());

        // 1. 發送手牌資訊
        StcDealCardSgJava stcDealCardSgJava = new StcDealCardSgJava(super.logic.dealBackCards(this.DEAL_COUNT_PER_PLAYER));
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcDealCardSgJava);
        super.table.sendMessageToHumanPlayer(stcDealCardSgJava);

        // 2. 紀錄詳細日誌
        super.logic.addDetail(new DetailDealHand("dealHand"));

        // 3. 設定定時器
        super.table.getTableTimer().schedule(new TimerTaskTimeOut(this), ConstSgJava.TimeEnumSgJava.DEAL_CARD.getCode(), ConstSgJava.TimeEnumSgJava.DEAL_CARD.getMilliTimeSec());
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
        private final StateDealCardSgJava state;

        public TimerTaskTimeOut(StateDealCardSgJava state){
            this.state = state;
        }

        @Override
        public void run() {
            this.state.onTimeout();
        }
    }
}

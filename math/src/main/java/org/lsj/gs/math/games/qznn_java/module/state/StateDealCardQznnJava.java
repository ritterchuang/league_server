package org.lsj.gs.math.games.qznn_java.module.state;

import org.lsj.gs.math.games.qznn_java.TableQznnJava;
import org.lsj.gs.math.games.qznn_java.entity.ConstQznnJava.TimeEnumQznnJava;
import org.lsj.gs.math.games.qznn_java.entity.detail.DetailDealHand;
import org.lsj.gs.math.games.qznn_java.entity.message.StcDealCardQznnJava;
import org.lsj.gs.math.games.qznn_java.module.gameAdjust.GameAdjustQznnJava;
import org.lsj.gs.math.games.qznn_java.module.logic.LogicQznnJava;
import org.lsj.gs.math.games.qznn_java.module.robotLogic.RobotLogicQznnJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

// 發牌狀態
public class StateDealCardQznnJava extends AbstractStateQznnJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateDealCardQznnJava.class);
    public StateDealCardQznnJava(TableQznnJava table, LogicQznnJava logic, RobotLogicQznnJava aiLogic, GameAdjustQznnJava gameAdjust, int stateId) {
        super(table, logic, aiLogic, gameAdjust, stateId);
    }

    @Override
    public void onEnter() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());
        // 1. 開啟調控發牌
        this.gameAdjust.startGameAdjust();

        // 2. 發送手牌資訊
        StcDealCardQznnJava stcDealCardQznnJava = new StcDealCardQznnJava(super.logic.getHumanListHandCardArray());
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcDealCardQznnJava);
        super.table.sendMessageToHumanPlayer(stcDealCardQznnJava);

        // 3. 紀錄日誌
        super.logic.getAllGamePlayerChairIndexList().forEach(chairIndex -> LOG.info(
                super.table.getTableLogTitle() + " chairIndex: {}, handCardNumber: {}",
                chairIndex,
                super.logic.getHandCardNumberArray(chairIndex)));

        // 4. 紀錄詳細日誌
        super.logic.addDetail(new DetailDealHand("dealHand"));

        // 5. 設定定時器
        super.table.getTableTimer().schedule(new TimerTaskTimeOut(this), TimeEnumQznnJava.DEAL_CARD.getCode(), TimeEnumQznnJava.DEAL_CARD.getMilliTimeSec());
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
        private final StateDealCardQznnJava state;

        public TimerTaskTimeOut(StateDealCardQznnJava state){
            this.state = state;
        }

        @Override
        public void run() {
            this.state.onTimeout();
        }
    }
}

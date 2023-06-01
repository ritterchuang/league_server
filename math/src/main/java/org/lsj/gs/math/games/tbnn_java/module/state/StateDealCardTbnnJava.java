package org.lsj.gs.math.games.tbnn_java.module.state;

import org.lsj.gs.math.games.tbnn_java.TableTbnnJava;
import org.lsj.gs.math.games.tbnn_java.entity.ConstTbnnJava;
import org.lsj.gs.math.games.tbnn_java.entity.detail.DetailDealHand;
import org.lsj.gs.math.games.tbnn_java.entity.message.StcDealCardTbnnJava;
import org.lsj.gs.math.games.tbnn_java.module.gameAdjust.GameAdjustTbnnJava;
import org.lsj.gs.math.games.tbnn_java.module.logic.LogicTbnnJava;
import org.lsj.gs.math.games.tbnn_java.module.robotLogic.RobotLogicTbnnJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

// 發牌狀態
public class StateDealCardTbnnJava extends AbstractStateTbnnJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateDealCardTbnnJava.class);
    public StateDealCardTbnnJava(TableTbnnJava table, LogicTbnnJava logic, RobotLogicTbnnJava aiLogic, GameAdjustTbnnJava gameAdjust, int stateId) {
        super(table, logic, aiLogic, gameAdjust, stateId);
    }

    @Override
    public void onEnter() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());
        // 1. 開啟調控發牌
        this.gameAdjust.startGameAdjust();

        // 2. 發送手牌資訊
        StcDealCardTbnnJava stcDealCardTbnnJava = new StcDealCardTbnnJava(super.logic.getHumanListHandCardArray());
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcDealCardTbnnJava);
        super.table.sendMessageToHumanPlayer(stcDealCardTbnnJava);

        // 3. 紀錄日誌
        super.logic.getAllGamePlayerChairIndexList().forEach(chairIndex -> LOG.info(
                super.table.getTableLogTitle() + " chairIndex: {}, handCardNumber: {}",
                chairIndex,
                super.logic.getHandCardNumberArray(chairIndex)));

        // 4. 紀錄詳細日誌
        super.logic.addDetail(new DetailDealHand("dealHand"));

        // 5. 設定定時器
        super.table.getTableTimer().schedule(new TimerTaskTimeOut(this), ConstTbnnJava.TimeEnumTbnnJava.DEAL_CARD.getCode(), ConstTbnnJava.TimeEnumTbnnJava.DEAL_CARD.getMilliTimeSec());
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
        private final StateDealCardTbnnJava state;

        public TimerTaskTimeOut(StateDealCardTbnnJava state){
            this.state = state;
        }

        @Override
        public void run() {
            this.state.onTimeout();
        }
    }
}

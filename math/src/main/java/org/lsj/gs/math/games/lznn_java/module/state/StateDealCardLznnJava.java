package org.lsj.gs.math.games.lznn_java.module.state;

import org.lsj.gs.math.games.lznn_java.TableLznnJava;
import org.lsj.gs.math.games.lznn_java.entity.ConstLznnJava;
import org.lsj.gs.math.games.lznn_java.entity.detail.DetailDealHand;
import org.lsj.gs.math.games.lznn_java.entity.message.StcDealCardLznnJava;
import org.lsj.gs.math.games.lznn_java.module.gameAdjust.GameAdjustLznnJava;
import org.lsj.gs.math.games.lznn_java.module.logic.LogicLzNnJava;
import org.lsj.gs.math.games.lznn_java.module.robotLogic.RobotLogicLznnJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

// 發牌狀態
public class StateDealCardLznnJava extends AbstractStateLznnJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateDealCardLznnJava.class);
    public StateDealCardLznnJava(TableLznnJava table, LogicLzNnJava logic, RobotLogicLznnJava aiLogic, GameAdjustLznnJava gameAdjust, int stateId) {
        super(table, logic, aiLogic, gameAdjust, stateId);
    }

    @Override
    public void onEnter() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());
        // 1. 開啟調控發牌
        this.gameAdjust.startGameAdjust();

        // 2. 發送手牌資訊
        StcDealCardLznnJava stcDealCardLznnJava = new StcDealCardLznnJava(super.logic.getHumanListHandCardArray());
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcDealCardLznnJava);
        super.table.sendMessageToHumanPlayer(stcDealCardLznnJava);

        // 3. 紀錄日誌
        super.logic.getAllGamePlayerChairIndexList().forEach(chairIndex -> LOG.info(
                super.table.getTableLogTitle() + " chairIndex: {}, handCardNumber: {}",
                chairIndex,
                super.logic.getHandCardNumberArray(chairIndex)));

        // 4. 紀錄詳細日誌
        super.logic.addDetail(new DetailDealHand("dealHand"));

        // 5. 設定定時器
        super.table.getTableTimer().schedule(new TimerTaskTimeOut(this), ConstLznnJava.TimeEnumLznnJava.DEAL_CARD.getCode(), ConstLznnJava.TimeEnumLznnJava.DEAL_CARD.getMilliTimeSec());
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
        private final StateDealCardLznnJava state;

        public TimerTaskTimeOut(StateDealCardLznnJava state){
            this.state = state;
        }

        @Override
        public void run() {
            this.state.onTimeout();
        }
    }
}

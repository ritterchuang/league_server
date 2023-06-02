package org.lsj.gs.math.games.ebg_java.module.state;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.games.ebg_java.TableEbgJava;
import org.lsj.gs.math.games.ebg_java.entity.ConstEbgJava;
import org.lsj.gs.math.games.ebg_java.entity.detail.DetailDealHand;
import org.lsj.gs.math.games.ebg_java.entity.message.StcDealCardEbgJava;
import org.lsj.gs.math.games.ebg_java.module.gameAdjust.GameAdjustEbgJava;
import org.lsj.gs.math.games.ebg_java.module.logic.LogicEbgJava;
import org.lsj.gs.math.games.ebg_java.module.robotLogic.RobotLogicEbgJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

// 發牌狀態
public class StateDealCardEbgJava extends AbstractStateEbgJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateDealCardEbgJava.class);
    private final int DEAL_COUNT_PER_PLAYER = 2; // 每人發牌數
    private final ITableUtil tableUtil;

    public StateDealCardEbgJava(TableEbgJava table, LogicEbgJava logic, RobotLogicEbgJava aiLogic, GameAdjustEbgJava gameAdjust, ITableUtil tableUtil, int stateId) {
        super(table, logic, aiLogic, gameAdjust, stateId);
        this.tableUtil = tableUtil;
    }

    @Override
    public void onEnter() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());
        // 1. 開啟調控發牌
        this.gameAdjust.startGameAdjust();

        // 2. 發送手牌資訊
        StcDealCardEbgJava stcDealCard = new StcDealCardEbgJava(super.logic.dealBackCards(this.DEAL_COUNT_PER_PLAYER), this.calculateDiceResult());
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcDealCard);
        super.table.sendMessageToHumanPlayer(stcDealCard);

        // 3. 紀錄日誌
        super.logic.getAllGamePlayerChairIndexList().forEach(chairIndex -> LOG.info(
                super.table.getTableLogTitle() + " chairIndex: {}, handCardNumber: {}",
                chairIndex,
                super.logic.getHandCardNumberArray(chairIndex)));

        // 4. 紀錄詳細日誌
        super.logic.addDetail(new DetailDealHand("dealHand"));

        // 5. 設定定時器
        super.table.getTableTimer().schedule(new TimerTaskTimeOut(this), ConstEbgJava.TimeEnumEbgJava.DEAL_CARD.getCode(), ConstEbgJava.TimeEnumEbgJava.DEAL_CARD.getMilliTimeSec());
    }

    // 計算骰子結果
    private int[] calculateDiceResult() {
        List<Integer> result = new ArrayList<>();

        for (int diceIndex = 0; diceIndex < 2; diceIndex++) {
            int diceNumber = this.tableUtil.getNotSupportSetRngDataRandomUtil().getRandomIntWithAccuracy(6, ConstMathCommon.AccuracyType.A32768) + 1;
            result.add(diceNumber);
        }

        return result.stream().mapToInt(x -> x).toArray();
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
        private final StateDealCardEbgJava state;

        public TimerTaskTimeOut(StateDealCardEbgJava state){
            this.state = state;
        }

        @Override
        public void run() {
            this.state.onTimeout();
        }
    }
}

package com.lx.gs.math.games.qzpj_java.module.state;

import com.lx.gs.math.core.common.ConstMathCommon;
import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.games.qzpj_java.TableQzpjJava;
import com.lx.gs.math.games.qzpj_java.entity.ConstQzpjJava;
import com.lx.gs.math.games.qzpj_java.entity.detail.DetailDealHand;
import com.lx.gs.math.games.qzpj_java.entity.message.StcDealCardQzpjJava;
import com.lx.gs.math.games.qzpj_java.module.gameAdjust.GameAdjustQzpjJava;
import com.lx.gs.math.games.qzpj_java.module.logic.LogicQzpjJava;
import com.lx.gs.math.games.qzpj_java.module.robotLogic.RobotLogicQzpjJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

// 發牌狀態
public class StateDealCardQzpjJava extends AbstractStateQzpjJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateDealCardQzpjJava.class);
    private final int DEAL_COUNT_PER_PLAYER = 2; // 每人發牌數
    private final ITableUtil tableUtil;

    public StateDealCardQzpjJava(TableQzpjJava table, LogicQzpjJava logic, RobotLogicQzpjJava aiLogic, GameAdjustQzpjJava gameAdjust, ITableUtil tableUtil, int stateId) {
        super(table, logic, aiLogic, gameAdjust, stateId);
        this.tableUtil = tableUtil;
    }

    @Override
    public void onEnter() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());
        // 1. 開啟調控發牌
        this.gameAdjust.startGameAdjust();

        // 2. 發送手牌資訊
        StcDealCardQzpjJava stcDealCard = new StcDealCardQzpjJava(super.logic.dealBackCards(this.DEAL_COUNT_PER_PLAYER), this.calculateDiceResult());
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
        super.table.getTableTimer().schedule(new TimerTaskTimeOut(this), ConstQzpjJava.TimeEnumQzpjJava.DEAL_CARD.getCode(), ConstQzpjJava.TimeEnumQzpjJava.DEAL_CARD.getMilliTimeSec());
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
        private final StateDealCardQzpjJava state;

        public TimerTaskTimeOut(StateDealCardQzpjJava state){
            this.state = state;
        }

        @Override
        public void run() {
            this.state.onTimeout();
        }
    }
}

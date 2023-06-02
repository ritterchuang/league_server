package org.lsj.gs.math.games.qznn_ksz_java.module.state;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.games.qznn_ksz_java.TableQznnKszJava;
import org.lsj.gs.math.games.qznn_ksz_java.entity.ConstQznnKszJava.TimeEnumQznnKszJava;
import org.lsj.gs.math.games.qznn_ksz_java.entity.detail.DetailDealHand;
import org.lsj.gs.math.games.qznn_ksz_java.entity.message.StcDealCardQznnKszJava;
import org.lsj.gs.math.games.qznn_ksz_java.module.gameAdjust.GameAdjustQznnKszJava;
import org.lsj.gs.math.games.qznn_ksz_java.module.logic.LogicQznnKszJava;
import org.lsj.gs.math.games.qznn_ksz_java.module.robotLogic.RobotLogicQznnKszJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.stream.Collectors;

// 發牌狀態
public class StateDealCardQznnKszJava extends AbstractStateQznnKszJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateDealCardQznnKszJava.class);
    private final int DEAL_COUNT_PER_PLAYER = 3; // 每人發牌數
    public StateDealCardQznnKszJava(TableQznnKszJava table, LogicQznnKszJava logic, RobotLogicQznnKszJava aiLogic, GameAdjustQznnKszJava gameAdjust, int stateId) {
        super(table, logic, aiLogic, gameAdjust, stateId);
    }

    @Override
    public void onEnter() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());

        // 1. 發前三張牌
        this.dealCard();

        // 2. 發送手牌資訊
        StcDealCardQznnKszJava stcDealCardQznnKszJava = new StcDealCardQznnKszJava(super.logic.getHumanListHandCardArray());
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcDealCardQznnKszJava);
        super.table.sendMessageToHumanPlayer(stcDealCardQznnKszJava);

        // 3. 紀錄日誌
        super.logic.getAllGamePlayerChairIndexList().forEach(chairIndex -> LOG.info(
                super.table.getTableLogTitle() + " chairIndex: {}, handCardNumber: {}",
                chairIndex,
                super.logic.getHandCardNumberArray(chairIndex)));

        // 4. 紀錄詳細日誌
        super.logic.addDetail(new DetailDealHand("dealHand"));

        // 5. 設定定時器
        super.table.getTableTimer().schedule(new TimerTaskTimeOut(this), TimeEnumQznnKszJava.DEAL_CARD.getCode(), TimeEnumQznnKszJava.DEAL_CARD.getMilliTimeSec());
    }

    private void dealCard(){
        // 1. 取得玩家列表
        List<Integer> allGamePlayerChairIndexList = this.logic.getAllGamePlayerChairIndexList();

        // 2. 洗牌
        this.logic.shuffleCardWall();

        // 3. 發未取牌
        List<ICard> unTakenCardList = this.logic.dealUnTakenCardList(this.DEAL_COUNT_PER_PLAYER * allGamePlayerChairIndexList.size());

        // 4. 分牌
        Map<Integer, List<ICard>> unTakenCardListMap = new HashMap<>();
        allGamePlayerChairIndexList.forEach(chairIndex ->
                unTakenCardListMap.put(
                        chairIndex,
                        unTakenCardList.stream().skip((long) unTakenCardListMap.size() * this.DEAL_COUNT_PER_PLAYER).limit(this.DEAL_COUNT_PER_PLAYER).collect(Collectors.toList())));

        // 5. 將牌改成取出狀態
        unTakenCardListMap.values().forEach(cardList -> cardList.forEach(this.logic::getAppointObjCard));

        // 6. 紀錄手牌
        this.logic.addPlayerHandCardListMap(unTakenCardListMap);
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
        private final StateDealCardQznnKszJava state;

        public TimerTaskTimeOut(StateDealCardQznnKszJava state){
            this.state = state;
        }

        @Override
        public void run() {
            this.state.onTimeout();
        }
    }
}

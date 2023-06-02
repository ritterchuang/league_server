package org.lsj.gs.math.games.qznn_k4z_java.module.state;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.games.qznn_k4z_java.TableQznnK4zJava;
import org.lsj.gs.math.games.qznn_k4z_java.entity.ConstQznnK4zJava;
import org.lsj.gs.math.games.qznn_k4z_java.entity.detail.DetailDealHand;
import org.lsj.gs.math.games.qznn_k4z_java.entity.message.StcDealCardQznnK4zJava;
import org.lsj.gs.math.games.qznn_k4z_java.module.gameAdjust.GameAdjustQznnK4zJava;
import org.lsj.gs.math.games.qznn_k4z_java.module.logic.LogicQznnK4zJava;
import org.lsj.gs.math.games.qznn_k4z_java.module.robotLogic.RobotLogicQznnK4zJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.stream.Collectors;

// 發牌狀態
public class StateDealCardQznnK4zJava extends AbstractStateQznnK4zJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateDealCardQznnK4zJava.class);
    private final int DEAL_COUNT_PER_PLAYER = 4; // 每人發牌數
    public StateDealCardQznnK4zJava(TableQznnK4zJava table, LogicQznnK4zJava logic, RobotLogicQznnK4zJava aiLogic, GameAdjustQznnK4zJava gameAdjust, int stateId) {
        super(table, logic, aiLogic, gameAdjust, stateId);
    }

    @Override
    public void onEnter() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());

        // 1. 發前四張牌
        this.dealCard();

        // 2. 發送手牌資訊
        StcDealCardQznnK4zJava stcDealCardQznnK4zJava = new StcDealCardQznnK4zJava(super.logic.getHumanListHandCardArray());
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcDealCardQznnK4zJava);
        super.table.sendMessageToHumanPlayer(stcDealCardQznnK4zJava);

        // 3. 紀錄日誌
        super.logic.getAllGamePlayerChairIndexList().forEach(chairIndex -> LOG.info(
                super.table.getTableLogTitle() + " chairIndex: {}, handCardNumber: {}",
                chairIndex,
                super.logic.getHandCardNumberArray(chairIndex)));

        // 4. 紀錄詳細日誌
        super.logic.addDetail(new DetailDealHand("dealHand"));

        // 5. 設定定時器
        super.table.getTableTimer().schedule(new TimerTaskTimeOut(this), ConstQznnK4zJava.TimeEnumQznnK4zJava.DEAL_CARD.getCode(), ConstQznnK4zJava.TimeEnumQznnK4zJava.DEAL_CARD.getMilliTimeSec());
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
        private final StateDealCardQznnK4zJava state;

        public TimerTaskTimeOut(StateDealCardQznnK4zJava state){
            this.state = state;
        }

        @Override
        public void run() {
            this.state.onTimeout();
        }
    }
}

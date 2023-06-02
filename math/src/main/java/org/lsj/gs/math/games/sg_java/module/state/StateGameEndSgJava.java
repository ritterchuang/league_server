package org.lsj.gs.math.games.sg_java.module.state;

import org.lsj.gs.event.TableFinishedEvent;
import org.lsj.gs.math.games.sg_java.TableSgJava;
import org.lsj.gs.math.games.sg_java.entity.ConstSgJava;
import org.lsj.gs.math.games.sg_java.entity.message.StcSettlementSgJava;
import org.lsj.gs.math.games.sg_java.entity.message.data.StcSettlementDataSgJava;
import org.lsj.gs.math.games.sg_java.module.gameAdjust.GameAdjustSgJava;
import org.lsj.gs.math.games.sg_java.module.logic.LogicSgJava;
import org.lsj.gs.math.games.sg_java.module.robotLogic.RobotLogicSgJava;
import org.lsj.utils.MathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Event;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

// 新三公遊戲結束狀態
public class StateGameEndSgJava extends AbstractStateSgJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateGameEndSgJava.class);
    private final Event<TableFinishedEvent> event;
    public StateGameEndSgJava(TableSgJava table, LogicSgJava logic, RobotLogicSgJava aiLogic, GameAdjustSgJava gameAdjust, int stateId, Event<TableFinishedEvent> event) {
        super(table, logic, aiLogic, gameAdjust, stateId);
        this.event = event;
    }

    @Override
    public void onEnter() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());
        // 1. 傳送玩家餘額
        this.sendUpdateUser();

        // 2. 傳送所有玩家餘額
        this.sendSettlement();

        // 3. 牌桌結束
        super.table.getTableTimer().schedule(new TimerTaskTimeOut(this), ConstSgJava.TimeEnumSgJava.END_GAME.getCode(), ConstSgJava.TimeEnumSgJava.END_GAME.getMilliTimeSec());
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

    // 傳送玩家餘額
    private void sendUpdateUser() {
        super.table.sendUpdateUser(
                super.logic.getHumanUid(),
                MathUtil.moneyScale(MathUtil.add(super.logic.getHumanPlayerScore(), super.logic.getHumanPlayerBeginMoney()))
        );
    }

    // 傳送所有玩家餘額
    private void sendSettlement() {
        // 1. 計算所有玩家輸贏結果
        List<StcSettlementDataSgJava> stcSettlementDataList = new ArrayList<>();
        super.table.getAllGamePlayerList().forEach(player -> stcSettlementDataList.add(
                new StcSettlementDataSgJava(
                        player.getUid(),
                        MathUtil.moneyScale(super.logic.getPlayerScore(player.getChairIndex())),
                        MathUtil.moneyScale(MathUtil.add(player.getBeginMoney() , super.logic.getPlayerScore(player.getChairIndex()))))));

        // 2. 傳送
        StcSettlementSgJava stcSettlementSgJava = new StcSettlementSgJava(stcSettlementDataList.toArray(StcSettlementDataSgJava[]::new));
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcSettlementSgJava);
        super.table.sendMessageToHumanPlayer(stcSettlementSgJava);
    }


    //* 定時器相關 *//
    // 超時定時器
    public class TimerTaskTimeOut extends TimerTask {
        private final StateGameEndSgJava state;

        public TimerTaskTimeOut(StateGameEndSgJava state){
            this.state = state;
        }

        @Override
        public void run() {
            this.state.event.fire(new TableFinishedEvent(this.state.table.getGamePlayerHlr().getHumanGamePlayer().getUid(), this.state.table.getRoundId()));
        }
    }
}

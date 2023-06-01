package org.lsj.gs.math.games.ebg_java.module.state;

import org.lsj.gs.event.TableFinishedEvent;
import org.lsj.gs.math.games.ebg_java.TableEbgJava;
import org.lsj.gs.math.games.ebg_java.entity.ConstEbgJava;
import org.lsj.gs.math.games.ebg_java.entity.message.StcSettlementEbgJava;
import org.lsj.gs.math.games.ebg_java.entity.message.data.StcSettlementDataEbgJava;
import org.lsj.gs.math.games.ebg_java.module.gameAdjust.GameAdjustEbgJava;
import org.lsj.gs.math.games.ebg_java.module.logic.LogicEbgJava;
import org.lsj.gs.math.games.ebg_java.module.robotLogic.RobotLogicEbgJava;
import org.lsj.utils.MathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Event;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

// 遊戲結束狀態
public class StateGameEndEbgJava extends AbstractStateEbgJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateGameEndEbgJava.class);
    private final Event<TableFinishedEvent> event;
    public StateGameEndEbgJava(TableEbgJava table, LogicEbgJava logic, RobotLogicEbgJava aiLogic, GameAdjustEbgJava gameAdjust, int stateId, Event<TableFinishedEvent> event) {
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
        super.table.getTableTimer().schedule(new TimerTaskTimeOut(this), ConstEbgJava.TimeEnumEbgJava.END_GAME.getCode(), ConstEbgJava.TimeEnumEbgJava.END_GAME.getMilliTimeSec());
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
        List<StcSettlementDataEbgJava> stcSettlementDataList = new ArrayList<>();
        super.table.getAllGamePlayerList().forEach(player -> stcSettlementDataList.add(
                new StcSettlementDataEbgJava(
                        player.getUid(),
                        MathUtil.moneyScale(super.logic.getPlayerScore(player.getChairIndex())),
                        MathUtil.moneyScale(MathUtil.add(player.getBeginMoney() , super.logic.getPlayerScore(player.getChairIndex()))))));

        // 2. 傳送
        StcSettlementEbgJava stcSettlement = new StcSettlementEbgJava(stcSettlementDataList.toArray(StcSettlementDataEbgJava[]::new));
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcSettlement);
        super.table.sendMessageToHumanPlayer(stcSettlement);
    }


    //* 定時器相關 *//
    // 超時定時器
    public class TimerTaskTimeOut extends TimerTask {
        private final StateGameEndEbgJava state;

        public TimerTaskTimeOut(StateGameEndEbgJava state){
            this.state = state;
        }

        @Override
        public void run() {
            this.state.event.fire(new TableFinishedEvent(this.state.table.getGamePlayerHlr().getHumanGamePlayer().getUid(), this.state.table.getRoundId()));
        }
    }
}

package org.lsj.gs.math.games.zjh_java.module.state;

import org.lsj.gs.event.TableFinishedEvent;
import org.lsj.gs.math.games.zjh_java.TableZjhJava;
import org.lsj.gs.math.games.zjh_java.entity.ConstZjhJava;
import org.lsj.gs.math.games.zjh_java.entity.message.StcSettlementZjhJava;
import org.lsj.gs.math.games.zjh_java.entity.message.data.StcSettlementDataZjhJava;
import org.lsj.gs.math.games.zjh_java.module.gameAdjust.GameAdjustZjhJava;
import org.lsj.gs.math.games.zjh_java.module.logic.LogicZjhJava;
import org.lsj.gs.math.games.zjh_java.module.robotLogic.RobotLogicZjhJava;
import org.lsj.utils.MathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Event;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

// 遊戲結束狀態
public class StateGameEndZjhJava extends AbstractStateZjhJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateGameEndZjhJava.class);
    private final Event<TableFinishedEvent> event;
    public StateGameEndZjhJava(TableZjhJava table, LogicZjhJava logic, RobotLogicZjhJava aiLogic, GameAdjustZjhJava gameAdjust, int stateId, Event<TableFinishedEvent> event) {
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
        super.table.getTableTimer().schedule(new TimerTaskTimeOut(this), ConstZjhJava.TimeEnumZjhJava.END_GAME.getCode(), ConstZjhJava.TimeEnumZjhJava.END_GAME.getMilliTimeSec());
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
        List<StcSettlementDataZjhJava> stcSettlementDataList = new ArrayList<>();
        super.table.getAllGamePlayerList().forEach(player -> stcSettlementDataList.add(
                new StcSettlementDataZjhJava(
                        player.getUid(),
                        MathUtil.moneyScale(super.logic.getPlayerScore(player.getChairIndex())),
                        MathUtil.moneyScale(MathUtil.add(player.getBeginMoney() , super.logic.getPlayerScore(player.getChairIndex()))))));

        // 2. 傳送
        StcSettlementZjhJava stcSettlement = new StcSettlementZjhJava(stcSettlementDataList.toArray(StcSettlementDataZjhJava[]::new));
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcSettlement);
        super.table.sendMessageToHumanPlayer(stcSettlement);
    }


    //* 定時器相關 *//
    // 超時定時器
    public class TimerTaskTimeOut extends TimerTask {
        private final StateGameEndZjhJava state;

        public TimerTaskTimeOut(StateGameEndZjhJava state){
            this.state = state;
        }

        @Override
        public void run() {
            this.state.event.fire(new TableFinishedEvent(this.state.table.getGamePlayerHlr().getHumanGamePlayer().getUid(), this.state.table.getRoundId()));
        }
    }
}

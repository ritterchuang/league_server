package org.lsj.gs.math.games.lznn_java.module.state;

import org.lsj.gs.event.TableFinishedEvent;
import org.lsj.gs.math.games.lznn_java.TableLznnJava;
import org.lsj.gs.math.games.lznn_java.entity.ConstLznnJava;
import org.lsj.gs.math.games.lznn_java.entity.message.StcSettlementLznnJava;
import org.lsj.gs.math.games.lznn_java.entity.message.data.StcSettlementDataLznnJava;
import org.lsj.gs.math.games.lznn_java.module.gameAdjust.GameAdjustLznnJava;
import org.lsj.gs.math.games.lznn_java.module.logic.LogicLzNnJava;
import org.lsj.gs.math.games.lznn_java.module.robotLogic.RobotLogicLznnJava;
import org.lsj.utils.MathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Event;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

// 遊戲結束狀態
public class StateGameEndLznnJava extends AbstractStateLznnJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateGameEndLznnJava.class);
    private final Event<TableFinishedEvent> event;
    public StateGameEndLznnJava(TableLznnJava table, LogicLzNnJava logic, RobotLogicLznnJava aiLogic, GameAdjustLznnJava gameAdjust, int stateId, Event<TableFinishedEvent> event) {
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
        super.table.getTableTimer().schedule(new TimerTaskTimeOut(this), ConstLznnJava.TimeEnumLznnJava.END_GAME.getCode(), ConstLznnJava.TimeEnumLznnJava.END_GAME.getMilliTimeSec());
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
        List<StcSettlementDataLznnJava> stcSettlementDataList = new ArrayList<>();
        super.table.getAllGamePlayerList().forEach(player -> stcSettlementDataList.add(
                new StcSettlementDataLznnJava(
                        player.getUid(),
                        MathUtil.moneyScale(super.logic.getPlayerScore(player.getChairIndex())),
                        MathUtil.moneyScale(MathUtil.add(player.getBeginMoney() , super.logic.getPlayerScore(player.getChairIndex()))))));

        // 2. 傳送
        StcSettlementLznnJava stcSettlementLznnJava = new StcSettlementLznnJava(stcSettlementDataList.toArray(StcSettlementDataLznnJava[]::new));
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcSettlementLznnJava);
        super.table.sendMessageToHumanPlayer(stcSettlementLznnJava);
    }


    //* 定時器相關 *//
    // 超時定時器
    public class TimerTaskTimeOut extends TimerTask {
        private final StateGameEndLznnJava state;

        public TimerTaskTimeOut(StateGameEndLznnJava state){
            this.state = state;
        }

        @Override
        public void run() {
            this.state.event.fire(new TableFinishedEvent(this.state.table.getGamePlayerHlr().getHumanGamePlayer().getUid(), this.state.table.getRoundId()));
        }
    }
}

package org.lsj.gs.math.games.qznn_k4z_java.module.state;

import org.lsj.gs.event.TableFinishedEvent;
import org.lsj.gs.math.games.qznn_k4z_java.TableQznnK4zJava;
import org.lsj.gs.math.games.qznn_k4z_java.entity.ConstQznnK4zJava;
import org.lsj.gs.math.games.qznn_k4z_java.entity.message.StcSettlementQznnK4zJava;
import org.lsj.gs.math.games.qznn_k4z_java.entity.message.data.StcSettlementDataQznnK4zJava;
import org.lsj.gs.math.games.qznn_k4z_java.module.gameAdjust.GameAdjustQznnK4zJava;
import org.lsj.gs.math.games.qznn_k4z_java.module.logic.LogicQznnK4zJava;
import org.lsj.gs.math.games.qznn_k4z_java.module.robotLogic.RobotLogicQznnK4zJava;
import org.lsj.utils.MathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Event;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

// 遊戲結束狀態
public class StateGameEndQznnK4zJava extends AbstractStateQznnK4zJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateGameEndQznnK4zJava.class);
    private final Event<TableFinishedEvent> event;
    public StateGameEndQznnK4zJava(TableQznnK4zJava table, LogicQznnK4zJava logic, RobotLogicQznnK4zJava aiLogic, GameAdjustQznnK4zJava gameAdjust, int stateId, Event<TableFinishedEvent> event) {
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
        super.table.getTableTimer().schedule(new TimerTaskTimeOut(this), ConstQznnK4zJava.TimeEnumQznnK4zJava.END_GAME.getCode(), ConstQznnK4zJava.TimeEnumQznnK4zJava.END_GAME.getMilliTimeSec());
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
        List<StcSettlementDataQznnK4zJava> stcSettlementDataList = new ArrayList<>();
        super.table.getAllGamePlayerList().forEach(player -> stcSettlementDataList.add(
                new StcSettlementDataQznnK4zJava(
                        player.getUid(),
                        MathUtil.moneyScale(super.logic.getPlayerScore(player.getChairIndex())),
                        MathUtil.moneyScale(MathUtil.add(player.getBeginMoney() , super.logic.getPlayerScore(player.getChairIndex()))))));

        // 2. 傳送
        StcSettlementQznnK4zJava stcSettlementQznnK4zJava = new StcSettlementQznnK4zJava(stcSettlementDataList.toArray(StcSettlementDataQznnK4zJava[]::new));
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcSettlementQznnK4zJava);
        super.table.sendMessageToHumanPlayer(stcSettlementQznnK4zJava);
    }


    //* 定時器相關 *//
    // 超時定時器
    public class TimerTaskTimeOut extends TimerTask {
        private final StateGameEndQznnK4zJava state;

        public TimerTaskTimeOut(StateGameEndQznnK4zJava state){
            this.state = state;
        }

        @Override
        public void run() {
            this.state.event.fire(new TableFinishedEvent(this.state.table.getGamePlayerHlr().getHumanGamePlayer().getUid(), this.state.table.getRoundId()));
        }
    }
}

package org.lsj.gs.math.games.sg_java.module.state;

import org.lsj.gs.math.core.common.lock.FinishLock;
import org.lsj.gs.math.core.common.robotLogic.entity.RobotSelectResultBanker;
import org.lsj.gs.math.games.sg_java.TableSgJava;
import org.lsj.gs.math.games.sg_java.entity.ConstSgJava;
import org.lsj.gs.math.games.sg_java.entity.message.CtsSelectSgJava;
import org.lsj.gs.math.games.sg_java.entity.message.StcSelectSgJava;
import org.lsj.gs.math.games.sg_java.entity.message.StcStartShowSgJava;
import org.lsj.gs.math.games.sg_java.module.gameAdjust.GameAdjustSgJava;
import org.lsj.gs.math.games.sg_java.module.logic.LogicSgJava;
import org.lsj.gs.math.games.sg_java.module.robotLogic.RobotLogicSgJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.locks.ReentrantLock;

// 新三公選牌狀態
public class StateSelectSgJava extends AbstractStateSgJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateSelectSgJava.class);
    private final FinishLock finishLock = new FinishLock(new ReentrantLock()); // 完成鎖

    public StateSelectSgJava(TableSgJava table, LogicSgJava logic, RobotLogicSgJava aiLogic, GameAdjustSgJava gameAdjust, int stateId) {
        super(table, logic, aiLogic, gameAdjust, stateId);
    }

    @Override
    public void onEnter() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());

        // 1. 開啟調控發牌
        this.gameAdjust.startGameAdjust();

        // 2. 通知客戶端選牌
        StcStartShowSgJava stcStartShowSgJava = new StcStartShowSgJava(ConstSgJava.TimeEnumSgJava.SELECT.getTimeSec());
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcStartShowSgJava);
        super.table.sendMessageToHumanPlayer(stcStartShowSgJava);
        
        // 3. 設置機器人選牌定時器
        this.doRobotSelect();

        // 4. 設定定時器
        super.table.getTableTimer().schedule(new TimerTaskTimeOut(this), ConstSgJava.TimeEnumSgJava.SELECT.getCode(), ConstSgJava.TimeEnumSgJava.SELECT.getMilliTimeSec());
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

        try{
            // 1. 上鎖
            this.finishLock.lock();

            // 2. 已完成則不做事
            if(this.finishLock.isFinish()){
                LOG.warn(super.table.getTableLogTitle() + " onTimeout isFinish");
                return;
            }

            // 3. 紀錄已完成
            this.finishLock.setFinish(true);

            // 4. 結束選牌
            this.finishSelect();
        } finally {
            // 5. 解鎖
            this.finishLock.unlock();
        }
    }


    //* 流程相關 *//
    // 玩家選牌
    public void onMessageSelect(int chairIndex, CtsSelectSgJava data) {
        LOG.info(super.table.getTableLogTitle() + " chairIndex:{}, data:{}", chairIndex, data);

        try{
            // 1. 上鎖
            this.finishLock.lock();

            // 2. 已完成則不做事
            if(this.finishLock.isFinish()){
                LOG.warn(super.table.getTableLogTitle() + " onMessageSelect isFinish");
                return;
            }

            // 3. 檢查座位是否正確
            if (!super.logic.isValidChairIndex(chairIndex)) {
                LOG.error(super.table.getTableLogTitle() + " error: {}, chairIndex: {}", "chairIndex not correct", chairIndex);
                return;
            }

            // 4. 檢查玩家是否選過
            if (super.logic.isPlayerSelect(chairIndex)) {
                LOG.warn(super.table.getTableLogTitle() + " error: {}, chairIndex: {}", "repeat select", chairIndex);
                return;
            }

            // 5. 檢查是否過早選牌
            if (super.logic.isEarlyPlayerSelect(chairIndex)) {
                LOG.warn(super.table.getTableLogTitle() + " error: {}, chairIndex: {}", "early select", chairIndex);
                return;
            }

            // 6. 設定指定座位選牌結果
            super.logic.setPlayerSelectResult(chairIndex);

            // 7. 傳送選牌結果
            this.sendPlayerCardType(chairIndex);

            // 8. 檢查是否結束選牌
            if (super.logic.isFinishSelect()) {
                // 9. 紀錄已完成
                this.finishLock.setFinish(true);

                // 10. 結束選牌
                this.finishSelect();
            }
        } finally {
            // 11. 解鎖
            this.finishLock.unlock();
        }
    }

    // 設置機器人選牌定時
    private void doRobotSelect() {
        // 1. 計算機器人結果
        Map<Integer, RobotSelectResultBanker> robotResult = this.robotLogic.generateRobotSelectResult();

        // 2. 設置定時器觸發
        robotResult.forEach((key, value) -> this.table.getTableTimer().schedule(new TimerTaskRobotSelect(this, key, value.getType()), value.getMilliTimeSec()));
    }

    // 結束選牌
    private void finishSelect() {
        // 1. 清空計時器
        super.table.getTableTimer().cancel();

        // 2. 結束選牌
        super.logic.finishSelect();

        // 3. 紀錄牌型結果
        LOG.info(super.table.getTableLogTitle() + " allPlayerSelectType: {}", super.logic.getAllPlayerSelectTypeArray());

        // 4. 結束狀態
        super.iLeave();
    }


    //* 傳送相關 *//
    // 傳送真人傳送最大牌型


    // 傳送玩家選牌結果
    private void sendPlayerCardType(int chairIndex) {
        StcSelectSgJava stcSelectSgJava = new StcSelectSgJava(
                chairIndex,
                super.logic.getHandCardNumberArray(chairIndex),
                super.logic.getPlayerSelectType(chairIndex));
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcSelectSgJava);
        super.table.sendMessageToHumanPlayer(stcSelectSgJava);
    }


    //* 定時器相關 *//
    // 超時定時器
    public class TimerTaskTimeOut extends TimerTask {
        private final StateSelectSgJava state;

        public TimerTaskTimeOut(StateSelectSgJava state){
            this.state = state;
        }

        @Override
        public void run() {
            this.state.onTimeout();
        }
    }

    // 機器人選牌定時器
    public class TimerTaskRobotSelect extends TimerTask {
        private final StateSelectSgJava state;
        private final int chairIndex; // 座位
        private final int type; // 動作類型

        public TimerTaskRobotSelect(StateSelectSgJava state, int chairIndex, int type){
            this.state = state;
            this.chairIndex = chairIndex;
            this.type = type;
        }

        @Override
        public void run() {
            this.state.onMessageSelect(this.chairIndex, new CtsSelectSgJava());
        }
    }
}

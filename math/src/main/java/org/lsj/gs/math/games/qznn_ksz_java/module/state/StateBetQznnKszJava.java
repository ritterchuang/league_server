package org.lsj.gs.math.games.qznn_ksz_java.module.state;

import org.lsj.gs.math.core.common.lock.FinishLock;
import org.lsj.gs.math.core.common.robotLogic.entity.RobotRateResultBanker;
import org.lsj.gs.math.core.common.table.entity.detail.DetailState;
import org.lsj.gs.math.games.qznn_ksz_java.TableQznnKszJava;
import org.lsj.gs.math.games.qznn_ksz_java.entity.ConstQznnKszJava.OperationEnumQznnKszJava;
import org.lsj.gs.math.games.qznn_ksz_java.entity.ConstQznnKszJava.TimeEnumQznnKszJava;
import org.lsj.gs.math.games.qznn_ksz_java.entity.detail.DetailBet;
import org.lsj.gs.math.games.qznn_ksz_java.entity.message.CtsBetQznnKszJava;
import org.lsj.gs.math.games.qznn_ksz_java.entity.message.StcBetQznnKszJava;
import org.lsj.gs.math.games.qznn_ksz_java.entity.message.StcBetResultQznnKszJava;
import org.lsj.gs.math.games.qznn_ksz_java.entity.message.StcTurnQznnKszJava;
import org.lsj.gs.math.games.qznn_ksz_java.module.gameAdjust.GameAdjustQznnKszJava;
import org.lsj.gs.math.games.qznn_ksz_java.module.logic.LogicQznnKszJava;
import org.lsj.gs.math.games.qznn_ksz_java.module.robotLogic.RobotLogicQznnKszJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.locks.ReentrantLock;

// 下注狀態
public class StateBetQznnKszJava extends AbstractStateQznnKszJava {
    private static final String STATE_NAME = "bet";
    private static final Logger LOG = LoggerFactory.getLogger(StateBetQznnKszJava.class);
    private final FinishLock finishLock = new FinishLock(new ReentrantLock()); // 完成鎖

    public StateBetQznnKszJava(TableQznnKszJava table, LogicQznnKszJava logic, RobotLogicQznnKszJava aiLogic, GameAdjustQznnKszJava gameAdjust, int stateId) {
        super(table, logic, aiLogic, gameAdjust, stateId);
    }

    @Override
    public void onEnter() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());
        // 1. 初始倍數列表
        super.logic.generateCanBetRateListMap();

        // 2. 傳送可下注倍數列表
        this.sendCanBetRate();

        // 3. 設置機器人下注定時器
        this.doRobotBet();

        // 4. 紀錄狀態詳細日誌
        super.logic.addDetail(new DetailState(super.table.calculateSpendSec(), STATE_NAME));

        // 5. 設定定時器
        super.table.getTableTimer().schedule(new TimerTaskTimeOut(this), TimeEnumQznnKszJava.BET.getCode(), TimeEnumQznnKszJava.BET.getMilliTimeSec());
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

            // 4. 結束下注
            this.finishBetState();
        } finally {
            // 5. 解鎖
            this.finishLock.unlock();
        }
    }


    //* 流程相關 *//
    // 玩家下注
    public void onMessageBet(int chairIndex, CtsBetQznnKszJava data){
        LOG.info(super.table.getTableLogTitle() + " chairIndex: {}, data: {}", chairIndex, data);

        try{
            // 1. 上鎖
            this.finishLock.lock();

            // 2. 已完成則不做事
            if(this.finishLock.isFinish()){
                LOG.warn(super.table.getTableLogTitle() + " onMessageBet isFinish");
                return;
            }

            // 3. 檢查玩家座位
            if (!super.logic.isValidChairIndex(chairIndex)) {
                LOG.error(super.table.getTableLogTitle() + " error: {}, chairIndex: {}", "chairIndex not correct", chairIndex);
                return;
            }

            // 4. 不允許莊家下注
            if(super.logic.isBanker(chairIndex)){
                LOG.error(super.table.getTableLogTitle() + " error: {}, chairIndex: {}", "banker bet", chairIndex);
                return;
            }

            // 5. 不允許重複下注
            if(super.logic.isPlayerBet(chairIndex)){
                LOG.warn(super.table.getTableLogTitle() + " error: {}, chairIndex: {}", "repeat bet", chairIndex);
                return;
            }

            // 6. 驗證有效下注倍數
            if(!super.logic.isValidBetRate(chairIndex, data.getRate())){
                LOG.error(super.table.getTableLogTitle() + " error: {}, chairIndex: {}, rate: {}", "invalid rate", chairIndex, data.getRate());
                return;
            }

            // 7. 更新下注倍數
            super.logic.setPlayerBetRate(chairIndex, data.getRate());

            // 8. 傳送下注倍數
            this.sendBetRate(chairIndex, data.getRate());

            // 9. 紀錄下注詳細日誌
            super.logic.addDetail(new DetailBet(chairIndex, super.table.calculateSpendSec(), STATE_NAME, data.getRate()));

            // 10. 檢查是否結束下注狀態
            if (super.logic.isFinishBet()) {
                // 11. 紀錄已完成
                this.finishLock.setFinish(true);

                // 12. 結束下注狀態
                this.finishBetState();
            }
        } finally {
            // 13. 解鎖
            this.finishLock.unlock();
        }
    }

    // 設置機器人下注定時器
    private void doRobotBet() {
        // 1. 計算機器人結果
        Map<Integer, RobotRateResultBanker> robotResult = this.robotLogic.generateRobotBetResult();

        // 2. 設置定時器觸發
        robotResult.forEach((key, value) -> this.table.getTableTimer().schedule(
                new TimerTaskRobotBet(this, key, value.getRate()), value.getMilliTimeSec()));
    }

    // 結束下注狀態
    private void finishBetState(){
        // 1. 清空計時器
        super.table.getTableTimer().cancel();

        // 2. 請求結束搶倍數
        super.logic.finishBet();

        // 3. 傳送超時玩家下注
        this.sendTimeOutBetRate(super.logic.getTimeOutBetRate());

        // 4. 傳送下注結果
        StcBetResultQznnKszJava stcBetResultQznnKszJava = new StcBetResultQznnKszJava(super.logic.getBetResultArray());
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcBetResultQznnKszJava);
        super.table.sendMessageToHumanPlayer(stcBetResultQznnKszJava);

        // 5. 結束狀態
        super.iLeave();
    }


    //* 傳送相關 *//
    // 傳送可下注倍數列表
    private void sendCanBetRate() {
        StcTurnQznnKszJava stcTurnQznnKszJava = new StcTurnQznnKszJava(
                OperationEnumQznnKszJava.BET.getCode(),
                TimeEnumQznnKszJava.BET.getTimeSec(),
                0,
                super.logic.getHumanCanBetRateArray());
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcTurnQznnKszJava);
        super.table.sendMessageToHumanPlayer(stcTurnQznnKszJava);
    }

    // 傳送下注倍數
    private void sendBetRate(int chairIndex, int rate) {
        StcBetQznnKszJava stcBetQznnKszJava = new StcBetQznnKszJava(chairIndex, rate);
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcBetQznnKszJava);
        super.table.sendMessageToHumanPlayer(stcBetQznnKszJava);
    }

    // 傳送超時下注資訊
    private void sendTimeOutBetRate(Map<Integer, Integer> timeOutBetRateList) {
        // 1. 無人超時，不做事
        if (timeOutBetRateList.size() == 0) { return; }

        // 2. 傳送
        timeOutBetRateList.forEach(this::sendBetRate);
    }


    //* 定時器相關 *//
    // 超時定時器
    public class TimerTaskTimeOut extends TimerTask {
        private final StateBetQznnKszJava state;

        public TimerTaskTimeOut(StateBetQznnKszJava state){
            this.state = state;
        }

        @Override
        public void run() {
            this.state.onTimeout();
        }
    }

    // 狀態轉移定時器
    public class TimerTaskRobotBet extends TimerTask {
        private final StateBetQznnKszJava state;
        private final int chairIndex; // 座位
        private final int rate; // 下注

        public TimerTaskRobotBet(StateBetQznnKszJava state, int chair, int rate){
            this.state = state;
            this.chairIndex = chair;
            this.rate = rate;
        }

        @Override
        public void run() {
            this.state.onMessageBet(this.chairIndex, new CtsBetQznnKszJava(this.rate));
        }
    }
}
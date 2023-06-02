package org.lsj.gs.math.games.lznn_java.module.state;

import org.lsj.gs.math.core.common.lock.FinishLock;
import org.lsj.gs.math.core.common.robotLogic.entity.RobotRateResultBanker;
import org.lsj.gs.math.core.common.table.entity.detail.DetailState;
import org.lsj.gs.math.games.lznn_java.TableLznnJava;
import org.lsj.gs.math.games.lznn_java.entity.ConstLznnJava;
import org.lsj.gs.math.games.lznn_java.entity.detail.DetailBet;
import org.lsj.gs.math.games.lznn_java.entity.message.CtsBetLznnJava;
import org.lsj.gs.math.games.lznn_java.entity.message.StcBetLznnJava;
import org.lsj.gs.math.games.lznn_java.entity.message.StcBetResultLznnJava;
import org.lsj.gs.math.games.lznn_java.entity.message.StcTurnLznnJava;
import org.lsj.gs.math.games.lznn_java.module.gameAdjust.GameAdjustLznnJava;
import org.lsj.gs.math.games.lznn_java.module.logic.LogicLzNnJava;
import org.lsj.gs.math.games.lznn_java.module.robotLogic.RobotLogicLznnJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.locks.ReentrantLock;

// 下注狀態
public class StateBetLznnJava extends AbstractStateLznnJava {
    private static final String STATE_NAME = "bet";
    private static final Logger LOG = LoggerFactory.getLogger(StateBetLznnJava.class);
    private final FinishLock finishLock = new FinishLock(new ReentrantLock()); // 完成鎖

    public StateBetLznnJava(TableLznnJava table, LogicLzNnJava logic, RobotLogicLznnJava aiLogic, GameAdjustLznnJava gameAdjust, int stateId) {
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
        super.table.getTableTimer().schedule(new TimerTaskTimeOut(this), ConstLznnJava.TimeEnumLznnJava.BET.getCode(), ConstLznnJava.TimeEnumLznnJava.BET.getMilliTimeSec());
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
    public void onMessageBet(int chairIndex, CtsBetLznnJava data){
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
        StcBetResultLznnJava stcBetResultLznnJava = new StcBetResultLznnJava(super.logic.getBetResultArray());
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcBetResultLznnJava);
        super.table.sendMessageToHumanPlayer(stcBetResultLznnJava);

        // 5. 設置定時器
        super.table.getTableTimer().schedule(new TimerTaskTransState(this), ConstLznnJava.TimeEnumLznnJava.BET_RESULT.getMilliTimeSec());
    }


    //* 傳送相關 *//
    // 傳送可下注倍數列表
    private void sendCanBetRate() {
        StcTurnLznnJava stcTurnLznnJava = new StcTurnLznnJava(
                ConstLznnJava.OperationEnumLznnJava.BET.getCode(),
                ConstLznnJava.TimeEnumLznnJava.BET.getTimeSec(),
                0,
                super.logic.getHumanCanBetRateArray());
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcTurnLznnJava);
        super.table.sendMessageToHumanPlayer(stcTurnLznnJava);
    }

    // 傳送下注倍數
    private void sendBetRate(int chairIndex, int rate) {
        StcBetLznnJava stcBetLznnJava = new StcBetLznnJava(chairIndex, rate);
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcBetLznnJava);
        super.table.sendMessageToHumanPlayer(stcBetLznnJava);
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
        private final StateBetLznnJava state;

        public TimerTaskTimeOut(StateBetLznnJava state){
            this.state = state;
        }

        @Override
        public void run() {
            this.state.onTimeout();
        }
    }

    // 狀態轉移定時器
    public class TimerTaskTransState extends TimerTask {
        private final StateBetLznnJava state;

        public TimerTaskTransState(StateBetLznnJava state){
            this.state = state;
        }

        @Override
        public void run() {
            this.state.iLeave();
        }
    }

    // 狀態轉移定時器
    public class TimerTaskRobotBet extends TimerTask {
        private final StateBetLznnJava state;
        private final int chairIndex; // 座位
        private final int rate; // 下注

        public TimerTaskRobotBet(StateBetLznnJava state, int chair, int rate){
            this.state = state;
            this.chairIndex = chair;
            this.rate = rate;
        }

        @Override
        public void run() {
            this.state.onMessageBet(this.chairIndex, new CtsBetLznnJava(this.rate));
        }
    }
}
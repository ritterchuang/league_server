package org.lsj.gs.math.games.sg_java.module.state;

import org.lsj.gs.math.core.common.lock.FinishLock;
import org.lsj.gs.math.core.common.robotLogic.entity.RobotRateResultBanker;
import org.lsj.gs.math.core.common.table.entity.detail.DetailState;
import org.lsj.gs.math.games.sg_java.TableSgJava;
import org.lsj.gs.math.games.sg_java.entity.ConstSgJava;
import org.lsj.gs.math.games.sg_java.entity.detail.DetailBet;
import org.lsj.gs.math.games.sg_java.entity.message.CtsBetSgJava;
import org.lsj.gs.math.games.sg_java.entity.message.StcBetResultSgJava;
import org.lsj.gs.math.games.sg_java.entity.message.StcBetSgJava;
import org.lsj.gs.math.games.sg_java.entity.message.StcStartBetSgJava;
import org.lsj.gs.math.games.sg_java.module.gameAdjust.GameAdjustSgJava;
import org.lsj.gs.math.games.sg_java.module.logic.LogicSgJava;
import org.lsj.gs.math.games.sg_java.module.robotLogic.RobotLogicSgJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.locks.ReentrantLock;

// 新三公下注狀態 TODO 下注列表邏輯變更
public class StateBetSgJava extends AbstractStateSgJava {
    private static final String STATE_NAME = "bet";
    private static final Logger LOG = LoggerFactory.getLogger(StateBetSgJava.class);
    private final FinishLock finishLock = new FinishLock(new ReentrantLock()); // 完成鎖

    public StateBetSgJava(TableSgJava table, LogicSgJava logic, RobotLogicSgJava aiLogic, GameAdjustSgJava gameAdjust, int stateId) {
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
        super.table.getTableTimer().schedule(new TimerTaskTimeOut(this), ConstSgJava.TimeEnumSgJava.BET.getCode(), ConstSgJava.TimeEnumSgJava.BET.getMilliTimeSec());
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
    public void onMessageBet(int chairIndex, CtsBetSgJava data){
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
        StcBetResultSgJava stcBetResultSgJava = new StcBetResultSgJava(super.logic.getBetResultArray());
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcBetResultSgJava);
        super.table.sendMessageToHumanPlayer(stcBetResultSgJava);

        // 5. 結束狀態
        super.iLeave();
    }


    //* 傳送相關 *//
    // 傳送可下注倍數列表
    private void sendCanBetRate() {
        StcStartBetSgJava startBetSgJava = new StcStartBetSgJava(
                ConstSgJava.TimeEnumSgJava.BET.getTimeSec(),
                super.logic.getHumanCanBetRateArray());
        LOG.info(super.table.getTableLogTitle() + " message: {}", startBetSgJava);
        super.table.sendMessageToHumanPlayer(startBetSgJava);
    }

    // 傳送下注倍數
    private void sendBetRate(int chairIndex, int rate) {
        StcBetSgJava stcBetSgJava = new StcBetSgJava(chairIndex, rate);
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcBetSgJava);
        super.table.sendMessageToHumanPlayer(stcBetSgJava);
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
        private final StateBetSgJava state;

        public TimerTaskTimeOut(StateBetSgJava state){
            this.state = state;
        }

        @Override
        public void run() {
            this.state.onTimeout();
        }
    }

    // 狀態轉移定時器
    public class TimerTaskRobotBet extends TimerTask {
        private final StateBetSgJava state;
        private final int chairIndex; // 座位
        private final int rate; // 下注

        public TimerTaskRobotBet(StateBetSgJava state, int chair, int rate){
            this.state = state;
            this.chairIndex = chair;
            this.rate = rate;
        }

        @Override
        public void run() {
            this.state.onMessageBet(this.chairIndex, new CtsBetSgJava(this.rate));
        }
    }
}
package com.lx.gs.math.games.qzpj_java.module.state;

import com.lx.gs.math.core.common.lock.FinishLock;
import com.lx.gs.math.core.common.robotLogic.entity.RobotRateResultBanker;
import com.lx.gs.math.core.common.table.entity.detail.DetailState;
import com.lx.gs.math.games.qzpj_java.TableQzpjJava;
import com.lx.gs.math.games.qzpj_java.entity.ConstQzpjJava;
import com.lx.gs.math.games.qzpj_java.entity.detail.DetailNoVie;
import com.lx.gs.math.games.qzpj_java.entity.detail.DetailVieBanker;
import com.lx.gs.math.games.qzpj_java.entity.detail.DetailVieResult;
import com.lx.gs.math.games.qzpj_java.entity.message.CtsVieBankerQzpjJava;
import com.lx.gs.math.games.qzpj_java.entity.message.StcTurnQzpjJava;
import com.lx.gs.math.games.qzpj_java.entity.message.StcVieBankerQzpjJava;
import com.lx.gs.math.games.qzpj_java.entity.message.StcVieResultQzpjJava;
import com.lx.gs.math.games.qzpj_java.module.gameAdjust.GameAdjustQzpjJava;
import com.lx.gs.math.games.qzpj_java.module.logic.LogicQzpjJava;
import com.lx.gs.math.games.qzpj_java.module.robotLogic.RobotLogicQzpjJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.locks.ReentrantLock;

// 搶莊狀態 TODO 邏輯修改
public class StateVieBankerQzpjJava extends AbstractStateQzpjJava {
    private static final String STATE_NAME = "vieBanker";
    private static final Logger LOG = LoggerFactory.getLogger(StateVieBankerQzpjJava.class);
    private final FinishLock finishLock = new FinishLock(new ReentrantLock()); // 完成鎖

    public StateVieBankerQzpjJava(TableQzpjJava table, LogicQzpjJava logic, RobotLogicQzpjJava aiLogic, GameAdjustQzpjJava gameAdjust, int stateId) {
        super(table, logic, aiLogic, gameAdjust, stateId);
    }

    @Override
    public void onEnter() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());
        // 1. 通知客戶端開始搶莊
        this.sendCanVieRate();

        // 2. 設置機器人搶莊定時器
        this.doRobotVieBank();

        // 3. 紀錄狀態詳細日誌
        super.logic.addDetail(new DetailState(super.table.calculateSpendSec(), STATE_NAME));

        // 4. 設定定時器
        super.table.getTableTimer().schedule(new TimerTaskTimeOut(this), ConstQzpjJava.TimeEnumQzpjJava.VIE_BANKER.getCode(), ConstQzpjJava.TimeEnumQzpjJava.VIE_BANKER.getMilliTimeSec());
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

            // 4. 完成動作
            this.finishVieState();
        } finally {
            // 5. 解鎖
            this.finishLock.unlock();
        }
    }


    //* 流程相關 *//
    // 收到客戶端搶莊消息
    public void onMessageVieBanker(int chairIndex, CtsVieBankerQzpjJava data){
        LOG.info(super.table.getTableLogTitle() + " chairIndex:{}, data: {}", chairIndex, data);

        try{
            // 1. 上鎖
            this.finishLock.lock();

            // 2. 已完成則不做事
            if(this.finishLock.isFinish()){
                LOG.warn(super.table.getTableLogTitle() + " onMessageVieBanker isFinish");
                return;
            }

            // 3. 檢查玩家座位
            if (!super.logic.isValidChairIndex(chairIndex)) {
                LOG.error(super.table.getTableLogTitle() + " error: {}, chairIndex: {}", "chairIndex not correct", chairIndex);
                return;
            }

            // 4. 驗證有效搶莊倍數
            if(!super.logic.isValidVieRate(chairIndex, data.getRate())){
                LOG.error(super.table.getTableLogTitle() + " error: {}, chairIndex: {}, rate: {}", "invalid rate", chairIndex, data.getRate());
                return;
            }

            // 5. 不允許重複搶莊
            if(super.logic.isPlayerVied(chairIndex)){
                LOG.warn(super.table.getTableLogTitle() + " error: {}, chairIndex: {}", "repeat vie", chairIndex);
                return;
            }

            // 6. 更新搶莊倍數
            super.logic.setPlayerVieRate(chairIndex, data.getRate());

            // 7. 傳送搶莊倍數
            this.sendVieRate(chairIndex, data.getRate());

            // 8. 紀錄搶莊詳細日誌
            this.addDetailVieBanker(chairIndex, data);

            // 9. 檢查是否結束搶莊狀態
            if(super.logic.isFinishVie()){
                // 10. 紀錄已完成
                this.finishLock.setFinish(true);

                // 11. 結束搶莊狀態
                this.finishVieState();
            }
        } finally {
            // 11. 解鎖
            this.finishLock.unlock();
        }
    }

    // 設置機器人搶莊定時器
    private void doRobotVieBank() {
        // 1. 計算機器人結果
        Map<Integer, RobotRateResultBanker> robotResult = this.robotLogic.generateRobotVieBankResult();

        // 2. 設置定時器
        robotResult.forEach((key, value) -> super.table.getTableTimer().schedule(new TimerTaskRobotVieBank(this, key, value.getRate()), value.getMilliTimeSec()));
    }

    // 結束搶莊狀態
    private void finishVieState(){
        // 1. 清除計時器
        super.table.getTableTimer().cancel();

        // 2. 請求結束搶莊
        super.logic.finishVie();

        // 3. 傳送超時搶莊資訊
        this.sendTimeOutVieRate(super.logic.getTimeOutVieRate());

        // 4. 傳送搶莊結果
        this.sendVieResult();

        // 5. 紀錄日誌
        LOG.info(super.table.getTableLogTitle() + " bankerChairIndex: {}, bankerRate: {}", super.logic.getBankerChairIndex(), super.logic.getBankerRate());

        // 6. 紀錄搶莊結果詳細日誌
        super.logic.addDetail(new DetailVieResult("vieResult", super.logic.getBankerChairIndex(), super.logic.getBankerRate()));

        // 7. 動畫展示
        if (super.logic.isShowVieBankAnimation()) {
            long showBankerTime = (long) (1.5 * 1000) + ConstQzpjJava.TimeEnumQzpjJava.SHOW_BANKER.getMilliTimeSec();
            super.table.getTableTimer().schedule(new TimerTaskTransferState(this), showBankerTime);
        }else {
            super.iLeave();
        }
    }


    //* 傳送相關 *//
    // 傳送可搶莊列表
    private void sendCanVieRate() {
        StcTurnQzpjJava stcTurn = new StcTurnQzpjJava(
                ConstQzpjJava.OperationEnumQzpjJava.VIE.getCode(),
                ConstQzpjJava.TimeEnumQzpjJava.VIE_BANKER.getTimeSec(),
                super.logic.getHumanCanVieRateArray());
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcTurn);
        super.table.sendMessageToHumanPlayer(stcTurn);
    }

    // 傳送搶莊資訊
    private void sendVieRate(int chairIndex, int rate) {
        StcVieBankerQzpjJava stcVieBanker = new StcVieBankerQzpjJava(
                chairIndex,
                rate);
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcVieBanker);
        super.table.sendMessageToHumanPlayer(stcVieBanker);
    }

    // 傳送超時搶莊資訊
    private void sendTimeOutVieRate(Map<Integer, Integer> timeOutVieRates) {
        // 1. 無人超時，不做事
        if (timeOutVieRates.size() == 0) { return; }

        // 2. 傳送
        timeOutVieRates.forEach(this::sendVieRate);
    }

    // 傳送搶莊結果
    private void sendVieResult() {
        StcVieResultQzpjJava stcVieResult = new StcVieResultQzpjJava(
                super.logic.getBankerChairIndex(),
                super.logic.getBankerRate(),
                super.logic.getVieCandidateArrayMessage());
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcVieResult);
        super.table.sendMessageToHumanPlayer(stcVieResult);
    }


    //* 定時器相關 *//
    // 超時定時器
    public class TimerTaskTimeOut extends TimerTask {
        private final StateVieBankerQzpjJava state;

        public TimerTaskTimeOut(StateVieBankerQzpjJava state){
            this.state = state;
        }

        @Override
        public void run() {
            this.state.onTimeout();
        }
    }

    // 狀態轉移定時器
    public class TimerTaskTransferState extends TimerTask {
        private final StateVieBankerQzpjJava state;

        public TimerTaskTransferState(StateVieBankerQzpjJava state){
            this.state = state;
        }

        @Override
        public void run() {
            this.state.iLeave();
        }
    }

    // 機器人搶莊定時器
    public class TimerTaskRobotVieBank extends TimerTask {
        private final StateVieBankerQzpjJava state;
        private final int chairIndex; // 座位
        private final int rate; // 搶莊倍數

        public TimerTaskRobotVieBank(StateVieBankerQzpjJava state, int chair, int rate){
            this.state = state;
            this.chairIndex = chair;
            this.rate = rate;
        }

        @Override
        public void run() {
            this.state.onMessageVieBanker(this.chairIndex, new CtsVieBankerQzpjJava(this.rate));
        }
    }


    //* 詳細記錄相關 *//
    // 紀錄搶莊詳細紀錄
    private void addDetailVieBanker(int chairIndex, CtsVieBankerQzpjJava data){
        if(data.getRate() <= 0){
            super.logic.addDetail(new DetailNoVie(chairIndex, super.table.calculateSpendSec(), "noVie"));
        }else{
            super.logic.addDetail(new DetailVieBanker(chairIndex, super.table.calculateSpendSec(), STATE_NAME, data.getRate()));
        }
    }
}
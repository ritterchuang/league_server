package org.lsj.gs.math.games.tbnn_java.module.state;

import org.lsj.gs.math.core.common.lock.FinishLock;
import org.lsj.gs.math.core.common.robotLogic.entity.RobotSelectResultTonbi;
import org.lsj.gs.math.games.tbnn_java.TableTbnnJava;
import org.lsj.gs.math.games.tbnn_java.entity.ConstTbnnJava;
import org.lsj.gs.math.games.tbnn_java.entity.message.CtsSelectTbnnJava;
import org.lsj.gs.math.games.tbnn_java.entity.message.StcBeginSelectTbnnJava;
import org.lsj.gs.math.games.tbnn_java.entity.message.StcMaxCardTypeTbnnJava;
import org.lsj.gs.math.games.tbnn_java.entity.message.StcSelectTbnnJava;
import org.lsj.gs.math.games.tbnn_java.module.gameAdjust.GameAdjustTbnnJava;
import org.lsj.gs.math.games.tbnn_java.module.logic.LogicTbnnJava;
import org.lsj.gs.math.games.tbnn_java.module.robotLogic.RobotLogicTbnnJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.locks.ReentrantLock;

// 選牌狀態
public class StateSelectTbnnJava extends AbstractStateTbnnJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateSelectTbnnJava.class);
    private final FinishLock finishLock = new FinishLock(new ReentrantLock()); // 完成鎖

    public StateSelectTbnnJava(TableTbnnJava table, LogicTbnnJava logic, RobotLogicTbnnJava aiLogic, GameAdjustTbnnJava gameAdjust, int stateId) {
        super(table, logic, aiLogic, gameAdjust, stateId);
    }

    @Override
    public void onEnter() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());
        // 1. 通知客戶端選牌
        StcBeginSelectTbnnJava stcBeginSelectTbnnJava = new StcBeginSelectTbnnJava(ConstTbnnJava.TimeEnumTbnnJava.SELECT.getTimeSec());
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcBeginSelectTbnnJava);
        super.table.sendMessageToHumanPlayer(stcBeginSelectTbnnJava);
        
        // 2. 設置機器人選牌定時器
        this.doRobotSelect();

        // 3. 設定定時器
        super.table.getTableTimer().schedule(new TimerTaskTimeOut(this), ConstTbnnJava.TimeEnumTbnnJava.SELECT.getCode(), ConstTbnnJava.TimeEnumTbnnJava.SELECT.getMilliTimeSec());
    }

    @Override
    public void onLeave() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());
        // 清空定時器
        super.table.getTableTimer().cancel();
    }

    @Override
    public void onTimeout() {
        LOG.info(super.table.getTableLogTitle() + " onTimeout");

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
            this.finishSelect();
        } finally {
            // 5. 解鎖
            this.finishLock.unlock();
        }
    }


    //* 流程相關 *//
    // 玩家選牌
    public void onMessageSelect(int chairIndex, CtsSelectTbnnJava data) {
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

            // 4. 非攤牌
            if (data.getType() != ConstTbnnJava.SelectTypeEnumTbnnJava.SHOWDOWN.getCode()) {
                // 5. 檢查玩家是否選過
                if (super.logic.isPlayerSelect(chairIndex)) {
                    LOG.warn(super.table.getTableLogTitle() + " error: {}, chairIndex: {}", "repeat select", chairIndex);
                    return;
                }

                // 6. 檢查玩家手牌是否存在
                if (!super.logic.isInPlayerHand(chairIndex, data.getCards())) {
                    LOG.error(super.table.getTableLogTitle() +
                            " error: {}, chairIndex: {}, client cards: {}, system cards: {}",
                            "cards error",
                            chairIndex,
                            Arrays.toString(data.getCards()),
                            Arrays.toString(this.logic.getHandCardNumberArray(chairIndex)));
                    return;
                }
            }

            // 8. 設定指定座位選牌結果
            super.logic.setPlayerSelectResult(chairIndex);

            // 9. 若為真人則傳送最大牌型
            if (super.logic.isHumanChairIndex(chairIndex)) {
                this.sendHumanMaxCardType();
            }

            // 10. 傳送選牌結果
            this.sendPlayerCardType(chairIndex);

            // 11. 檢查是否結束選牌
            if (super.logic.isFinishSelect()) {
                // 12. 紀錄已完成
                this.finishLock.setFinish(true);

                // 13. 結束選牌
                this.finishSelect();
            }
        } finally {
            // 14. 解鎖
            this.finishLock.unlock();
        }
    }

    // 設置機器人選牌定時
    private void doRobotSelect() {
        // 1. 計算機器人結果
        Map<Integer, RobotSelectResultTonbi> robotResult = this.robotLogic.generateRobotSelectResult();

        // 2. 設置定時器觸發
        robotResult.forEach((key, value) -> this.table.getTableTimer().schedule(new TimerTaskRobotSelect(this, key, value.getType()), value.getMilliTimeSec()));
    }

    // 結束選牌
    void finishSelect() {
        // 1. 清空計時器
        super.table.getTableTimer().cancel();

        // 2. 結束選牌
        super.logic.finishSelect();

        // 3. 紀錄牌型結果
        LOG.info(super.table.getTableLogTitle() + " allPlayerSelectType: {}", super.logic.getAllPlayerSelectTypeArray());

        // 4. 設定定時器
        super.table.getTableTimer().schedule(new TimerTaskTransState(this), ConstTbnnJava.TimeEnumTbnnJava.SELECT_RESULT.getMilliTimeSec());
    }


    //* 傳送相關 *//
    // 傳送真人傳送最大牌型
    private void sendHumanMaxCardType() {
        StcMaxCardTypeTbnnJava stcMaxCardTypeTbnnJava = new StcMaxCardTypeTbnnJava(
                super.logic.getHumanChairIndex(),
                super.logic.getHumanPlayerSelectResult().getNiuTypeEnumCustom().getType());
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcMaxCardTypeTbnnJava);
        super.table.sendMessageToHumanPlayer(stcMaxCardTypeTbnnJava);
    }

    // 傳送玩家選牌結果
    private void sendPlayerCardType(int chairIndex) {
        StcSelectTbnnJava stcSelectTbnnJava = new StcSelectTbnnJava(
                chairIndex,
                super.logic.getHandCardNumberArray(chairIndex),
                super.logic.getPlayerLiftCardNumber(chairIndex),
                super.logic.getPlayerSelectType(chairIndex),
                super.logic.getPlayerIsNiu(chairIndex));
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcSelectTbnnJava);
        super.table.sendMessageToHumanPlayer(stcSelectTbnnJava);
    }


    //* 定時器相關 *//
    // 超時定時器
    public class TimerTaskTimeOut extends TimerTask {
        private final StateSelectTbnnJava state;

        public TimerTaskTimeOut(StateSelectTbnnJava state){
            this.state = state;
        }

        @Override
        public void run() {
            this.state.onTimeout();
        }
    }

    // 狀態轉移定時器
    public class TimerTaskTransState extends TimerTask {
        private final StateSelectTbnnJava state;

        public TimerTaskTransState(StateSelectTbnnJava state){
            this.state = state;
        }

        @Override
        public void run() {
            this.state.iLeave();
        }
    }

    // 機器人選牌定時器
    public class TimerTaskRobotSelect extends TimerTask {
        private final StateSelectTbnnJava state;
        private final int chairIndex; // 座位
        private final int type; // 動作類型

        public TimerTaskRobotSelect(StateSelectTbnnJava state, int chairIndex, int type){
            this.state = state;
            this.chairIndex = chairIndex;
            this.type = type;
        }

        @Override
        public void run() {
            this.state.onMessageSelect(this.chairIndex, new CtsSelectTbnnJava(null, this.type));
        }
    }
}

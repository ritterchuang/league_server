package org.lsj.gs.math.games.zjh_java.module.state;

import org.lsj.gs.math.core.common.lock.FinishLock;
import org.lsj.gs.math.core.common.robotLogic.entity.RobotSelectResultBanker;
import org.lsj.gs.math.games.zjh_java.TableZjhJava;
import org.lsj.gs.math.games.zjh_java.entity.ConstZjhJava;
import org.lsj.gs.math.games.zjh_java.entity.message.*;
import org.lsj.gs.math.games.zjh_java.entity.message.*;
import org.lsj.gs.math.games.zjh_java.module.gameAdjust.GameAdjustZjhJava;
import org.lsj.gs.math.games.zjh_java.module.logic.LogicZjhJava;
import org.lsj.gs.math.games.zjh_java.module.robotLogic.RobotLogicZjhJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.locks.ReentrantLock;

// 選牌狀態
public class StateSelectZjhJava extends AbstractStateZjhJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateSelectZjhJava.class);
    private final FinishLock finishLock = new FinishLock(new ReentrantLock()); // 完成鎖

    public StateSelectZjhJava(TableZjhJava table, LogicZjhJava logic, RobotLogicZjhJava aiLogic, GameAdjustZjhJava gameAdjust, int stateId) {
        super(table, logic, aiLogic, gameAdjust, stateId);
    }

    @Override
    public void onEnter() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());
        // 1. 通知客戶端選牌
        StcBeginSelectZjhJava stcBeginSelect = new StcBeginSelectZjhJava(ConstZjhJava.TimeEnumZjhJava.SELECT.getTimeSec());
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcBeginSelect);
        super.table.sendMessageToHumanPlayer(stcBeginSelect);
        
        // 2. 設置機器人選牌定時器
        this.doRobotSelect();

        // 3. 設定定時器
        super.table.getTableTimer().schedule(new TimerTaskTimeOut(this), ConstZjhJava.TimeEnumZjhJava.SELECT.getCode(), ConstZjhJava.TimeEnumZjhJava.SELECT.getMilliTimeSec());
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
    public void onMessageSelect(int chairIndex, CtsSelectZjhJava data) {
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
            if (data.getType() != ConstZjhJava.SelectTypeEnumZjhJava.SHOWDOWN.getCode()) {
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

                // 7. 不是最大牌型則傳送選牌錯誤
                if (!super.logic.isMaxStack(data.getCards())) {
                    StcMaxSelectZjhJava stcMaxSelect = new StcMaxSelectZjhJava(
                            chairIndex,
                            1);
                    LOG.info(super.table.getTableLogTitle() + " message: {}", stcMaxSelect);
                    super.table.sendMessageToHumanPlayer(stcMaxSelect); // 原無效傳輸協議
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
        Map<Integer, RobotSelectResultBanker> robotResult = this.robotLogic.generateRobotSelectResult();

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
        super.table.getTableTimer().schedule(new TimerTaskTransState(this), ConstZjhJava.TimeEnumZjhJava.SELECT_RESULT.getMilliTimeSec());
    }


    //* 傳送相關 *//
    // 傳送真人傳送最大牌型
    private void sendHumanMaxCardType() {
        StcMaxCardTypeZjhJava stcMaxCardType = new StcMaxCardTypeZjhJava(
                super.logic.getHumanChairIndex(),
                super.logic.getHumanPlayerSelectResult().getTypeEnumCustom().getType());
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcMaxCardType);
        super.table.sendMessageToHumanPlayer(stcMaxCardType);
    }

    // 傳送玩家選牌結果
    private void sendPlayerCardType(int chairIndex) {
        StcSelectZjhJava stcSelect = new StcSelectZjhJava(
                chairIndex,
                super.logic.getHandCardNumberArray(chairIndex),
                super.logic.getPlayerLiftCardNumber(chairIndex),
                super.logic.getPlayerSelectType(chairIndex));
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcSelect);
        super.table.sendMessageToHumanPlayer(stcSelect);
    }


    //* 定時器相關 *//
    // 超時定時器
    public class TimerTaskTimeOut extends TimerTask {
        private final StateSelectZjhJava state;

        public TimerTaskTimeOut(StateSelectZjhJava state){
            this.state = state;
        }

        @Override
        public void run() {
            this.state.onTimeout();
        }
    }

    // 狀態轉移定時器
    public class TimerTaskTransState extends TimerTask {
        private final StateSelectZjhJava state;

        public TimerTaskTransState(StateSelectZjhJava state){
            this.state = state;
        }

        @Override
        public void run() {
            this.state.iLeave();
        }
    }

    // 機器人選牌定時器
    public class TimerTaskRobotSelect extends TimerTask {
        private final StateSelectZjhJava state;
        private final int chairIndex; // 座位
        private final int type; // 動作類型

        public TimerTaskRobotSelect(StateSelectZjhJava state, int chairIndex, int type){
            this.state = state;
            this.chairIndex = chairIndex;
            this.type = type;
        }

        @Override
        public void run() {
            this.state.onMessageSelect(this.chairIndex, new CtsSelectZjhJava(null, this.type));
        }
    }
}

package org.lsj.gs.math.games.lznn_java.module.state;

import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.AbstractNiuStack;
import org.lsj.gs.math.core.common.lock.FinishLock;
import org.lsj.gs.math.core.common.robotLogic.entity.RobotSelectResultBanker;
import org.lsj.gs.math.games.lznn_java.TableLznnJava;
import org.lsj.gs.math.games.lznn_java.entity.ConstLznnJava;
import org.lsj.gs.math.games.lznn_java.entity.message.CtsSelectLznnJava;
import org.lsj.gs.math.games.lznn_java.entity.message.StcBeginSelectLznnJava;
import org.lsj.gs.math.games.lznn_java.entity.message.StcSelectLznnJava;
import org.lsj.gs.math.games.lznn_java.module.gameAdjust.GameAdjustLznnJava;
import org.lsj.gs.math.games.lznn_java.module.logic.LogicLzNnJava;
import org.lsj.gs.math.games.lznn_java.module.robotLogic.RobotLogicLznnJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.locks.ReentrantLock;

// 選牌狀態
public class StateSelectLznnJava extends AbstractStateLznnJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateSelectLznnJava.class);
    private final FinishLock finishLock = new FinishLock(new ReentrantLock()); // 完成鎖

    public StateSelectLznnJava(TableLznnJava table, LogicLzNnJava logic, RobotLogicLznnJava aiLogic, GameAdjustLznnJava gameAdjust, int stateId) {
        super(table, logic, aiLogic, gameAdjust, stateId);
    }

    @Override
    public void onEnter() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());
        // 1. 通知客戶端選牌
        StcBeginSelectLznnJava stcBeginSelectQznnJava = new StcBeginSelectLznnJava(ConstLznnJava.TimeEnumLznnJava.SELECT.getTimeSec());
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcBeginSelectQznnJava);
        super.table.sendMessageToHumanPlayer(stcBeginSelectQznnJava);
        
        // 2. 設置機器人選牌定時器
        this.doRobotSelect();

        // 3. 設定定時器
        super.table.getTableTimer().schedule(new TimerTaskTimeOut(this), ConstLznnJava.TimeEnumLznnJava.SELECT.getCode(), ConstLznnJava.TimeEnumLznnJava.SELECT.getMilliTimeSec());
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
    public void onMessageSelect(int chairIndex, CtsSelectLznnJava data) {
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
            if (data.getType() != ConstLznnJava.SelectTypeEnumLznnJava.SHOWDOWN.getCode()) {
                // 5. 檢查玩家是否選過
                if (super.logic.isPlayerSelect(chairIndex)) {
                    LOG.warn(super.table.getTableLogTitle() + " error: {}, chairIndex: {}", "repeat select", chairIndex);
                    return;
                }
            }

            // 8. 設定指定座位選牌結果
            super.logic.setPlayerSelectResult(chairIndex);

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
        super.table.getTableTimer().schedule(new TimerTaskTransState(this), ConstLznnJava.TimeEnumLznnJava.SELECT_RESULT.getMilliTimeSec());
    }


    //* 傳送相關 *//

    // 傳送玩家選牌結果
    private void sendPlayerCardType(int chairIndex) {
        // 1. 取得牛型結果
        AbstractNiuStack abstractNiuStack = super.logic.getPlayerSelectResult(chairIndex);

        // 2. 傳送封包
        StcSelectLznnJava stcSelectLznnJava = new StcSelectLznnJava(
                chairIndex,
                abstractNiuStack.getHandCardList().stream().mapToInt(card -> card.getNumber()).toArray(),
                abstractNiuStack.getLiftCardList().stream().mapToInt(card -> card.getNumber()).toArray(),
                super.logic.getPlayerSelectType(chairIndex));
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcSelectLznnJava);
        super.table.sendMessageToHumanPlayer(stcSelectLznnJava);
    }


    //* 定時器相關 *//
    // 超時定時器
    public class TimerTaskTimeOut extends TimerTask {
        private final StateSelectLznnJava state;

        public TimerTaskTimeOut(StateSelectLznnJava state){
            this.state = state;
        }

        @Override
        public void run() {
            this.state.onTimeout();
        }
    }

    // 狀態轉移定時器
    public class TimerTaskTransState extends TimerTask {
        private final StateSelectLznnJava state;

        public TimerTaskTransState(StateSelectLznnJava state){
            this.state = state;
        }

        @Override
        public void run() {
            this.state.iLeave();
        }
    }

    // 機器人選牌定時器
    public class TimerTaskRobotSelect extends TimerTask {
        private final StateSelectLznnJava state;
        private final int chairIndex; // 座位
        private final int type; // 動作類型

        public TimerTaskRobotSelect(StateSelectLznnJava state, int chairIndex, int type){
            this.state = state;
            this.chairIndex = chairIndex;
            this.type = type;
        }

        @Override
        public void run() {
            this.state.onMessageSelect(this.chairIndex, new CtsSelectLznnJava(null, this.type));
        }
    }
}

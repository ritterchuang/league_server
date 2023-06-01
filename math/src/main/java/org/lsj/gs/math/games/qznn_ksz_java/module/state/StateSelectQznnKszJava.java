package org.lsj.gs.math.games.qznn_ksz_java.module.state;

import org.lsj.gs.math.core.common.lock.FinishLock;
import org.lsj.gs.math.core.common.robotLogic.entity.RobotSelectResultBanker;
import org.lsj.gs.math.games.qznn_ksz_java.TableQznnKszJava;
import org.lsj.gs.math.games.qznn_ksz_java.entity.ConstQznnKszJava.TimeEnumQznnKszJava;
import org.lsj.gs.math.games.qznn_ksz_java.entity.message.CtsSelectQznnKszJava;
import org.lsj.gs.math.games.qznn_ksz_java.entity.message.StcBeginSelectQznnKszJava;
import org.lsj.gs.math.games.qznn_ksz_java.entity.message.StcMaxCardTypeQznnKszJava;
import org.lsj.gs.math.games.qznn_ksz_java.entity.message.StcSelectQznnKszJava;
import org.lsj.gs.math.games.qznn_ksz_java.module.gameAdjust.GameAdjustQznnKszJava;
import org.lsj.gs.math.games.qznn_ksz_java.module.logic.LogicQznnKszJava;
import org.lsj.gs.math.games.qznn_ksz_java.module.robotLogic.RobotLogicQznnKszJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

// 選牌狀態
public class StateSelectQznnKszJava extends AbstractStateQznnKszJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateSelectQznnKszJava.class);
    private final FinishLock finishLock = new FinishLock(new ReentrantLock()); // 完成鎖

    public StateSelectQznnKszJava(TableQznnKszJava table, LogicQznnKszJava logic, RobotLogicQznnKszJava aiLogic, GameAdjustQznnKszJava gameAdjust, int stateId) {
        super(table, logic, aiLogic, gameAdjust, stateId);
    }

    @Override
    public void onEnter() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());

        // 1. 開啟調控發牌
        this.gameAdjust.startGameAdjust();

        // 2. 通知客戶端選牌
        StcBeginSelectQznnKszJava stcBeginSelectQznnKszJava = new StcBeginSelectQznnKszJava(
                Arrays.stream(super.logic.getHumanListHandCardArray()).boxed().collect(Collectors.toList()).subList(Math.max(super.logic.getHumanListHandCardArray().length - 2, 0), super.logic.getHumanListHandCardArray().length).stream().mapToInt(i->i).toArray(),
                TimeEnumQznnKszJava.SELECT.getTimeSec(),
                super.logic.getBetResultArray());
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcBeginSelectQznnKszJava);
        super.table.sendMessageToHumanPlayer(stcBeginSelectQznnKszJava);
        
        // 3. 設置機器人選牌定時器
        this.doRobotSelect();

        // 4. 設定定時器
        super.table.getTableTimer().schedule(new TimerTaskTimeOut(this), TimeEnumQznnKszJava.SELECT.getCode(), TimeEnumQznnKszJava.SELECT.getMilliTimeSec());
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
    public void onMessageSelect(int chairIndex, CtsSelectQznnKszJava data) {
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

            // 7. 若為真人則傳送最大牌型
            if (super.logic.isHumanChairIndex(chairIndex)) {
                this.sendHumanMaxCardType();
            }

            // 8. 傳送選牌結果
            this.sendPlayerCardType(chairIndex);

            // 9. 檢查是否結束選牌
            if (super.logic.isFinishSelect()) {
                // 10. 紀錄已完成
                this.finishLock.setFinish(true);

                // 11. 結束選牌
                this.finishSelect();
            }
        } finally {
            // 12. 解鎖
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
    private void sendHumanMaxCardType() {
        StcMaxCardTypeQznnKszJava stcMaxCardTypeQznnKszJava = new StcMaxCardTypeQznnKszJava(
                super.logic.getHumanChairIndex(),
                super.logic.getHumanPlayerSelectResult().getNiuTypeEnumCustom().getType());
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcMaxCardTypeQznnKszJava);
        super.table.sendMessageToHumanPlayer(stcMaxCardTypeQznnKszJava);
    }

    // 傳送玩家選牌結果
    private void sendPlayerCardType(int chairIndex) {
        StcSelectQznnKszJava stcSelectQznnKszJava = new StcSelectQznnKszJava(
                chairIndex,
                super.logic.getHandCardNumberArray(chairIndex),
                super.logic.getPlayerLiftCardNumber(chairIndex),
                super.logic.getPlayerSelectType(chairIndex));
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcSelectQznnKszJava);
        super.table.sendMessageToHumanPlayer(stcSelectQznnKszJava);
    }


    //* 定時器相關 *//
    // 超時定時器
    public class TimerTaskTimeOut extends TimerTask {
        private final StateSelectQznnKszJava state;

        public TimerTaskTimeOut(StateSelectQznnKszJava state){
            this.state = state;
        }

        @Override
        public void run() {
            this.state.onTimeout();
        }
    }

    // 機器人選牌定時器
    public class TimerTaskRobotSelect extends TimerTask {
        private final StateSelectQznnKszJava state;
        private final int chairIndex; // 座位
        private final int type; // 動作類型

        public TimerTaskRobotSelect(StateSelectQznnKszJava state, int chairIndex, int type){
            this.state = state;
            this.chairIndex = chairIndex;
            this.type = type;
        }

        @Override
        public void run() {
            this.state.onMessageSelect(this.chairIndex, new CtsSelectQznnKszJava(new int[]{}));
        }
    }
}

package com.lx.gs.math.games.lznn_java.module.state;

import com.lx.gs.math.core.common.gameAdjust.entity.UidScore;
import com.lx.gs.math.core.common.table.entity.detail.DetailState;
import com.lx.gs.math.games.lznn_java.TableLznnJava;
import com.lx.gs.math.games.lznn_java.entity.ConstLznnJava;
import com.lx.gs.math.games.lznn_java.entity.detail.DetailCompareResult;
import com.lx.gs.math.games.lznn_java.entity.message.StcCompareLznnJava;
import com.lx.gs.math.games.lznn_java.module.gameAdjust.GameAdjustLznnJava;
import com.lx.gs.math.games.lznn_java.module.logic.LogicLzNnJava;
import com.lx.gs.math.games.lznn_java.module.robotLogic.RobotLogicLznnJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TimerTask;

// 比牌狀態
public class StateCompareLznnJava extends AbstractStateLznnJava {
    private static final String STATE_NAME = "cmp";
    private static final Logger LOG = LoggerFactory.getLogger(StateCompareLznnJava.class);
    public StateCompareLznnJava(TableLznnJava table, LogicLzNnJava logic, RobotLogicLznnJava aiLogic, GameAdjustLznnJava gameAdjust, int stateId) {
        super(table, logic, aiLogic, gameAdjust, stateId);
    }

    @Override
    public void onEnter() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());
        // 1. 紀錄狀態詳細日誌
        super.logic.addDetail(new DetailState(super.table.calculateSpendSec(), STATE_NAME));

        // 2. 計算每位玩家輸贏
        Map<Integer, UidScore> allPlayerUidScoreMap = super.logic.calculateUidScoreMap();
        allPlayerUidScoreMap.forEach((chairIndex, uidScore) -> LOG.info(super.table.getTableLogTitle() + " chairIndex: {}, uidScore: {}", chairIndex, uidScore));

        // 3. 設定玩家輸贏分
        super.logic.setUidScoreMap(allPlayerUidScoreMap);

        // 4. 記錄所有玩家比牌結果詳細日誌
        this.addDetailCompareResult(allPlayerUidScoreMap);

        // 5. 傳送所有玩家資訊
        this.sendCompare();

        // 6. 設定定時器
        super.table.getTableTimer().schedule(new TimerTaskTimeOut(this), ConstLznnJava.TimeEnumLznnJava.COMPARE.getCode(), ConstLznnJava.TimeEnumLznnJava.COMPARE.getMilliTimeSec());
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

    // 傳送比牌結果
    private void sendCompare() {
        StcCompareLznnJava stcCompareLznnJava = new StcCompareLznnJava(
                super.logic.getAllPlayerSelectCardNumberArray(),
                super.logic.getAllPlayerLiftCardNumberArray(),
                super.logic.getAllPlayerSelectTypeArray(),
                super.logic.getAllPlayerScoreArray());
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcCompareLznnJava);
        super.table.sendMessageToHumanPlayer(stcCompareLznnJava);
    }

    // 超時定時器
    public class TimerTaskTimeOut extends TimerTask {
        private final StateCompareLznnJava state;

        public TimerTaskTimeOut(StateCompareLznnJava state){
            this.state = state;
        }

        @Override
        public void run() {
            this.state.onTimeout();
        }
    }

    // 新增比牌結果詳細記錄
    private void addDetailCompareResult(Map<Integer, UidScore> allPlayerUidScoreMap) {
        allPlayerUidScoreMap.forEach((chairIndex, uidScore) -> super.logic.addDetail(new DetailCompareResult(
                chairIndex,
                uidScore.getScore(),
                super.logic.getHandCardNumberArray(chairIndex),
                super.logic.getPlayerSelectType(chairIndex)
        )));
    }
}

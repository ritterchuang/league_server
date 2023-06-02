package org.lsj.gs.math.games.zjh_java.module.state;

import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.table.entity.detail.DetailState;
import org.lsj.gs.math.games.zjh_java.TableZjhJava;
import org.lsj.gs.math.games.zjh_java.entity.ConstZjhJava;
import org.lsj.gs.math.games.zjh_java.entity.detail.DetailCompareResult;
import org.lsj.gs.math.games.zjh_java.entity.message.StcCompareZjhJava;
import org.lsj.gs.math.games.zjh_java.module.gameAdjust.GameAdjustZjhJava;
import org.lsj.gs.math.games.zjh_java.module.logic.LogicZjhJava;
import org.lsj.gs.math.games.zjh_java.module.robotLogic.RobotLogicZjhJava;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TimerTask;

// 比牌狀態
public class StateCompareZjhJava extends AbstractStateZjhJava {
    private static final String STATE_NAME = "cmp";
    private static final Logger LOG = LoggerFactory.getLogger(StateCompareZjhJava.class);
    public StateCompareZjhJava(TableZjhJava table, LogicZjhJava logic, RobotLogicZjhJava aiLogic, GameAdjustZjhJava gameAdjust, int stateId) {
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
        super.table.getTableTimer().schedule(new TimerTaskTimeOut(this), ConstZjhJava.TimeEnumZjhJava.COMPARE.getCode(), ConstZjhJava.TimeEnumZjhJava.COMPARE.getMilliTimeSec());
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
        StcCompareZjhJava stcCompare = new StcCompareZjhJava(
                super.logic.getAllPlayerHandCardNumberArray(),
                super.logic.getAllPlayerLiftCardNumberArray(),
                super.logic.getAllPlayerSelectTypeArray(),
                super.logic.getAllPlayerScoreArray());
        LOG.info(super.table.getTableLogTitle() + " message: {}", stcCompare);
        super.table.sendMessageToHumanPlayer(stcCompare);
    }

    // 超時定時器
    public class TimerTaskTimeOut extends TimerTask {
        private final StateCompareZjhJava state;

        public TimerTaskTimeOut(StateCompareZjhJava state){
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

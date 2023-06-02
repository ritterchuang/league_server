package org.lsj.gs.math.games.mybjl_java.module.state;

import org.lsj.gs.math.core.baiRen.ConstMathBaiRen;
import org.lsj.gs.math.core.common.robotLogic.entity.AreaBetResultBaiRen;
import org.lsj.gs.math.core.common.table.entity.detail.play.DetailPlayBaiRenBet;
import org.lsj.gs.math.core.common.table.entity.message.baiRen.CtsBetBaiRenJava;
import org.lsj.gs.math.games.mybjl_java.TableMybjlJava;
import org.lsj.gs.math.games.mybjl_java.module.gameAdjust.GameAdjustMybjlJava;
import org.lsj.gs.math.games.mybjl_java.module.logic.LogicMybjlJava;
import org.lsj.gs.math.games.mybjl_java.module.robotLogic.RobotLogicMybjlJava;
import org.lsj.websocket.ProtocolCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.TimerTask;

// 遊戲開始狀態
public class StateGameBeginMybjlJava extends AbstractStateMybjlJava {
    private static final Logger LOG = LoggerFactory.getLogger(StateGameBeginMybjlJava.class);
    public StateGameBeginMybjlJava(TableMybjlJava table, LogicMybjlJava logic, RobotLogicMybjlJava aiLogic, GameAdjustMybjlJava gameAdjust, int stateId) {
        super(table, logic, aiLogic, gameAdjust, stateId);
    }

    @Override
    public void onEnter() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());

        // 1. 更新起始時間
        super.logic.updateBetTime();

        // 2. 新增遊戲日誌
        super.addHumanLogGameBegin();

        // 3. 新增遊戲開始 時間詳細日誌
        super.logic.addDetailGameBeginTime();

        // 4. 遊戲開始前更新 初始餘額
        super.table.updatePlayerBeginMoneyBeforeGame();

        // 5. 開啟新局
        super.table.updateRoundId();

        // 6. 傳送局號
        super.table.sendRoundLogId();

        // 7. 傳送開始下注動作
        super.table.sendAction(ConstMathBaiRen.Action.BEGIN.getCode(), super.table.getMathConfig().getBetTimeSec());

        // 8. 傳送富豪榜
        super.table.sendRichList();

        // 9. 傳送玩家金額
        super.table.sendMoney();

        // 10. 機器人押注邏輯
        this.doRobotBet();
    }

    // 執行機器人押注邏輯
    private void doRobotBet() {
        // 1. 產生機器人下注結果
        List<AreaBetResultBaiRen> areaBetResultBaiRenList = super.robotLogic.generateRobotAreaBetResultList(super.getLeftTimeSec());

        // 2. 傳送機器人下注封包
        areaBetResultBaiRenList.forEach(areaBetResult ->
                    super.table.getTableTimer().schedule(
                            new TimerTaskRobotAreaBet(super.table, areaBetResult),
                            areaBetResult.getMilliTimeSec())
                );
    }

    // 機器人區域押注定時器
    public class TimerTaskRobotAreaBet extends TimerTask {
        private final TableMybjlJava table; // 牌桌
        private final AreaBetResultBaiRen areaBetResult; // 區域押注結果

        public TimerTaskRobotAreaBet(TableMybjlJava table, AreaBetResultBaiRen areaBetResult){
            this.table = table;
            this.areaBetResult = areaBetResult;
        }

        @Override
        public void run() {
            // 1. 更新下注區域
            this.table.getLogic().updateAreaBet(this.areaBetResult.getChairIndex(), this.areaBetResult.getAreaId(), areaBetResult.getBetAmount());

            // 2. 傳送押注封包
            this.table.sendBet(this.areaBetResult);

            // 3. 傳送區域押注封包
            this.table.sendArea();
        }
    }

    @Override
    public void onLeave() {
        LOG.info(super.table.getTableLogTitle() + " State: {} {}",this.getClass().getSimpleName(), new Object(){}.getClass().getEnclosingMethod().getName());

        // 1. 清空定時器
        super.table.getTableTimer().cancel();

        // 2. 傳送停止下注動作
        super.table.sendAction(ConstMathBaiRen.Action.STOP.getCode(), 0.0);
    }

    @Override
    public void onTimeout() {
        super.iLeave();
    }

    //* 流程相關 *//
    // 收到客戶端下注消息
    public void onRecvBetMsg(int chairIndex, CtsBetBaiRenJava data){
        // 1. 檢查玩家座位
        if (!super.logic.isValidChairIndex(chairIndex)) {
            LOG.error(super.table.getTableLogTitle() + " error: {}, chairIndex: {}",
                    "chairIndex not correct",
                    chairIndex);
            return;
        }

        // 2. 驗證下注區域合法性
        if(!super.logic.isValidArea(data.getArea())){
            LOG.error(super.table.getTableLogTitle() + " error: {}, chairIndex: {}, area: {}",
                    "area not correct",
                    chairIndex,
                    data.getArea());
            super.table.sendBetResult(chairIndex, data, ProtocolCode.FA_BET_RESULT_FAIL1.getCode());
            return;
        }

        // 3. 驗證下注金額列表合法性
        if(!super.logic.isValidChips(data.getChips())){
            LOG.error(super.table.getTableLogTitle() + " error: {}, chairIndex: {}, chips: {}",
                    "chips not correct",
                    chairIndex,
                    data.getChips());
            super.table.sendBetResult(chairIndex, data, ProtocolCode.FA_BET_RESULT_FAIL2.getCode());
            return;
        }

        // 4. 驗證下注單點限額
        if(!super.logic.isAreaBetToTopLimit(chairIndex, data.getArea(), data.getChips())){
            LOG.warn(super.table.getTableLogTitle() + " error: {}, chairIndex: {},  area: {}, chips: {}, topLimit:{}, userTotalAreaBet:{}",
                    "area bet to topLimit",
                    chairIndex,
                    data.getArea(),
                    data.getChips(),
                    super.logic.getAreasTopLimitMap().get(data.getArea()),
                    super.logic.getPlayerAreaBetArray(chairIndex)[data.getArea()]
            );
            super.table.sendBetResult(chairIndex, data, ProtocolCode.FA_BET_RESULT_FAIL4.getCode());
            return;
        }

        // 5. 驗證玩家餘額
        if(!super.table.isMoneyEnough(chairIndex, super.logic.getPlayerTotalAreaBet(chairIndex), data.getChips())){
            LOG.error(super.table.getTableLogTitle() + " error: {}, chairIndex: {}, money:{}, chips: {}",
                    "money not enough",
                    chairIndex,
                    super.table.getHumanGamePlayer().getBeginMoney(),
                    data.getChips());
            super.table.sendBetResult(chairIndex, data, ProtocolCode.FA_BET_RESULT_FAIL5.getCode());
            return;
        }

        // 6. 驗證下注區域可行性(不可同時投注紅黑)
        if(!super.logic.canBetToArea(chairIndex, data.getArea())){
            LOG.error(super.table.getTableLogTitle() + " error: {}, chairIndex: {}, area: {}",
                    "can not bet to area",
                    chairIndex,
                    data.getArea());
            super.table.sendBetResult(chairIndex, data, ProtocolCode.FA_BET_RESULT_FAIL10.getCode());
            return;
        }

        // 7. 更新玩家押注資訊
        super.logic.updateAreaBet(chairIndex, data.getArea(), data.getChips());

        // 8. 記錄真人詳細日誌
        super.logic.addDetailBaiRen(new DetailPlayBaiRenBet(
                super.table.calculateSpendSec(), data.getArea(), data.getChips()));

        // 9. 更新區域押注
        super.table.sendArea();

        // 10. 傳送玩家押注資訊
        super.table.sendBet(new AreaBetResultBaiRen(
               0,
                this.logic.getHumanChairIndex(),
                data.getArea(),
                data.getChips()
        ));

        // 11. 傳送玩家當前擁有押注資訊
        super.table.sendOwnBet();

        // 12. 傳送下注訊息
        super.table.sendBetResult(chairIndex, data, ProtocolCode.SUCCESS.getCode());
    }
}

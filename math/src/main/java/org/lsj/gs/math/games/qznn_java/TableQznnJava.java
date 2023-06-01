package org.lsj.gs.math.games.qznn_java;

import org.lsj.gs.event.TableFinishedEvent;
import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigQznnJava;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultCard;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module.GameBetLogResultCtrCard;
import org.lsj.gs.math.core.common.poolCtr.entity.IPoolConfig;
import org.lsj.gs.math.core.common.table.AbstractTableMessageEventCard;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtilCard;
import org.lsj.gs.math.games.qznn_java.entity.ConstQznnJava;
import org.lsj.gs.math.games.qznn_java.entity.message.*;
import org.lsj.gs.math.games.qznn_java.module.gameAdjust.GameAdjustQznnJava;
import org.lsj.gs.math.games.qznn_java.module.logic.LogicQznnJava;
import org.lsj.gs.math.games.qznn_java.module.robotLogic.RobotLogicQznnJava;
import org.lsj.gs.math.games.qznn_java.module.state.*;
import org.lsj.gs.user.IUser;
import org.lsj.utils.JsonUtil;
import org.lsj.websocket.ReceiveGameCommand;
import org.lsj.gs.math.games.qznn_java.entity.message.*;
import org.lsj.gs.math.games.qznn_java.module.state.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Event;
import javax.websocket.Session;

// 搶莊牛牛遊戲桌
public class TableQznnJava extends AbstractTableMessageEventCard {
    private static final Logger LOG = LoggerFactory.getLogger(TableQznnJava.class);
    private final LogicQznnJava logic; // 遊戲邏輯
    private final RobotLogicQznnJava robotLogic; // 機器人遊戲邏輯
    private final GameAdjustQznnJava gameAdjust; // 遊戲調控

    public TableQznnJava(Event<TableFinishedEvent> event,
                         int tableId,
                         TableFieldConfig tableFieldConfig,
                         TableGameConfigQznnJava tableGameConfig,
                         IPoolConfig poolConfig,
                         IUser user,
                         ITableUtilCard tableUtil) throws TableException {
        // 1. 初始化
        super(  event,
                tableFieldConfig,
                tableGameConfig,
                tableId,
                poolConfig,
                user,
                tableUtil);

        this.logic = new LogicQznnJava(this,
                tableFieldConfig,
                tableGameConfig,
                super.getGamePlayerHlr(),
                super.getPoolCtr(),
                new GameBetLogResultCtrCard(this.getBeginTimeMillis(),
                        super.getTableId(),
                        super.getTableConfigMgr().getConfig(),
                        super.getGamePlayerHlr(),
                        super.getPoolCtr(),
                        tableUtil),
                tableUtil);
        this.robotLogic = new RobotLogicQznnJava(
                super.getGamePlayerHlr(),
                super.getPoolCtr(), tableUtil,
                tableGameConfig.getRobotLogicConfigQz(),
                this.logic);
        this.gameAdjust = new GameAdjustQznnJava(
                super.getAlgorithmTypeCtr(),
                this.logic,
                super.getPoolCtr(),
                tableUtil);

        // 2. 註冊狀態機
        this.registerState();
    }

    //* 流程相關 *//

    // 啟動遊戲開始前處理
    @Override
    public void startBeforeGameBegin(){
        super.getStateMgr().setState(ConstQznnJava.StateEnumQznnJava.GAME_BEGIN.getCode());
    }

    // 啟動遊戲結束後處理
    @Override
    public void startAfterGameEnd(){
        super.startAfterGameEnd(this.getLogic().getHumanPlayerScore());
    }

    //* 傳輸協議相關 *//

    // 接收客戶端封包
    @Override
    public void receivedGameCommand(ReceiveGameCommand receiveGameCommand){
        switch (ConstMessageQznnJava.MessageEnum.fromEnumString(receiveGameCommand.getKey())){
            case CTS_VIE_BANKER: this.receivedGameCommandVieBanker(receiveGameCommand); return;
            case CTS_BET: this.receivedGameCommandBet(receiveGameCommand); return;
            case CTS_SELECT: this.receivedGameCommandSelect(receiveGameCommand); return;
            default:
        }
    }

    // 接收搶莊客戶端封包
    private void receivedGameCommandVieBanker(ReceiveGameCommand receiveGameCommand){
        // 1. 當前狀態檢查
        if(super.stateMgr.getCurrentStateId() != ConstQznnJava.StateEnumQznnJava.VIE_BANKER.getCode()){
            LOG.warn(this.getTableLogTitle() + " error: {}, receiveGameCommand: {}", "state error", receiveGameCommand.toString());
            return;
        }

        // 2. 請求
        try {
            ((StateVieBankerQznnJava)super.stateMgr.getStateMap().get(ConstQznnJava.StateEnumQznnJava.VIE_BANKER.getCode()))
                    .onMessageVieBanker(super.getGamePlayerHlr().getHumanChairIndex(), JsonUtil.getInstance().readValue(receiveGameCommand.getMessage(), CtsVieBankerQznnJava.class));
        } catch (Exception e) {
            LOG.error(this.getTableLogTitle() + " error: {}, receiveGameCommand: {}", "exe error", receiveGameCommand.toString());
            e.printStackTrace();
        }
    }

    // 接收下注客戶端封包
    private void receivedGameCommandBet(ReceiveGameCommand receiveGameCommand){
        // 1. 當前狀態檢查
        if(super.stateMgr.getCurrentStateId() != ConstQznnJava.StateEnumQznnJava.BET.getCode()){
            LOG.warn(this.getTableLogTitle() + " error: {}, receiveGameCommand: {}", "state error", receiveGameCommand.toString());
            return;
        }

        // 2. 請求
        try {
            ((StateBetQznnJava)super.stateMgr.getStateMap().get(ConstQznnJava.StateEnumQznnJava.BET.getCode()))
                    .onMessageBet(super.getGamePlayerHlr().getHumanChairIndex(), JsonUtil.getInstance().readValue(receiveGameCommand.getMessage(), CtsBetQznnJava.class));
        } catch (Exception e) {
            LOG.error(this.getTableLogTitle() + " error: {}, receiveGameCommand: {}", "exe error", receiveGameCommand.toString());
            e.printStackTrace();
        }
    }

    // 接收選牌客戶端封包
    private void receivedGameCommandSelect(ReceiveGameCommand receiveGameCommand){
        // 1. 當前狀態檢查
        if(super.stateMgr.getCurrentStateId() != ConstQznnJava.StateEnumQznnJava.SELECT.getCode()){
            LOG.warn(this.getTableLogTitle() + " error: {}, receiveGameCommand: {}", "state error", receiveGameCommand.toString());
            return;
        }

        // 2. 請求
        try {
            ((StateSelectQznnJava)super.stateMgr.getStateMap().get(ConstQznnJava.StateEnumQznnJava.SELECT.getCode()))
                    .onMessageSelect(super.getGamePlayerHlr().getHumanChairIndex(), JsonUtil.getInstance().readValue(receiveGameCommand.getMessage(), CtsSelectQznnJava.class));
        } catch (Exception e) {
            LOG.error(this.getTableLogTitle() + " error: {}, receiveGameCommand: {}", "exe error", receiveGameCommand.toString());
            e.printStackTrace();
        }
    }

    // 傳送斷線資訊
    @Override
    public void sendCutReturn(Session session) {
        // 1. 取得斷線重連基礎資訊
        int stage = super.getStateMgr().getCurrentStateId(); // 取得遊戲狀態
        int leftTime = super.getLeftSecond(this.getTimerIdByStateId(stage)); // 剩餘時間

        // 2. 搶莊階段資訊
        int banker = this.logic.getBankerChairIndex(); // 莊家
        int bankerRate = this.logic.getBankerRate(); // 莊家倍數
        int maxRate = this.logic.getHumanMaxVieRate(); // 最大搶莊倍數
        int[] vieRates = this.logic.getVieRateArray(); // 所有玩家搶莊倍數

        // 3 .下注階段資訊
        int[] betRates = this.logic.getBetResultArray(); // 所有玩家下注倍數
        int[] rates = this.calculateRateArray(stage); // 各階段可執行倍數

        // 4. 發牌階段資訊
        int[][] cards = this.logic.calculateCutReturnHandCardListArray();

        // 5. 選牌階段資訊
        int[][] lifts = this.logic.getAllPlayerLiftCardNumberArray(); // 所有玩家提起的牌
        int[][] select = this.logic.getAllPlayerSelectCardNumberArray(); // 所有玩家選牌狀態
        int maxType = this.logic.getHumanPlayerSelectType(); // 取得真人最佳牌型

        // 6. 比牌階段資訊
        int[] types = this.logic.getAllPlayerSelectTypeArray(); // 所有玩家牌型
        double[] scores = this.logic.getAllPlayerScoreArray(); // 所有玩家淨利

        // 7. 回傳斷線重連資訊
        StcCutReturnQznnJava stcCutReturnQznnJava = new StcCutReturnQznnJava(
                stage,
                leftTime,
                rates,
                banker,
                bankerRate,
                maxRate,
                vieRates,
                betRates,
                cards,
                lifts,
                select,
                maxType,
                types,
                scores
        );
        LOG.info(super.getTableLogTitle() + " message: {}", stcCutReturnQznnJava);
        super.sendMessageToHumanPlayer(stcCutReturnQznnJava);
    }

    // 計算當前可下注倍數列表
    private int[] calculateRateArray(int stage) {
        if (stage == ConstQznnJava.StateEnumQznnJava.VIE_BANKER.getCode()) {
            return this.logic.getHumanCanVieRateArray();
        }
        else if (stage == ConstQznnJava.StateEnumQznnJava.BET.getCode()) {
            return this.logic.getHumanCanBetRateArray();
        }

        return new int[]{};
    }


    //* 定時器相關 *//

    // 取得當前計時器索引
    private int getTimerIdByStateId(int stateId) {
        switch (ConstQznnJava.StateEnumQznnJava.fromCode(stateId)){
            case GAME_BEGIN: return ConstQznnJava.TimeEnumQznnJava.GAME_BEGIN.getCode();
            case VIE_BANKER: return ConstQznnJava.TimeEnumQznnJava.VIE_BANKER.getCode();
            case BET: return ConstQznnJava.TimeEnumQznnJava.BET.getCode();
            case DEAL_CARD: return ConstQznnJava.TimeEnumQznnJava.DEAL_CARD.getCode();
            case SELECT: return ConstQznnJava.TimeEnumQznnJava.SELECT.getCode();
            case COMPARE: return ConstQznnJava.TimeEnumQznnJava.COMPARE.getCode();
            default: return ConstQznnJava.TimeEnumQznnJava.READY.getCode();
        }
    }


    //* 狀態機相關 *//

    // 註冊狀態
    @Override
    public void registerState(){
        // 1. 註冊狀態機
        super.getStateMgr().registerState(new StateWaitBeginQznnJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstQznnJava.StateEnumQznnJava.WAIT_BEGIN.getCode()));
        super.getStateMgr().registerState(new StateGameBeginQznnJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstQznnJava.StateEnumQznnJava.GAME_BEGIN.getCode()));
        super.getStateMgr().registerState(new StateVieBankerQznnJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstQznnJava.StateEnumQznnJava.VIE_BANKER.getCode()));
        super.getStateMgr().registerState(new StateBetQznnJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstQznnJava.StateEnumQznnJava.BET.getCode()));
        super.getStateMgr().registerState(new StateDealCardQznnJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstQznnJava.StateEnumQznnJava.DEAL_CARD.getCode()));
        super.getStateMgr().registerState(new StateSelectQznnJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstQznnJava.StateEnumQznnJava.SELECT.getCode()));
        super.getStateMgr().registerState(new StateCompareQznnJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstQznnJava.StateEnumQznnJava.COMPARE.getCode()));
        super.getStateMgr().registerState(new StateGameEndQznnJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstQznnJava.StateEnumQznnJava.GAME_END.getCode(), super.getEvent()));

        // 2. 初始化狀態
        super.getStateMgr().setState(ConstQznnJava.StateEnumQznnJava.WAIT_BEGIN.getCode());
    }

    // 切換狀態
    @Override
    public void switchState(int exitStateId) {
        switch (ConstQznnJava.StateEnumQznnJava.fromCode(exitStateId)){
            case WAIT_BEGIN: super.getStateMgr().setState(ConstQznnJava.StateEnumQznnJava.GAME_BEGIN.getCode()); break;
            case GAME_BEGIN: super.getStateMgr().setState(ConstQznnJava.StateEnumQznnJava.VIE_BANKER.getCode()); break;
            case VIE_BANKER: super.getStateMgr().setState(ConstQznnJava.StateEnumQznnJava.BET.getCode()); break;
            case BET: super.getStateMgr().setState(ConstQznnJava.StateEnumQznnJava.DEAL_CARD.getCode()); break;
            case DEAL_CARD: super.getStateMgr().setState(ConstQznnJava.StateEnumQznnJava.SELECT.getCode()); break;
            case SELECT: super.getStateMgr().setState(ConstQznnJava.StateEnumQznnJava.COMPARE.getCode()); break;
            case COMPARE: super.getStateMgr().setState(ConstQznnJava.StateEnumQznnJava.GAME_END.getCode()); break;
            case GAME_END: super.getStateMgr().setState(ConstQznnJava.StateEnumQznnJava.WAIT_BEGIN.getCode()); break;
        }
    }


    //* 牌桌資訊 *//
    public LogicQznnJava getLogic(){
        return this.logic;
    }

    public RobotLogicQznnJava getRobotLogic() {
        return robotLogic;
    }

    // 取得遊戲結果
    @Override
    public IGameBetLogResultCard getResult() { return this.logic.getGameBetLogResultCard(this.logic.getHumanUidScore()); }
}

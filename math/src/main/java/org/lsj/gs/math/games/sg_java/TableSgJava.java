package org.lsj.gs.math.games.sg_java;

import org.lsj.gs.event.TableFinishedEvent;
import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigSgJava;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultCard;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module.GameBetLogResultCtrCard;
import org.lsj.gs.math.core.common.poolCtr.entity.IPoolConfig;
import org.lsj.gs.math.core.common.table.AbstractTableMessageEventCard;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtilCard;
import org.lsj.gs.math.games.sg_java.entity.ConstSgJava;
import org.lsj.gs.math.games.sg_java.entity.message.*;
import org.lsj.gs.math.games.sg_java.module.gameAdjust.GameAdjustSgJava;
import org.lsj.gs.math.games.sg_java.module.logic.LogicSgJava;
import org.lsj.gs.math.games.sg_java.module.robotLogic.RobotLogicSgJava;
import org.lsj.gs.math.games.sg_java.module.state.*;
import org.lsj.gs.user.IUser;
import org.lsj.utils.JsonUtil;
import org.lsj.websocket.ReceiveGameCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Event;
import javax.websocket.Session;

// 新三公遊戲桌
public class TableSgJava extends AbstractTableMessageEventCard{
    private static final Logger LOG = LoggerFactory.getLogger(TableSgJava.class);
    private final LogicSgJava logic; // 遊戲邏輯
    private final RobotLogicSgJava robotLogic; // 機器人遊戲邏輯
    private final GameAdjustSgJava gameAdjust; // 遊戲調控

    public TableSgJava(Event<TableFinishedEvent> event,
                       int tableId,
                       TableFieldConfig tableFieldConfig,
                       TableGameConfigSgJava tableGameConfig,
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

        this.logic = new LogicSgJava(this,
                tableFieldConfig,
                tableGameConfig,
                super.getGamePlayerHlr(),
                super.getPoolCtr(),
                new GameBetLogResultCtrCard(this.getBeginTimeMillis(),
                        this.getTableId(),
                        super.getTableConfigMgr().getConfig(),
                        super.getGamePlayerHlr(),
                        super.getPoolCtr(),
                        tableUtil),
                tableUtil);
        this.robotLogic = new RobotLogicSgJava(super.getGamePlayerHlr(), super.getPoolCtr(), tableUtil,
                tableGameConfig.getRobotLogicConfigBanker(),
                this.logic);
        this.gameAdjust = new GameAdjustSgJava(
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
    public void startBeforeGameBegin(){ super.getStateMgr().setState(ConstSgJava.StateEnumSgJava.GAME_BEGIN.getCode()); }

    // 啟動遊戲結束後處理
    @Override
    public void startAfterGameEnd(){
        super.startAfterGameEnd(this.getLogic().getHumanPlayerScore());
    }


    //* 傳輸協議相關 *//
    // 接收客戶端封包
    @Override
    public void receivedGameCommand(ReceiveGameCommand receiveGameCommand){
        switch (ConstMessageSgJava.MessageEnum.fromEnumString(receiveGameCommand.getKey())){
            case CTS_VIE_BANKER: this.receivedGameCommandVieBanker(receiveGameCommand); return;
            case CTS_BET: this.receivedGameCommandBet(receiveGameCommand); return;
            case CTS_SELECT: this.receivedGameCommandSelect(receiveGameCommand); return;
            default:
        }
    }

    // 接收搶莊客戶端封包
    private void receivedGameCommandVieBanker(ReceiveGameCommand receiveGameCommand){
        // 1. 當前狀態檢查
        if(super.stateMgr.getCurrentStateId() != ConstSgJava.StateEnumSgJava.VIE_BANKER.getCode()){
            LOG.warn(this.getTableLogTitle() + " error: {}, receiveGameCommand: {}", "state error", receiveGameCommand.toString());
            return;
        }

        // 2. 請求
        try {
            ((StateVieBankerSgJava)super.stateMgr.getStateMap().get(ConstSgJava.StateEnumSgJava.VIE_BANKER.getCode()))
                    .onMessageVieBanker(super.getGamePlayerHlr().getHumanChairIndex(), JsonUtil.getInstance().readValue(receiveGameCommand.getMessage(), CtsVieBankerSgJava.class));
        } catch (Exception e) {
            LOG.error(this.getTableLogTitle() + " error: {}, receiveGameCommand: {}", "exe error", receiveGameCommand.toString());
            e.printStackTrace();
        }
    }

    // 接收下注客戶端封包
    private void receivedGameCommandBet(ReceiveGameCommand receiveGameCommand){
        // 1. 當前狀態檢查
        if(super.stateMgr.getCurrentStateId() != ConstSgJava.StateEnumSgJava.BET.getCode()){
            LOG.warn(this.getTableLogTitle() + " error: {}, receiveGameCommand: {}", "state error", receiveGameCommand.toString());
            return;
        }

        // 2. 請求
        try {
            ((StateBetSgJava)super.stateMgr.getStateMap().get(ConstSgJava.StateEnumSgJava.BET.getCode()))
                    .onMessageBet(super.getGamePlayerHlr().getHumanChairIndex(), JsonUtil.getInstance().readValue(receiveGameCommand.getMessage(), CtsBetSgJava.class));
        } catch (Exception e) {
            LOG.error(this.getTableLogTitle() + " error: {}, receiveGameCommand: {}", "exe error", receiveGameCommand.toString());
            e.printStackTrace();
        }
    }

    // 接收選牌客戶端封包
    private void receivedGameCommandSelect(ReceiveGameCommand receiveGameCommand){
        // 1. 當前狀態檢查
        if(super.stateMgr.getCurrentStateId() != ConstSgJava.StateEnumSgJava.SELECT.getCode()){
            LOG.warn(this.getTableLogTitle() + " error: {}, receiveGameCommand: {}", "state error",receiveGameCommand.toString());
            return;
        }

        // 2. 請求
        try {
            ((StateSelectSgJava)super.stateMgr.getStateMap().get(ConstSgJava.StateEnumSgJava.SELECT.getCode()))
                    .onMessageSelect(super.getGamePlayerHlr().getHumanChairIndex(), JsonUtil.getInstance().readValue(receiveGameCommand.getMessage(), CtsSelectSgJava.class));
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
        int[] robbankers = this.logic.getVieRateArray(); // 所有玩家搶莊倍數

        // 3 .下注階段資訊
        int[] betRates = this.logic.getBetResultArray(); // 所有玩家下注倍數

        // 4. 發牌階段資訊
        int[][] cards = this.logic.calculateCutReturnHandCardListArray();

        // 6. 比牌階段資訊
        int[] types = this.logic.getAllPlayerSelectTypeArray(); // 所有玩家牌型

        // 7. 回傳斷線重連資訊
        StcCutReturnSgJava stcCutReturnSgJava = new StcCutReturnSgJava(
                stage,
                leftTime,
                banker,
                robbankers,
                betRates,
                cards,
                types
        );
        LOG.info(super.getTableLogTitle() + " message: {}", stcCutReturnSgJava);
        super.sendMessageToHumanPlayer(stcCutReturnSgJava);

        // 8. 回傳搶莊搶閒資訊
        this.sendRobMessageToHumanPlayer(stage);
    }

    // 回傳搶莊搶閒資訊
    private void sendRobMessageToHumanPlayer(int stage) {
        int leftTime = super.getLeftSecond(this.getTimerIdByStateId(stage)); // 剩餘時間

        if (stage == ConstSgJava.StateEnumSgJava.VIE_BANKER.getCode()) {
            super.sendMessageToHumanPlayer(new StcStartRobSgJava(leftTime, this.logic.getHumanCanVieRateArray()));
        }

        if (stage == ConstSgJava.StateEnumSgJava.BET.getCode()) {
            super.sendMessageToHumanPlayer(new StcStartBetSgJava(leftTime, this.logic.getHumanCanBetRateArray()));
        }
    }


    //* 定時器相關 *//
    // 取得當前計時器索引
    private int getTimerIdByStateId(int stateId) {
        switch (ConstSgJava.StateEnumSgJava.fromCode(stateId)){
            case GAME_BEGIN: return ConstSgJava.TimeEnumSgJava.GAME_BEGIN.getCode();
            case DEAL_CARD: return ConstSgJava.TimeEnumSgJava.DEAL_CARD.getCode();
            case VIE_BANKER: return ConstSgJava.TimeEnumSgJava.VIE_BANKER.getCode();
            case BET: return ConstSgJava.TimeEnumSgJava.BET.getCode();
            case SELECT: return ConstSgJava.TimeEnumSgJava.SELECT.getCode();
            case COMPARE: return ConstSgJava.TimeEnumSgJava.COMPARE.getCode();
            default: return ConstSgJava.TimeEnumSgJava.READY.getCode();
        }
    }


    //* 狀態機相關 *//
    // 註冊狀態
    @Override
    public void registerState(){
        // 1. 註冊狀態機
        super.getStateMgr().registerState(new StateWaitBeginSgJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstSgJava.StateEnumSgJava.WAIT_BEGIN.getCode()));
        super.getStateMgr().registerState(new StateGameBeginSgJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstSgJava.StateEnumSgJava.GAME_BEGIN.getCode()));
        super.getStateMgr().registerState(new StateDealCardSgJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstSgJava.StateEnumSgJava.DEAL_CARD.getCode()));
        super.getStateMgr().registerState(new StateVieBankerSgJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstSgJava.StateEnumSgJava.VIE_BANKER.getCode()));
        super.getStateMgr().registerState(new StateBetSgJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstSgJava.StateEnumSgJava.BET.getCode()));
        super.getStateMgr().registerState(new StateSelectSgJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstSgJava.StateEnumSgJava.SELECT.getCode()));
        super.getStateMgr().registerState(new StateCompareSgJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstSgJava.StateEnumSgJava.COMPARE.getCode()));
        super.getStateMgr().registerState(new StateGameEndSgJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstSgJava.StateEnumSgJava.GAME_END.getCode(), super.getEvent()));

        // 2. 初始化狀態
        super.getStateMgr().setState(ConstSgJava.StateEnumSgJava.WAIT_BEGIN.getCode());
    }

    // 切換狀態
    @Override
    public void switchState(int exitStateId) {
        switch (ConstSgJava.StateEnumSgJava.fromCode(exitStateId)){
            case WAIT_BEGIN: super.getStateMgr().setState(ConstSgJava.StateEnumSgJava.GAME_BEGIN.getCode()); break;
            case GAME_BEGIN: super.getStateMgr().setState(ConstSgJava.StateEnumSgJava.DEAL_CARD.getCode()); break;
            case DEAL_CARD: super.getStateMgr().setState(ConstSgJava.StateEnumSgJava.VIE_BANKER.getCode()); break;
            case VIE_BANKER: super.getStateMgr().setState(ConstSgJava.StateEnumSgJava.BET.getCode()); break;
            case BET: super.getStateMgr().setState(ConstSgJava.StateEnumSgJava.SELECT.getCode()); break;
            case SELECT: super.getStateMgr().setState(ConstSgJava.StateEnumSgJava.COMPARE.getCode()); break;
            case COMPARE: super.getStateMgr().setState(ConstSgJava.StateEnumSgJava.GAME_END.getCode()); break;
            case GAME_END: super.getStateMgr().setState(ConstSgJava.StateEnumSgJava.WAIT_BEGIN.getCode()); break;
        }

    }


    //* 牌桌資訊 *//
    public LogicSgJava getLogic(){
        return this.logic;
    }

    public RobotLogicSgJava getRobotLogic() {
        return robotLogic;
    }

    // 取得遊戲結果
    @Override
    public IGameBetLogResultCard getResult() { return this.logic.getGameBetLogResultCard(this.logic.getHumanUidScore()); }
}

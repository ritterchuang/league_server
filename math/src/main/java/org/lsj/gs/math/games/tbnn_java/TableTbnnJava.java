package org.lsj.gs.math.games.tbnn_java;

import org.lsj.gs.event.TableFinishedEvent;
import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigTbnnJava;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultCard;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module.GameBetLogResultCtrCard;
import org.lsj.gs.math.core.common.poolCtr.entity.IPoolConfig;
import org.lsj.gs.math.core.common.table.AbstractTableMessageEventCard;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtilCard;
import org.lsj.gs.math.games.tbnn_java.entity.ConstTbnnJava;
import org.lsj.gs.math.games.tbnn_java.entity.message.ConstMessageTbnnJava;
import org.lsj.gs.math.games.tbnn_java.entity.message.CtsBetTbnnJava;
import org.lsj.gs.math.games.tbnn_java.entity.message.CtsSelectTbnnJava;
import org.lsj.gs.math.games.tbnn_java.entity.message.StcCutReturnTbnnJava;
import org.lsj.gs.math.games.tbnn_java.module.gameAdjust.GameAdjustTbnnJava;
import org.lsj.gs.math.games.tbnn_java.module.logic.LogicTbnnJava;
import org.lsj.gs.math.games.tbnn_java.module.robotLogic.RobotLogicTbnnJava;
import org.lsj.gs.math.games.tbnn_java.module.state.*;
import org.lsj.gs.user.IUser;
import org.lsj.utils.JsonUtil;
import org.lsj.websocket.ReceiveGameCommand;
import org.lsj.gs.math.games.tbnn_java.module.state.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Event;
import javax.websocket.Session;

// 通比牛牛遊戲桌
public class TableTbnnJava extends AbstractTableMessageEventCard {
    private static final Logger LOG = LoggerFactory.getLogger(TableTbnnJava.class);
    private final LogicTbnnJava logic; // 遊戲邏輯
    private final RobotLogicTbnnJava robotLogic; // 機器人遊戲邏輯
    private final GameAdjustTbnnJava gameAdjust; // 遊戲調控

    public TableTbnnJava(Event<TableFinishedEvent> event,
                         int tableId,
                         TableFieldConfig tableFieldConfig,
                         TableGameConfigTbnnJava tableGameConfig,
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

        this.logic = new LogicTbnnJava(this,
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
        this.robotLogic = new RobotLogicTbnnJava(
                super.getGamePlayerHlr(),
                super.getPoolCtr(), tableUtil,
                tableGameConfig.getRobotLogicConfigTonbi(),
                this.logic);
        this.gameAdjust = new GameAdjustTbnnJava(
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
        super.getStateMgr().setState(ConstTbnnJava.StateEnumTbnnJava.GAME_BEGIN.getCode());
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
        switch (ConstMessageTbnnJava.MessageEnum.fromEnumString(receiveGameCommand.getKey())){
            case CTS_BET: this.receivedGameCommandBet(receiveGameCommand); return;
            case CTS_SELECT: this.receivedGameCommandSelect(receiveGameCommand); return;
            default:
        }
    }

    // 接收下注客戶端封包
    private void receivedGameCommandBet(ReceiveGameCommand receiveGameCommand){
        // 1. 當前狀態檢查
        if(super.stateMgr.getCurrentStateId() != ConstTbnnJava.StateEnumTbnnJava.BET.getCode()){
            LOG.warn(this.getTableLogTitle() + " error: {}, receiveGameCommand: {}", "state error", receiveGameCommand.toString());
            return;
        }

        // 2. 請求
        try {
            ((StateBetTbnnJava)super.stateMgr.getStateMap().get(ConstTbnnJava.StateEnumTbnnJava.BET.getCode()))
                    .onMessageBet(super.getGamePlayerHlr().getHumanChairIndex(), JsonUtil.getInstance().readValue(receiveGameCommand.getMessage(), CtsBetTbnnJava.class));
        } catch (Exception e) {
            LOG.error(this.getTableLogTitle() + " error: {}, receiveGameCommand: {}", "exe error", receiveGameCommand.toString());
            e.printStackTrace();
        }
    }

    // 接收選牌客戶端封包
    private void receivedGameCommandSelect(ReceiveGameCommand receiveGameCommand){
        // 1. 當前狀態檢查
        if(super.stateMgr.getCurrentStateId() != ConstTbnnJava.StateEnumTbnnJava.SELECT.getCode()){
            LOG.warn(this.getTableLogTitle() + " error: {}, receiveGameCommand: {}", "state error", receiveGameCommand.toString());
            return;
        }

        // 2. 請求
        try {
            ((StateSelectTbnnJava)super.stateMgr.getStateMap().get(ConstTbnnJava.StateEnumTbnnJava.SELECT.getCode()))
                    .onMessageSelect(super.getGamePlayerHlr().getHumanChairIndex(), JsonUtil.getInstance().readValue(receiveGameCommand.getMessage(), CtsSelectTbnnJava.class));
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
        StcCutReturnTbnnJava stcCutReturnTbnnJava = new StcCutReturnTbnnJava(
                stage,
                leftTime,
                rates,
                betRates,
                cards,
                lifts,
                select,
                maxType,
                types,
                scores
        );
        LOG.info(super.getTableLogTitle() + " message: {}", stcCutReturnTbnnJava);
        super.sendMessageToHumanPlayer(stcCutReturnTbnnJava);
    }

    // 計算當前可下注倍數列表
    private int[] calculateRateArray(int stage) {
        if (stage == ConstTbnnJava.StateEnumTbnnJava.BET.getCode()) {
            return this.logic.getHumanCanBetRateArray();
        }

        return new int[]{};
    }


    //* 定時器相關 *//

    // 取得當前計時器索引
    private int getTimerIdByStateId(int stateId) {
        switch (ConstTbnnJava.StateEnumTbnnJava.fromCode(stateId)){
            case GAME_BEGIN: return ConstTbnnJava.TimeEnumTbnnJava.GAME_BEGIN.getCode();
            case BET: return ConstTbnnJava.TimeEnumTbnnJava.BET.getCode();
            case DEAL_CARD: return ConstTbnnJava.TimeEnumTbnnJava.DEAL_CARD.getCode();
            case SELECT: return ConstTbnnJava.TimeEnumTbnnJava.SELECT.getCode();
            case COMPARE: return ConstTbnnJava.TimeEnumTbnnJava.COMPARE.getCode();
            default: return ConstTbnnJava.TimeEnumTbnnJava.READY.getCode();
        }
    }


    //* 狀態機相關 *//

    // 註冊狀態
    @Override
    public void registerState(){
        // 1. 註冊狀態機
        super.getStateMgr().registerState(new StateWaitBeginTbnnJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstTbnnJava.StateEnumTbnnJava.WAIT_BEGIN.getCode()));
        super.getStateMgr().registerState(new StateGameBeginTbnnJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstTbnnJava.StateEnumTbnnJava.GAME_BEGIN.getCode()));
        super.getStateMgr().registerState(new StateBetTbnnJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstTbnnJava.StateEnumTbnnJava.BET.getCode()));
        super.getStateMgr().registerState(new StateDealCardTbnnJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstTbnnJava.StateEnumTbnnJava.DEAL_CARD.getCode()));
        super.getStateMgr().registerState(new StateSelectTbnnJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstTbnnJava.StateEnumTbnnJava.SELECT.getCode()));
        super.getStateMgr().registerState(new StateCompareTbnnJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstTbnnJava.StateEnumTbnnJava.COMPARE.getCode()));
        super.getStateMgr().registerState(new StateGameEndTbnnJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstTbnnJava.StateEnumTbnnJava.GAME_END.getCode(), super.getEvent()));

        // 2. 初始化狀態
        super.getStateMgr().setState(ConstTbnnJava.StateEnumTbnnJava.WAIT_BEGIN.getCode());
    }

    // 切換狀態
    @Override
    public void switchState(int exitStateId) {
        switch (ConstTbnnJava.StateEnumTbnnJava.fromCode(exitStateId)){
            case WAIT_BEGIN: super.getStateMgr().setState(ConstTbnnJava.StateEnumTbnnJava.GAME_BEGIN.getCode()); break;
            case GAME_BEGIN: super.getStateMgr().setState(ConstTbnnJava.StateEnumTbnnJava.BET.getCode()); break;
            case BET: super.getStateMgr().setState(ConstTbnnJava.StateEnumTbnnJava.DEAL_CARD.getCode()); break;
            case DEAL_CARD: super.getStateMgr().setState(ConstTbnnJava.StateEnumTbnnJava.SELECT.getCode()); break;
            case SELECT: super.getStateMgr().setState(ConstTbnnJava.StateEnumTbnnJava.COMPARE.getCode()); break;
            case COMPARE: super.getStateMgr().setState(ConstTbnnJava.StateEnumTbnnJava.GAME_END.getCode()); break;
            case GAME_END: super.getStateMgr().setState(ConstTbnnJava.StateEnumTbnnJava.WAIT_BEGIN.getCode()); break;
        }
    }


    //* 牌桌資訊 *//
    public LogicTbnnJava getLogic(){
        return this.logic;
    }

    public RobotLogicTbnnJava getRobotLogic() {
        return robotLogic;
    }

    // 取得遊戲結果
    @Override
    public IGameBetLogResultCard getResult() { return this.logic.getGameBetLogResultCard(this.logic.getHumanUidScore()); }
}

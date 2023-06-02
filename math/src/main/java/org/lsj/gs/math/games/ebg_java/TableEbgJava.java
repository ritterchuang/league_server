package org.lsj.gs.math.games.ebg_java;

import org.lsj.gs.event.TableFinishedEvent;
import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigEbgJava;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultCard;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module.GameBetLogResultCtrCard;
import org.lsj.gs.math.core.common.poolCtr.entity.IPoolConfig;
import org.lsj.gs.math.core.common.table.AbstractTableMessageEventCard;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtilCard;
import org.lsj.gs.math.games.ebg_java.entity.ConstEbgJava;
import org.lsj.gs.math.games.ebg_java.entity.message.ConstMessageEbgJava;
import org.lsj.gs.math.games.ebg_java.entity.message.CtsBetEbgJava;
import org.lsj.gs.math.games.ebg_java.entity.message.CtsVieBankerEbgJava;
import org.lsj.gs.math.games.ebg_java.entity.message.StcCutReturnEbgJava;
import org.lsj.gs.math.games.ebg_java.module.gameAdjust.GameAdjustEbgJava;
import org.lsj.gs.math.games.ebg_java.module.logic.LogicEbgJava;
import org.lsj.gs.math.games.ebg_java.module.robotLogic.RobotLogicEbgJava;
import org.lsj.gs.math.games.ebg_java.module.state.*;
import org.lsj.gs.user.IUser;
import org.lsj.utils.JsonUtil;
import org.lsj.utils.MathUtil;
import org.lsj.websocket.ReceiveGameCommand;
import org.lsj.gs.math.games.ebg_java.module.state.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Event;
import javax.websocket.Session;

// 搶莊二八槓遊戲桌
public class TableEbgJava extends AbstractTableMessageEventCard {
    private static final Logger LOG = LoggerFactory.getLogger(TableEbgJava.class);
    private final LogicEbgJava logic; // 遊戲邏輯
    private final RobotLogicEbgJava robotLogic; // 機器人遊戲邏輯
    private final GameAdjustEbgJava gameAdjust; // 遊戲調控

    public TableEbgJava(Event<TableFinishedEvent> event,
                        int tableId,
                        TableFieldConfig tableFieldConfig,
                        TableGameConfigEbgJava tableGameConfig,
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

        this.logic = new LogicEbgJava(this,
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
        this.robotLogic = new RobotLogicEbgJava(
                super.getGamePlayerHlr(),
                super.getPoolCtr(), tableUtil,
                tableGameConfig.getRobotLogicConfigBanker(),
                this.logic);
        this.gameAdjust = new GameAdjustEbgJava(
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
        super.getStateMgr().setState(ConstEbgJava.StateEnumEbgJava.GAME_BEGIN.getCode());
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
        switch (ConstMessageEbgJava.MessageEnum.fromEnumString(receiveGameCommand.getKey())){
            case CTS_VIE_BANKER: this.receivedGameCommandVieBanker(receiveGameCommand); return;
            case CTS_BET: this.receivedGameCommandBet(receiveGameCommand); return;
            default:
        }
    }

    // 接收搶莊客戶端封包
    private void receivedGameCommandVieBanker(ReceiveGameCommand receiveGameCommand){
        // 1. 當前狀態檢查
        if(super.stateMgr.getCurrentStateId() != ConstEbgJava.StateEnumEbgJava.VIE_BANKER.getCode()){
            LOG.warn(this.getTableLogTitle() + " error: {}, receiveGameCommand: {}", "state error", receiveGameCommand.toString());
            return;
        }

        // 2. 請求
        try {
            ((StateVieBankerEbgJava)super.stateMgr.getStateMap().get(ConstEbgJava.StateEnumEbgJava.VIE_BANKER.getCode()))
                    .onMessageVieBanker(super.getGamePlayerHlr().getHumanChairIndex(), JsonUtil.getInstance().readValue(receiveGameCommand.getMessage(), CtsVieBankerEbgJava.class));
        } catch (Exception e) {
            LOG.error(this.getTableLogTitle() + " error: {}, receiveGameCommand: {}", "exe error", receiveGameCommand.toString());
            e.printStackTrace();
        }
    }

    // 接收下注客戶端封包
    private void receivedGameCommandBet(ReceiveGameCommand receiveGameCommand){
        // 1. 當前狀態檢查
        if(super.stateMgr.getCurrentStateId() != ConstEbgJava.StateEnumEbgJava.BET.getCode()){
            LOG.warn(this.getTableLogTitle() + " error: {}, receiveGameCommand: {}", "state error", receiveGameCommand.toString());
            return;
        }

        // 2. 請求
        try {
            ((StateBetEbgJava)super.stateMgr.getStateMap().get(ConstEbgJava.StateEnumEbgJava.BET.getCode()))
                    .onMessageBet(super.getGamePlayerHlr().getHumanChairIndex(), JsonUtil.getInstance().readValue(receiveGameCommand.getMessage(), CtsBetEbgJava.class));
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
        int[] vieRates = this.logic.getVieRateArray(); // 所有玩家搶莊倍數

        // 3 .下注階段資訊
        int[] betRates = this.logic.getBetResultArray(); // 所有玩家下注倍數
        int[] opRates = this.calculateRateArray(stage); // 各階段可執行倍數
        double ownBet = this.calculateOwnBet(betRates); // 真人下注額
        double totalBet = this.calculateTotalBet(betRates); // 牌桌下注額

        // 4. 發牌階段資訊
        int[][] cards = this.logic.calculateCutReturnHandCardListArray();

        // 6. 比牌階段資訊
        int[] types = this.logic.getAllPlayerSelectTypeArray(); // 所有玩家牌型
        double[] scores = this.logic.getAllPlayerScoreArray(); // 所有玩家淨利

        // 7. 回傳斷線重連資訊
        StcCutReturnEbgJava stcCutReturn = new StcCutReturnEbgJava(
                stage,
                leftTime,
                opRates,
                banker,
                bankerRate,
                vieRates,
                betRates,
                ownBet,
                totalBet,
                cards,
                types,
                scores
        );
        LOG.info(super.getTableLogTitle() + " message: {}", stcCutReturn);
        super.sendMessageToHumanPlayer(stcCutReturn);
    }

    // 計算當前可下注倍數列表
    private int[] calculateRateArray(int stage) {
        if (stage == ConstEbgJava.StateEnumEbgJava.VIE_BANKER.getCode()) {
            return this.logic.getHumanCanVieRateArray();
        }
        else if (stage == ConstEbgJava.StateEnumEbgJava.BET.getCode()) {
            return this.logic.getHumanCanBetRateArray();
        }

        return new int[]{};
    }

    // 計算真人投注額
    private double calculateOwnBet(int[] betResult) {
        int ownBet = betResult[this.logic.getHumanChairIndex()] == -1 ? 0 : betResult[this.logic.getHumanChairIndex()];

        return MathUtil.moneyScale(MathUtil.multiply(super.tableConfigMgr.getConfig().getBase(), ownBet));
    }

    // 計算閒家投注額
    private double calculateTotalBet(int[] betResult) {
        int result = 0;
        for (int index = 0; index < betResult.length; index++) {
            if (betResult[index] > 0) {
                result += betResult[index];
            }
        }

        return MathUtil.moneyScale(MathUtil.multiply(super.tableConfigMgr.getConfig().getBase(), result));
    }


    //* 定時器相關 *//

    // 取得當前計時器索引
    private int getTimerIdByStateId(int stateId) {
        switch (ConstEbgJava.StateEnumEbgJava.fromCode(stateId)){
            case GAME_BEGIN: return ConstEbgJava.TimeEnumEbgJava.GAME_BEGIN.getCode();
            case VIE_BANKER: return ConstEbgJava.TimeEnumEbgJava.VIE_BANKER.getCode();
            case BET: return ConstEbgJava.TimeEnumEbgJava.BET.getCode();
            case DEAL_CARD: return ConstEbgJava.TimeEnumEbgJava.DEAL_CARD.getCode();
            case COMPARE: return ConstEbgJava.TimeEnumEbgJava.COMPARE.getCode();
            default: return ConstEbgJava.TimeEnumEbgJava.READY.getCode();
        }
    }


    //* 狀態機相關 *//

    // 註冊狀態
    @Override
    public void registerState(){
        // 1. 註冊狀態機
        super.getStateMgr().registerState(new StateWaitBeginEbgJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstEbgJava.StateEnumEbgJava.WAIT_BEGIN.getCode()));
        super.getStateMgr().registerState(new StateGameBeginEbgJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstEbgJava.StateEnumEbgJava.GAME_BEGIN.getCode()));
        super.getStateMgr().registerState(new StateVieBankerEbgJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstEbgJava.StateEnumEbgJava.VIE_BANKER.getCode()));
        super.getStateMgr().registerState(new StateBetEbgJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstEbgJava.StateEnumEbgJava.BET.getCode()));
        super.getStateMgr().registerState(new StateDealCardEbgJava(this, this.logic, this.robotLogic, this.gameAdjust, super.tableUtil, ConstEbgJava.StateEnumEbgJava.DEAL_CARD.getCode()));
        super.getStateMgr().registerState(new StateCompareEbgJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstEbgJava.StateEnumEbgJava.COMPARE.getCode()));
        super.getStateMgr().registerState(new StateGameEndEbgJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstEbgJava.StateEnumEbgJava.GAME_END.getCode(), super.getEvent()));


       // 2. 初始化狀態
        super.getStateMgr().setState(ConstEbgJava.StateEnumEbgJava.WAIT_BEGIN.getCode());
    }

    // 切換狀態
    @Override
    public void switchState(int exitStateId) {
        switch (ConstEbgJava.StateEnumEbgJava.fromCode(exitStateId)){
            case WAIT_BEGIN: super.getStateMgr().setState(ConstEbgJava.StateEnumEbgJava.GAME_BEGIN.getCode()); break;
            case GAME_BEGIN: super.getStateMgr().setState(ConstEbgJava.StateEnumEbgJava.VIE_BANKER.getCode()); break;
            case VIE_BANKER: super.getStateMgr().setState(ConstEbgJava.StateEnumEbgJava.BET.getCode()); break;
            case BET: super.getStateMgr().setState(ConstEbgJava.StateEnumEbgJava.DEAL_CARD.getCode()); break;
            case DEAL_CARD: super.getStateMgr().setState(ConstEbgJava.StateEnumEbgJava.COMPARE.getCode()); break;
            case COMPARE: super.getStateMgr().setState(ConstEbgJava.StateEnumEbgJava.GAME_END.getCode()); break;
            case GAME_END: super.getStateMgr().setState(ConstEbgJava.StateEnumEbgJava.WAIT_BEGIN.getCode()); break;
        }
    }


    //* 牌桌資訊 *//
    public LogicEbgJava getLogic(){
        return this.logic;
    }

    public RobotLogicEbgJava getRobotLogic() {
        return robotLogic;
    }

    // 取得遊戲結果
    @Override
    public IGameBetLogResultCard getResult() { return this.logic.getGameBetLogResultCard(this.logic.getHumanUidScore()); }
}

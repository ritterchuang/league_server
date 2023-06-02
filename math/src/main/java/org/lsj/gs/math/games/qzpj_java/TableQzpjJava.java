package org.lsj.gs.math.games.qzpj_java;

import org.lsj.gs.event.TableFinishedEvent;
import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigQzpjJava;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultCard;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module.GameBetLogResultCtrCard;
import org.lsj.gs.math.core.common.poolCtr.entity.IPoolConfig;
import org.lsj.gs.math.core.common.table.AbstractTableMessageEventCard;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtilCard;
import org.lsj.gs.math.games.qzpj_java.entity.ConstQzpjJava;
import org.lsj.gs.math.games.qzpj_java.entity.message.ConstMessageQzpjJava;
import org.lsj.gs.math.games.qzpj_java.entity.message.CtsBetQzpjJava;
import org.lsj.gs.math.games.qzpj_java.entity.message.CtsVieBankerQzpjJava;
import org.lsj.gs.math.games.qzpj_java.entity.message.StcCutReturnQzpjJava;
import org.lsj.gs.math.games.qzpj_java.module.gameAdjust.GameAdjustQzpjJava;
import org.lsj.gs.math.games.qzpj_java.module.logic.LogicQzpjJava;
import org.lsj.gs.math.games.qzpj_java.module.robotLogic.RobotLogicQzpjJava;
import org.lsj.gs.math.games.qzpj_java.module.state.*;
import org.lsj.gs.user.IUser;
import org.lsj.utils.JsonUtil;
import org.lsj.utils.MathUtil;
import org.lsj.websocket.ReceiveGameCommand;
import org.lsj.gs.math.games.qzpj_java.module.state.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Event;
import javax.websocket.Session;

// 搶莊牌九遊戲桌
public class TableQzpjJava extends AbstractTableMessageEventCard {
    private static final Logger LOG = LoggerFactory.getLogger(TableQzpjJava.class);
    private final LogicQzpjJava logic; // 遊戲邏輯
    private final RobotLogicQzpjJava robotLogic; // 機器人遊戲邏輯
    private final GameAdjustQzpjJava gameAdjust; // 遊戲調控

    public TableQzpjJava(Event<TableFinishedEvent> event,
                         int tableId,
                         TableFieldConfig tableFieldConfig,
                         TableGameConfigQzpjJava tableGameConfig,
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

        this.logic = new LogicQzpjJava(this,
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
        this.robotLogic = new RobotLogicQzpjJava(
                super.getGamePlayerHlr(),
                super.getPoolCtr(), tableUtil,
                tableGameConfig.getRobotLogicConfigBanker(),
                this.logic);
        this.gameAdjust = new GameAdjustQzpjJava(
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
        super.getStateMgr().setState(ConstQzpjJava.StateEnumQzpjJava.GAME_BEGIN.getCode());
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
        switch (ConstMessageQzpjJava.MessageEnum.fromEnumString(receiveGameCommand.getKey())){
            case CTS_VIE_BANKER: this.receivedGameCommandVieBanker(receiveGameCommand); return;
            case CTS_BET: this.receivedGameCommandBet(receiveGameCommand); return;
            default:
        }
    }

    // 接收搶莊客戶端封包
    private void receivedGameCommandVieBanker(ReceiveGameCommand receiveGameCommand){
        // 1. 當前狀態檢查
        if(super.stateMgr.getCurrentStateId() != ConstQzpjJava.StateEnumQzpjJava.VIE_BANKER.getCode()){
            LOG.warn(this.getTableLogTitle() + " error: {}, receiveGameCommand: {}", "state error", receiveGameCommand.toString());
            return;
        }

        // 2. 請求
        try {
            ((StateVieBankerQzpjJava)super.stateMgr.getStateMap().get(ConstQzpjJava.StateEnumQzpjJava.VIE_BANKER.getCode()))
                    .onMessageVieBanker(super.getGamePlayerHlr().getHumanChairIndex(), JsonUtil.getInstance().readValue(receiveGameCommand.getMessage(), CtsVieBankerQzpjJava.class));
        } catch (Exception e) {
            LOG.error(this.getTableLogTitle() + " error: {}, receiveGameCommand: {}", "exe error", receiveGameCommand.toString());
            e.printStackTrace();
        }
    }

    // 接收下注客戶端封包
    private void receivedGameCommandBet(ReceiveGameCommand receiveGameCommand){
        // 1. 當前狀態檢查
        if(super.stateMgr.getCurrentStateId() != ConstQzpjJava.StateEnumQzpjJava.BET.getCode()){
            LOG.warn(this.getTableLogTitle() + " error: {}, receiveGameCommand: {}", "state error", receiveGameCommand.toString());
            return;
        }

        // 2. 請求
        try {
            ((StateBetQzpjJava)super.stateMgr.getStateMap().get(ConstQzpjJava.StateEnumQzpjJava.BET.getCode()))
                    .onMessageBet(super.getGamePlayerHlr().getHumanChairIndex(), JsonUtil.getInstance().readValue(receiveGameCommand.getMessage(), CtsBetQzpjJava.class));
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
        StcCutReturnQzpjJava stcCutReturn = new StcCutReturnQzpjJava(
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
        if (stage == ConstQzpjJava.StateEnumQzpjJava.VIE_BANKER.getCode()) {
            return this.logic.getHumanCanVieRateArray();
        }
        else if (stage == ConstQzpjJava.StateEnumQzpjJava.BET.getCode()) {
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
        switch (ConstQzpjJava.StateEnumQzpjJava.fromCode(stateId)){
            case GAME_BEGIN: return ConstQzpjJava.TimeEnumQzpjJava.GAME_BEGIN.getCode();
            case VIE_BANKER: return ConstQzpjJava.TimeEnumQzpjJava.VIE_BANKER.getCode();
            case BET: return ConstQzpjJava.TimeEnumQzpjJava.BET.getCode();
            case DEAL_CARD: return ConstQzpjJava.TimeEnumQzpjJava.DEAL_CARD.getCode();
            case COMPARE: return ConstQzpjJava.TimeEnumQzpjJava.COMPARE.getCode();
            default: return ConstQzpjJava.TimeEnumQzpjJava.READY.getCode();
        }
    }


    //* 狀態機相關 *//

    // 註冊狀態
    @Override
    public void registerState(){
        // 1. 註冊狀態機
        super.getStateMgr().registerState(new StateWaitBeginQzpjJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstQzpjJava.StateEnumQzpjJava.WAIT_BEGIN.getCode()));
        super.getStateMgr().registerState(new StateGameBeginQzpjJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstQzpjJava.StateEnumQzpjJava.GAME_BEGIN.getCode()));
        super.getStateMgr().registerState(new StateVieBankerQzpjJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstQzpjJava.StateEnumQzpjJava.VIE_BANKER.getCode()));
        super.getStateMgr().registerState(new StateBetQzpjJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstQzpjJava.StateEnumQzpjJava.BET.getCode()));
        super.getStateMgr().registerState(new StateDealCardQzpjJava(this, this.logic, this.robotLogic, this.gameAdjust, super.tableUtil, ConstQzpjJava.StateEnumQzpjJava.DEAL_CARD.getCode()));
        super.getStateMgr().registerState(new StateCompareQzpjJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstQzpjJava.StateEnumQzpjJava.COMPARE.getCode()));
        super.getStateMgr().registerState(new StateGameEndQzpjJava(this, this.logic, this.robotLogic, this.gameAdjust, ConstQzpjJava.StateEnumQzpjJava.GAME_END.getCode(), super.getEvent()));


       // 2. 初始化狀態
        super.getStateMgr().setState(ConstQzpjJava.StateEnumQzpjJava.WAIT_BEGIN.getCode());
    }

    // 切換狀態
    @Override
    public void switchState(int exitStateId) {
        switch (ConstQzpjJava.StateEnumQzpjJava.fromCode(exitStateId)){
            case WAIT_BEGIN: super.getStateMgr().setState(ConstQzpjJava.StateEnumQzpjJava.GAME_BEGIN.getCode()); break;
            case GAME_BEGIN: super.getStateMgr().setState(ConstQzpjJava.StateEnumQzpjJava.VIE_BANKER.getCode()); break;
            case VIE_BANKER: super.getStateMgr().setState(ConstQzpjJava.StateEnumQzpjJava.BET.getCode()); break;
            case BET: super.getStateMgr().setState(ConstQzpjJava.StateEnumQzpjJava.DEAL_CARD.getCode()); break;
            case DEAL_CARD: super.getStateMgr().setState(ConstQzpjJava.StateEnumQzpjJava.COMPARE.getCode()); break;
            case COMPARE: super.getStateMgr().setState(ConstQzpjJava.StateEnumQzpjJava.GAME_END.getCode()); break;
            case GAME_END: super.getStateMgr().setState(ConstQzpjJava.StateEnumQzpjJava.WAIT_BEGIN.getCode()); break;
        }
    }


    //* 牌桌資訊 *//
    public LogicQzpjJava getLogic(){
        return this.logic;
    }

    public RobotLogicQzpjJava getRobotLogic() {
        return robotLogic;
    }

    // 取得遊戲結果
    @Override
    public IGameBetLogResultCard getResult() { return this.logic.getGameBetLogResultCard(this.logic.getHumanUidScore()); }
}

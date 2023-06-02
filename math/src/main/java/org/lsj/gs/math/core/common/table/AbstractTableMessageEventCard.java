package org.lsj.gs.math.core.common.table;

import org.lsj.gs.event.TableFinishedEvent;
import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.AbstractTableGameConfig;
import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gamePlayerHlr.entity.GamePlayer;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResult;
import org.lsj.gs.math.core.common.poolCtr.entity.IPoolConfig;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.math.core.common.table.entity.message.core.IMessage;
import org.lsj.gs.math.core.common.table.entity.message.core.StcEnableWaterMatch;
import org.lsj.gs.math.core.common.table.entity.message.core.StcUserLeave;
import org.lsj.gs.math.core.common.table.module.tableEvent.ITableEvent;
import org.lsj.gs.math.core.common.table.module.tableEvent.TableEventHlr;
import org.lsj.gs.math.core.common.table.module.tableGame.ITableGameCard;
import org.lsj.gs.math.core.common.table.module.tableProtocol.ITableProtocolMessage;
import org.lsj.gs.math.core.common.table.module.tableProtocol.TableProtocolHlrMessage;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtilCard;
import org.lsj.gs.math.core.common.table.module.tableUtil.tableTimer.ITableTimer;
import org.lsj.gs.user.IUser;
import org.lsj.utils.MathUtil;
import org.lsj.websocket.ReceiveGameCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Event;
import javax.websocket.Session;
import java.util.List;

// 抽象遊戲桌 Message 棋牌
public abstract class AbstractTableMessageEventCard extends AbstractTable implements ITableProtocolMessage, ITableEvent, ITableGameCard {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractTableMessageEventCard.class); // 日誌器

    protected final TableProtocolHlrMessage tableProtocolHlrMessage; // 遊戲桌傳輸處理器
    protected final TableEventHlr tableEventHlr; // 牌桌事件處理器
    protected final ITableUtilCard tableUtil; // 遊戲桌工具包

    public AbstractTableMessageEventCard(
            Event<TableFinishedEvent> event,
            TableFieldConfig tableFieldConfig,
            AbstractTableGameConfig tableGameConfig,
            int tableId,
            IPoolConfig poolConfig,
            IUser user,
            ITableUtilCard tableUtil) throws TableException {
        // 1. 初始化處理器
        super(  tableFieldConfig,
                tableGameConfig,
                tableId,
                poolConfig,
                user,
                ConstMathCommon.GamePlayerHlrConsumeRnd.YES,
                true,
                tableUtil);
        this.tableProtocolHlrMessage = new TableProtocolHlrMessage(tableUtil);
        this.tableEventHlr = new TableEventHlr(event);
        this.tableUtil = tableUtil;

        // 2. 計算局號
        this.updateRoundId();

        // 3. 計算個人控
        super.calculatePersonControlAllResult();
        LOG.info(this.getTableLogTitle() + " adjustInfo:{}, personAdjustType:{}, personControlLogString:{}",
                super.getPoolCtr().getAdjustInfoString(),
                super.getPoolCtr().getPersonAdjustType(),
                super.getPoolCtr().getPersonControlLogString());
    }

    //* 遊戲桌傳輸處理器 *//

    // 接收客戶端封包
    @Override
    public abstract void receivedGameCommand(ReceiveGameCommand mathCommand);

    // 傳送狀態更新訊息
    @Override
    public void sendUpdateState(int enterStateIndex) {
        this.tableProtocolHlrMessage.sendUpdateState(enterStateIndex, super.getHumanGamePlayer().getSession());
    }

    // 傳送封包給玩家
    @Override
    public void sendMessageToHumanPlayer(IMessage message) {
        this.tableProtocolHlrMessage.sendMessageBySession(message, super.getHumanGamePlayer().getSession());
    }

    // 傳送所有玩家資訊 TODO 不傳 uid，直接拿真人即可
    @Override
    public void sendUpdateUser(int uid, double dollar){
        this.tableProtocolHlrMessage.sendUpdateUser(uid, dollar, super.getHumanGamePlayer().getSession());
    }

    // 傳送所有玩家列表
    @Override
    public void sendUserList() {
        this.tableProtocolHlrMessage.sendUserList(super.getAllGamePlayerList(), super.getHumanGamePlayer(), super.getHumanGamePlayer().getSession());
    }

    // 傳送真人玩家金額
    @Override
    public void sendHumanUpdateScore() {
        this.tableProtocolHlrMessage.sendHumanUpdateScore(super.getScaledAllPlayerBeginMoneyArray(), super.getHumanGamePlayer().getSession());
    }

    // 傳送局號
    @Override
    public void sendRoundLogId() {
        this.tableProtocolHlrMessage.sendRoundLogId(this.getRoundId(), super.getHumanGamePlayer().getSession());
    }


    //* 牌桌事件處理器 *//
    @Override
    public Event<TableFinishedEvent> getEvent() {
        return this.tableEventHlr.getEvent();
    } // 取得遊戲桌事件


    //* 遊戲桌遊戲處理器 *//

    // 更新局號
    @Override
    public void updateRoundId() {
        super.updateRoundId(this.calculateRoundId());
    }

    // 計算局號
    private String calculateRoundId() {
        return super.tableInfoHlr.calculateRoundIdSec(super.getTableConfigMgr().getConfig().getFieldIndex());
    }

    // 計算花費時間(秒)
    @Override
    public long calculateSpendSec(){
        return super.calculateSpendSec();
    }

    // 取得遊戲編碼
    @Override
    public int getGameId(){
        return super.getGameId();
    }

    // 啟動遊戲開始前處理
    @Override
    public abstract void startBeforeGameBegin();

    // 啟動遊戲結束後處理
    @Override
    public abstract void startAfterGameEnd();

    // 啟動強制靜止
    @Override
    public void startForceStop(){
        // 清空計時器
        this.getTableTimer().cancel();
    }

    // 啟動遊戲結束後處理
    protected void startAfterGameEnd(double humanPlayerScore) {
        // 1. 判斷玩家餘額是否足夠
        if (MathUtil.add(super.getHumanGamePlayer().getBeginMoney(), humanPlayerScore) < super.getTableConfigMgr().getConfig().getLimitMin()) {
            StcUserLeave stcUserLeave = new StcUserLeave(
                    super.getHumanGamePlayer().getUid(),
                    super.getHumanGamePlayer().getChairIndex(),
                    ConstMathCommon.LeaveReason.MONEY_NOT_ENOUGH.getCode()
            );

            LOG.info(this.getTableLogTitle() + " message: {}", stcUserLeave);
            this.sendMessageToHumanPlayer(stcUserLeave);
            return;
        }

        // 2. 傳送開啟匹配訊息
        StcEnableWaterMatch stcEnableWaterMatch = new StcEnableWaterMatch();
        LOG.info(this.getTableLogTitle() + " message: {}", stcEnableWaterMatch);
        this.sendMessageToHumanPlayer(stcEnableWaterMatch);

        // 3. 清空使用過的隨機亂數
        this.tableUtil.reset();
    }

    // 取得所有玩家資訊
    @Override
    public List<GamePlayer> getAllGamePlayerList(){
        return super.getAllGamePlayerList();
    }

    // 取得調整至金錢單位的所有玩家初始金額
    @Override
    public double[] getScaledAllPlayerBeginMoneyArray(){
        return super.getScaledAllPlayerBeginMoneyArray();
    }

    // 取得局結果
    @Override
    public abstract IGameBetLogResult getResult();

    // 取得牌桌定時器
    @Override
    public ITableTimer getTableTimer() {
        return this.tableUtil.getTableTimer();
    }

    // 取得指定定時器剩餘時間
    @Override
    public int getLeftSecond(int timerId) {
        return this.tableUtil.getTableTimer().getLeftSecond(timerId);
    }

    // 傳送斷線資訊
    @Override
    public abstract void sendCutReturn(Session session);

    // 更新玩家會話 [因為服務端無更新餘額，所以數值改使用更新 session]
    @Override
    public void updateUser(IUser user) {
        super.gamePlayerHlr.updateHumanSession(user);
    }
}

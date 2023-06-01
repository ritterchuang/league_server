package org.lsj.gs.math.core.common.table;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.AbstractTableGameConfigBaiRen;
import org.lsj.gs.math.core.baiRen.StcMessageUtilBaiRen;
import org.lsj.gs.math.core.baiRen.mathConfigHlr.BaiRenMathConfigHlr;
import org.lsj.gs.math.core.baiRen.mathConfigHlr.entity.BaiRenMathConfig;
import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.logic.logicBaiRen.AbstractLogicBaiRen;
import org.lsj.gs.math.core.common.poolCtr.entity.IPoolConfig;
import org.lsj.gs.math.core.common.robotLogic.entity.AreaBetResultBaiRen;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.math.core.common.table.entity.message.baiRen.CtsBetBaiRenJava;
import org.lsj.gs.math.core.common.table.entity.message.core.IMessage;
import org.lsj.gs.math.core.common.table.module.tableGame.ITableGameBaiRen;
import org.lsj.gs.math.core.common.table.module.tableProtocol.ITableProtocolMessage;
import org.lsj.gs.math.core.common.table.module.tableProtocol.TableProtocolHlrMessage;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtilBaiRen;
import org.lsj.gs.math.core.common.table.module.tableUtil.tableTimer.ITableTimer;
import org.lsj.gs.user.IUser;
import org.lsj.websocket.ReceiveGameCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 抽象遊戲桌 Message百人
public abstract class AbstractTableMessageBaiRen extends AbstractTable implements ITableProtocolMessage, ITableGameBaiRen {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractTableMessageBaiRen.class); // 日誌器

    protected final TableProtocolHlrMessage tableProtocolHlrMessage; // 遊戲桌傳輸處理器
    protected final BaiRenMathConfigHlr mathConfigHlr; // 數值設定處理器
    protected final ITableUtilBaiRen tableUtil; // 遊戲桌工具包
    private final StcMessageUtilBaiRen stcMessageUtilBaiRen; // 百人訊息工具包

    public AbstractTableMessageBaiRen(
            TableFieldConfig tableFieldConfig,
            AbstractTableGameConfigBaiRen tableGameConfig,
            int tableId,
            IPoolConfig poolConfig,
            IUser user,
            ITableUtilBaiRen tableUtil) throws TableException {
        // 1. 初始化處理器
        super(  tableFieldConfig,
                tableGameConfig,
                tableId,
                poolConfig,
                user,
                ConstMathCommon.GamePlayerHlrConsumeRnd.NO,
                false,
                tableUtil);
        // 2. 模組初始化
        this.mathConfigHlr = new BaiRenMathConfigHlr(tableFieldConfig, tableGameConfig);
        this.tableProtocolHlrMessage = new TableProtocolHlrMessage(tableUtil);
        this.tableUtil = tableUtil;
        this.stcMessageUtilBaiRen = new StcMessageUtilBaiRen();

        // 3. 計算局號
        this.updateRoundId();
    }

    //* 遊戲桌傳輸處理器 *//

    // 接收客戶端封包
    @Override
    public abstract void receivedGameCommand(ReceiveGameCommand mathCommand);

    // 傳送封包給玩家
    @Override
    public void sendMessageToHumanPlayer(IMessage message) {
        if(super.getGamePlayerHlr().isHumanEnterTable()) {
            this.tableProtocolHlrMessage.sendMessageBySession(message, super.getHumanGamePlayer().getSession());
        }
    }

    // 傳送狀態更新訊息
    @Override
    public void sendUpdateState(int enterStateIndex) {
        if(super.getGamePlayerHlr().isHumanEnterTable()) {
            this.tableProtocolHlrMessage.sendUpdateState(enterStateIndex, super.getHumanGamePlayer().getSession());
        }
    }

    // 傳送所有玩家列表
    @Override
    public void sendUserList() {
        if (super.getGamePlayerHlr().isHumanEnterTable()) {
            this.tableProtocolHlrMessage.sendUserList(super.getAllGamePlayerList(), super.getHumanGamePlayer(), super.getHumanGamePlayer().getSession());
        }
    }

    // 傳送局號
    @Override
    public void sendRoundLogId() {
        if(super.getGamePlayerHlr().isHumanEnterTable()) {
            this.tableProtocolHlrMessage.sendRoundLogId(this.getRoundId(), super.getHumanGamePlayer().getSession());
        }
    }

    // 傳送玩家餘額資訊
    @Override
    public void sendUpdateUser(int uid, double dollar){
        if(super.getGamePlayerHlr().isHumanEnterTable()) {
            this.tableProtocolHlrMessage.sendUpdateUser(uid, dollar, super.getHumanGamePlayer().getSession());
        }
    }


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

    // 真人入桌
    @Override
    public abstract void enterTable(IUser user, double leftTimeSec);

    // 真人入桌
    protected void enterTable(IUser user){
        super.gamePlayerHlr.enterTable(user);
    }

    // 驗證玩家餘額
    @Override
    public boolean isMoneyEnough(int chairIndex, int previousTotalBet, int chips) {
        return super.gamePlayerHlr.getPlayerBeginMoney(chairIndex) >= (previousTotalBet + chips);
    }

    // 驗證玩家餘額夠不夠賠倍數
    @Override
    public boolean isMoneyEnoughToPayRate(int chairIndex, int previousTotalBet, int chips, int typeRate) {
        return  (chips + previousTotalBet) * typeRate <= super.gamePlayerHlr.getPlayerBeginMoney(chairIndex);
    }

    // 真人離桌
    @Override
    public abstract boolean leaveTable();

    // 切換狀態
    @Override
    public void switchState(int exitStateId) {}

    // 取得牌桌數值設定
    @Override
    public BaiRenMathConfig getMathConfig() {
        return this.mathConfigHlr.getBaiRenMathConfig();
    }

    // 取得玩家人數
    @Override
    public int getPlayerNum() {
        return  super.getAllGamePlayerList().size()
                + 100
                + this.tableUtil.getNotSupportSetRngDataRandomUtil().getRandomIntWithAccuracy(100, ConstMathCommon.AccuracyType.A32768);
    }

    // 更新水池
    @Override
    public void updatePoolConfig(IPoolConfig poolConfig){
        super.updatePoolConfig(poolConfig, this.getClass().getSimpleName());
    }

    // 傳送設定
    @Override
    public void sendConfig(ObjectNode configNode){
        if(super.getGamePlayerHlr().isHumanEnterTable()) {
            this.tableProtocolHlrMessage.sendConfig(configNode, super.getHumanGamePlayer().getSession());
        }
    }


    //* 百人共同傳輸訊息 *//

    // 傳送動作
    @Override
    public void sendAction(int actionCode, double actionTimeSec){
        this.sendMessageToHumanPlayer(this.stcMessageUtilBaiRen.calculateStcAction(actionCode, actionTimeSec));
    }

    // 傳送區域押注
    @Override
    public void sendArea() {
        this.sendMessageToHumanPlayer(this.stcMessageUtilBaiRen.calculateStcArea(this.getLogic()));
    }

    // 傳送玩家下注訊息
    @Override
    public void sendBet(AreaBetResultBaiRen areaBetResult){
        this.sendMessageToHumanPlayer(this.stcMessageUtilBaiRen.calculateStcBet(areaBetResult, this.getLogic()));
    }

    // 傳送下注結果訊息
    @Override
    public void sendBetResult(int chairIndex, CtsBetBaiRenJava ctsBetBaiRenJava, int code) {
        this.sendMessageToHumanPlayer(this.stcMessageUtilBaiRen.calculateStcBetResult(chairIndex, ctsBetBaiRenJava, code, this.getLogic()));
    }

    // 傳送路線圖
    @Override
    public abstract void sendChart();

    // 傳送富豪榜與真人餘額
    @Override
    public void sendMoney() {
        this.sendMessageToHumanPlayer(this.stcMessageUtilBaiRen.calculateStcMoney(this.getLogic()));
    }

    // 傳送下注訊息
    @Override
    public void sendOwnBet() {
        this.sendMessageToHumanPlayer(this.stcMessageUtilBaiRen.calculateStcOwnBet(this.getLogic()));
    }

    // 傳送下注前富豪榜
    @Override
    public void sendRichList() {
        this.sendMessageToHumanPlayer(this.stcMessageUtilBaiRen.calculateStcRichList(this.getLogic()));
    }

    // 傳送玩家人數
    @Override
    public void sendUserNum(){
        this.sendMessageToHumanPlayer(this.stcMessageUtilBaiRen.calculateUserNum(this.getPlayerNum()));
    }


    // 取得遊戲桌定時器
    @Override
    public ITableTimer getTableTimer() {
        return this.tableUtil.getTableTimer();
    }

    // 更新真人資訊
    @Override
    public void updateUser(IUser user) {
        super.updateHumanUser(user);
    }

    // 設定亂數
    @Override
    public void setRngData(String rngDataString){
        this.tableUtil.getMainRandomUtil().setRandomNumberListByString(rngDataString);
    }

    // 玩家是否押注標誌
    public boolean isHumanBet() {
        return this.getLogic().isHumanBet();
    }

    public abstract AbstractLogicBaiRen getLogic();

    // 重置
    @Override
    public void reset() {
        super.reset();
        this.tableProtocolHlrMessage.reset();
        this.mathConfigHlr.reset();
        this.tableUtil.reset();
    }
}

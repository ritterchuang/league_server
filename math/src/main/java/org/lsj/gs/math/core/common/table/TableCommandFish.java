package org.lsj.gs.math.core.common.table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigFish;
import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.GameAdjustFish;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultFish;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module.GameBetLogResultCtrFish;
import org.lsj.gs.math.core.common.poolCtr.entity.IPoolConfig;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.math.core.common.table.entity.message.fish.CtsGetHitResultData;
import org.lsj.gs.math.core.common.table.module.tableGame.ITableGameFish;
import org.lsj.gs.math.core.common.table.module.tableProtocol.ITableProtocolCommand;
import org.lsj.gs.math.core.common.table.module.tableProtocol.TableProtocolHlrCommand;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtilFish;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.logic.LogicFish;
import org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.client.MathFishConfig;
import org.lsj.gs.math.core.fish.state.StateGameBeginFish;
import org.lsj.gs.math.core.fish.state.StateGameEndFish;
import org.lsj.gs.math.core.fish.state.StateWaitBeginFish;
import org.lsj.gs.protocol.Command;
import org.lsj.gs.user.IUser;
import org.lsj.utils.JsonUtil;
import org.lsj.websocket.ProtocolCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.util.Optional;

// 遊戲桌 Command 魚機
public class TableCommandFish extends AbstractTable implements ITableProtocolCommand, ITableGameFish {
    private static final Logger LOG = LoggerFactory.getLogger(TableCommandFish.class);
    protected final TableProtocolHlrCommand tableProtocolHlrCommand; // 遊戲桌傳輸處理器
    private final LogicFish logic; // 遊戲邏輯
    private final GameAdjustFish gameAdjust; // 遊戲調控
    protected final ITableUtilFish tableUtil; // 遊戲桌工具包

    public TableCommandFish(
            int tableId,
            TableFieldConfig tableFieldConfig,
            TableGameConfigFish tableGameConfig,
            IPoolConfig poolConfig,
            IUser user,
            ITableUtilFish tableUtil) throws TableException {
        // 初始化處理器
        super(  tableFieldConfig,
                tableGameConfig,
                tableId,
                poolConfig,
                user,
                ConstMathCommon.GamePlayerHlrConsumeRnd.NO,
                true,
                tableUtil);
        // 1. 模組初始化
        this.tableProtocolHlrCommand = new TableProtocolHlrCommand(tableUtil);
        this.logic = new LogicFish(this,
                tableFieldConfig,
                tableGameConfig,
                super.getGamePlayerHlr(),
                super.getPoolCtr(),
                new GameBetLogResultCtrFish(
                        this.getBeginTimeMillis(),
                        this.getTableId(),
                        super.getTableConfigMgr().getConfig(),
                        super.getGamePlayerHlr(),
                        super.getPoolCtr(),
                        tableUtil),
                tableUtil);
        this.gameAdjust = new GameAdjustFish(
                super.getAlgorithmTypeCtr(),
                this.logic,
                super.getPoolCtr(),
                tableUtil);
        this.tableUtil = tableUtil;

        // 2. 計算局號
        this.updateRoundId();

        // 3. 註冊狀態機
        this.registerState();
    }

    //* 遊戲桌傳輸處理器 *//

    // 傳送狀態更新訊息
    @Override
    public void sendUpdateState(int enterStateIndex) {this.tableProtocolHlrCommand.sendUpdateState(enterStateIndex);}

    // 傳送封包給玩家
    @Override
    public void sendMessageToHumanPlayer(ProtocolCode protocolCode, String command, JsonNode dataJsonNode){
        this.tableProtocolHlrCommand.sendMessageToHumanPlayer(protocolCode, command, super.getHumanGamePlayer().getSession(), dataJsonNode);
    }

    // 傳送問題消息給玩家
    @Override
    public void sendErrorMessageToHumanPlayer(ProtocolCode protocolCode, String command){
        this.tableProtocolHlrCommand.sendErrorMessageToHumanPlayer(protocolCode, command, super.getHumanGamePlayer().getSession());
    }


    //** 遊戲桌遊戲處理器 **//

    // 更新局號
    @Override
    public void updateRoundId() {
        super.updateRoundId(this.calculateRoundId());
    }

    // 計算局號
    private String calculateRoundId(){
        return super.tableInfoHlr.calculateRoundIdMillis(super.getTableConfigMgr().getConfig().getFieldIndex());
    }

    // 啟動遊戲結束前處理
    @Override
    public void startBeforeGameEnd(){
        super.getStateMgr().setState(ConstMathFish.StateEnumFish.GAME_END.getCode());
    }

    // 註冊狀態
    @Override
    public void registerState() {
        // 1. 註冊狀態機
        super.getStateMgr().registerState(new StateWaitBeginFish(this, this.logic, this.gameAdjust, ConstMathFish.StateEnumFish.WAIT_BEGIN.getCode()));
        super.getStateMgr().registerState(new StateGameBeginFish(this, this.logic, this.gameAdjust, ConstMathFish.StateEnumFish.GAME_BEGIN.getCode()));
        super.getStateMgr().registerState(new StateGameEndFish(this, this.logic, this.gameAdjust, ConstMathFish.StateEnumFish.GAME_END.getCode()));

        // 2. 初始化狀態
        super.getStateMgr().setState(ConstMathFish.StateEnumFish.WAIT_BEGIN.getCode());
        super.getStateMgr().setState(ConstMathFish.StateEnumFish.GAME_BEGIN.getCode());
    }

    // 切換狀態
    @Override
    public void switchState(int exitStateId) {}

    // 取得數值設定
    @Override
    public MathFishConfig getMathFishConfig(){
        return this.logic.getMathFishConfig();
    }


    // 檢查真人玩家初始金額足夠性
    @Override
    public boolean checkHumanBeginMoneyEnough(double cost){
        return super.checkHumanBeginMoneyEnough(cost);
    }

    // 更新真人玩家初始金額
    @Override
    public void updateHumanAfterMoney(double score){
        super.updateHumanAfterMoney(score);
    }

    // 更新水池
    @Override
    public void updatePoolConfig(IPoolConfig poolConfig){
        super.updatePoolConfig(poolConfig, this.getClass().getSimpleName());
    }

    // 取得打擊結果
    @Override
    public IGameBetLogResultFish getHitResult(String ctsGetHitResultDataString) throws TableException {
        // 1. 當前狀態檢查
        if(super.stateMgr.getCurrentStateId() != ConstMathFish.StateEnumFish.GAME_BEGIN.getCode()){
            LOG.error(super.getTableLogTitle() + " error: {}, getHitResult: {}", "state error", ctsGetHitResultDataString);
            this.sendErrorMessageToHumanPlayer(
                    ProtocolCode.FAIL,
                    Command.HIT_TARGET.getStcCommand());
            throw new TableException(ConstMathCommon.TableProtocolCode.COMMON_STATE_ERROR, Level.ERROR, "state error");
        }

        // 2. 請求打擊
        IGameBetLogResultFish result;
        try {
            result = ((StateGameBeginFish)super.stateMgr.getStateMap().get(ConstMathFish.StateEnumFish.GAME_BEGIN.getCode()))
                    .getHitResult(JsonUtil.getInstance().readValue(ctsGetHitResultDataString, CtsGetHitResultData.class));
        } catch (JsonProcessingException je) {
            LOG.error(super.getTableLogTitle() + " error: {}, getHitResult: {}", "json format error", ctsGetHitResultDataString);
            this.sendErrorMessageToHumanPlayer(
                    ProtocolCode.FAIL,
                    Command.HIT_TARGET.getStcCommand());
            throw new TableException(ConstMathCommon.TableProtocolCode.COMMON_JSON_FORMAT_ERROR, Level.ERROR, "json format error", je);
        } catch (TableException te){
            throw te;
        } catch (Exception e){
            LOG.error(super.getTableLogTitle() + " error: {}, getHitResult: {}", "unexpect error", ctsGetHitResultDataString);
            this.sendErrorMessageToHumanPlayer(
                    ProtocolCode.FAIL,
                    Command.HIT_TARGET.getStcCommand());
            throw new TableException(ConstMathCommon.TableProtocolCode.COMMON_FAIL, Level.ERROR, "unexpect error", e);
        }

        return result;
    }

    // 計算返還結果
    @Override
    public Optional<IGameBetLogResultFish> calculateFishReturnResult() {
        // 1. 更新局號
        this.updateRoundId();

        // 2. 回傳返還結果
        return this.logic.calculateFishReturnResult();
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

    // 取得遊戲邏輯
    public LogicFish getLogic(){
        return this.logic;
    }

    // 重置
    @Override
    public void reset() {
        super.reset();
        this.tableProtocolHlrCommand.reset();
        this.logic.resetOneBoard();
        this.tableUtil.reset();
    }
}

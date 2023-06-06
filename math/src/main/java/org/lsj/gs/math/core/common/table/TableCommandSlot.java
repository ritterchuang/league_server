package org.lsj.gs.math.core.common.table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.lsj.gs.math.config.entity.tableFieldConfig.TableFieldConfig;
import org.lsj.gs.math.config.entity.tableGameConfig.TableGameConfigSlot;
import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.cmdOutResultPgr.CmdOutResultPgr;
import org.lsj.gs.math.core.common.cmdOutResultPgr.entity.CmdOutResult;
import org.lsj.gs.math.core.common.gameAdjust.GameAdjustSlot;
import org.lsj.gs.math.core.common.gameFlowHlr.GameFlowHlr;
import org.lsj.gs.math.core.common.gameFlowHlr.enity.config.GameFlowHlrConfig;
import org.lsj.gs.math.core.common.gameFlowHlr.enity.result.GameFlowHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.GameStateHlrMgr;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.config.GameStateHlrMgrConfig;
import org.lsj.gs.math.core.common.gameFlowHlr.module.systemStateHlr.ConditionStateHlr;
import org.lsj.gs.math.core.common.gameFlowHlr.module.systemStateHlr.ConditionStateHlrConfig;
import org.lsj.gs.math.core.common.gameFlowHlr.module.systemStateHlr.conditionHlrMgr.ConditionHlrMgr;
import org.lsj.gs.math.core.common.gameFlowHlr.module.systemStateHlr.conditionHlrMgr.enity.ConditionHlrMgrConfig;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultSlot;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.module.GameBetLogResultCtrSlot;
import org.lsj.gs.math.core.common.logic.logicSlot.ILogicSlot;
import org.lsj.gs.math.core.common.logic.logicSlot.LogicSlotFactory;
import org.lsj.gs.math.core.common.poolCtr.entity.IPoolConfig;
import org.lsj.gs.math.core.common.spinResultPgr.SpinResultPgr;
import org.lsj.gs.math.core.common.spinResultPgr.enity.SpinResult;
import org.lsj.gs.math.core.common.spinResultPgr.module.SpinResultPgrConfigModule;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.math.core.common.table.entity.message.slot.ClientSpinRequest;
import org.lsj.gs.math.core.common.table.module.tableGame.ITableGameSlot;
import org.lsj.gs.math.core.common.table.module.tableProtocol.ITableProtocolCommand;
import org.lsj.gs.math.core.common.table.module.tableProtocol.TableProtocolHlrCommand;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtilSlot;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.client.MathSlotConfig;
import org.lsj.gs.math.core.slot.oneBoardResultMgr.enity.LastPlayedProgressResult;
import org.lsj.gs.math.core.slot.oneBoardResultMgr.enity.SlotCutReturn;
import org.lsj.gs.math.core.slot.state.StateGameBeginSlot;
import org.lsj.gs.math.core.slot.state.StateGameEndSlot;
import org.lsj.gs.math.core.slot.state.StateWaitBeginSlot;
import org.lsj.gs.protocol.Command;
import org.lsj.gs.user.IUser;
import org.lsj.utils.JsonUtil;
import org.lsj.websocket.ProtocolCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.util.Optional;

// 遊戲桌 Command 老虎機
public class TableCommandSlot extends AbstractTable implements ITableProtocolCommand, ITableGameSlot {
    private static final Logger LOG = LoggerFactory.getLogger(TableCommandSlot.class);
    private final TableProtocolHlrCommand tableProtocolHlrCommand; // 遊戲桌傳輸處理器
    private final ILogicSlot logic; // 數值邏輯
    private final GameFlowHlr gameFlowHlr; // 遊戲流程處理者
    private final GameAdjustSlot gameAdjust; // 遊戲調控
    private final ITableUtilSlot tableUtil; // 遊戲桌工具包

    private final CmdOutResultPgr cmdOutResultPgr; // CmdOut結果封裝器
    private final SpinResultPgr spinResultPgr;

    public TableCommandSlot(
            int tableId,
            TableFieldConfig tableFieldConfig,
            TableGameConfigSlot tableGameConfig,
            IPoolConfig poolConfig,
            IUser user,
            ITableUtilSlot tableUtil) throws TableException {

        // 1. 初始化處理器
        super(  tableFieldConfig,
                tableGameConfig,
                tableId,
                poolConfig,
                user,
                ConstMathCommon.GamePlayerHlrConsumeRnd.NO,
                true,
                tableUtil);

        // 2. 取得設定
        GameFlowHlrConfig gameFlowHlrConfig = super.getTableConfigMgr().getModuleConfigMgrSlot().createGameFlowHlrConfig(tableGameConfig);
        ConditionHlrMgrConfig conditionHlrMgrConfig = gameFlowHlrConfig.createConditionHlrMgrConfig();
        ConditionStateHlrConfig conditionStateHlrConfig = gameFlowHlrConfig.createConditionStateHlrConfig();
        GameStateHlrMgrConfig gameStateHlrMgrConfig = gameFlowHlrConfig.createGameStateHlrMgrConfig();

        // 2. 模組初始化
        this.tableProtocolHlrCommand = new TableProtocolHlrCommand(tableUtil);
        this.logic = new LogicSlotFactory().create(
                tableGameConfig.getLogicType(),
                this,
                tableFieldConfig,
                tableGameConfig,
                super.getGamePlayerHlr(),
                super.getPoolCtr(),
                new GameBetLogResultCtrSlot(
                        this.getBeginTimeMillis(),
                        this.getTableId(),
                        super.getTableConfigMgr().getConfig(),
                        super.getGamePlayerHlr(),
                        super.getPoolCtr(),
                        tableUtil),
                tableUtil);
        ConditionHlrMgr conditionHlrMgr = new ConditionHlrMgr(conditionHlrMgrConfig, super.getGamePlayerHlr(), super.getPoolCtr(), tableUtil);
        ConditionStateHlr conditionStateHlr = new ConditionStateHlr(conditionStateHlrConfig, super.getGamePlayerHlr(), super.getPoolCtr(), tableUtil);
        GameStateHlrMgr gameStateHlrMgr = new GameStateHlrMgr(gameStateHlrMgrConfig, this.logic, tableUtil);
        this.gameFlowHlr = new GameFlowHlr(gameFlowHlrConfig, this.logic, conditionStateHlr, conditionHlrMgr, gameStateHlrMgr, super.getGamePlayerHlr(), super.getPoolCtr(), tableUtil);
        this.gameAdjust = new GameAdjustSlot(
                super.getAlgorithmTypeCtr(),
                this.logic,
                this.gameFlowHlr,
                super.getPoolCtr(),
                tableUtil
        );
        this.tableUtil = tableUtil;

        SpinResultPgrConfigModule configModule = new SpinResultPgrConfigModule();
        this.cmdOutResultPgr = new CmdOutResultPgr();
        this.spinResultPgr = new SpinResultPgr(
                configModule.generateSpinResultPgrConfig(tableFieldConfig.getGameId(), tableGameConfig),
                tableUtil);

        // 3. 更新局號
        this.updateRoundId();

        // 4. 註冊狀態機
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


    //* 遊戲桌遊戲處理器 *//

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
        super.getStateMgr().setState(ConstMathSlot.StateEnumSlot.GAME_END.getCode());
    }

    // 註冊狀態
    @Override
    public void registerState() {
        // 1. 註冊狀態機
        super.getStateMgr().registerState(new StateWaitBeginSlot(this, this.logic, this.gameAdjust, ConstMathSlot.StateEnumSlot.WAIT_BEGIN.getCode()));
        super.getStateMgr().registerState(new StateGameBeginSlot(this, this.logic, this.gameAdjust, ConstMathSlot.StateEnumSlot.GAME_BEGIN.getCode()));
        super.getStateMgr().registerState(new StateGameEndSlot(this, this.logic, this.gameAdjust, ConstMathSlot.StateEnumSlot.GAME_END.getCode()));

        // 2. 初始化狀態
        super.getStateMgr().setState(ConstMathSlot.StateEnumSlot.WAIT_BEGIN.getCode());
        super.getStateMgr().setState(ConstMathSlot.StateEnumSlot.GAME_BEGIN.getCode());
    }

    // 切換狀態
    @Override
    public void switchState(int exitStateId) {}

    // 取得數值設定
    @Override
    public MathSlotConfig getMathSlotConfig(){
        return this.logic.getMathSlotConfig();
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

    // 取得玩家下注金額
    @Override
    public double getSpinCost(String ctsSpinRequestString) throws TableException {
        // 1. 當前狀態檢查
        if(super.stateMgr.getCurrentStateId() != ConstMathSlot.StateEnumSlot.GAME_BEGIN.getCode()){
            LOG.error(super.getTableLogTitle() + " error: {}, getSpinCost: {}", "state error", ctsSpinRequestString);
            this.sendErrorMessageToHumanPlayer(
                    ProtocolCode.FAIL,
                    Command.SPIN_REQUEST.getStcCommand());
            throw new TableException(ConstMathCommon.TableProtocolCode.COMMON_STATE_ERROR, Level.WARN, "state error");
        }

        // 2. 取得玩家下注金額
        try {
            return ((StateGameBeginSlot) super.stateMgr.getStateMap().get(ConstMathSlot.StateEnumSlot.GAME_BEGIN.getCode())).getSpinCost(JsonUtil.getInstance().readValue(ctsSpinRequestString, ClientSpinRequest.class));
        } catch (JsonProcessingException je) {
            LOG.error(this.getTableLogTitle() + " error: {}, getSpinCost: {}", "json format error", ctsSpinRequestString);
            this.sendErrorMessageToHumanPlayer(
                    ProtocolCode.FAIL,
                    Command.SPIN_REQUEST.getStcCommand());
            throw new TableException(ConstMathCommon.TableProtocolCode.COMMON_JSON_FORMAT_ERROR, Level.ERROR, "json format error", je);
        }catch (TableException te){
            throw te;
        } catch (Exception e){
            LOG.error(this.getTableLogTitle() + " error: {}, getSpinCost: {}", "unexpect error", ctsSpinRequestString);
            this.sendErrorMessageToHumanPlayer(
                    ProtocolCode.FAIL,
                    Command.SPIN_REQUEST.getStcCommand());
            throw new TableException(ConstMathCommon.TableProtocolCode.COMMON_FAIL, Level.WARN, "unexpect error", e);
        }
    }

    // 取得押注結果
    @Override
    public IGameBetLogResultSlot getSpinResult(String ctsSpinRequestString) throws TableException {
        // 1. 當前狀態檢查
        if(super.stateMgr.getCurrentStateId() != ConstMathSlot.StateEnumSlot.GAME_BEGIN.getCode()){
            LOG.error(super.getTableLogTitle() + " error: {}, getSpinResult: {}", "state error", ctsSpinRequestString);
            this.sendErrorMessageToHumanPlayer(
                    ProtocolCode.FAIL,
                    Command.SPIN_REQUEST.getStcCommand());
            throw new TableException(ConstMathCommon.TableProtocolCode.COMMON_STATE_ERROR, Level.WARN, "state error");
        }

        // 2. 請求押注
        IGameBetLogResultSlot result;
        try {
            result = ((StateGameBeginSlot)super.stateMgr.getStateMap().get(ConstMathSlot.StateEnumSlot.GAME_BEGIN.getCode()))
                    .getSpinResult(JsonUtil.getInstance().readValue(ctsSpinRequestString, ClientSpinRequest.class));
        }catch (JsonProcessingException je) {
            LOG.error(this.getTableLogTitle() + " error: {}, getSpinResult: {}", "json format error", ctsSpinRequestString);
            this.sendErrorMessageToHumanPlayer(
                    ProtocolCode.FAIL,
                    Command.SPIN_REQUEST.getStcCommand());
            throw new TableException(ConstMathCommon.TableProtocolCode.COMMON_JSON_FORMAT_ERROR, Level.ERROR, "json format error", je);
        }catch (TableException te){
            throw te;
        } catch (Exception e){
            LOG.error(this.getTableLogTitle() + " error: {}, getSpinResult: {}", "unexpect error", ctsSpinRequestString);
            this.sendErrorMessageToHumanPlayer(
                    ProtocolCode.FAIL,
                    Command.SPIN_REQUEST.getStcCommand());
            throw new TableException(ConstMathCommon.TableProtocolCode.COMMON_FAIL, Level.WARN, "unexpect error", e);
        }

        // 3. 回傳
        return result;
    }

    public void sendCmdOutResultToHumanPlayer(){
        // 1. 取得 CmdOutResult
        CmdOutResult result = this.packageCmdOutResult(this.logic.getGameFlowerResult());

        // 2. 傳送給客端
        LOG.debug(this.getTableLogTitle() + " ctsSpinRequest:{}, cmdResult:{}",
                JsonUtil.getInstance().writeValueAsStringWithoutException(this.logic.getSpinRequest()),
                JsonUtil.getInstance().writeValueAsStringWithoutException(result));
        this.tableUtil.getWebSocketUtil().sendResponse(
                this.getHumanGamePlayer().getSession(),
                JsonUtil.getInstance().writeValueAsStringWithoutException(result)
        );
    }

    @Override
    public void sendSpinResultToHumanPlayer(){
        ((StateGameBeginSlot)super.stateMgr.getStateMap().get(ConstMathSlot.StateEnumSlot.GAME_BEGIN.getCode())).sendSpinResultToHumanPlayer();
    }

    // 取得斷線重連結果
    @Override
    public Optional<SlotCutReturn> getSlotCutReturn() {
        return this.logic.getSlotCutReturn(this.spinResultPgr);
    }

    // 更新客端撥放進度
    @Override
    public void updateLastPlayedProgressResult(String ctsLastPlayedProgressResult) throws TableException {
        try {
            this.logic.updateLastPlayedProgressResult(JsonUtil.getInstance().readValue(ctsLastPlayedProgressResult, LastPlayedProgressResult.class));
        }catch (JsonProcessingException je) {
            LOG.error(this.getTableLogTitle() + " error: {}, updateLastPlayedProgressResult: {}", "json format error", ctsLastPlayedProgressResult);
            this.sendErrorMessageToHumanPlayer(
                    ProtocolCode.FAIL,
                    Command.LAST_PLAYED_PROGRESS_RESULT.getStcCommand());
            throw new TableException(ConstMathCommon.TableProtocolCode.COMMON_JSON_FORMAT_ERROR, Level.ERROR, "json format error", je);
        }catch (Exception e){
            LOG.error(this.getTableLogTitle() + " error: {}, updateLastPlayedProgressResult: {}", "unexpect error", ctsLastPlayedProgressResult);
            this.sendErrorMessageToHumanPlayer(
                    ProtocolCode.FAIL,
                    Command.LAST_PLAYED_PROGRESS_RESULT.getStcCommand());
            throw new TableException(ConstMathCommon.TableProtocolCode.COMMON_FAIL, Level.ERROR, "unexpect error", e);
        }
    }

    // 設定亂數
    @Override
    public void setRngData(String rngDataString){
        this.tableUtil.getMainRandomUtil().setRandomNumberListByString(rngDataString);
    }

    // 更新真人資訊
    @Override
    public void updateUser(IUser user) {
        super.updateHumanUser(user);
    }

    // 取得遊戲邏輯
    public ILogicSlot getLogic() {
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

    public CmdOutResult packageCmdOutResult(GameFlowHlrResult gameFlowHlrResult) {
        return this.cmdOutResultPgr.packageCmdOutResult(gameFlowHlrResult);
    }

    public SpinResult packageSpinResult(GameFlowHlrResult gameFlowHlrResult) {
        return this.spinResultPgr.packageSpinResult(gameFlowHlrResult);
    }
}

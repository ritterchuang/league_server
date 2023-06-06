package org.lsj.gs.math.core.common.state.stateSlot;

import com.fasterxml.jackson.databind.JsonNode;
import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.cmdOutResultPgr.entity.CmdOutResult;
import org.lsj.gs.math.core.common.gameAdjust.IGameAdjust;
import org.lsj.gs.math.core.common.gameFlowHlr.enity.result.GameFlowHlrResult;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResultSlot;
import org.lsj.gs.math.core.common.logic.logicSlot.ILogicSlot;
import org.lsj.gs.math.core.common.spinResultPgr.enity.SpinResult;
import org.lsj.gs.math.core.common.state.IState;
import org.lsj.gs.math.core.common.table.TableCommandSlot;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.math.core.common.table.entity.message.fish.GamePlayerResult;
import org.lsj.gs.math.core.common.table.entity.message.slot.ClientSpinRequest;
import org.lsj.gs.math.core.common.table.entity.message.slot.StcGetSpinResultData;
import org.lsj.gs.protocol.Command;
import org.lsj.utils.JsonUtil;
import org.lsj.utils.MathUtil;
import org.lsj.websocket.ProtocolCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

// 抽象虎機遊戲開始狀態
public abstract class AbstractStateGameBeginSlot extends AbstractStateSlot implements IState {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractStateGameBeginSlot.class);

    public AbstractStateGameBeginSlot(TableCommandSlot table, ILogicSlot logicSlot, IGameAdjust gameAdjust, int stateId) {
        super(table, logicSlot, gameAdjust, stateId);
    }

    //* 計算器相關 *//
    public IGameBetLogResultSlot getSpinResult(ClientSpinRequest clientSpinRequest) throws TableException {
        // 1. 清空資訊
        super.table.reset();

        // 2. 更新玩家餘額
        super.table.updatePlayerBeginMoneyBeforeGame();

        // 3. 計算局號並更新
        super.table.updateRoundId();

        // 4. 檢查押注正確性
        this.checkSpinRequestAndBeginMoney(clientSpinRequest);

        // 5. 更新斷線重連資訊
        this.updateCutReturnWithoutSpinResult(clientSpinRequest);

        // 6. 計算個人控
        super.table.calculatePersonControlAllResult();
        LOG.debug(super.table.getTableLogTitle() + " adjustInfo:{}, personAdjustType:{}, personControlLogString:{}",
                super.table.getPoolCtr().getAdjustInfoString(),
                super.table.getPoolCtr().getPersonAdjustType(),
                super.table.getPoolCtr().getPersonControlLogString());

        // 7. 開始調控
        super.gameAdjust.startGameAdjust();

        // 8. 取得調控結果
        GameFlowHlrResult gameFlowHlrResult = super.logic.getGameFlowerResult();
        LOG.debug(super.table.getTableLogTitle() + " ctsSpinRequest:{}, gameFlowHlrResult:{}",
                JsonUtil.getInstance().writeValueAsStringWithoutException(clientSpinRequest),
                JsonUtil.getInstance().writeValueAsStringWithoutException(gameFlowHlrResult));

        // 9. 新增注單詳細記錄
        super.logic.addDetailSlot(super.logic.getSpinRequest(), super.logic.getGameFlowerResult());

        // 10. 更新 剩餘金額
        super.table.updateHumanAfterMoney(
                MathUtil.moneyScale(MathUtil.subtract(gameFlowHlrResult.getTotalWin(), super.logic.getSpinCost(clientSpinRequest))));

        // 11. 計算注單資訊
        IGameBetLogResultSlot gameBetLogResultSlot = super.logic.getGameBetLogResultSlot(super.logic.getHumanUidScore());

        // 12. 回傳
        return gameBetLogResultSlot;
    }

    public void sendSpinResultToHumanPlayer() {
        // 1. 取得調控結果
        GameFlowHlrResult gameFlowHlrResult = super.logic.getGameFlowerResult();

        // 2. 計算客端打擊結果
        SpinResult spinResult = super.table.packageSpinResult(gameFlowHlrResult);

        // 3. 計算客端資訊
        JsonNode stcGetSpinResultNode = this.calculateStcGetSpinResultNode(spinResult);

        // 4. 傳送客端資訊
        LOG.debug(super.table.getTableLogTitle() + " ctsSpinRequest:{}, stcSpinResult:{}",
                JsonUtil.getInstance().writeValueAsStringWithoutException(super.logic.getSpinRequest()),
                stcGetSpinResultNode.toString());
        super.table.sendMessageToHumanPlayer(
                ProtocolCode.SUCCESS,
                Command.SPIN_REQUEST.getStcCommand(),
                stcGetSpinResultNode);
    }

    // 取得玩家下注金額
    public double getSpinCost(ClientSpinRequest clientSpinRequest) throws TableException {
        // 1. 檢查押注正確性
        this.checkSpinRequest(clientSpinRequest);

        // 2. 回傳玩家押注
        return super.logic.getSpinCost(clientSpinRequest);
    }

    // 檢查押注請求
    private void checkSpinRequest(ClientSpinRequest clientSpinRequest) throws TableException {
        // 1. 押注請求檢查結果
        ConstMathCommon.TableProtocolCode tableProtocolCode = super.logic.checkSpinRequest(clientSpinRequest);

        // 2. 押注分數錯誤，噴錯
        if (ConstMathCommon.TableProtocolCode.SLOT_CREDIT_NOT_EXIST.equals(tableProtocolCode)) {
            LOG.error(super.table.getTableLogTitle() + " {}", "spinRequest error");
            super.table.sendErrorMessageToHumanPlayer(ProtocolCode.FAIL, Command.SPIN_REQUEST.getStcCommand());
            throw new TableException(ConstMathCommon.TableProtocolCode.SLOT_CREDIT_NOT_EXIST, Level.ERROR, "slot credit not exist");
        }

        // 3. 押注類型錯誤，噴錯
        if (ConstMathCommon.TableProtocolCode.SLOT_BET_TYPE_NOT_EXIST.equals(tableProtocolCode)) {
            LOG.error(super.table.getTableLogTitle() + " {}", "spinRequest error");
            super.table.sendErrorMessageToHumanPlayer(ProtocolCode.FAIL, Command.SPIN_REQUEST.getStcCommand());
            throw new TableException(ConstMathCommon.TableProtocolCode.SLOT_BET_TYPE_NOT_EXIST, Level.ERROR, "slot bet type not exist");
        }

        // 4. 玩法類型錯誤，噴錯
        if (ConstMathCommon.TableProtocolCode.SLOT_SPIN_TYPE_NOT_EXIST.equals(tableProtocolCode)) {
            LOG.error(super.table.getTableLogTitle() + " {}", "spinRequest error");
            super.table.sendErrorMessageToHumanPlayer(ProtocolCode.FAIL, Command.SPIN_REQUEST.getStcCommand());
            throw new TableException(ConstMathCommon.TableProtocolCode.SLOT_SPIN_TYPE_NOT_EXIST, Level.ERROR, "slot spin type not exist");
        }

        // 5. 押注玩法類型錯誤，噴錯
        if (ConstMathCommon.TableProtocolCode.SLOT_BET_SPIN_TYPE_NOT_EXIST.equals(tableProtocolCode)) {
            LOG.error(super.table.getTableLogTitle() + " {}", "spinRequest error");
            super.table.sendErrorMessageToHumanPlayer(ProtocolCode.FAIL, Command.SPIN_REQUEST.getStcCommand());
            throw new TableException(ConstMathCommon.TableProtocolCode.SLOT_BET_SPIN_TYPE_NOT_EXIST, Level.ERROR, "slot betSpin type not exist");
        }
    }

    // 檢查押注正確性與玩家起始金額
    private void checkSpinRequestAndBeginMoney(ClientSpinRequest clientSpinRequest) throws TableException {
        // 1. 檢查押注正確性
        this.checkSpinRequest(clientSpinRequest);

        // 2. 判斷玩家起始金額 是否足夠
        if (!super.table.checkHumanBeginMoneyEnough(super.logic.getSpinCost(clientSpinRequest))) {
            LOG.warn(super.table.getTableLogTitle() + " {}", "money not enough");
            super.table.sendErrorMessageToHumanPlayer(ProtocolCode.FA_GAME_MONEY_NOT_ENOUGH, Command.SPIN_REQUEST.getStcCommand());
            throw new TableException(ConstMathCommon.TableProtocolCode.COMMON_MONEY_NOT_ENOUGH, Level.WARN, "common money not enough");
        }
    }

    // 依照結果計算完 JsonNode
    private JsonNode calculateStcGetSpinResultNode(SpinResult spinResult){
        return JsonUtil.getInstance().getObjectMapper().convertValue(
                new StcGetSpinResultData(
                        super.logic.getAfterSpinBetGamePlayerResult(),
                        new GamePlayerResult(super.logic.getHumanPlayerAfterMoney()),
                        spinResult,
                        super.logic.getPlayerBet()),
                JsonNode.class);
    }

    // 依照押注請求計算下注後餘額
    private GamePlayerResult calculateAfterSpinBetGamePlayerResult(ClientSpinRequest clientSpinRequest) {
        // 1. 取得客戶端押注成本
        double playerCost = super.logic.getSpinCost(clientSpinRequest);

        // 2. 取得客戶押注前餘額
        double beginMoney = super.logic.getHumanPlayerBeginMoney();

        // 3. 計算押注后玩家餘額
        double afterSpinMoney = MathUtil.moneyScale(MathUtil.subtract(beginMoney, playerCost));

        // 4. 回傳
        return new GamePlayerResult(afterSpinMoney);
    }

    // 更新斷線重連資訊
    private void updateCutReturnWithoutSpinResult(ClientSpinRequest clientSpinRequest) {
        // 1. 更新斷線重連前局號
        super.logic.setRoundId(super.table.getRoundId());

        // 2. 更新斷線重連前局號
        super.logic.setCurrentSpinRequest(clientSpinRequest);

        // 3. 更新斷線重連前局號
        super.logic.setAfterSpinBetGamePlayerResult(this.calculateAfterSpinBetGamePlayerResult(clientSpinRequest));
    }
}

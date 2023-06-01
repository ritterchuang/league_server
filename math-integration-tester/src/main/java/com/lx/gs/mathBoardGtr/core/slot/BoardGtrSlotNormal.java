package com.lx.gs.mathBoardGtr.core.slot;

import com.lx.gs.math.core.common.gameFlowHlr.enity.result.GameFlowHlrResult;
import com.lx.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResult;
import com.lx.gs.math.core.common.table.entity.exception.TableException;
import com.lx.gs.math.core.common.table.entity.message.slot.ClientSpinRequest;
import com.lx.gs.math.core.common.table.entity.message.slot.betSpinTypeExtend.BetSpinTypeExtendNoneNormal;
import com.lx.gs.math.core.slot.ConstMathSlot;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.client.CreditBetBox;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.client.MathSlotConfig;
import com.lx.gs.math.core.slot.mathSlotConfigHlr.module.clientBetSpinConfigHlr.enity.result.ClientBetSpinConfigExtendNoneNormal;
import com.lx.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import com.lx.gs.mathStr.config.entity.ControlAlgorithmConfig;
import com.lx.gs.mathStr.core.entity.GamePlayerSimulation;
import com.lx.gs.mathStr.core.entity.playStr.BoardGtrResult;
import com.lx.gs.mathStr.core.entity.playStr.gameResultExtend.slot.GameResultExtendSlotJava;
import com.lx.gs.mathStr.core.module.poolCltr.PoolCtr;
import com.lx.utils.JsonUtil;

// 虎機一般局產生器
public class BoardGtrSlotNormal extends AbstractBoardGtrSlot {
    private final MathSlotConfig mathSlotConfig; // 數值設定

    public BoardGtrSlotNormal(GamePlayerSimulation player, PoolCtr poolCtr, PlayGameFieldConfig playGameFieldConfig, ControlAlgorithmConfig controlAlgorithmConfig) {
        super(player, poolCtr, playGameFieldConfig, controlAlgorithmConfig);
        this.mathSlotConfig = super.table.getMathSlotConfig();
    }

    @Override
    public BoardGtrResult calculateOneBoardResult() {
        // 1. 取得押注請求
        String cstSpinRequest = JsonUtil.getInstance().writeValueAsStringWithoutException(this.createClientSpinRequest());

        // 2. 取得打擊結果
        IGameBetLogResult gameBetLogResult = null;
        try {
            gameBetLogResult = super.table.getSpinResult(cstSpinRequest);
        } catch (TableException e) {
            e.printStackTrace();
        }

        GameFlowHlrResult gameFlowHlrResult = super.table.getLogic().getGameFlowerResult();

        // 3. 傳送資料給客端
        super.table.sendSpinResultToHumanPlayer();

        // 4. 封裝
        return new BoardGtrResult(
                super.calculateStatisticResult(),
                gameBetLogResult,
                new GameResultExtendSlotJava(
                        gameBetLogResult.getGameBetLogObj().getValidBet(),
                        gameBetLogResult.getGameBetLogObj().getScore(),
                        gameBetLogResult.getGameBetLogObj().getBet(),
                        gameFlowHlrResult
                ));
    }

    // 建立隨機押注
    private ClientSpinRequest createClientSpinRequest() {
        ClientBetSpinConfigExtendNoneNormal clientBetSpinConfigExtend = (ClientBetSpinConfigExtendNoneNormal) this.mathSlotConfig.getBetSpinConfigCluster().getBetSpinTypeToConfigMap().get(ConstMathSlot.BetType.NONE).get(ConstMathSlot.SpinType.NORMAL).getBetSpinConfigExtend();

        CreditBetBox randomCreditBox = clientBetSpinConfigExtend.getCreditBetBoxList().get(0);

        return new ClientSpinRequest(randomCreditBox.getCredit(), ConstMathSlot.BetType.NONE, ConstMathSlot.SpinType.NORMAL, ConstMathSlot.BetSpinType.NONE_NORMAL, new BetSpinTypeExtendNoneNormal());
    }
}

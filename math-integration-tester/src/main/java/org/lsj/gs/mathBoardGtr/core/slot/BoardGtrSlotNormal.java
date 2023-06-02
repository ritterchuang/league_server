package org.lsj.gs.mathBoardGtr.core.slot;

import org.lsj.gs.math.core.common.gameFlowHlr.enity.result.GameFlowHlrResult;
import org.lsj.gs.math.core.common.logic.gameBetLogResultCtr.enity.IGameBetLogResult;
import org.lsj.gs.math.core.common.table.entity.exception.TableException;
import org.lsj.gs.math.core.common.table.entity.message.slot.ClientSpinRequest;
import org.lsj.gs.math.core.common.table.entity.message.slot.betSpinTypeExtend.BetSpinTypeExtendNoneNormal;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.client.CreditBetBox;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.client.MathSlotConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.module.clientBetSpinConfigHlr.enity.result.ClientBetSpinConfigExtendNoneNormal;
import org.lsj.gs.mathBoardGtr.config.entity.playGameField.PlayGameFieldConfig;
import org.lsj.gs.mathStr.config.entity.ControlAlgorithmConfig;
import org.lsj.gs.mathStr.core.entity.GamePlayerSimulation;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.slot.GameResultExtendSlotJava;
import org.lsj.gs.mathStr.core.module.poolCltr.PoolCtr;
import org.lsj.utils.JsonUtil;

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

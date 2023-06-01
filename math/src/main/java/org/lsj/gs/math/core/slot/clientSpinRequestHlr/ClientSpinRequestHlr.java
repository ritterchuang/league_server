package org.lsj.gs.math.core.slot.clientSpinRequestHlr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.table.entity.message.slot.ClientSpinRequest;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.ClientSpinRequestHlrConfig;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.betSpinConfig.BetSpinConfig;
import org.lsj.gs.math.core.slot.paymentHlrMgr.PaymentHlrMgr;

// 押注請求檢查者
public class ClientSpinRequestHlr {
    private final ClientSpinRequestHlrConfig config; // 客端押注請求處理者設定
    private final ITableUtil tableUtil; // 牌桌工具包

    public ClientSpinRequestHlr(ClientSpinRequestHlrConfig config, ITableUtil tableUtil) {
        this.config = config;
        this.tableUtil = tableUtil;
    }

    // 檢查押注請求
    public ConstMathCommon.TableProtocolCode checkClientSpinRequest(ClientSpinRequest clientSpinRequest) {
        // 1. 檢查押注金額是否合法
        if (this.config.getBetSpinConfigCluster().getCreditList().contains(clientSpinRequest.getPlayCredit()) == false) {
            return ConstMathCommon.TableProtocolCode.SLOT_CREDIT_NOT_EXIST;
        }

        // 2. 檢查押注類型是否合法
        if (this.config.getBetTypeList().contains(clientSpinRequest.getBetType()) == false) {
            return ConstMathCommon.TableProtocolCode.SLOT_BET_TYPE_NOT_EXIST;
        }

        // 3. 檢查玩法類型是否合法
        if (this.config.getSpinTypeList().contains(clientSpinRequest.getSpinType()) == false) {
            return ConstMathCommon.TableProtocolCode.SLOT_SPIN_TYPE_NOT_EXIST;
        }

        // 4. 檢查額外設定
        if (this.checkBetSpinType(clientSpinRequest) == false) {
            return ConstMathCommon.TableProtocolCode.SLOT_BET_SPIN_TYPE_NOT_EXIST;
        }

        return ConstMathCommon.TableProtocolCode.COMMON_SUCCESS;
    }

    // 取得服務端押注請求
    public SpinRequest getSpinRequest(ClientSpinRequest clientSpinRequest, PaymentHlrMgr paymentHlrMgr) {
        // 1. 計算含底注的算分
        double playerCreditBase = paymentHlrMgr.getCreditBase(clientSpinRequest.getPlayCredit(), clientSpinRequest.getBetType(), clientSpinRequest.getSpinType());

        // 2. 計算成本
        double playerCost = paymentHlrMgr.getPlayerCost(clientSpinRequest.getPlayCredit(), clientSpinRequest.getBetType(), clientSpinRequest.getSpinType());

        // 3. 回傳
        return new SpinRequest(playerCreditBase, playerCost, clientSpinRequest.getBetType(), clientSpinRequest.getSpinType(), clientSpinRequest.getBetSpinTypeExtend());
    }

    // 檢察押注玩法類型
    private boolean checkBetSpinType(ClientSpinRequest clientSpinRequest) {
        // 1. 取得設定
        BetSpinConfig betSpinConfig = this.config.getBetSpinConfigCluster().getBetSpinToBetConfigMap().get(clientSpinRequest.getBetType()).
                                        get(clientSpinRequest.getSpinType());

        // 2. 取得客端傳入
        ConstMathSlot.BetSpinType requestBetSpinType = clientSpinRequest.getBetSpinType();

        // 3. 回傳
        return betSpinConfig.getBetSpinType().equals(requestBetSpinType);
    }
}

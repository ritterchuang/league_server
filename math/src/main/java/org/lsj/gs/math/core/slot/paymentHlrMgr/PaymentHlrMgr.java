package org.lsj.gs.math.core.slot.paymentHlrMgr;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.client.CreditBetBox;
import org.lsj.gs.math.core.slot.paymentHlrMgr.enity.PaymentHlrConfig;
import org.lsj.gs.math.core.slot.paymentHlrMgr.enity.PaymentHlrMgrConfig;
import org.lsj.gs.math.core.slot.paymentHlrMgr.module.IPaymentHlr;
import org.lsj.gs.math.core.slot.paymentHlrMgr.module.PaymentHlrFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 押注處理器管理者
public class PaymentHlrMgr {
    private final PaymentHlrMgrConfig config; // 押注處理器管理者設定
    private final ITableUtil tableUtil; // 牌桌工具包
    private final Map<ConstMathSlot.BetType, Map<ConstMathSlot.SpinType, IPaymentHlr>> betSpinTypeToPaymentHlrMap; // <押注, <玩法, 成本處理者>> 對應表

    public PaymentHlrMgr(PaymentHlrMgrConfig paymentHlrMgrConfig, ITableUtil tableUtil) {
        this.config = paymentHlrMgrConfig;
        this.tableUtil = tableUtil;
        this.betSpinTypeToPaymentHlrMap = this.createBetSpinTypeToPaymentHlrMap();
    }

    // 計算押注列表
    public List<CreditBetBox> getCreditBetBoxList(ConstMathSlot.BetType betType, ConstMathSlot.SpinType spinType) {
        return this.betSpinTypeToPaymentHlrMap.get(betType).get(spinType).calculateCreditBoxList();
    }

    // 計算算分基準
    public double getCreditBase(double credit, ConstMathSlot.BetType betType, ConstMathSlot.SpinType spinType) {
        return this.betSpinTypeToPaymentHlrMap.get(betType).get(spinType).calculateCreditBase(credit);
    }

    // 計算成本
    public double getPlayerCost(double credit, ConstMathSlot.BetType betType, ConstMathSlot.SpinType spinType) {
        return this.betSpinTypeToPaymentHlrMap.get(betType).get(spinType).calculatePlayerCost(credit);
    }

    // 取得玩家押注
    public double getPlayerBet(double credit, ConstMathSlot.BetType betType, ConstMathSlot.SpinType spinType) {
        return this.betSpinTypeToPaymentHlrMap.get(betType).get(spinType).calculatePlayerBet(credit);
    }

    // 建立 <押注, <玩法, 成本處理者>> 對應表
    private Map<ConstMathSlot.BetType, Map<ConstMathSlot.SpinType, IPaymentHlr>> createBetSpinTypeToPaymentHlrMap() {
        Map<ConstMathSlot.BetType, Map<ConstMathSlot.SpinType, IPaymentHlr>> result = new HashMap<>();

        this.config.getBetSpinTypeToPaymentHlrConfig().forEach(
                (key, value) -> result.put(key, this.createSpinTypeToPaymentHlrMap(value)));

        return  result;
    }

    // 建立 <玩法, 成本處理者> 對應表
    private Map<ConstMathSlot.SpinType, IPaymentHlr> createSpinTypeToPaymentHlrMap(Map<ConstMathSlot.SpinType, PaymentHlrConfig> spinTypeToPaymentConfigMap) {
        Map<ConstMathSlot.SpinType, IPaymentHlr> result = new HashMap<>();

        spinTypeToPaymentConfigMap.forEach(
                (key, value) -> result.put(key, new PaymentHlrFactory().create(value, this.tableUtil)));

        return result;
    }
}

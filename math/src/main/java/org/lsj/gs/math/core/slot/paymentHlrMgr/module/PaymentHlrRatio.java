package org.lsj.gs.math.core.slot.paymentHlrMgr.module;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.client.CreditBetBox;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.paymentConfig.PaymentConfigExtendRatio;
import org.lsj.gs.math.core.slot.paymentHlrMgr.enity.PaymentHlrConfig;
import org.lsj.utils.MathUtil;

import java.util.ArrayList;
import java.util.List;

// 收費處理者 比例
public class PaymentHlrRatio implements IPaymentHlr {
    private final PaymentHlrConfig config; // 成本處理者設定
    private final ITableUtil tableUtil; // 牌桌工具包

    public PaymentHlrRatio(PaymentHlrConfig config, ITableUtil tableUtil) {
        this.config = config;
        this.tableUtil = tableUtil;
    }

    // 計算押注列表
    @Override
    public List<CreditBetBox> calculateCreditBoxList() {
        // 1. 建立空殼
        List<CreditBetBox> result = new ArrayList<>();

        // 2. 取得資訊
        List<Double> creditList = this.config.getCreditList();

        // 3. 計算押注列表
        creditList.forEach(credit -> result.add(this.calculateCreditBoxList(credit)));

        // 4. 回傳
        return result;
    }

    // 依照押注分數計計算算分基準
    @Override
    public double calculateCreditBase(double credit) {
        return MathUtil.moneyScale(MathUtil.multiply(credit, this.config.getBase()));
    }

    // 依照押注分數計算成本
    @Override
    public double calculatePlayerCost(double credit) {
        // 1. 取得倍數值
        double costRatio = ((PaymentConfigExtendRatio)this.config.getPaymentConfigExtend()).getCostRatio();

        // 2. 取得底注
        double base = this.config.getBase();

        // 3. 回傳
        return MathUtil.moneyScale(MathUtil.multiply(MathUtil.multiply(credit, base), costRatio));
    }

    // 依照押注分數計算玩家押注
    @Override
    public double calculatePlayerBet(double credit) {
        // 1. 取得倍數值
        double betRatio = ((PaymentConfigExtendRatio)this.config.getPaymentConfigExtend()).getBetRatio();

        // 2. 取得底注
        double base = this.config.getBase();

        // 3. 回傳
        return MathUtil.moneyScale(MathUtil.multiply(MathUtil.multiply(credit, base), betRatio));
    }

    // 計算押注資訊
    private CreditBetBox calculateCreditBoxList(double credit) {
        return new CreditBetBox(MathUtil.moneyScale(credit), this.calculatePlayerCost(credit));
    }
}

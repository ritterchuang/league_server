package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.paymentConfig;

// 成本計算方式
public class PaymentConfigExtendRatio extends PaymentConfigExtend {
    private final double costRatio; // 成本倍數 [credit 與 bet 比值]
    private final double betRatio;  // 投注倍數 [credit 與 bet 比值]

    public PaymentConfigExtendRatio(double costRatio, double betRatio) {
        this.costRatio = costRatio;
        this.betRatio = betRatio;
    }

    public double getCostRatio() {
        return costRatio;
    }

    public double getBetRatio() {
        return betRatio;
    }
}

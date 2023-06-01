package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.client;

// 押注成本結構體
public class CreditBetBox {
    private final double credit; // 算分基準
    private final double bet; // 成本

    public CreditBetBox(double credit, double bet) {
        this.credit = credit;
        this.bet = bet;
    }

    public double getCredit() {
        return credit;
    }

    public double getBet() {
        return bet;
    }
}

package org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity;

import org.lsj.gs.math.core.common.table.entity.message.slot.betSpinTypeExtend.BetSpinTypeExtend;
import org.lsj.gs.math.core.slot.ConstMathSlot;

// 押注請求服務端
public class SpinRequest {
    private final double playerCreditBase; // 玩家算分押注 [已含底注]
    private final double playerCost; // 玩家成本 [已含底注]
    private final ConstMathSlot.BetType betType; // 押注類型
    private final ConstMathSlot.SpinType spinType; // 玩法類型
    private final BetSpinTypeExtend betSPinTypeExtend; // 押注玩法額外設定

    public SpinRequest(double playerCreditBase, double playerCost, ConstMathSlot.BetType betType, ConstMathSlot.SpinType spinType, BetSpinTypeExtend betSPinTypeExtend) {
        this.playerCreditBase = playerCreditBase;
        this.playerCost = playerCost;
        this.betType = betType;
        this.spinType = spinType;
        this.betSPinTypeExtend = betSPinTypeExtend;
    }

    public double getPlayerCreditBase() {
        return playerCreditBase;
    }

    public double getPlayerCost() {
        return playerCost;
    }

    public ConstMathSlot.BetType getBetType() {
        return betType;
    }

    public ConstMathSlot.SpinType getSpinType() {
        return spinType;
    }

    public BetSpinTypeExtend getBetSPinTypeExtend() {
        return betSPinTypeExtend;
    }
}

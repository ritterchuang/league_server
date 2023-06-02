package org.lsj.gs.math.core.common.table.entity.message.slot;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.lsj.gs.math.core.common.table.entity.message.slot.betSpinTypeExtend.BetSpinTypeExtend;
import org.lsj.gs.math.core.common.table.entity.message.slot.betSpinTypeExtend.BetSpinTypeExtendBuyFeature01;
import org.lsj.gs.math.core.common.table.entity.message.slot.betSpinTypeExtend.BetSpinTypeExtendBuyFeature02;
import org.lsj.gs.math.core.common.table.entity.message.slot.betSpinTypeExtend.BetSpinTypeExtendNoneNormal;
import org.lsj.gs.math.core.slot.ConstMathSlot;

// 老虎機請求
public class ClientSpinRequest {
    private final double playCredit; // 押注金額
    private final ConstMathSlot.BetType betType; // 押注類型
    private final ConstMathSlot.SpinType spinType; // 玩法類型
    private final ConstMathSlot.BetSpinType betSpinType; // 押注玩法類型
    private final BetSpinTypeExtend betSpinTypeExtend; // 押注玩法額外設定

    public ClientSpinRequest() {
        this.playCredit = 0.0;
        this.betType = ConstMathSlot.BetType.INVALID;
        this.spinType = ConstMathSlot.SpinType.INVALID;
        this.betSpinType = ConstMathSlot.BetSpinType.INVALID;
        this.betSpinTypeExtend = new BetSpinTypeExtend();
    }

    public ClientSpinRequest(double playCredit, ConstMathSlot.BetType betType, ConstMathSlot.SpinType spinType, ConstMathSlot.BetSpinType betSpinType, BetSpinTypeExtend betSpinTypeExtend) {
        this.playCredit = playCredit;
        this.betType = betType;
        this.spinType = spinType;
        this.betSpinType = betSpinType;
        this.betSpinTypeExtend = betSpinTypeExtend;
    }

    public double getPlayCredit() {
        return playCredit;
    }

    public ConstMathSlot.BetType getBetType() {
        return betType;
    }

    public ConstMathSlot.SpinType getSpinType() {
        return spinType;
    }

    public ConstMathSlot.BetSpinType getBetSpinType() {
        return betSpinType;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "betSpinType")
    @JsonSubTypes({
            @JsonSubTypes.Type(value = BetSpinTypeExtendNoneNormal.class, name = "NONE_NORMAL"),
            @JsonSubTypes.Type(value = BetSpinTypeExtendBuyFeature01.class, name = "NONE_BUYFEATURE01"),
            @JsonSubTypes.Type(value = BetSpinTypeExtendBuyFeature02.class, name = "NONE_BUYFEATURE02")
    })
    public BetSpinTypeExtend getBetSpinTypeExtend() {
        return betSpinTypeExtend;
    }
}

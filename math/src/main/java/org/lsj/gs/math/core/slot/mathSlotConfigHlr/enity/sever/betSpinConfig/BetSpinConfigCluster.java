package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.betSpinConfig;

import org.lsj.gs.math.core.slot.ConstMathSlot;

import java.util.List;
import java.util.Map;

// 押注設定集合
public class BetSpinConfigCluster {
    private final List<Double> creditList; // 押注列表
    private final ConstMathSlot.BetType defaultBetType; // 預設投注類型
    private final ConstMathSlot.SpinType defaultSpinType; // 預設玩法類型
    private final Map<ConstMathSlot.BetType, Map<ConstMathSlot.SpinType, BetSpinConfig>> betSpinToBetConfigMap; // 押注設定對應表

    public BetSpinConfigCluster(List<Double> creditList, ConstMathSlot.BetType defaultBetType, ConstMathSlot.SpinType defaultSpinType, Map<ConstMathSlot.BetType, Map<ConstMathSlot.SpinType, BetSpinConfig>> betSpinToBetConfigMap) {
        this.creditList = creditList;
        this.defaultBetType = defaultBetType;
        this.defaultSpinType = defaultSpinType;
        this.betSpinToBetConfigMap = betSpinToBetConfigMap;
    }

    public List<Double> getCreditList() {
        return creditList;
    }

    public ConstMathSlot.BetType getDefaultBetType() {
        return defaultBetType;
    }

    public ConstMathSlot.SpinType getDefaultSpinType() {
        return defaultSpinType;
    }

    public Map<ConstMathSlot.BetType, Map<ConstMathSlot.SpinType, BetSpinConfig>> getBetSpinToBetConfigMap() {
        return betSpinToBetConfigMap;
    }
}

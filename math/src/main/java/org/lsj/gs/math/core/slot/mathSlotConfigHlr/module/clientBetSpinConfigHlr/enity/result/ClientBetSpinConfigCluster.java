package org.lsj.gs.math.core.slot.mathSlotConfigHlr.module.clientBetSpinConfigHlr.enity.result;

import org.lsj.gs.math.core.slot.ConstMathSlot;

import java.util.Map;

// 客端投注設定集合
public class ClientBetSpinConfigCluster {
    private final ConstMathSlot.BetType defaultBetType; // 預設押注類型
    private final ConstMathSlot.SpinType defaultSpinType; // 預設玩法類型
    private final Map<ConstMathSlot.BetType, Map<ConstMathSlot.SpinType, ClientBetSpinConfig>> betSpinTypeToConfigMap; // 客製押注玩法額外設定

    public ClientBetSpinConfigCluster(ConstMathSlot.BetType defaultBetType, ConstMathSlot.SpinType defaultSpinType, Map<ConstMathSlot.BetType, Map<ConstMathSlot.SpinType, ClientBetSpinConfig>> betSpinTypeToConfigMap) {
        this.defaultBetType = defaultBetType;
        this.defaultSpinType = defaultSpinType;
        this.betSpinTypeToConfigMap = betSpinTypeToConfigMap;
    }

    public ConstMathSlot.BetType getDefaultBetType() {
        return defaultBetType;
    }

    public ConstMathSlot.SpinType getDefaultSpinType() {
        return defaultSpinType;
    }

    public Map<ConstMathSlot.BetType, Map<ConstMathSlot.SpinType, ClientBetSpinConfig>> getBetSpinTypeToConfigMap() {
        return betSpinTypeToConfigMap;
    }
}

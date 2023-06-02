package org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.betSpinConfig.BetSpinConfigCluster;

import java.util.List;

// 客端押注需求處理者設定
public class ClientSpinRequestHlrConfig {
    private final double base; // 底注
    private final List<ConstMathSlot.BetType> betTypeList; // 押注類型列表
    private final List<ConstMathSlot.SpinType> spinTypeList; // 玩法類型列表
    private final BetSpinConfigCluster betSpinConfigCluster; // 押注玩法設定集合

    public ClientSpinRequestHlrConfig(double base, List<ConstMathSlot.BetType> betTypeList, List<ConstMathSlot.SpinType> spinTypeList, BetSpinConfigCluster betSpinConfigCluster) {
        this.base = base;
        this.betTypeList = betTypeList;
        this.spinTypeList = spinTypeList;
        this.betSpinConfigCluster = betSpinConfigCluster;
    }

    public double getBase() {
        return base;
    }

    public List<ConstMathSlot.BetType> getBetTypeList() {
        return betTypeList;
    }

    public List<ConstMathSlot.SpinType> getSpinTypeList() {
        return spinTypeList;
    }

    public BetSpinConfigCluster getBetSpinConfigCluster() {
        return betSpinConfigCluster;
    }
}

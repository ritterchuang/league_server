package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig.cascadeConfig.cascadeConfigExtend;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfig;

import java.util.List;

// 消除額外設定 麻將胡了
public class CascadeConfigExtendMjhl extends CascadeConfigExtend{
    private final ReelConfig cascadeReelConfig; // 消除輪帶表設定
    private final List<Double> goldenPositionProbList; // [col][0] = weight (true)
    private final List<Integer> multiplierList; // 倍數列表

    private final ConstMathSlot.SymbolAttribute transSymbolAttribute; // 轉換標誌

    public CascadeConfigExtendMjhl(ReelConfig cascadeReelConfig, List<Double> goldenPositionProbList, List<Integer> multiplierList, ConstMathSlot.SymbolAttribute transSymbolAttribute) {
        this.cascadeReelConfig = cascadeReelConfig;
        this.goldenPositionProbList = goldenPositionProbList;
        this.multiplierList = multiplierList;
        this.transSymbolAttribute = transSymbolAttribute;
    }

    public ReelConfig getCascadeReelConfig() {
        return cascadeReelConfig;
    }

    public List<Double> getGoldenPositionProbList() {
        return goldenPositionProbList;
    }

    public List<Integer> getMultiplierList() {
        return multiplierList;
    }

    public ConstMathSlot.SymbolAttribute getTransSymbolAttribute() {
        return transSymbolAttribute;
    }
}

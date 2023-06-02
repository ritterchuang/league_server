package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.cascadeClusterConfig.cascadeConfig.cascadeConfigExtend;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.reelConfig.ReelConfig;

import java.util.List;

// 消除額外設定 超級王牌
public class CascadeConfigExtendCjwp extends CascadeConfigExtend{
    private final ReelConfig cascadeReelConfig; // 消除輪帶表設定
    private final List<Double> goldenPositionProbList; // [col][0] = weight (true)
    private final List<Integer> multiplierList; // 倍數列表
    private final ConstMathSlot.SymbolAttribute[] transSymbolAttribute; // 轉換標誌
    private final int[] transSymbolWeight; // 轉換標誌權重
    private final int[] randomWildCount; // RandomWild 數量
    private final int[] randomWildCountWeight; // RandomWild 數量權重
    private final int[] randomWildAppearColumn; // RandomWild 可出現軸ID

    public CascadeConfigExtendCjwp(ReelConfig cascadeReelConfig, List<Double> goldenPositionProbList, List<Integer> multiplierList, ConstMathSlot.SymbolAttribute[] transSymbolAttribute, int[] transSymbolWeight, int[] randomWildCount, int[] randomWildCountWeight, int[] randomWildAppearColumn) {
        this.cascadeReelConfig = cascadeReelConfig;
        this.goldenPositionProbList = goldenPositionProbList;
        this.multiplierList = multiplierList;
        this.transSymbolAttribute = transSymbolAttribute;
        this.transSymbolWeight = transSymbolWeight;
        this.randomWildCount = randomWildCount;
        this.randomWildCountWeight = randomWildCountWeight;
        this.randomWildAppearColumn = randomWildAppearColumn;
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

    public ConstMathSlot.SymbolAttribute[] getTransSymbolAttribute() {
        return transSymbolAttribute;
    }
    public int[] getTransSymbolWeight() {
        return transSymbolWeight;
    }

    public int[] getRandomWildCount() {
        return randomWildCount;
    }

    public int[] getRandomWildCountWeight() {
        return randomWildCountWeight;
    }

    public int[] getRandomWildAppearColumn() { return randomWildAppearColumn; }
}

package com.lx.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.roundNormalGameConfig;

import java.util.List;

// 一般遊戲局額外設定仙境探寶免費遊戲
public class RoundNormalGameConfigExtendXjtbFreeGame extends RoundNormalGameConfigExtend{
    private final int targetSymbolId; // 目標標誌 Id
    private final List<List<Integer>> multiplierList; // 倍數列表 [column][multiplierIdx] = multiplier
    private final List<List<Integer>> weightList; // 權重列表 [column][weightIdx] = weight

    public RoundNormalGameConfigExtendXjtbFreeGame(int targetSymbolId, List<List<Integer>> multiplierList, List<List<Integer>> weightList) {
        this.targetSymbolId = targetSymbolId;
        this.multiplierList = multiplierList;
        this.weightList = weightList;
    }

    public int getTargetSymbolId() {
        return targetSymbolId;
    }

    public List<List<Integer>> getMultiplierList() {
        return multiplierList;
    }

    public List<List<Integer>> getWeightList() {
        return weightList;
    }
}

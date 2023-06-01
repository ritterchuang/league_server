package org.lsj.gs.math.core.card.resultCtr.bjlResultCtr;

import java.util.Map;

// 百家結果計算器遊戲設定
public class BjlBaiRenResultCtrGameConfig {
    private final Map<Integer, Double> betAreaIdToRateMap; // 下注區域賠率對應表 <BetAreaId, Rate>

    public BjlBaiRenResultCtrGameConfig(Map<Integer, Double> betAreaIdToRateMap) {
        this.betAreaIdToRateMap = betAreaIdToRateMap;
    }

    public Map<Integer, Double> getBetAreaIdToRateMap() {
        return betAreaIdToRateMap;
    }
}

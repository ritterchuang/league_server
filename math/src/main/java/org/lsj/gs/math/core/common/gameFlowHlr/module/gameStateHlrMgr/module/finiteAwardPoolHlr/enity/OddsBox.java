package org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.module.finiteAwardPoolHlr.enity;

// 倍數資訊
public class OddsBox {
    private final int odds; // 倍數
    private final int weight; // 權重

    public OddsBox(int odds, int weight) {
        this.odds = odds;
        this.weight = weight;
    }

    public int getOdds() {
        return odds;
    }

    public int getWeight() {
        return weight;
    }
}

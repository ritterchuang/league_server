package org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.roundConfig.roundCascadeGameConfig;

// 消除遊戲局額外設定 羅馬競技場 免費遊戲
public class RoundCascadeGameConfigExtendLmjjcFreeGame extends RoundCascadeGameConfigExtend{
    private final int minTargetSymbolCount; // 觸發免費遊戲，至少要幾個SC
    private final int addRound; // 每多一個 SC，增加幾場免費遊戲

    public RoundCascadeGameConfigExtendLmjjcFreeGame(int minTargetSymbolCount, int addRound) {
        this.minTargetSymbolCount = minTargetSymbolCount;
        this.addRound = addRound;
    }

    public int getMinTargetSymbolCount() {
        return minTargetSymbolCount;
    }

    public int getAddRound() {
        return addRound;
    }
}

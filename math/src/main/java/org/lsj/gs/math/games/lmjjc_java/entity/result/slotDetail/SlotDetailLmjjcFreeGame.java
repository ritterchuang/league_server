package org.lsj.gs.math.games.lmjjc_java.entity.result.slotDetail;

import org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetail;

// 虎機詳細記錄羅馬競技場 免費遊戲
public class SlotDetailLmjjcFreeGame extends SlotDetail {
    private final int roundIndex; // 場次
    private final int totalRound; // 總場次
    private final double roundTotalWin; // 總得分

    public SlotDetailLmjjcFreeGame(int roundIndex, int totalRound, double roundTotalWin) {
        this.roundIndex = roundIndex;
        this.totalRound = totalRound;
        this.roundTotalWin = roundTotalWin;
    }

    public int getRoundIndex() {
        return roundIndex;
    }

    public int getTotalRound() {
        return totalRound;
    }

    public double getRoundTotalWin() {
        return roundTotalWin;
    }
}

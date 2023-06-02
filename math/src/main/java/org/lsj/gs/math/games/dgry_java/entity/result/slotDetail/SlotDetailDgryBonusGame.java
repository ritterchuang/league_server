package org.lsj.gs.math.games.dgry_java.entity.result.slotDetail;

import org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetail;
import org.lsj.gs.math.games.dgry_java.entity.config.DgryBonusGameDisplayType;

// 虎機詳細記錄帝国榮耀 額外遊戲
public class SlotDetailDgryBonusGame extends SlotDetail {
    private final int roundIndex; // 場次
    private final int totalRound; // 總場次
    private final double roundTotalWin; // 總得分
    private final DgryBonusGameDisplayType displayResult; //表演結果

    public SlotDetailDgryBonusGame(int roundIndex, int totalRound, double roundTotalWin, DgryBonusGameDisplayType displayResult) {
        this.roundIndex = roundIndex;
        this.totalRound = totalRound;
        this.roundTotalWin = roundTotalWin;
        this.displayResult = displayResult;
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

    public DgryBonusGameDisplayType getDisplayResult() {
        return displayResult;
    }
}

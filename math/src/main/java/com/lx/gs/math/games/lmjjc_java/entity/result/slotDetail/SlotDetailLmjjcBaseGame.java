package com.lx.gs.math.games.lmjjc_java.entity.result.slotDetail;

import com.lx.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetail;

// 虎機詳細記錄羅馬競技場 基本遊戲
public class SlotDetailLmjjcBaseGame extends SlotDetail {
    private final double spinCost; // 押注成本
    private final double baseGameTotalWin; // 總得分

    public SlotDetailLmjjcBaseGame(double spinCost, double baseGameTotalWin) {
        this.spinCost = spinCost;
        this.baseGameTotalWin = baseGameTotalWin;
    }

    public double getSpinCost() {
        return spinCost;
    }

    public double getBaseGameTotalWin() {
        return baseGameTotalWin;
    }
}

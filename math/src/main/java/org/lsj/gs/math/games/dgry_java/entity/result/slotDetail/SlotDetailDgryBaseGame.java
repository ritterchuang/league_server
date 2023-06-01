package org.lsj.gs.math.games.dgry_java.entity.result.slotDetail;

import org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetail;

// 虎機詳細記錄帝国榮耀 基本遊戲
public class SlotDetailDgryBaseGame extends SlotDetail {
    private final double spinCost; // 押注成本
    private final double baseGameTotalWin; // 總得分

    public SlotDetailDgryBaseGame(double spinCost, double baseGameTotalWin) {
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

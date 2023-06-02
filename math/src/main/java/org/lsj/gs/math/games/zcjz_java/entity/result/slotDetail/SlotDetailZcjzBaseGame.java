package org.lsj.gs.math.games.zcjz_java.entity.result.slotDetail;

import org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetail;

// 虎機詳細記錄父類別
public class SlotDetailZcjzBaseGame extends SlotDetail {
    private final double spinCost; // 押注成本
    private final String screenColumn_1; // 第一欄畫面
    private final String screenColumn_2; // 第二欄畫面
    private final String screenColumn_3; // 第三蘭畫面
    private final double baseGameTotalWin; // 總得分

    public SlotDetailZcjzBaseGame(double spinCost, String screenColumn_1, String screenColumn_2, String screenColumn_3, double baseGameTotalWin) {
        this.spinCost = spinCost;
        this.screenColumn_1 = screenColumn_1;
        this.screenColumn_2 = screenColumn_2;
        this.screenColumn_3 = screenColumn_3;
        this.baseGameTotalWin = baseGameTotalWin;
    }


    public double getSpinCost() {
        return spinCost;
    }

    public String getScreenColumn_1() {
        return screenColumn_1;
    }

    public String getScreenColumn_2() {
        return screenColumn_2;
    }

    public String getScreenColumn_3() {
        return screenColumn_3;
    }

    public double getBaseGameTotalWin() {
        return baseGameTotalWin;
    }
}

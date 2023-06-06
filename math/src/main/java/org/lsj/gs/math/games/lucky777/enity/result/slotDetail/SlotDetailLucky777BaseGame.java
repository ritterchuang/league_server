package org.lsj.gs.math.games.lucky777.enity.result.slotDetail;

import org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetail;

// 虎機詳細記錄 Lucky777 基本遊戲
public class SlotDetailLucky777BaseGame extends SlotDetail {
    private final double spinCost; // 押注成本
    private final double baseGameTotalWin; // 總得分
    private final String screenColumn_1; // 第一欄畫面
    private final String screenColumn_2; // 第二欄畫面
    private final String screenColumn_3; // 第三欄畫面

    public SlotDetailLucky777BaseGame(double spinCost, double baseGameTotalWin, String screenColumn_1, String screenColumn_2, String screenColumn_3) {
        this.spinCost = spinCost;
        this.baseGameTotalWin = baseGameTotalWin;
        this.screenColumn_1 = screenColumn_1;
        this.screenColumn_2 = screenColumn_2;
        this.screenColumn_3 = screenColumn_3;
    }

    public double getSpinCost() {
        return spinCost;
    }

    public double getBaseGameTotalWin() {
        return baseGameTotalWin;
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
}

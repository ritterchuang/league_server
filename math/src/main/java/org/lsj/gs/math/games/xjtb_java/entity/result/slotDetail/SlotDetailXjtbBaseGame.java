package org.lsj.gs.math.games.xjtb_java.entity.result.slotDetail;

import org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetail;

// 虎機詳細記錄仙境探寶基本遊戲
public class SlotDetailXjtbBaseGame extends SlotDetail {
    private final double spinCost; // 押注成本
    private final double baseGameTotalWin; // 總得分
    private final String screenColumn_1; // 第一欄畫面
    private final String screenColumn_2; // 第二欄畫面
    private final String screenColumn_3; // 第三欄畫面
    private final String screenColumn_4; // 第四欄畫面
    private final String screenColumn_5; // 第五欄畫面

    public SlotDetailXjtbBaseGame(double spinCost, double baseGameTotalWin, String screenColumn_1, String screenColumn_2, String screenColumn_3, String screenColumn_4, String screenColumn_5) {
        this.spinCost = spinCost;
        this.baseGameTotalWin = baseGameTotalWin;
        this.screenColumn_1 = screenColumn_1;
        this.screenColumn_2 = screenColumn_2;
        this.screenColumn_3 = screenColumn_3;
        this.screenColumn_4 = screenColumn_4;
        this.screenColumn_5 = screenColumn_5;
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

    public String getScreenColumn_4() {
        return screenColumn_4;
    }

    public String getScreenColumn_5() {
        return screenColumn_5;
    }
}

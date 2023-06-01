package org.lsj.gs.math.games.dgry_java.entity.result.slotDetail;

import org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetail;

// 虎機詳細記錄帝国榮耀 消除內容
public class SlotDetailDgryCascade extends SlotDetail {
    private final int cascadeIndex;          // 消除次數
    private final double cascadeTotalWin;    // 總得分

    private final String screenColumn_1;     // 消除前第一欄畫面
    private final String screenColumn_2;     // 消除前第二欄畫面
    private final String screenColumn_3;     // 消除前第三欄畫面
    private final String screenColumn_4;     // 消除前第四欄畫面
    private final String screenColumn_5;     // 消除前第五欄畫面

    public SlotDetailDgryCascade(int cascadeIndex, double cascadeTotalWin, String screenColumn_1, String screenColumn_2, String screenColumn_3, String screenColumn_4, String screenColumn_5) {
        this.cascadeIndex = cascadeIndex;
        this.cascadeTotalWin = cascadeTotalWin;
        this.screenColumn_1 = screenColumn_1;
        this.screenColumn_2 = screenColumn_2;
        this.screenColumn_3 = screenColumn_3;
        this.screenColumn_4 = screenColumn_4;
        this.screenColumn_5 = screenColumn_5;
    }

    public int getCascadeIndex() {
        return cascadeIndex;
    }

    public double getCascadeTotalWin() {
        return cascadeTotalWin;
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

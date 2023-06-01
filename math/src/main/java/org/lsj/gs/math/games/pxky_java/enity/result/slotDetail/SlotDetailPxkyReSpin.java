package org.lsj.gs.math.games.pxky_java.enity.result.slotDetail;

import org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetail;

// 虎機詳細記錄父類別
public class SlotDetailPxkyReSpin extends SlotDetail {
    private final String reSpinScreenColumn_1; // 重轉第一欄畫面
    private final String reSpinScreenColumn_2; // 重轉第二欄畫面
    private final String reSpinScreenColumn_3; // 重轉第三蘭畫面
    private final double reSpinTotalWin; // 重轉總得分

    public SlotDetailPxkyReSpin(String reSpinScreenColumn_1, String reSpinScreenColumn_2, String reSpinScreenColumn_3, double reSpinTotalWin) {
        this.reSpinScreenColumn_1 = reSpinScreenColumn_1;
        this.reSpinScreenColumn_2 = reSpinScreenColumn_2;
        this.reSpinScreenColumn_3 = reSpinScreenColumn_3;
        this.reSpinTotalWin = reSpinTotalWin;
    }


    public String getReSpinScreenColumn_1() {
        return reSpinScreenColumn_1;
    }

    public String getReSpinScreenColumn_2() {
        return reSpinScreenColumn_2;
    }

    public String getReSpinScreenColumn_3() {
        return reSpinScreenColumn_3;
    }

    public double getReSpinTotalWin() {
        return reSpinTotalWin;
    }
}

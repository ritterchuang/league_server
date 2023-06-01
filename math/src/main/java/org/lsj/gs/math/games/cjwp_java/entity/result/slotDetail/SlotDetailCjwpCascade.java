package org.lsj.gs.math.games.cjwp_java.entity.result.slotDetail;

import org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetail;

// 虎機詳細記錄超級王牌 消除內容
public class SlotDetailCjwpCascade extends SlotDetail {
    private final int cascadeIndex;          // 消除次數
    private final int cascadeMultiplier;     // 倍數
    private final double cascadeTotalWin;    // 總得分

    private final String screenColumn_1;     // 消除前第一欄畫面
    private final String screenColumn_2;     // 消除前第二欄畫面
    private final String screenColumn_3;     // 消除前第三欄畫面
    private final String screenColumn_4;     // 消除前第四欄畫面
    private final String screenColumn_5;     // 消除前第五欄畫面

    private final String goldenPosition_2;   // 消除前第二欄黃金位置
    private final String goldenPosition_3;   // 消除前第三欄黃金位置
    private final String goldenPosition_4;   // 消除前第四欄黃金位置

    public SlotDetailCjwpCascade(int cascadeIndex, int cascadeMultiplier, double cascadeTotalWin, String screenColumn_1, String screenColumn_2, String screenColumn_3, String screenColumn_4, String screenColumn_5, String goldenPosition_2, String goldenPosition_3, String goldenPosition_4) {
        this.cascadeIndex = cascadeIndex;
        this.cascadeMultiplier = cascadeMultiplier;
        this.cascadeTotalWin = cascadeTotalWin;
        this.screenColumn_1 = screenColumn_1;
        this.screenColumn_2 = screenColumn_2;
        this.screenColumn_3 = screenColumn_3;
        this.screenColumn_4 = screenColumn_4;
        this.screenColumn_5 = screenColumn_5;
        this.goldenPosition_2 = goldenPosition_2;
        this.goldenPosition_3 = goldenPosition_3;
        this.goldenPosition_4 = goldenPosition_4;
    }

    public int getCascadeIndex() {
        return cascadeIndex;
    }

    public int getCascadeMultiplier() {
        return cascadeMultiplier;
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

    public String getGoldenPosition_2() {
        return goldenPosition_2;
    }

    public String getGoldenPosition_3() {
        return goldenPosition_3;
    }

    public String getGoldenPosition_4() {
        return goldenPosition_4;
    }
}

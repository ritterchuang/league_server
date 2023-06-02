package org.lsj.gs.math.games.sn_java.entity.result.slotDetail;

import org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetail;

// 虎機詳細記錄水牛免費遊戲
public class SlotDetailSnFreeGame extends SlotDetail {
    private final int roundIndex; // 場次
    private final int totalRound; // 總場次
    private final double roundTotalWin; // 場次總得分
    private final String screenColumn_1; // 第一欄畫面
    private final String screenColumn_2; // 第二欄畫面
    private final String screenColumn_3; // 第三欄畫面
    private final String screenColumn_4; // 第四欄畫面
    private final String screenColumn_5; // 第五欄畫面
    private final String multiplier_2; // 第二軸倍數列表
    private final String multiplier_3; // 第三軸倍數列表
    private final String multiplier_4; // 第四軸倍數列表

    public SlotDetailSnFreeGame(int roundIndex, int totalRound, double roundTotalWin, String screenColumn_1, String screenColumn_2, String screenColumn_3, String screenColumn_4, String screenColumn_5, String multiplier_2, String multiplier_3, String multiplier_4) {
        this.roundIndex = roundIndex;
        this.totalRound = totalRound;
        this.roundTotalWin = roundTotalWin;
        this.screenColumn_1 = screenColumn_1;
        this.screenColumn_2 = screenColumn_2;
        this.screenColumn_3 = screenColumn_3;
        this.screenColumn_4 = screenColumn_4;
        this.screenColumn_5 = screenColumn_5;
        this.multiplier_2 = multiplier_2;
        this.multiplier_3 = multiplier_3;
        this.multiplier_4 = multiplier_4;
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

    public String getMultiplier_2() {
        return multiplier_2;
    }

    public String getMultiplier_3() {
        return multiplier_3;
    }

    public String getMultiplier_4() {
        return multiplier_4;
    }
}

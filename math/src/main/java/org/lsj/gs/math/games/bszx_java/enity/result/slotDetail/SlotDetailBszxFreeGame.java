package org.lsj.gs.math.games.bszx_java.enity.result.slotDetail;

import org.lsj.gs.math.core.slot.slotDetailHlrMgr.enity.SlotDetail;

// 虎機詳細記錄 寶石之星虎機免費遊戲
public class SlotDetailBszxFreeGame extends SlotDetail {
    private final int roundIndex; // 場次
    private final int totalRound; // 總場次
    private final double totalWin; // 總得分
    private final String screenColumn_1; // 重轉第一欄畫面
    private final String screenColumn_2; // 重轉第二欄畫面
    private final String screenColumn_3; // 重轉第三欄畫面
    private final String screenColumn_4; // 重轉第四欄畫面
    private final String screenColumn_5; // 重轉第五欄畫面

    public SlotDetailBszxFreeGame(int roundIndex, int totalRound, double totalWin, String screenColumn_1, String screenColumn_2, String screenColumn_3, String screenColumn_4, String screenColumn_5) {
        this.roundIndex = roundIndex;
        this.totalRound = totalRound;
        this.totalWin = totalWin;
        this.screenColumn_1 = screenColumn_1;
        this.screenColumn_2 = screenColumn_2;
        this.screenColumn_3 = screenColumn_3;
        this.screenColumn_4 = screenColumn_4;
        this.screenColumn_5 = screenColumn_5;
    }

    public int getRoundIndex() {
        return roundIndex;
    }

    public int getTotalRound() {
        return totalRound;
    }

    public double getTotalWin() {
        return totalWin;
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

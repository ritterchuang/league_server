package org.lsj.gs.math.core.slot.oneBoardResultMgr.enity;

// 虎機客端最新撥放資訊
public class LastPlayedProgressResult {
    private int lastPlayedStateIndex; // 已撥完的狀態流水號
    private int lastPlayedRoundIndex; // 已撥完的遊戲局數流水號

    public LastPlayedProgressResult() {
        this.lastPlayedStateIndex = 0;
        this.lastPlayedRoundIndex = 0;
    }

    public int getLastPlayedStateIndex() {
        return lastPlayedStateIndex;
    }

    public int getLastPlayedRoundIndex() {
        return lastPlayedRoundIndex;
    }
}

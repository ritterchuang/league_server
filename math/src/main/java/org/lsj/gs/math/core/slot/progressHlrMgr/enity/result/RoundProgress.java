package org.lsj.gs.math.core.slot.progressHlrMgr.enity.result;

// 遊戲場次進度
public class RoundProgress {
    private final int currentRound; // 當前局數
    private final int addRound; // 增加局數
    private final int totalRound; // 總局數

    public RoundProgress(int currentRound, int addRound, int totalRound) {
        this.currentRound = currentRound;
        this.addRound = addRound;
        this.totalRound = totalRound;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public int getAddRound() {
        return addRound;
    }

    public int getTotalRound() {
        return totalRound;
    }
}

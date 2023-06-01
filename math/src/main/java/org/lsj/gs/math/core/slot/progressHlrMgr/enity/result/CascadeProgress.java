package org.lsj.gs.math.core.slot.progressHlrMgr.enity.result;

// 遊戲消除進度
public class CascadeProgress {
    private final int currentTimes; // 當前局數
    private final int addTimes; // 增加局數
    private final int totalTimes; // 總局數

    public CascadeProgress(int currentTimes, int addTimes, int totalTimes) {
        this.currentTimes = currentTimes;
        this.addTimes = addTimes;
        this.totalTimes = totalTimes;
    }

    public int getCurrentTimes() {
        return currentTimes;
    }

    public int getAddTimes() {
        return addTimes;
    }

    public int getTotalTimes() {
        return totalTimes;
    }
}

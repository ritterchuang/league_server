package org.lsj.gs.math.core.slot.oneBoardResultMgr.enity;

// 虎機斷線重連播放進度資訊
public class SlotCutReturnProgressResult {
    private final int nextGameStateIndex; // 下次遊戲狀態流水號
    private final int nextRoundIndex; // 下次遊戲局數流水號

    public SlotCutReturnProgressResult(int nextGameStateIndex, int nextRoundIndex) {
        this.nextGameStateIndex = nextGameStateIndex;
        this.nextRoundIndex = nextRoundIndex;
    }

    public int getNextGameStateIndex() {
        return nextGameStateIndex;
    }

    public int getNextRoundIndex() {
        return nextRoundIndex;
    }
}

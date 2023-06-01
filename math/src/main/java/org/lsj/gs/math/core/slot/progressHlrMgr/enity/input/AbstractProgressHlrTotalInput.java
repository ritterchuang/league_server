package org.lsj.gs.math.core.slot.progressHlrMgr.enity.input;

// 抽象遊戲進度處理者 總場次 輸入參數
public abstract class AbstractProgressHlrTotalInput{
    private final int processIndex; // 進度指標

    public AbstractProgressHlrTotalInput(int processIndex) {
        this.processIndex = processIndex;
    }

    public int getProcessIndex() {
        return processIndex;
    }
}

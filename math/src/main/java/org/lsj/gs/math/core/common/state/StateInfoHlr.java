package org.lsj.gs.math.core.common.state;

// 狀態資訊處理器
public class StateInfoHlr {
    private final int stateId; // 狀態索引
    private double leftTimeSec; // 剩餘秒數

    public StateInfoHlr(int stateId) {
        this.stateId = stateId;
        this.leftTimeSec = 0.0;
    }

    public int getStateId() {
        return stateId;
    }

    public double getLeftTimeSec() {
        return leftTimeSec;
    }

    public void setLeftTimeSec(double leftTimeSec) {
        this.leftTimeSec = leftTimeSec;
    }
}

package org.lsj.gs.math.core.common.robotLogic.entity;

// 抽象機器人結果
public abstract class AbstractRobotResult {
    protected long milliTimeSec; // 等待時間

    public AbstractRobotResult(long milliTimeSec) {
        this.milliTimeSec = milliTimeSec;
    }

    public long getMilliTimeSec() {
        return milliTimeSec;
    }

    public void setMilliTimeSec(long milliTimeSec) {
        this.milliTimeSec = milliTimeSec;
    }
}

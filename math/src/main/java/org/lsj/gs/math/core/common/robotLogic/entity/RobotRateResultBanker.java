package org.lsj.gs.math.core.common.robotLogic.entity;

// 機器人倍數結果
public class RobotRateResultBanker extends AbstractRobotResultBanker {
    private int rate; // 下注倍數

    public RobotRateResultBanker(long milliTimeSec, int rate) {
        super(milliTimeSec);
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}

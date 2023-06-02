package org.lsj.gs.math.core.baiRen.mathConfigHlr.entity;

// 百人時間設定
public class BaiRenTimeConfig {
    private final double betTimeSec; // 下注時間秒數
    private final double endTimeSec; // 結算時間秒數

    public BaiRenTimeConfig(double betTimeSec, double endTimeSec) {
        this.betTimeSec = betTimeSec;
        this.endTimeSec = endTimeSec;
    }

    public double getBetTimeSec() {
        return betTimeSec;
    }

    public double getEndTimeSec() {
        return endTimeSec;
    }
}

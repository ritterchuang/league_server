package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity;

// 機器人特殊倍數資訊
public class RobotSpecialFeatureOddsInfo {
    private final int specialFeatureOdds; // 特殊事件倍數
    private final double oddsAppearProbability; // 倍數出現率

    public RobotSpecialFeatureOddsInfo(int specialFeatureOdds, double oddsAppearProbability) {
        this.specialFeatureOdds = specialFeatureOdds;
        this.oddsAppearProbability = oddsAppearProbability;
    }

    public int getSpecialFeatureOdds() {
        return specialFeatureOdds;
    }

    public double getOddsAppearProbability() {
        return oddsAppearProbability;
    }
}

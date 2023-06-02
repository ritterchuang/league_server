package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.result;

// 機器人基礎倍數資訊
public class RobotBasicOddsInfo {
    private final int basicOdds; // 基本倍數
    private final double oddsAppearProbability; // 倍數出現率

    public RobotBasicOddsInfo(int basicOdds, double oddsAppearProbability) {
        this.basicOdds = basicOdds;
        this.oddsAppearProbability = oddsAppearProbability;
    }

    public int getBasicOdds() {
        return basicOdds;
    }

    public double getOddsAppearProbability() {
        return oddsAppearProbability;
    }
}

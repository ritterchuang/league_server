package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrResult;

// 機器人獎勵子彈結果
public class RobotAwardBulletGtrResult {
    private final AwardBulletGtrResult awardBulletGtrResult; // 獎勵子彈結果
    private final double expectOdds; // 預期倍數
    private final double appearProbability; // 出現率

    public RobotAwardBulletGtrResult(AwardBulletGtrResult awardBulletGtrResult, double expectOdds, double appearProbability) {
        this.awardBulletGtrResult = awardBulletGtrResult;
        this.expectOdds = expectOdds;
        this.appearProbability = appearProbability;
    }

    public AwardBulletGtrResult getAwardBulletGtrResult() {
        return awardBulletGtrResult;
    }

    public double getExpectOdds() {
        return expectOdds;
    }

    public double getAppearProbability() {
        return appearProbability;
    }
}

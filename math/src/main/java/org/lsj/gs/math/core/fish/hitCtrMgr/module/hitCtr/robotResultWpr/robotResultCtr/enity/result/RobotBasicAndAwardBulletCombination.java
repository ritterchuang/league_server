package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.robotResultWpr.robotResultCtr.enity.result;

import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrResult.RobotAwardBulletGtrResult;
import org.lsj.utils.MathUtil;

// 機器人基礎倍數與獎勵子彈組合
public class RobotBasicAndAwardBulletCombination {
    private final int basicOdds; // 基礎倍數
    private final RobotAwardBulletGtrResult robotAwardBulletGtrResult; // 機器人獎勵子彈結果
    private final double appearProbability; // 出現率

    public RobotBasicAndAwardBulletCombination(int basicOdds, RobotAwardBulletGtrResult robotAwardBulletGtrResult, double appearProbability) {
        this.basicOdds = basicOdds;
        this.robotAwardBulletGtrResult = robotAwardBulletGtrResult;
        this.appearProbability = appearProbability;
    }

    // 取得出現並死亡率
    public double getAppearAndKillProbability(double usedRtp) {
        // 1. 計算總倍數
        double totalOdds = MathUtil.add(this.basicOdds, this.robotAwardBulletGtrResult.getExpectOdds());

        // 2. 計算死亡率
        double killProbability = Math.min(MathUtil.divide(usedRtp, totalOdds), 1.0);

        // 3. 回傳
        return MathUtil.multiply(this.appearProbability, killProbability);
    }

    // 取得出現沒死亡率
    public double getAppearAndNoKillProbability(double usedRtp) {
        // 1. 計算總倍數
        double totalOdds = MathUtil.add(this.basicOdds, this.robotAwardBulletGtrResult.getExpectOdds());

        // 2. 計算沒死亡率
        double noKillProbability = MathUtil.subtract(1.0, Math.min(MathUtil.divide(usedRtp, totalOdds), 1.0));

        // 3. 回傳
        return MathUtil.multiply(this.appearProbability, noKillProbability);
    }

    public int getBasicOdds() {
        return basicOdds;
    }

    public RobotAwardBulletGtrResult getRobotAwardBulletGtrResult() {
        return robotAwardBulletGtrResult;
    }

    public double getAppearProbability() {
        return appearProbability;
    }
}

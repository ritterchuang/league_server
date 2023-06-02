package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity;

import org.lsj.utils.MathUtil;

// 機器人特殊事件組合
public class RobotSpecialFeatureCombination {
    private final int specialOdds; // 特殊事件倍數
    private final double basicWin; // 基礎贏分
    private final double appearProbability; // 出現率

    public RobotSpecialFeatureCombination(int specialOdds, double basicWin, double appearProbability) {
        this.specialOdds = specialOdds;
        this.basicWin = basicWin;
        this.appearProbability = appearProbability;
    }

    // 取得出現並觸發率
    public double getAppearAndTriggerProbability(double usedRtp) {
        // 1. 計算觸發率
        double triggerProbability = MathUtil.divide(usedRtp, this.specialOdds);

        // 2. 觸發率防呆
        triggerProbability = (triggerProbability > 1) ? 1 : triggerProbability;

        // 3. 出現並觸發率
        return MathUtil.multiply(this.appearProbability, triggerProbability);
    }

    // 取得出現沒觸發率
    public double getAppearAndNoTriggerProbability(double usedRtp) {
        // 1. 計算沒觸發率
        double noTriggerProbability = MathUtil.subtract(1.0, MathUtil.divide(usedRtp, this.specialOdds));

        // 2. 沒觸發率防呆
        noTriggerProbability = (noTriggerProbability < 0) ? 0 : noTriggerProbability;

        // 3. 出現並沒觸發率
        return MathUtil.multiply(this.appearProbability, noTriggerProbability);
    }

    public int getSpecialOdds() {
        return specialOdds;
    }

    public double getBasicWin() {
        return basicWin;
    }

    public double getAppearProbability() {
        return appearProbability;
    }
}

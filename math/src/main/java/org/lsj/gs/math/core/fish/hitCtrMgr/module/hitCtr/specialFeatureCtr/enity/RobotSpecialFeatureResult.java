package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity;

// 機器人特殊事件結果
public class RobotSpecialFeatureResult {
    private final SpecialFeatureResult specialFeatureResult; // 特殊事件結果
    private final double appearAndTriggerProbability; // 特殊事件出現觸發率

    public RobotSpecialFeatureResult(SpecialFeatureResult specialFeatureResult, double appearAndTriggerProbability) {
        this.specialFeatureResult = specialFeatureResult;
        this.appearAndTriggerProbability = appearAndTriggerProbability;
    }

    public RobotSpecialFeatureResult() {
        this.specialFeatureResult = new SpecialFeatureResult();
        this.appearAndTriggerProbability = 0.0;
    }

    public SpecialFeatureResult getSpecialFeatureResult() {
        return specialFeatureResult;
    }

    public double getAppearAndTriggerProbability() {
        return appearAndTriggerProbability;
    }
}

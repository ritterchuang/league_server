package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureResultExtend.SpecialFeatureResultExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureResultExtend.SpecialFeatureResultExtendInvalid;

// 特殊事件結果
public class SpecialFeatureResult {
    private final boolean killFlag; // 擊殺標誌
    private final double totalWin; // 特殊事件總得分
    private final ConstMathFish.SpecialFeatureEnumType specialFeatureEnumType; // 特殊獎項類型
    private final SpecialFeatureResultExtend specialFeatureResultExtend; // 額外特殊獎項結果

    public SpecialFeatureResult() {
        this.killFlag = false;
        this.totalWin = 0.0;
        this.specialFeatureEnumType = ConstMathFish.SpecialFeatureEnumType.INVALID;
        this.specialFeatureResultExtend = new SpecialFeatureResultExtendInvalid();
    }

    public SpecialFeatureResult(boolean killFlag, double totalWin, ConstMathFish.SpecialFeatureEnumType specialFeatureEnumType, SpecialFeatureResultExtend specialFeatureResultExtend) {
        this.killFlag = killFlag;
        this.totalWin = totalWin;
        this.specialFeatureEnumType = specialFeatureEnumType;
        this.specialFeatureResultExtend = specialFeatureResultExtend;
    }

    public boolean isKillFlag() {
        return killFlag;
    }

    public double getTotalWin() {
        return totalWin;
    }

    public ConstMathFish.SpecialFeatureEnumType getSpecialFeatureType() {
        return specialFeatureEnumType;
    }

    public SpecialFeatureResultExtend getSpecialFeatureResultExtend() {
        return specialFeatureResultExtend;
    }
}

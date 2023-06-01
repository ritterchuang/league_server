package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureCtrConfigExtend;

// 特殊事件設定父類別
public class SpecialFeatureCtrConfigExtend {
    private final double rtpUseRatio; // 使用Rtp比例

    public SpecialFeatureCtrConfigExtend(double rtpUseRatio) {
        this.rtpUseRatio = rtpUseRatio;
    }

    public double getRtpUseRatio() {
        return rtpUseRatio;
    }
}

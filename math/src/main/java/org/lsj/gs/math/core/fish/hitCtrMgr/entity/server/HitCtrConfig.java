package org.lsj.gs.math.core.fish.hitCtrMgr.entity.server;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrConfigExtend.AwardBulletGtrConfigExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.rtpChoiceHlr.enity.RtpChoiceHlrConfigExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureCtrConfigExtend.SpecialFeatureCtrConfigExtend;

// 打擊計算器設定
public class HitCtrConfig {
    private final ConstMathFish.HitType hitType; // 打擊類型
    private final HitTypeConfigExtend hitTypeConfigExtend; // 客製打擊設定
    private final ConstMathFish.SpecialFeatureEnumType specialFeatureEnumType; // 特殊事件類型
    private final SpecialFeatureCtrConfigExtend specialFeatureCtrConfigExtend; // 特殊事件設定
    private final ConstMathFish.AwardBulletType awardBulletType; // 獎勵子彈類型
    private final AwardBulletGtrConfigExtend awardBulletGtrConfigExtend; // 獎勵子彈設定
    private final ConstMathFish.RtpChoiceType rtpChoiceType; // Rtp選擇類型
    private final RtpChoiceHlrConfigExtend rtpChoiceHlrConfigExtend; // Rtp選擇類型額外設定

    public HitCtrConfig(ConstMathFish.HitType hitType, HitTypeConfigExtend hitTypeConfigExtend, ConstMathFish.SpecialFeatureEnumType specialFeatureEnumType, SpecialFeatureCtrConfigExtend specialFeatureCtrConfigExtend, ConstMathFish.AwardBulletType awardBulletType, AwardBulletGtrConfigExtend awardBulletGtrConfigExtend, ConstMathFish.RtpChoiceType rtpChoiceType, RtpChoiceHlrConfigExtend rtpChoiceHlrConfigExtend) {
        this.hitType = hitType;
        this.hitTypeConfigExtend = hitTypeConfigExtend;
        this.specialFeatureEnumType = specialFeatureEnumType;
        this.specialFeatureCtrConfigExtend = specialFeatureCtrConfigExtend;
        this.awardBulletType = awardBulletType;
        this.awardBulletGtrConfigExtend = awardBulletGtrConfigExtend;
        this.rtpChoiceType = rtpChoiceType;
        this.rtpChoiceHlrConfigExtend = rtpChoiceHlrConfigExtend;
    }

    public ConstMathFish.HitType getHitType() {
        return hitType;
    }

    public HitTypeConfigExtend getHitTypeConfigExtend() {
        return hitTypeConfigExtend;
    }

    public ConstMathFish.SpecialFeatureEnumType getSpecialFeatureType() {
        return specialFeatureEnumType;
    }

    public SpecialFeatureCtrConfigExtend getSpecialFeatureCtrConfigExtend() {
        return specialFeatureCtrConfigExtend;
    }

    public ConstMathFish.AwardBulletType getAwardBulletType() {
        return awardBulletType;
    }

    public AwardBulletGtrConfigExtend getAwardBulletGtrConfigExtend() {
        return awardBulletGtrConfigExtend;
    }

    public ConstMathFish.RtpChoiceType getRtpChoiceType() {
        return rtpChoiceType;
    }

    public RtpChoiceHlrConfigExtend getRtpChoiceHlrConfigExtend() {
        return rtpChoiceHlrConfigExtend;
    }
}

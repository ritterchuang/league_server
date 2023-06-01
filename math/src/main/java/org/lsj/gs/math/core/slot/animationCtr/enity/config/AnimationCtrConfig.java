package org.lsj.gs.math.core.slot.animationCtr.enity.config;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 動畫計算器設定
public class AnimationCtrConfig {
    private final ConstMathSlot.AnimationType animationType; // 動畫類型
    private final AnimationConfigExtend configExtend; // 動畫類型額外設定

    public AnimationCtrConfig(AnimationConfig animationConfig) {
        this.animationType = animationConfig.getAnimationType();
        this.configExtend = animationConfig.getConfigExtend();
    }

    public ConstMathSlot.AnimationType getAnimationType() {
        return animationType;
    }

    public AnimationConfigExtend getConfigExtend() {
        return configExtend;
    }
}

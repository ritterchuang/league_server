package org.lsj.gs.math.core.slot.animationCtr.enity.config;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 動畫設定
public class AnimationConfig {
    private final ConstMathSlot.AnimationType animationType; // 動畫類型
    private final AnimationConfigExtend configExtend; // 動畫類型額外設定

    public AnimationConfig(ConstMathSlot.AnimationType animationType, AnimationConfigExtend configExtend) {
        this.animationType = animationType;
        this.configExtend = configExtend;
    }

    public ConstMathSlot.AnimationType getAnimationType() {
        return animationType;
    }

    public AnimationConfigExtend getConfigExtend() {
        return configExtend;
    }
}

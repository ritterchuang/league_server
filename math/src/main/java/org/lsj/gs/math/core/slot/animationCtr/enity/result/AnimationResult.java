package org.lsj.gs.math.core.slot.animationCtr.enity.result;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 倍數動畫結果
public class AnimationResult {
    private final ConstMathSlot.AnimationType animationType; // 動畫表演類型
    private final AnimationResultExtend animationResultExtend; // 客製動畫額外結果

    public AnimationResult(ConstMathSlot.AnimationType animationType, AnimationResultExtend animationResultExtend) {
        this.animationType = animationType;
        this.animationResultExtend = animationResultExtend;
    }

    public ConstMathSlot.AnimationType getAnimationType() {
        return animationType;
    }

    public AnimationResultExtend getAnimationResultExtend() {
        return animationResultExtend;
    }
}

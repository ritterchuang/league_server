package com.lx.gs.math.core.slot.animationCtr.module;

import com.lx.gs.math.core.slot.animationCtr.IAnimationCtr;
import com.lx.gs.math.core.slot.animationCtr.enity.config.AnimationCtrConfig;

// 動畫計算者工廠
public class AnimationCtrFactory {

    // 取得動畫計算者 TODO Default return Invalid
    public IAnimationCtr create(AnimationCtrConfig animationCtrConfig) {
        switch (animationCtrConfig.getAnimationType()) {
            default: return new AnimationCtrOddsAnimation(animationCtrConfig);
        }
    }
}

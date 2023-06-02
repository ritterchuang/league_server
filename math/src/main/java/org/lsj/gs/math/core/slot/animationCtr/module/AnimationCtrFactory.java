package org.lsj.gs.math.core.slot.animationCtr.module;

import org.lsj.gs.math.core.slot.animationCtr.IAnimationCtr;
import org.lsj.gs.math.core.slot.animationCtr.enity.config.AnimationCtrConfig;

// 動畫計算者工廠
public class AnimationCtrFactory {

    // 取得動畫計算者 TODO Default return Invalid
    public IAnimationCtr create(AnimationCtrConfig animationCtrConfig) {
        switch (animationCtrConfig.getAnimationType()) {
            default: return new AnimationCtrOddsAnimation(animationCtrConfig);
        }
    }
}

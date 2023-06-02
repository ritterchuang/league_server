package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletRtpUseTypeHlr.module;

import org.lsj.gs.math.core.fish.ConstMathFish;

// 非法子彈Rtp處理者
public class BulletRtpUseHlrInvalid implements IBulletRtpUseHlr {
    private final ConstMathFish.BulletRtpUseType bulletRtpUseType; // 子彈Rtp設定類型

    public BulletRtpUseHlrInvalid(ConstMathFish.BulletRtpUseType bulletRtpUseType) {
        this.bulletRtpUseType = bulletRtpUseType;
    }

    @Override
    public double getBulletRtp() {
        return 0.0;
    }
}

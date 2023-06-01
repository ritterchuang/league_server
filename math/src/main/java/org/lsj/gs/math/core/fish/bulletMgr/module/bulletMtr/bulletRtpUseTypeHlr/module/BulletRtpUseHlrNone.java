package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletRtpUseTypeHlr.module;

import org.lsj.gs.math.core.fish.ConstMathFish;

// 子彈Rtp處理者不攜帶
public class BulletRtpUseHlrNone implements IBulletRtpUseHlr {
    private final ConstMathFish.BulletRtpUseType bulletRtpUseType; // 子彈Rtp設定類型

    public BulletRtpUseHlrNone(ConstMathFish.BulletRtpUseType bulletRtpUseType) {
        this.bulletRtpUseType = bulletRtpUseType;
    }

    @Override
    public double getBulletRtp() {
        return 0.0;
    }
}

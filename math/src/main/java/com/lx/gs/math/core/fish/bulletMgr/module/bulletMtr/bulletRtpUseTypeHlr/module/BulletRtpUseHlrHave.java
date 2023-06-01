package com.lx.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletRtpUseTypeHlr.module;

import com.lx.gs.math.core.fish.ConstMathFish;
import com.lx.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletRtpUseTypeHlr.enity.sever.BulletRtpUseConfigExtend;
import com.lx.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletRtpUseTypeHlr.enity.sever.BulletRtpUseConfigExtendHave;

// 子彈Rtp處理者攜帶
public class BulletRtpUseHlrHave implements IBulletRtpUseHlr {
    private final ConstMathFish.BulletRtpUseType bulletRtpUseType; // 子彈Rtp設定類型
    private final BulletRtpUseConfigExtendHave config; // 子彈Rtp設定單一

    public BulletRtpUseHlrHave(ConstMathFish.BulletRtpUseType bulletRtpUseType, BulletRtpUseConfigExtend bulletRtpUseConfigExtend) {
        this.bulletRtpUseType = bulletRtpUseType;
        this.config = (BulletRtpUseConfigExtendHave) bulletRtpUseConfigExtend;
    }

    @Override
    public double getBulletRtp() {
        return this.config.getBulletRtp();
    }
}

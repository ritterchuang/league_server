package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletRtpUseTypeHlr.module;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletRtpUseTypeHlr.enity.sever.BulletRtpUseConfigExtend;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletRtpUseTypeHlr.enity.sever.BulletRtpUseConfigExtendHave;

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

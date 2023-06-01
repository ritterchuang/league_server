package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletRtpUseTypeHlr.module;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletRtpUseTypeHlr.enity.sever.BulletRtpUseConfigExtend;

// 子彈Rtp使用處理者工廠
public class BulletRtpUseHlrFactory {

    // 建立 子彈Rtp使用處理
    public IBulletRtpUseHlr createBulletRtpUseHlr(ConstMathFish.BulletRtpUseType bulletRtpUseType, BulletRtpUseConfigExtend bulletRtpUseConfigExtend) {
        switch (bulletRtpUseType) {
            case NONE: return new BulletRtpUseHlrNone(bulletRtpUseType);
            case HAVE: return new BulletRtpUseHlrHave(bulletRtpUseType, bulletRtpUseConfigExtend);
            default: return new BulletRtpUseHlrInvalid(bulletRtpUseType);
        }
    }
}

package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletRtpUseTypeHlr.enity.sever;

// 子彈Rtp使用設定HAVE
public class BulletRtpUseConfigExtendHave extends BulletRtpUseConfigExtend{
    private final double bulletRtp; // 子彈Rtp

    public BulletRtpUseConfigExtendHave(double bulletRtp) {
        this.bulletRtp = bulletRtp;
    }

    public double getBulletRtp() {
        return bulletRtp;
    }
}

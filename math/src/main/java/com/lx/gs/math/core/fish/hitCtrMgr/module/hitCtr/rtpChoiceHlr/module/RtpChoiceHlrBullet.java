package com.lx.gs.math.core.fish.hitCtrMgr.module.hitCtr.rtpChoiceHlr.module;

import com.lx.gs.math.core.common.ConstMathCommon;
import com.lx.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import com.lx.gs.math.core.fish.ConstMathFish;
import com.lx.gs.math.core.fish.bulletMgr.BulletMgr;
import com.lx.gs.math.core.fish.bulletMgr.module.bulletMtr.BulletMtr;
import com.lx.gs.math.core.fish.hitCtrMgr.module.hitCtr.rtpChoiceHlr.enity.RtpChoiceHlrConfigExtend;
import com.lx.gs.math.core.fish.hitCtrMgr.module.hitCtr.rtpChoiceHlr.enity.RtpChoiceHlrConfigExtendBullet;

// Rtp選擇處理者子彈
public class RtpChoiceHlrBullet implements IRtpChoiceHlr {
    private final ConstMathFish.RtpChoiceType rtpChoiceType; // Rtp選擇類型
    private final RtpChoiceHlrConfigExtendBullet config; // Rtp額外設定子彈

    public RtpChoiceHlrBullet(ConstMathFish.RtpChoiceType rtpChoiceType, RtpChoiceHlrConfigExtend rtpChoiceHlrConfigExtend) {
        this.rtpChoiceType = rtpChoiceType;
        this.config = (RtpChoiceHlrConfigExtendBullet) rtpChoiceHlrConfigExtend;
    }

    // 計算真人此次使用的Rtp
    @Override
    public double calculateUsedRtp(int bulletIndex, BulletMgr bulletMgr, ConstMathCommon.ShuffleType shuffleType, GameAdjustParameter gameAdjustParameter, double humanRtp) {
        // 1. 取得子彈中介者
        BulletMtr bulletMtr = bulletMgr.getBulletMtr(bulletIndex);

        // 2. 取得Rtp
        return bulletMtr.getBulletRtp();
    }

    // 計算機器人使用Rtp
    @Override
    public double calculateRobotUsedRtp(int bulletIndex, BulletMgr bulletMgr, double robotRtp) {
        // 1. 取得子彈中介者
        BulletMtr bulletMtr = bulletMgr.getBulletMtr(bulletIndex);

        // 2. 取得Rtp
        return bulletMtr.getBulletRtp();
    }
}

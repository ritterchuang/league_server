package com.lx.gs.math.core.fish.hitCtrMgr.module.hitCtr.rtpChoiceHlr.module;

import com.lx.gs.math.core.common.ConstMathCommon;
import com.lx.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import com.lx.gs.math.core.fish.ConstMathFish;
import com.lx.gs.math.core.fish.bulletMgr.BulletMgr;
import com.lx.gs.math.core.fish.hitCtrMgr.module.hitCtr.rtpChoiceHlr.enity.RtpChoiceHlrConfigExtend;

// 非法Rtp選擇處理者
public class RtpChoiceHlrInvalid implements IRtpChoiceHlr {
    private final ConstMathFish.RtpChoiceType rtpChoiceType; // Rtp選擇類型

    public RtpChoiceHlrInvalid(ConstMathFish.RtpChoiceType rtpChoiceType, RtpChoiceHlrConfigExtend rtpChoiceHlrConfigExtend) {
        this.rtpChoiceType = rtpChoiceType;
    }

    // 計算此次使用的Rtp
    @Override
    public double calculateUsedRtp(int bulletIndex, BulletMgr bulletMgr, ConstMathCommon.ShuffleType shuffleType, GameAdjustParameter gameAdjustParameter, double humanRtp) {
        return 0;
    }

    // 計算機器人使用Rtp
    @Override
    public double calculateRobotUsedRtp(int bulletIndex, BulletMgr bulletMgr, double robotRtp) {
        return 0;
    }
}

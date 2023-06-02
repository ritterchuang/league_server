package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.rtpChoiceHlr.module;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.rtpChoiceHlr.enity.RtpChoiceHlrConfigExtend;

// Rtp選擇處理者工廠
public class RtpChoiceHlrFactory {

    // 創建Rtp選擇者
    public IRtpChoiceHlr createRtpChoiceHlr(ConstMathFish.RtpChoiceType rtpChoiceType, RtpChoiceHlrConfigExtend rtpChoiceHlrConfigExtend) {
        switch (rtpChoiceType){
            case BULLET: return new RtpChoiceHlrBullet(rtpChoiceType, rtpChoiceHlrConfigExtend);
            case COMPANY_ADJUST: return new RtpChoiceHlrCompanyAdjust(rtpChoiceType, rtpChoiceHlrConfigExtend);
            default: return new RtpChoiceHlrInvalid(rtpChoiceType, rtpChoiceHlrConfigExtend);
        }
    }
}

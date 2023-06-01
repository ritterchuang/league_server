package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.rtpChoiceHlr.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.rtpChoiceHlr.enity.RtpChoiceHlrConfigExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.rtpChoiceHlr.enity.RtpChoiceHlrConfigExtendCompanyAdjust;
import org.lsj.utils.MathUtil;

// Rtp選擇處理者公司
public class RtpChoiceHlrCompanyAdjust implements IRtpChoiceHlr {
    private final ConstMathFish.RtpChoiceType rtpChoiceType; // Rtp選擇類型
    private final RtpChoiceHlrConfigExtendCompanyAdjust config; // Rtp額外設定公司

    public RtpChoiceHlrCompanyAdjust(ConstMathFish.RtpChoiceType rtpChoiceType, RtpChoiceHlrConfigExtend rtpChoiceHlrConfigExtend) {
        this.rtpChoiceType = rtpChoiceType;
        this.config = (RtpChoiceHlrConfigExtendCompanyAdjust) rtpChoiceHlrConfigExtend;
    }

    // 計算此次使用的Rtp
    @Override
    public double calculateUsedRtp(int bulletIndex, BulletMgr bulletMgr, ConstMathCommon.ShuffleType shuffleType, GameAdjustParameter gameAdjustParameter, double humanRtp) {
        switch (shuffleType) {
            case NATURE: return humanRtp;
            case PSEUDO_NATURE: return MathUtil.subtract(1.0, gameAdjustParameter.getAdjustParameter().getCompanyProfitRateMean());
            default: return 0.0;
        }
    }

    // 計算機器人使用Rtp
    @Override
    public double calculateRobotUsedRtp(int bulletIndex, BulletMgr bulletMgr, double robotRtp) {
        return robotRtp;
    }
}

package org.lsj.gs.math.core.slot.reelRtpStateHlr;

import org.lsj.gs.math.core.AbstractModule;
import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity.GameAdjustParameter;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.utils.MathUtil;

// 高低表處理者
public class ReelRtpStateHlr extends AbstractModule {
    private final ReelRtpStateHlrConfig config; // 設定檔
    private final double PROFIT_RATE_RADIUS; // 利潤率半徑
    private final double humanRtp; // 真人返獎率

    public ReelRtpStateHlr(ReelRtpStateHlrConfig config, GamePlayerHlr gamePlayerHlr, PoolCtr poolCtr, ITableUtil tableUtil) {
        super(gamePlayerHlr, poolCtr, tableUtil);
        this.PROFIT_RATE_RADIUS = 0.005;
        this.humanRtp = MathUtil.subtract(
                1.0,
                MathUtil.add(super.poolCtr.getPoolConfig().getAgencyPool().getFeeRate(), this.PROFIT_RATE_RADIUS)
        );
        this.config = config;
    }

    // 計算高低表
    public ConstMathSlot.ReelRtpType calculateReelRtpType(ConstMathCommon.ShuffleType shuffleType, GameAdjustParameter gameAdjustParameter) {
        // 1. 計算此次使用 RTP
        double usedRtp = this.calculateUsedRtp(shuffleType, gameAdjustParameter);

        // 2. 不在設定範圍內，直接用低表
        if (usedRtp > this.config.getReelRtpTypeToDoubleMap().get(ConstMathSlot.ReelRtpType.HIGH)
        ||  usedRtp < this.config.getReelRtpTypeToDoubleMap().get(ConstMathSlot.ReelRtpType.LOW)) {
            return ConstMathSlot.ReelRtpType.LOW;
        }

        // 3. 計算觸發高表機率
        double highReelProbability = this.calculateHighReelProbability(usedRtp);

        // 4. 判斷是否觸發高表
        boolean isHighReel = this.tableUtil.getMainRandomUtil().isHitWithAccuracy(highReelProbability, ConstMathCommon.AccuracyType.MILLION);

        // 5. 回傳
        return isHighReel ? ConstMathSlot.ReelRtpType.HIGH : ConstMathSlot.ReelRtpType.LOW;
    }

    // 依照 shuffleType 決定使用 RTP
    private double calculateUsedRtp(ConstMathCommon.ShuffleType shuffleType, GameAdjustParameter gameAdjustParameter) {
        switch (shuffleType) {
            case NATURE: return this.humanRtp;
            case PSEUDO_NATURE: return MathUtil.subtract(1.0, gameAdjustParameter.getAdjustParameter().getCompanyProfitRateMean());
            default: return 0.0;
        }
    }

    // 計算高表觸發率 [ prob = (expectRtp - p1) / (p2 - p1)]
    private double calculateHighReelProbability(double expectRtp) {
        // 1. 取得 p1, p2
        double p1 = this.config.getReelRtpTypeToDoubleMap().get(ConstMathSlot.ReelRtpType.LOW);
        double p2 = this.config.getReelRtpTypeToDoubleMap().get(ConstMathSlot.ReelRtpType.HIGH);

        // 2. 分子
        double numerator = MathUtil.subtract(expectRtp, p1);

        // 3. 分母
        double denominator = MathUtil.subtract(p2, p1);

        // 4. 分母為0，回傳0
        if (denominator == 0) {
            return 0.0;
        }

        // 5. 回傳
        return MathUtil.divide(numerator, denominator);
    }

    @Override
    public void reset() {
    }
}

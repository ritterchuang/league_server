package org.lsj.gs.math.core.common.gameAdjust.module.gameAdjustParameterCtr.entity;

import org.lsj.utils.MathUtil;

// 調控參數
public class AdjustParameter implements IAdjustParameter{
    private final double PROFIT_RATE_RADIUS = 0.005; // 利潤率半徑
    private final double companyProfitRateMean; // 公司利潤率
    private final double companyProfitRateTop; // 公司利潤率頂標
    private final double companyProfitRateBottom; // 公司利潤率底標

    public AdjustParameter(double feeRate) {
        this.companyProfitRateMean = MathUtil.add(feeRate, this.PROFIT_RATE_RADIUS);
        this.companyProfitRateTop = MathUtil.add(feeRate, MathUtil.multiply(2.0, this.PROFIT_RATE_RADIUS));
        this.companyProfitRateBottom = feeRate;
    }

    @Override
    public double getProfitRateRadius() {
        return PROFIT_RATE_RADIUS;
    }

    @Override
    public double getCompanyProfitRateMean() {
        return companyProfitRateMean;
    }

    @Override
    public double getCompanyProfitRateTop() {
        return companyProfitRateTop;
    }

    @Override
    public double getCompanyProfitRateBottom() {
        return companyProfitRateBottom;
    }
}

package org.lsj.gs.math.core.common.poolCtr.module;

import org.lsj.gs.pool.AgencyPool;
import org.lsj.utils.MathUtil;

// 代理水池計算器
public class AgencyPoolCtr {
    private AgencyPool agencyPool; // 代理水池

    public AgencyPoolCtr(AgencyPool agencyPool) {
        this.agencyPool = agencyPool;
    }

    // 更新代理水池
    public void updateAgencyPool(AgencyPool agencyPool) {
        this.agencyPool = agencyPool;
    }

    // 計算當前公司利潤率
    public double calculateCurrentCompanyProfitRate(double humanValidBet, double humanScore){
        // 1. 計算參數
        double numerator = MathUtil.add(humanScore, this.agencyPool.getTotalScore());
        double denominator = MathUtil.add(humanValidBet, this.agencyPool.getTotalBet());

        // 2. 分母零防呆
        if(denominator == 0.0){ return 0.0;}

        return MathUtil.multiply(
                MathUtil.divide(numerator, denominator),
                -1.0
        );
    }

    public double getFeeRate() {
        return this.agencyPool.getFeeRate();
    }

    public double getFeeRateTop() {
        return this.agencyPool.getFeeRateTop();
    }
}

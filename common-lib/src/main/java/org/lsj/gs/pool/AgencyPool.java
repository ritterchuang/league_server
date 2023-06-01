package org.lsj.gs.pool;

import org.lsj.ConstCommon;
import org.lsj.utils.MathUtil;

// 代理水池
public class AgencyPool {
    private double totalBet; // 總押碼
    private double totalScore; // 總淨利
    private double feeRate; // 手續費率底標
    private int companyId; // 公司識別碼
    private int baseAgencyId; // 基底代理識別碼
    private int useAgencyId; // 玩家代理識別碼
    private boolean isPoolExists; // 水池存在標誌

    private final double FEE_RATE_RADIUS = 0.005; // 手續率半徑
    private double feeRateMean; // 手續費率均值
    private double feeRateTop; // 手續費率頂標

    public AgencyPool() {
        this.totalBet = 0.0;
        this.totalScore = 0.0;
        this.feeRate = ConstCommon.DEFAULT_COMPANY_PROFIT_RATE;
        this.companyId = 0;
        this.baseAgencyId = 0;
        this.useAgencyId = 0;
        this.isPoolExists = false;

        this.feeRateMean = MathUtil.add(ConstCommon.DEFAULT_COMPANY_PROFIT_RATE, this.FEE_RATE_RADIUS);
        this.feeRateTop = MathUtil.add(ConstCommon.DEFAULT_COMPANY_PROFIT_RATE, MathUtil.multiply(2.0, this.FEE_RATE_RADIUS));
    }

    public AgencyPool(double totalBet, double totalScore, double feeRate, int companyId, int baseAgencyId, int useAgencyId, boolean isPoolExists) {
        this.totalBet = totalBet;
        this.totalScore = totalScore;
        this.feeRate = feeRate;
        this.companyId = companyId;
        this.baseAgencyId = baseAgencyId;
        this.useAgencyId = useAgencyId;
        this.isPoolExists = isPoolExists;

        this.feeRateMean = MathUtil.add(feeRate, this.FEE_RATE_RADIUS);
        this.feeRateTop = MathUtil.add(feeRate, MathUtil.multiply(2.0, this.FEE_RATE_RADIUS));
    }

    // 檢驗成員正確性
    public AgencyPool checkConstructor(AgencyPool agencyPool) {
        if (agencyPool == null) {
            return this;
        }
        this.totalBet = Math.max(agencyPool.totalBet, 0.0);
        this.totalScore = agencyPool.getTotalScore();
        this.feeRate = agencyPool.getFeeRate();
        this.companyId = agencyPool.getCompanyId();
        this.baseAgencyId = agencyPool.getBaseAgencyId();
        this.useAgencyId = agencyPool.getUseAgencyId();
        this.isPoolExists = agencyPool.isPoolExists();

        this.feeRateMean = MathUtil.add(agencyPool.getFeeRate(), this.FEE_RATE_RADIUS);
        this.feeRateTop = MathUtil.add(agencyPool.getFeeRate(), MathUtil.multiply(2.0, this.FEE_RATE_RADIUS));
        return this;
    }

    public double getTotalBet() {
        return totalBet;
    }

    public void setTotalBet(double totalBet) {
        this.totalBet = totalBet;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public double getFeeRate() {
        return feeRate;
    }

    public int getCompanyId() {
        return companyId;
    }

    public int getBaseAgencyId() {
        return baseAgencyId;
    }

    public int getUseAgencyId() {
        return useAgencyId;
    }

    public boolean isPoolExists() {
        return isPoolExists;
    }

    public double getFeeRateMean() {
        return feeRateMean;
    }

    public double getFeeRateTop() {
        return feeRateTop;
    }
}

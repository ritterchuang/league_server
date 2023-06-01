package org.lsj.db;

public class NumericalConfigObjBuilder {
    private long lastUpdateTime;
    private double vieBankerExecRate;
    private int adjustType;
    private String lastUpdateAccount;
    private int status;
    private double videoGameExecRate;
    private double catchFishExecRate;
    private double hunderedExecRate;
    private double pvpExecRate;
    private String name;
    private int id;

    public NumericalConfigObjBuilder setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
        return this;
    }

    public NumericalConfigObjBuilder setVieBankerExecRate(double vieBankerExecRate) {
        this.vieBankerExecRate = vieBankerExecRate;
        return this;
    }

    public NumericalConfigObjBuilder setAdjustType(int adjustType) {
        this.adjustType = adjustType;
        return this;
    }

    public NumericalConfigObjBuilder setLastUpdateAccount(String lastUpdateAccount) {
        this.lastUpdateAccount = lastUpdateAccount;
        return this;
    }

    public NumericalConfigObjBuilder setStatus(int status) {
        this.status = status;
        return this;
    }

    public NumericalConfigObjBuilder setVideoGameExecRate(double videoGameExecRate) {
        this.videoGameExecRate = videoGameExecRate;
        return this;
    }

    public NumericalConfigObjBuilder setCatchFishExecRate(double catchFishExecRate) {
        this.catchFishExecRate = catchFishExecRate;
        return this;
    }

    public NumericalConfigObjBuilder setHunderedExecRate(double hunderedExecRate) {
        this.hunderedExecRate = hunderedExecRate;
        return this;
    }

    public NumericalConfigObjBuilder setPvpExecRate(double pvpExecRate) {
        this.pvpExecRate = pvpExecRate;
        return this;
    }

    public NumericalConfigObjBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public NumericalConfigObjBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public NumericalConfigObj createNumericalConfigObj() {
        return new NumericalConfigObj(lastUpdateTime, vieBankerExecRate, adjustType, lastUpdateAccount, status, videoGameExecRate, catchFishExecRate, hunderedExecRate, pvpExecRate, name, id);
    }
}

package org.lsj.db;

public class CompanyNewPersonControlObjBuilder {
    private String selectedStrategy;
    private int priorityLevel;
    private String controlStrategyJson;
    private long lastUpdateTime;
    private int drainWinRoundEnd;
    private int drainWinRoundStart;
    private int cardStandard;
    private double controlEndLine;
    private double playerWinRate;
    private String remark;
    private int status;
    private int maxExecTimes;
    private String numericalJson;
    private String gameIds;
    private long createTime;
    private double controlCheckTime;
    private int killWinRoundEnd;
    private int killWinRoundStart;
    private double killDrainRate;
    private double qzWinRateJc;
    private double pkWinRateJc;
    private String controlStrategy;
    private String description;
    private String name;
    private int companyId;
    private int id;

    public CompanyNewPersonControlObjBuilder setSelectedStrategy(String selectedStrategy) {
        this.selectedStrategy = selectedStrategy;
        return this;
    }

    public CompanyNewPersonControlObjBuilder setPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
        return this;
    }

    public CompanyNewPersonControlObjBuilder setControlStrategyJson(String controlStrategyJson) {
        this.controlStrategyJson = controlStrategyJson;
        return this;
    }

    public CompanyNewPersonControlObjBuilder setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
        return this;
    }

    public CompanyNewPersonControlObjBuilder setDrainWinRoundEnd(int drainWinRoundEnd) {
        this.drainWinRoundEnd = drainWinRoundEnd;
        return this;
    }

    public CompanyNewPersonControlObjBuilder setDrainWinRoundStart(int drainWinRoundStart) {
        this.drainWinRoundStart = drainWinRoundStart;
        return this;
    }

    public CompanyNewPersonControlObjBuilder setCardStandard(int cardStandard) {
        this.cardStandard = cardStandard;
        return this;
    }

    public CompanyNewPersonControlObjBuilder setControlEndLine(double controlEndLine) {
        this.controlEndLine = controlEndLine;
        return this;
    }

    public CompanyNewPersonControlObjBuilder setPlayerWinRate(double playerWinRate) {
        this.playerWinRate = playerWinRate;
        return this;
    }

    public CompanyNewPersonControlObjBuilder setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public CompanyNewPersonControlObjBuilder setStatus(int status) {
        this.status = status;
        return this;
    }

    public CompanyNewPersonControlObjBuilder setMaxExecTimes(int maxExecTimes) {
        this.maxExecTimes = maxExecTimes;
        return this;
    }

    public CompanyNewPersonControlObjBuilder setNumericalJson(String numericalJson) {
        this.numericalJson = numericalJson;
        return this;
    }

    public CompanyNewPersonControlObjBuilder setGameIds(String gameIds) {
        this.gameIds = gameIds;
        return this;
    }

    public CompanyNewPersonControlObjBuilder setCreateTime(long createTime) {
        this.createTime = createTime;
        return this;
    }

    public CompanyNewPersonControlObjBuilder setControlCheckTime(double controlCheckTime) {
        this.controlCheckTime = controlCheckTime;
        return this;
    }

    public CompanyNewPersonControlObjBuilder setKillWinRoundEnd(int killWinRoundEnd) {
        this.killWinRoundEnd = killWinRoundEnd;
        return this;
    }

    public CompanyNewPersonControlObjBuilder setKillWinRoundStart(int killWinRoundStart) {
        this.killWinRoundStart = killWinRoundStart;
        return this;
    }

    public CompanyNewPersonControlObjBuilder setKillDrainRate(double killDrainRate) {
        this.killDrainRate = killDrainRate;
        return this;
    }

    public CompanyNewPersonControlObjBuilder setQzWinRateJc(double qzWinRateJc) {
        this.qzWinRateJc = qzWinRateJc;
        return this;
    }

    public CompanyNewPersonControlObjBuilder setPkWinRateJc(double pkWinRateJc) {
        this.pkWinRateJc = pkWinRateJc;
        return this;
    }

    public CompanyNewPersonControlObjBuilder setControlStrategy(String controlStrategy) {
        this.controlStrategy = controlStrategy;
        return this;
    }

    public CompanyNewPersonControlObjBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public CompanyNewPersonControlObjBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CompanyNewPersonControlObjBuilder setCompanyId(int companyId) {
        this.companyId = companyId;
        return this;
    }

    public CompanyNewPersonControlObjBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public CompanyNewPersonControlObj createCompanyNewPersonControlObj() {
        return new CompanyNewPersonControlObj(selectedStrategy, priorityLevel, controlStrategyJson, lastUpdateTime, drainWinRoundEnd, drainWinRoundStart, cardStandard, controlEndLine, playerWinRate, remark, status, maxExecTimes, numericalJson, gameIds, createTime, controlCheckTime, killWinRoundEnd, killWinRoundStart, killDrainRate, qzWinRateJc, pkWinRateJc, controlStrategy, description, name, companyId, id);
    }
}

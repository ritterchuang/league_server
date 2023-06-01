package org.lsj.db;

public class CompanyFieldObjBuilder {
    private int id;
    private int companyId;
    private int gameId;
    private String gameNameCn;
    private int fieldId;
    private String fieldName;
    private String fieldNameCn;
    private int state;
    private short invisible;
    private double limitMin;
    private double limitMax;
    private double limitKick;
    private double base;
    private double waterlineDrain;
    private double waterlineKill;
    private double threshold;
    private double optimizationRate;
    private double maxLoseRate;
    private int adjustLevel;
    private double waterlimitLower;
    private double waterlimitUpper;
    private String adjustConfig;
    private String cfgAi;
    private long updateTime;
    private double platformLost;
    private double platformLostFix;
    private short loseRound1;
    private short loseRound2;
    private double maxTake;
    private double gameRate;
    private short play;
    private short feeType;
    private String cardLibJson;
    private String extendJson;

    public CompanyFieldObjBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public CompanyFieldObjBuilder setCompanyId(int companyId) {
        this.companyId = companyId;
        return this;
    }

    public CompanyFieldObjBuilder setGameId(int gameId) {
        this.gameId = gameId;
        return this;
    }

    public CompanyFieldObjBuilder setGameNameCn(String gameNameCn) {
        this.gameNameCn = gameNameCn;
        return this;
    }

    public CompanyFieldObjBuilder setFieldId(int fieldId) {
        this.fieldId = fieldId;
        return this;
    }

    public CompanyFieldObjBuilder setFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public CompanyFieldObjBuilder setFieldNameCn(String fieldNameCn) {
        this.fieldNameCn = fieldNameCn;
        return this;
    }

    public CompanyFieldObjBuilder setState(int state) {
        this.state = state;
        return this;
    }

    public CompanyFieldObjBuilder setInvisible(short invisible) {
        this.invisible = invisible;
        return this;
    }

    public CompanyFieldObjBuilder setLimitMin(double limitMin) {
        this.limitMin = limitMin;
        return this;
    }

    public CompanyFieldObjBuilder setLimitMax(double limitMax) {
        this.limitMax = limitMax;
        return this;
    }

    public CompanyFieldObjBuilder setLimitKick(double limitKick) {
        this.limitKick = limitKick;
        return this;
    }

    public CompanyFieldObjBuilder setBase(double base) {
        this.base = base;
        return this;
    }

    public CompanyFieldObjBuilder setWaterlineDrain(double waterlineDrain) {
        this.waterlineDrain = waterlineDrain;
        return this;
    }

    public CompanyFieldObjBuilder setWaterlineKill(double waterlineKill) {
        this.waterlineKill = waterlineKill;
        return this;
    }

    public CompanyFieldObjBuilder setThreshold(double threshold) {
        this.threshold = threshold;
        return this;
    }

    public CompanyFieldObjBuilder setOptimizationRate(double optimizationRate) {
        this.optimizationRate = optimizationRate;
        return this;
    }

    public CompanyFieldObjBuilder setMaxLoseRate(double maxLoseRate) {
        this.maxLoseRate = maxLoseRate;
        return this;
    }

    public CompanyFieldObjBuilder setAdjustLevel(int adjustLevel) {
        this.adjustLevel = adjustLevel;
        return this;
    }

    public CompanyFieldObjBuilder setWaterlimitLower(double waterlimitLower) {
        this.waterlimitLower = waterlimitLower;
        return this;
    }

    public CompanyFieldObjBuilder setWaterlimitUpper(double waterlimitUpper) {
        this.waterlimitUpper = waterlimitUpper;
        return this;
    }

    public CompanyFieldObjBuilder setAdjustConfig(String adjustConfig) {
        this.adjustConfig = adjustConfig;
        return this;
    }

    public CompanyFieldObjBuilder setCfgAi(String cfgAi) {
        this.cfgAi = cfgAi;
        return this;
    }

    public CompanyFieldObjBuilder setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public CompanyFieldObjBuilder setPlatformLost(double platformLost) {
        this.platformLost = platformLost;
        return this;
    }

    public CompanyFieldObjBuilder setPlatformLostFix(double platformLostFix) {
        this.platformLostFix = platformLostFix;
        return this;
    }

    public CompanyFieldObjBuilder setLoseRound1(short loseRound1) {
        this.loseRound1 = loseRound1;
        return this;
    }

    public CompanyFieldObjBuilder setLoseRound2(short loseRound2) {
        this.loseRound2 = loseRound2;
        return this;
    }

    public CompanyFieldObjBuilder setMaxTake(double maxTake) {
        this.maxTake = maxTake;
        return this;
    }

    public CompanyFieldObjBuilder setGameRate(double gameRate) {
        this.gameRate = gameRate;
        return this;
    }

    public CompanyFieldObjBuilder setPlay(short play) {
        this.play = play;
        return this;
    }

    public CompanyFieldObjBuilder setFeeType(short feeType) {
        this.feeType = feeType;
        return this;
    }

    public CompanyFieldObjBuilder setCardLibJson(String cardLibJson) {
        this.cardLibJson = cardLibJson;
        return this;
    }

    public CompanyFieldObjBuilder setExtendJson(String extendJson) {
        this.extendJson = extendJson;
        return this;
    }

    public CompanyFieldObj createCompanyFieldObj() {
        return new CompanyFieldObj(id, companyId, gameId, gameNameCn, fieldId, fieldName, fieldNameCn, state, invisible, limitMin, limitMax, limitKick, base, waterlineDrain, waterlineKill, threshold, optimizationRate, maxLoseRate, adjustLevel, waterlimitLower, waterlimitUpper, adjustConfig, cfgAi, updateTime, platformLost, platformLostFix, loseRound1, loseRound2, maxTake, gameRate, play, feeType, cardLibJson, extendJson);
    }
}

package org.lsj.db;

public class CompanyFieldObj {
    private int id; // 流水號索引
    private int companyId; // 公司識別碼
    private int gameId; // 遊戲識別碼
    private String gameNameCn; // 遊戲中文名稱
    private int fieldId; // 房間識別碼
    private String fieldName; // 房間英文名稱
    private String fieldNameCn; // 房間中文名稱
    private int state; // 房間狀態; 0: 關閉不踢出; 1: 開啟; 2: 關閉並踢出
    private short invisible; // 是否隱藏; 0: 不隱藏; 1: 隱藏
    private double limitMin; // 房間最低進入門檻
    private double limitMax; // 房間最高進入門檻
    private double limitKick; // 房間踢人門檻
    private double base; // 遊戲底分
    private double waterlineDrain; // 調控放水線(確定棄用)
    private double waterlineKill; // 調控殺線(確定棄用)
    private double threshold; // 調控門檻值(確定棄用)
    private double optimizationRate; // 調控優化率(確定棄用)
    private double maxLoseRate; // 調控最大賠率(確定棄用)
    private int adjustLevel; // 調控級別(確定棄用)
    private double waterlimitLower; // 調控儲水下限(確定棄用)
    private double waterlimitUpper; // 調控儲水上限(確定棄用)
    private String adjustConfig; // 調控配置(確定棄用)
    private String cfgAi; // 機器人配置; JSON格式;
    private long updateTime; // 更新時間
    private double platformLost; // 調控平台允許虧損值(確定棄用)
    private double platformLostFix; // 調控平台允許虧損值(確定棄用)
    private short loseRound1; // 連輸局數下限(確定棄用)
    private short loseRound2; // 連輸局數上限(確定棄用)
    private double maxTake; // 最大攜帶金額; 鎖分金額;
    private double gameRate; // 手續費率(百分比)
    private short play; // 玩法類型
    private short feeType; // 手續費類型
    private String cardLibJson; // 牌庫配置
    private String extendJson; // 客製配置

    // 原始建構子提供JSON解析用
    public CompanyFieldObj() {}

    public CompanyFieldObj(int id, int companyId, int gameId, String gameNameCn, int fieldId, String fieldName, String fieldNameCn, int state, short invisible, double limitMin, double limitMax, double limitKick, double base, double waterlineDrain, double waterlineKill, double threshold, double optimizationRate, double maxLoseRate, int adjustLevel, double waterlimitLower, double waterlimitUpper, String adjustConfig, String cfgAi, long updateTime, double platformLost, double platformLostFix, short loseRound1, short loseRound2, double maxTake, double gameRate, short play, short feeType, String cardLibJson, String extendJson) {
        this.id = id;
        this.companyId = companyId;
        this.gameId = gameId;
        this.gameNameCn = gameNameCn;
        this.fieldId = fieldId;
        this.fieldName = fieldName;
        this.fieldNameCn = fieldNameCn;
        this.state = state;
        this.invisible = invisible;
        this.limitMin = limitMin;
        this.limitMax = limitMax;
        this.limitKick = limitKick;
        this.base = base;
        this.waterlineDrain = waterlineDrain;
        this.waterlineKill = waterlineKill;
        this.threshold = threshold;
        this.optimizationRate = optimizationRate;
        this.maxLoseRate = maxLoseRate;
        this.adjustLevel = adjustLevel;
        this.waterlimitLower = waterlimitLower;
        this.waterlimitUpper = waterlimitUpper;
        this.adjustConfig = adjustConfig;
        this.cfgAi = cfgAi;
        this.updateTime = updateTime;
        this.platformLost = platformLost;
        this.platformLostFix = platformLostFix;
        this.loseRound1 = loseRound1;
        this.loseRound2 = loseRound2;
        this.maxTake = maxTake;
        this.gameRate = gameRate;
        this.play = play;
        this.feeType = feeType;
        this.cardLibJson = cardLibJson;
        this.extendJson = extendJson;
    }

    public int getId() {
        return id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public int getGameId() {
        return gameId;
    }

    public String getGameNameCn() {
        return gameNameCn;
    }

    public int getFieldId() {
        return fieldId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldNameCn() {
        return fieldNameCn;
    }

    public int getState() {
        return state;
    }

    public short getInvisible() {
        return invisible;
    }

    public double getLimitMin() {
        return limitMin;
    }

    public double getLimitMax() {
        return limitMax;
    }

    public double getLimitKick() {
        return limitKick;
    }

    public double getBase() {
        return base;
    }

    public double getWaterlineDrain() {
        return waterlineDrain;
    }

    public double getWaterlineKill() {
        return waterlineKill;
    }

    public double getThreshold() {
        return threshold;
    }

    public double getOptimizationRate() {
        return optimizationRate;
    }

    public double getMaxLoseRate() {
        return maxLoseRate;
    }

    public int getAdjustLevel() {
        return adjustLevel;
    }

    public double getWaterlimitLower() {
        return waterlimitLower;
    }

    public double getWaterlimitUpper() {
        return waterlimitUpper;
    }

    public String getAdjustConfig() {
        return adjustConfig;
    }

    public String getCfgAi() {
        return cfgAi;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public double getPlatformLost() {
        return platformLost;
    }

    public double getPlatformLostFix() {
        return platformLostFix;
    }

    public short getLoseRound1() {
        return loseRound1;
    }

    public short getLoseRound2() {
        return loseRound2;
    }

    public double getMaxTake() {
        return maxTake;
    }

    public double getGameRate() {
        return gameRate;
    }

    public short getPlay() {
        return play;
    }

    public short getFeeType() {
        return feeType;
    }

    public String getCardLibJson() {
        return cardLibJson;
    }

    public String getExtendJson() {
        return extendJson;
    }

    public void setLimitMin(double limitMin) {
        this.limitMin = limitMin;
    }

    public void setLimitMax(double limitMax) {
        this.limitMax = limitMax;
    }

    public void setLimitKick(double limitKick) {
        this.limitKick = limitKick;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public void setMaxTake(double maxTake) {
        this.maxTake = maxTake;
    }

    public void setExtendJson(String extendJson) {
        this.extendJson = extendJson;
    }
}

package org.lsj.db;

// 公司個人控設定
public class CompanyNewPersonControlObj {

    private String selectedStrategy; // 已選擇策略(無用)
    private int priorityLevel; // 個人控優先度(大則優先)
    private String controlStrategyJson; // 個人控策略JSON格式
    private long lastUpdateTime; // 更新時間
    private int drainWinRoundEnd; // 舊調控放分連贏右區間(無用)
    private int drainWinRoundStart; // 舊調控放分連贏左區間(無用)
    private int cardStandard; // 舊調控做牌標準(無用)
    private double controlEndLine; // 舊調控停止線(無用)
    private double playerWinRate; // 舊調控玩家勝率受控機率(無用)
    private String remark; // 風控標籤
    private int status; // 個人控狀態; 0: 關閉; 1: 開啟;
    private int maxExecTimes; // 最大執行次數
    private String numericalJson; // 數控設定JSON格式
    private String gameIds; // 舊調控遊戲索引列表字串(無用)
    private long createTime; // 創建時間
    private double controlCheckTime; // 舊調控輪詢時間(無用)
    private int killWinRoundEnd; // 舊調控殺分連贏右區間(無用)
    private int killWinRoundStart; // 舊調控殺分連贏左區間(無用)
    private double killDrainRate; // 舊調控殺放做牌機率(無用)
    private double qzWinRateJc; // 舊調控搶莊類玩家加持勝率(無用)
    private double pkWinRateJc; // 舊調控對戰類玩家加持勝率(無用)
    private String controlStrategy; // 舊調控策略(無用)
    private String description; // 描述
    private String name; // 個人控名稱
    private int companyId; // 公司索引
    private int id; // 公司個人控設定索引

    public CompanyNewPersonControlObj(String selectedStrategy, int priorityLevel, String controlStrategyJson, long lastUpdateTime, int drainWinRoundEnd, int drainWinRoundStart, int cardStandard, double controlEndLine, double playerWinRate, String remark, int status, int maxExecTimes, String numericalJson, String gameIds, long createTime, double controlCheckTime, int killWinRoundEnd, int killWinRoundStart, double killDrainRate, double qzWinRateJc, double pkWinRateJc, String controlStrategy, String description, String name, int companyId, int id) {
        this.selectedStrategy = selectedStrategy;
        this.priorityLevel = priorityLevel;
        this.controlStrategyJson = controlStrategyJson;
        this.lastUpdateTime = lastUpdateTime;
        this.drainWinRoundEnd = drainWinRoundEnd;
        this.drainWinRoundStart = drainWinRoundStart;
        this.cardStandard = cardStandard;
        this.controlEndLine = controlEndLine;
        this.playerWinRate = playerWinRate;
        this.remark = remark;
        this.status = status;
        this.maxExecTimes = maxExecTimes;
        this.numericalJson = numericalJson;
        this.gameIds = gameIds;
        this.createTime = createTime;
        this.controlCheckTime = controlCheckTime;
        this.killWinRoundEnd = killWinRoundEnd;
        this.killWinRoundStart = killWinRoundStart;
        this.killDrainRate = killDrainRate;
        this.qzWinRateJc = qzWinRateJc;
        this.pkWinRateJc = pkWinRateJc;
        this.controlStrategy = controlStrategy;
        this.description = description;
        this.name = name;
        this.companyId = companyId;
        this.id = id;
    }

    public String getSelectedStrategy() {
        return selectedStrategy;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    public String getControlStrategyJson() {
        return controlStrategyJson;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public int getDrainWinRoundEnd() {
        return drainWinRoundEnd;
    }

    public int getDrainWinRoundStart() {
        return drainWinRoundStart;
    }

    public int getCardStandard() {
        return cardStandard;
    }

    public double getControlEndLine() {
        return controlEndLine;
    }

    public double getPlayerWinRate() {
        return playerWinRate;
    }

    public String getRemark() {
        return remark;
    }

    public int getStatus() {
        return status;
    }

    public int getMaxExecTimes() {
        return maxExecTimes;
    }

    public String getNumericalJson() {
        return numericalJson;
    }

    public String getGameIds() {
        return gameIds;
    }

    public long getCreateTime() {
        return createTime;
    }

    public double getControlCheckTime() {
        return controlCheckTime;
    }

    public int getKillWinRoundEnd() {
        return killWinRoundEnd;
    }

    public int getKillWinRoundStart() {
        return killWinRoundStart;
    }

    public double getKillDrainRate() {
        return killDrainRate;
    }

    public double getQzWinRateJc() {
        return qzWinRateJc;
    }

    public double getPkWinRateJc() {
        return pkWinRateJc;
    }

    public String getControlStrategy() {
        return controlStrategy;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getCompanyId() {
        return companyId;
    }

    public int getId() {
        return id;
    }
}

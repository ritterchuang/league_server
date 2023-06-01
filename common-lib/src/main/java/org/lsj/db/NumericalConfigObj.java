package org.lsj.db;

// 數控設定(依遊戲類型決定執行機率)
public class NumericalConfigObj {

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

    public NumericalConfigObj(long lastUpdateTime, double vieBankerExecRate, int adjustType, String lastUpdateAccount, int status, double videoGameExecRate, double catchFishExecRate, double hunderedExecRate, double pvpExecRate, String name, int id) {
        this.lastUpdateTime = lastUpdateTime;
        this.vieBankerExecRate = vieBankerExecRate;
        this.adjustType = adjustType;
        this.lastUpdateAccount = lastUpdateAccount;
        this.status = status;
        this.videoGameExecRate = videoGameExecRate;
        this.catchFishExecRate = catchFishExecRate;
        this.hunderedExecRate = hunderedExecRate;
        this.pvpExecRate = pvpExecRate;
        this.name = name;
        this.id = id;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public double getVieBankerExecRate() {
        return vieBankerExecRate;
    }

    public int getAdjustType() {
        return adjustType;
    }

    public String getLastUpdateAccount() {
        return lastUpdateAccount;
    }

    public int getStatus() {
        return status;
    }

    public double getVideoGameExecRate() {
        return videoGameExecRate;
    }

    public double getCatchFishExecRate() {
        return catchFishExecRate;
    }

    public double getHunderedExecRate() {
        return hunderedExecRate;
    }

    public double getPvpExecRate() {
        return pvpExecRate;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}

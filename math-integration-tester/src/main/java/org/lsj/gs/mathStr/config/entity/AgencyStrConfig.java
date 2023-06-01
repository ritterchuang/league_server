package org.lsj.gs.mathStr.config.entity;

// 代理模擬器設定
public class AgencyStrConfig {
    private final String scenarioHlrConfigPath; // 情境設定路徑
    private final String gameCenterMgrConfigPath; // 遊戲中心管理器設定路徑
    private final String userFactoryConfigPath; // 玩家工廠設定路徑
    private final String poolCtrConfigPath; // 水池計算器設定路徑
    private final String agencyStnConfigPath; // 統計者設定路徑
    private final String controlConfigPath; // 強控演算法設定路徑
    private final ScenarioHlrConfig scenarioHlrConfig; // 情境設定
    private final GameCenterMgrConfig gameCenterMgrConfig; // 遊戲中心管理器設定
    private final GamePlayerFactoryConfig gamePlayerFactoryConfig; // 玩家工廠設定
    private final PoolCtrConfig poolCtrConfig; // 水池計算器設定
    private final AgencyStnConfig agencyStnConfig; // 統計者設定
    private final ControlAlgorithmConfig controlAlgorithmConfig; // 除錯設定

    public AgencyStrConfig(String scenarioHlrConfigPath,
                           String gameCenterMgrConfigPath,
                           String userFactoryConfigPath,
                           String poolCtrConfigPath,
                           String agencyStnConfigPath,
                           String controlConfigPath,
                           ScenarioHlrConfig scenarioHlrConfig,
                           GameCenterMgrConfig gameCenterMgrConfig,
                           GamePlayerFactoryConfig gamePlayerFactoryConfig,
                           PoolCtrConfig poolCtrConfig,
                           AgencyStnConfig agencyStnConfig,
                           ControlAlgorithmConfig controlAlgorithmConfig) {
        this.scenarioHlrConfigPath = scenarioHlrConfigPath;
        this.gameCenterMgrConfigPath = gameCenterMgrConfigPath;
        this.userFactoryConfigPath = userFactoryConfigPath;
        this.poolCtrConfigPath = poolCtrConfigPath;
        this.agencyStnConfigPath = agencyStnConfigPath;
        this.controlConfigPath = controlConfigPath;
        this.scenarioHlrConfig = scenarioHlrConfig;
        this.gameCenterMgrConfig = gameCenterMgrConfig;
        this.gamePlayerFactoryConfig = gamePlayerFactoryConfig;
        this.poolCtrConfig = poolCtrConfig;
        this.agencyStnConfig = agencyStnConfig;
        this.controlAlgorithmConfig = controlAlgorithmConfig;
    }

    // 印出設定路徑
    public void printPath(){
        System.out.println("情境設定檔: " + this.getScenarioHlrConfigPath());
        System.out.println("遊戲設定檔: " + this.getGameCenterMgrConfigPath());
        System.out.println("玩家設定檔: " + this.getUserFactoryConfigPath());
        System.out.println("水池設定檔: " + this.getPoolCtrConfigPath());
        System.out.println("統計設定檔: " + this.getAgencyStnConfigPath());
        System.out.println("強控設定檔: " + this.getControlConfigPath());
    }

    // 取得扁平路徑
    public String getFlatPath(){
        return new StringBuilder()
                .append("情境設定檔: " + this.getScenarioHlrConfigPath()).append("; ")
                .append("遊戲設定檔: " + this.getGameCenterMgrConfigPath()).append("; ")
                .append("玩家設定檔: " + this.getUserFactoryConfigPath()).append("; ")
                .append("水池設定檔: " + this.getPoolCtrConfigPath()).append("; ")
                .append("統計設定檔: " + this.getAgencyStnConfigPath()).append("; ")
                .append("強控設定檔: " + this.getControlConfigPath()).append("; ")
                .toString();
    }

    public String getScenarioHlrConfigPath() {
        return scenarioHlrConfigPath;
    }

    public String getGameCenterMgrConfigPath() {
        return gameCenterMgrConfigPath;
    }

    public String getUserFactoryConfigPath() {
        return userFactoryConfigPath;
    }

    public String getPoolCtrConfigPath() {
        return poolCtrConfigPath;
    }

    public String getAgencyStnConfigPath() {
        return agencyStnConfigPath;
    }

    public String getControlConfigPath() {
        return controlConfigPath;
    }

    public ScenarioHlrConfig getScenarioHlrConfig() {
        return scenarioHlrConfig;
    }

    public GameCenterMgrConfig getGameCenterMgrConfig() {
        return gameCenterMgrConfig;
    }

    public GamePlayerFactoryConfig getUserFactoryConfig() {
        return gamePlayerFactoryConfig;
    }

    public PoolCtrConfig getPoolCtrConfig() {
        return poolCtrConfig;
    }

    public AgencyStnConfig getAgencyStnConfig() {
        return agencyStnConfig;
    }

    public ControlAlgorithmConfig getControlAlgorithmConfig() {
        return controlAlgorithmConfig;
    }
}

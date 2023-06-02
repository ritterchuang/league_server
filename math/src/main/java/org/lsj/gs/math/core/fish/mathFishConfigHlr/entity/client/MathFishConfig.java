package org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.client;

// 數值設定
public class MathFishConfig {
    private final RobotPlayerConfig robotPlayerConfig; // 機器人玩家設定
    private final RobotGameConfig robotGameConfig; // 機器人遊戲設定
    private final ClientBulletConfig clientBulletConfig; // 客端子彈設定
    private final ClientHitConfig clientHitConfig; // 客端打擊設定

    // 原始建構子提供JSON解析用
    public MathFishConfig() {
        this.robotPlayerConfig = null;
        this.robotGameConfig = null;
        this.clientBulletConfig = null;
        this.clientHitConfig = null;
    }

    public MathFishConfig(RobotPlayerConfig robotPlayerConfig, RobotGameConfig robotGameConfig, ClientBulletConfig clientBulletConfig, ClientHitConfig clientHitConfig) {
        this.robotPlayerConfig = robotPlayerConfig;
        this.robotGameConfig = robotGameConfig;
        this.clientBulletConfig = clientBulletConfig;
        this.clientHitConfig = clientHitConfig;
    }

    public RobotPlayerConfig getRobotPlayerConfig() {
        return robotPlayerConfig;
    }

    public RobotGameConfig getRobotGameConfig() {
        return robotGameConfig;
    }

    public ClientBulletConfig getClientBulletConfig() {
        return clientBulletConfig;
    }

    public ClientHitConfig getClientHitConfig() {
        return clientHitConfig;
    }
}

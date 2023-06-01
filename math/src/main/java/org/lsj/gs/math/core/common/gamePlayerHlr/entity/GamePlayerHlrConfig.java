package org.lsj.gs.math.core.common.gamePlayerHlr.entity;

// 遊戲玩家處理器設定
public class GamePlayerHlrConfig {
    private final int minUser; // 最小玩家數
    private final int maxUser; // 最大玩家數
    private final double baseScore; // 底注
    private final double limitMin; // 房間最低進入門檻

    public GamePlayerHlrConfig(int minUser, int maxUser, double baseScore, double limitMin) {
        this.minUser = minUser;
        this.maxUser = maxUser;
        this.baseScore = baseScore;
        this.limitMin = limitMin;
    }

    public int getMinUser() {
        return minUser;
    }

    public int getMaxUser() {
        return maxUser;
    }

    public double getBaseScore() {
        return baseScore;
    }

    public double getLimitMin() {
        return limitMin;
    }
}

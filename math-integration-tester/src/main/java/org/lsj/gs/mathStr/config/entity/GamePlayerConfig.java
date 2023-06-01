package org.lsj.gs.mathStr.config.entity;

// 玩家設定
public class GamePlayerConfig {
    private final double userInitMoney; // 玩家起始攜帶金額
    private final int maxBoard; // 每位玩家最多執行場次
    private final int weight; // 權重

    public GamePlayerConfig(double userInitMoney, int maxBoard, int weight) {
        this.userInitMoney = userInitMoney;
        this.maxBoard = maxBoard;
        this.weight = weight;
    }

    public double getUserInitMoney() {
        return userInitMoney;
    }

    public int getMaxBoard() {
        return maxBoard;
    }

    public int getWeight() {
        return weight;
    }
}

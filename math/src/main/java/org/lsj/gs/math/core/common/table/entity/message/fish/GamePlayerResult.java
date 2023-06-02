package org.lsj.gs.math.core.common.table.entity.message.fish;

// 遊戲玩家結果
public class GamePlayerResult {
    private final double balance; // 玩家餘額

    // 原始建構子提供JSON解析用
    public GamePlayerResult() {
        this.balance = 0.0;
    }

    public GamePlayerResult(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }
}

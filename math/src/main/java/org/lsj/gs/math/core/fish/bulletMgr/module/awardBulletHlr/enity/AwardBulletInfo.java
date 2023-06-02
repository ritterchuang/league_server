package org.lsj.gs.math.core.fish.bulletMgr.module.awardBulletHlr.enity;

import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;

// 獎勵子彈資訊
public class AwardBulletInfo {
    private ClientBullet clientBullet; // 已獲得獎勵子彈
    private int amount; // 數量
    private double baseBet; // 子彈價值

    public AwardBulletInfo(ClientBullet clientBullet, int amount, double baseBet) {
        this.clientBullet = clientBullet;
        this.amount = amount;
        this.baseBet = baseBet;
    }

    public AwardBulletInfo() {
        this.clientBullet = new ClientBullet();
        this.amount = 0;
        this.baseBet = 0.0;
    }

    public ClientBullet getClientBullet() {
        return clientBullet;
    }

    public void setClientBullet(ClientBullet clientBullet) {
        this.clientBullet = clientBullet;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getBaseBet() {
        return baseBet;
    }

    public void setBaseBet(double baseBet) {
        this.baseBet = baseBet;
    }
}

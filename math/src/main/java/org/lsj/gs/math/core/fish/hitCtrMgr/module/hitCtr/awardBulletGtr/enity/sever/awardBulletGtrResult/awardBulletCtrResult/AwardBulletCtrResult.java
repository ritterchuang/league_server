package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrResult.awardBulletCtrResult;

import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;

// 獎勵子彈計算者結果
public class AwardBulletCtrResult {
    private final ClientBullet clientBullet; // 獎勵子彈
    private final int bulletAmount; // 獎勵子彈個數

    public AwardBulletCtrResult() {
        this.clientBullet = new ClientBullet();
        this.bulletAmount = 0;
    }

    public AwardBulletCtrResult(ClientBullet clientBullet, int bulletAmount) {
        this.clientBullet = clientBullet;
        this.bulletAmount = bulletAmount;
    }

    public ClientBullet getClientBullet() {
        return clientBullet;
    }

    public int getBulletAmount() {
        return bulletAmount;
    }
}

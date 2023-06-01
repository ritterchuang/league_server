package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.client.awardBullet;

import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;

// 單一獲得子彈資訊
public class AwardBulletExtendOneType extends AwardBulletExtend{
    private final ClientBullet obtainedBullet; // 子彈訊息
    private final int amount; // 子彈數量

    // 原始建構子提供JSON解析用
    public AwardBulletExtendOneType() {
        this.obtainedBullet = new ClientBullet();
        this.amount = 0;
    }

    public AwardBulletExtendOneType(ClientBullet obtainedBullet, int amount) {
        this.obtainedBullet = obtainedBullet;
        this.amount = amount;
    }

    public ClientBullet getObtainedBullet() {
        return obtainedBullet;
    }

    public int getAmount() {
        return amount;
    }
}

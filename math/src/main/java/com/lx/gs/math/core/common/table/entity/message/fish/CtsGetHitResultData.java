package com.lx.gs.math.core.common.table.entity.message.fish;

import com.lx.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import com.lx.gs.math.core.fish.hitCtrMgr.entity.client.ClientHit;
import com.lx.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;

// 服務端給客端的打擊結果資訊
public class CtsGetHitResultData {
    private final int fishSeq; // 序號
    private final ClientBullet bullet; // 子彈訊息
    private final ClientTarget target; // 目標訊息
    private final ClientHit hit; // 打擊資訊

    // 原始建構子提供JSON解析用
    public CtsGetHitResultData() {
        this.fishSeq = -1;
        this.bullet = new ClientBullet();
        this.target = new ClientTarget();
        this.hit = new ClientHit();
    }

    public CtsGetHitResultData(int fishSeq, ClientBullet bullet, ClientTarget target, ClientHit hit) {
        this.fishSeq = fishSeq;
        this.bullet = bullet;
        this.target = target;
        this.hit = hit;
    }

    public int getFishSeq() {
        return fishSeq;
    }

    public ClientBullet getBullet() {
        return bullet;
    }

    public ClientTarget getTarget() {
        return target;
    }

    public ClientHit getHit() {
        return hit;
    }
}

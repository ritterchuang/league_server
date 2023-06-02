package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.module.awardBulletCtr;

import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrResult.awardBulletCtrResult.AwardBulletCtrResult;

// 獎勵子彈計算者介面
public interface IAwardBulletCtr {

    // 計算獎勵子彈結果
    AwardBulletCtrResult calculateAwardBulletCtrResult(int killCount, int maxAmount, int awardBulletIndex, Bullet currentBullet , BulletMgr bulletMgr);

    // 計算獎勵子彈倍數
    double calculateExpectTotalOdds(int maxAmount, int[] amountArray, int[] amountWeightArray, int awardBulletIndex, Bullet currentBullet, BulletMgr bulletMgr);
}

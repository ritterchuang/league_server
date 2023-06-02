package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.module.awardBulletCtr;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrResult.awardBulletCtrResult.AwardBulletCtrResult;

// 非法獎勵子彈計算者
public class AwardBulletCtrInvalid implements IAwardBulletCtr {
    private final ConstMathFish.AwardBulletCtrType awardBulletCtrType; // 獎勵子彈計算者類型

    public AwardBulletCtrInvalid(ConstMathFish.AwardBulletCtrType awardBulletCtrType) {
        this.awardBulletCtrType = awardBulletCtrType;
    }

    @Override
    public AwardBulletCtrResult calculateAwardBulletCtrResult(int killCount, int maxAmount, int awardBulletIndex, Bullet currentBullet, BulletMgr bulletMgr) {
        return new AwardBulletCtrResult();
    }


    @Override
    public double calculateExpectTotalOdds(int maxAmount, int[] amountArray, int[] amountWeightArray, int awardBulletIndex, Bullet currentBullet, BulletMgr bulletMgr) {
        return 0.0;
    }
}

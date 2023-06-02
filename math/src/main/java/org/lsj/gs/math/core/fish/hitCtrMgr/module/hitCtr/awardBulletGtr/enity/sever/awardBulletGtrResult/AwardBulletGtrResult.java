package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrResult;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.client.awardBullet.AwardBulletExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.client.awardBullet.AwardBulletExtendInvalid;

// 獎勵子彈結果
public class AwardBulletGtrResult {
    private final ConstMathFish.AwardBulletType awardBulletType; // 獎勵子彈類型
    private final AwardBulletExtend awardBulletExtend; // 獎勵子彈結果

    public AwardBulletGtrResult() {
        this.awardBulletType = ConstMathFish.AwardBulletType.INVALID;
        this.awardBulletExtend = new AwardBulletExtendInvalid();
    }

    public AwardBulletGtrResult(ConstMathFish.AwardBulletType awardBulletType, AwardBulletExtend awardBulletExtend) {
        this.awardBulletType = awardBulletType;
        this.awardBulletExtend = awardBulletExtend;
    }

    public ConstMathFish.AwardBulletType getAwardBulletType() {
        return awardBulletType;
    }

    public AwardBulletExtend getAwardBulletExtend() {
        return awardBulletExtend;
    }
}

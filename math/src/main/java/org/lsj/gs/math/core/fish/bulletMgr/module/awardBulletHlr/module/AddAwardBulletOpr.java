package org.lsj.gs.math.core.fish.bulletMgr.module.awardBulletHlr.module;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.bulletMgr.module.awardBulletHlr.enity.AwardBulletInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.client.awardBullet.AwardBulletExtend;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.client.awardBullet.AwardBulletExtendOneType;

// 新增獎勵子彈操作者
public class AddAwardBulletOpr {

    public AddAwardBulletOpr() {
    }

    // 新增獎勵子彈
    public void operateAwardBulletInfo(AwardBulletInfo awardBulletInfo, Bullet bullet, ConstMathFish.AwardBulletType awardBulletType, AwardBulletExtend awardBulletExtend) {
        // 1. 若為 None，不做事
        if (awardBulletType == ConstMathFish.AwardBulletType.NONE) {
            return;
        }

        // 2. 若獲得數量為0，不更新
        AwardBulletExtendOneType awardBulletExtendOneType = (AwardBulletExtendOneType)awardBulletExtend;
        if (awardBulletExtendOneType.getAmount() <= 0) {
            return;
        }

        // 3. 若為相同子彈代碼，更新數量為 原本紀錄 + 新獲得
        if (awardBulletInfo.getClientBullet().getBulletIndex() == awardBulletExtendOneType.getObtainedBullet().getBulletIndex()) {
            this.updateValue(awardBulletInfo, awardBulletExtendOneType.getObtainedBullet(), awardBulletInfo.getAmount() + awardBulletExtendOneType.getAmount(), bullet.getBet());
            return;
        }

        // 4. 更新數量為 新獲得
        this.updateValue(awardBulletInfo, awardBulletExtendOneType.getObtainedBullet(), awardBulletExtendOneType.getAmount(), bullet.getBet());
    }

    // 更新獎勵子彈紀錄值
    private void updateValue(AwardBulletInfo awardBulletInfo, ClientBullet clientBullet, int bulletAmount, double baseBet) {
        awardBulletInfo.setClientBullet(clientBullet);
        awardBulletInfo.setAmount(bulletAmount);
        awardBulletInfo.setBaseBet(baseBet);
    }
}

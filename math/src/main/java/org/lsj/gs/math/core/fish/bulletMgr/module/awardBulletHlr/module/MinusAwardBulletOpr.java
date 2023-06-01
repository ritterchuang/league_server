package org.lsj.gs.math.core.fish.bulletMgr.module.awardBulletHlr.module;

import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.bulletMgr.module.awardBulletHlr.enity.AwardBulletInfo;

// 扣除獎勵子彈操作者
public class MinusAwardBulletOpr {

    public MinusAwardBulletOpr() {
    }

    // 扣除獎勵子彈
    public void operateAwardBulletInfo(AwardBulletInfo awardBulletInfo, Bullet currentBullet) {
        // 1. 若此次子彈與記錄不同，不做事
        if (awardBulletInfo.getClientBullet().getBulletIndex() != currentBullet.getBulletIndex()) {
            return;
        }

        // 2. 扣除使用個數
        awardBulletInfo.setAmount(awardBulletInfo.getAmount() - 1);
    }
}

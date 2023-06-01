package org.lsj.gs.mathBoardGtr.core.fish.fishUtil.clientBulletGtr;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.client.awardBullet.AwardBulletExtend;
import org.lsj.gs.math.core.fish.mathFishConfigHlr.entity.client.MathFishConfig;

// 客端子彈處理者介面
public interface IClientBulletGtr {
    // 計算客端模擬子彈
    ClientBullet generateClientBullet(MathFishConfig mathFishConfig);

    // 更新獎勵子彈
    void updateAwardBullet(ConstMathFish.AwardBulletType awardBulletType, AwardBulletExtend awardBulletExtend, double baseBet);
}

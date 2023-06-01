package com.lx.gs.mathBoardGtr.core.fish.fishUtil.clientBulletGtr;

import com.lx.gs.math.core.fish.ConstMathFish;
import com.lx.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import com.lx.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.client.awardBullet.AwardBulletExtend;
import com.lx.gs.math.core.fish.mathFishConfigHlr.entity.client.MathFishConfig;

// 客端子彈處理者介面
public interface IClientBulletGtr {
    // 計算客端模擬子彈
    ClientBullet generateClientBullet(MathFishConfig mathFishConfig);

    // 更新獎勵子彈
    void updateAwardBullet(ConstMathFish.AwardBulletType awardBulletType, AwardBulletExtend awardBulletExtend, double baseBet);
}

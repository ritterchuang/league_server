package org.lsj.gs.math.core.fish.bulletMgr.module.awardBulletHlr.module;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.BulletMtr;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.client.awardBullet.AwardBulletExtend;

import java.util.Map;

// 獎勵子彈處理者介面
public interface IAwardBulletHlr {

    // 更新紀錄獎勵子彈資訊
    void update(ConstMathFish.AwardBulletType awardBulletType, AwardBulletExtend awardBulletExtend, Bullet bullet);

    // 取得獎勵子彈成本
    double getObtainedAwardBulletCost(Map<Integer, BulletMtr> bulletMtrMap);

    // 計算返還金額
    double calculateReturnValue(Map<Integer, BulletMtr> bulletMtrMap);

    // 計算獎勵子彈預期金額
    double calculateAwardBulletExpectTotalWin(HitResult hitResult, double bulletBet, Map<Integer, BulletMtr> bulletMtrMap);
}

package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.module;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrResult.AwardBulletGtrResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrResult.RobotAwardBulletGtrResult;

import java.util.List;

// 獎勵子彈生產者介面
public interface IAwardBulletGtr {
    //* 計算者相關 *//
    // 生產子彈
    AwardBulletGtrResult calculateAwardBulletGtrResult(int killCount, Bullet currentBullet, BulletMgr bulletMgr);

    // 計算倍數
    double calculateAwardBulletOdds(Bullet currentBullet, BulletMgr bulletMgr);

    //* 獎勵子彈資訊相關 *//
    // 取得獎勵子彈類型
    ConstMathFish.AwardBulletType getAwardBulletType();

    //* 機器人相關 *//
    // 計算機器人獎勵子彈結果
    List<RobotAwardBulletGtrResult> calculateRobotAwardBulletGtrResult(BulletMgr bulletMgr);

    AwardBulletGtrResult calculateNoKillResult();

}

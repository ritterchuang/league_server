package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import org.lsj.gs.math.core.fish.bulletMgr.module.awardBulletHlr.module.AwardBulletHlr;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.client.BulletCostExtend;

// 子彈成本處理者介面
public interface IBulletCostHlr {
    //* 計算相關 *//
    // 計算子彈價值
    double calculateBulletBaseBet(ClientBullet clientBullet, AwardBulletHlr awardBulletHlr);

    // 計算客端子彈成本列表
    double[] calculateBulletCostList(double base);

    // 計算獎勵客製子彈
    BulletCostExtend calculateAwardBulletCostExtend();

    //* 成本資訊相關 *//
    // 取得子彈成本類型
    ConstMathFish.BulletCostType getBulletCostType();

    // 取得子彈成本
    double getBulletCost(ClientBullet clientBullet);

    //* 檢查相關 *//
    // 檢查子彈完備性
    ConstMathCommon.TableProtocolCode checkBulletCompleteness(ClientBullet clientBullet);
}

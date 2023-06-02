package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.enity.client.BulletTypeExtend;

// 子彈類型處理者介面
public interface IBulletTypeHlr {
    //* 計算相關 *//
    // 計算獎勵客製子彈BulletExtend
    BulletTypeExtend calculateAwardBulletTypeExtend();

    //* 子彈資訊相關 *//
    // 取得子彈類型
    ConstMathFish.BulletType getBulletType();

    //* 檢查相關 *//
    // 檢查子彈完備性
    ConstMathCommon.TableProtocolCode checkBulletCompleteness(ClientBullet clientBullet);
}

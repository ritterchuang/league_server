package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.enity.client.BulletTypeExtend;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.enity.client.BulletTypeExtendInvalid;

// 非法子彈類型處理者
public class BulletTypeHlrInvalid implements IBulletTypeHlr {
    private final ConstMathFish.BulletType bulletType; // 子彈類型

    public BulletTypeHlrInvalid(ConstMathFish.BulletType bulletType) {
        this.bulletType = bulletType;
    }

    //* 計算相關 *//
    // 計算獎勵客製子彈資訊
    @Override
    public BulletTypeExtend calculateAwardBulletTypeExtend() {
        return new BulletTypeExtendInvalid();
    }

    //* 子彈資訊相關 *//
    // 取得子彈類型
    @Override
    public ConstMathFish.BulletType getBulletType() {
        return this.bulletType;
    }

    //* 檢查相關 *//
    // 檢查子彈完整性
    @Override
    public ConstMathCommon.TableProtocolCode checkBulletCompleteness(ClientBullet clientBullet) {
        return ConstMathCommon.TableProtocolCode.FISH_BULLET_NOT_CONSISTENCY;
    }
}

package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletAmountHlr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import org.lsj.gs.math.core.fish.bulletMgr.module.awardBulletHlr.module.AwardBulletHlr;

// 非法子彈數量處理者
public class BulletAmountHlrInvalid implements IBulletAmountHlr {
    private final ConstMathFish.BulletAmountType bulletAmountType; // 子彈數量類型

    public BulletAmountHlrInvalid(ConstMathFish.BulletAmountType bulletAmountType) {
        this.bulletAmountType = bulletAmountType;
    }

    @Override
    public ConstMathCommon.TableProtocolCode checkBulletExistence(ClientBullet clientBullet, AwardBulletHlr awardBulletHlr) {
        return ConstMathCommon.TableProtocolCode.FISH_BULLET_NOT_CONSISTENCY;
    }
}

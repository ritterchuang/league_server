package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletAmountHlr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import org.lsj.gs.math.core.fish.bulletMgr.module.awardBulletHlr.module.AwardBulletHlr;

// 子彈數量處理者無限數量
public class BulletAmountHlrInFinite implements IBulletAmountHlr {
    private final ConstMathFish.BulletAmountType bulletAmountType; // 子彈數量類型

    public BulletAmountHlrInFinite(ConstMathFish.BulletAmountType bulletAmountType) {
        this.bulletAmountType = bulletAmountType;
    }


    // 檢查子彈存在性 TODO 多種子彈以後實作
    @Override
    public ConstMathCommon.TableProtocolCode checkBulletExistence(ClientBullet clientBullet, AwardBulletHlr awardBulletHlr) {
        // 1. 存在獎勵子彈，不允許一般付費子彈
        if (awardBulletHlr.getObtainedAwardBulletAmount() > 0) {
            return ConstMathCommon.TableProtocolCode.FISH_BULLET_STATE_ERROR;
        }

        // 2. 回傳正確代碼
        return ConstMathCommon.TableProtocolCode.COMMON_SUCCESS;
    }
}

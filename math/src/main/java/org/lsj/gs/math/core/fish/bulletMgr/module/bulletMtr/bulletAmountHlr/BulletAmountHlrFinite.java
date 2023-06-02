package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletAmountHlr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import org.lsj.gs.math.core.fish.bulletMgr.module.awardBulletHlr.module.AwardBulletHlr;

// 子彈數量處理者有限數量
public class BulletAmountHlrFinite implements IBulletAmountHlr {
    private final ConstMathFish.BulletAmountType bulletAmountType; // 子彈數量類型

    public BulletAmountHlrFinite(ConstMathFish.BulletAmountType bulletAmountType) {
        this.bulletAmountType = bulletAmountType;
    }

    // 檢查子彈存在性
    @Override
    public ConstMathCommon.TableProtocolCode checkBulletExistence(ClientBullet clientBullet, AwardBulletHlr awardBulletHlr) {
        // 1. 檢查子彈代碼是否正確
        if (clientBullet.getBulletIndex() != awardBulletHlr.getObtainedAwardBulletIndex()) {
            return ConstMathCommon.TableProtocolCode.FISH_BULLET_INDEX_NOT_EXIST;
        }

        // 2. 檢查子彈數量
        if (awardBulletHlr.getObtainedAwardBulletAmount() <= 0) {
            return ConstMathCommon.TableProtocolCode.FISH_BULLET_AMOUNT_NOT_ENOUGH;
        }

        // 3. 回傳正確代碼
        return ConstMathCommon.TableProtocolCode.COMMON_SUCCESS;
    }
}

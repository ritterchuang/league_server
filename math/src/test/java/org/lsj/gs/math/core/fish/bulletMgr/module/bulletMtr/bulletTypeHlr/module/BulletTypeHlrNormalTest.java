package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.client.BulletCostExtendFree;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.enity.sever.BulletConfigExtendNormal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 子彈類型處理者一般測試
@ExtendWith(MockitoExtension.class)
class BulletTypeHlrNormalTest {
    BulletTypeHlrNormal bulletTypeHlrNormal;

    // 給定不一致子彈類型，當檢查子彈，預期噴錯
    @Test
    void test_given_differentBulletType_when_checkBulletCompleteness_then_return_errorCode() {
        // 1. given
        this.bulletTypeHlrNormal = new BulletTypeHlrNormal(ConstMathFish.BulletType.NORMAL, new BulletConfigExtendNormal());

        // 2. when
        ConstMathCommon.TableProtocolCode tableProtocolCode = this.bulletTypeHlrNormal.checkBulletCompleteness(new ClientBullet());

        // 3. then
        assertEquals(tableProtocolCode, ConstMathCommon.TableProtocolCode.FISH_BULLET_NOT_CONSISTENCY);
    }

    // 給定不一致子彈類型，當檢查子彈，預期噴錯
    @Test
    void test_given_null_bulletTypeExtend_when_checkBulletCompleteness_then_return_errorCode() {
        // 1. given
        this.bulletTypeHlrNormal = new BulletTypeHlrNormal(ConstMathFish.BulletType.NORMAL, new BulletConfigExtendNormal());
        ClientBullet clientBullet = new ClientBullet(1, ConstMathFish.BulletType.NORMAL, null, ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree());

        // 2. when
        ConstMathCommon.TableProtocolCode tableProtocolCode = this.bulletTypeHlrNormal.checkBulletCompleteness(clientBullet);

        // 3. then
        assertEquals(tableProtocolCode, ConstMathCommon.TableProtocolCode.FISH_BULLET_NOT_COMPLETE);
    }
}
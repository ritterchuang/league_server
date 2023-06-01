package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.sever.bulletCostConfigExtend.BulletCostConfigExtendFree;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.enity.client.BulletTypeExtendNormal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 子彈成本處理者免費測試
@ExtendWith(MockitoExtension.class)
class BulletCostHlrFreeTest {
    BulletCostHlrFree bulletCostHlrFree; // 子彈成本處理者免費

    // 給定不一致子彈成本類型，當檢查子彈，預期噴錯
    @Test
    void test_given_differentBulletCostType_when_checkBulletCompleteness_then_return_errorCode() {
        // 1. given
        this.bulletCostHlrFree = new BulletCostHlrFree(1.0, ConstMathFish.BulletCostType.FREE, new BulletCostConfigExtendFree());

        // 2. when
        ConstMathCommon.TableProtocolCode tableProtocolCode = this.bulletCostHlrFree.checkBulletCompleteness(new ClientBullet());

        // 3. then
        assertEquals(tableProtocolCode, ConstMathCommon.TableProtocolCode.FISH_BULLET_NOT_CONSISTENCY);
    }

    // 給定空的子彈額外成本資訊，當檢查子彈，預期噴錯
    @Test
    void test_given_null_bulletCostConfigExtendFree_when_checkBulletCompleteness_then_throws_exception() {
        // 1. given
        this.bulletCostHlrFree = new BulletCostHlrFree(1.0, ConstMathFish.BulletCostType.FREE, new BulletCostConfigExtendFree());

        // 2. when
        ConstMathCommon.TableProtocolCode tableProtocolCode = this.bulletCostHlrFree.checkBulletCompleteness(new ClientBullet(1, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, null));

        // 3. then
        assertEquals(tableProtocolCode, ConstMathCommon.TableProtocolCode.FISH_BULLET_NOT_COMPLETE);
    }
}
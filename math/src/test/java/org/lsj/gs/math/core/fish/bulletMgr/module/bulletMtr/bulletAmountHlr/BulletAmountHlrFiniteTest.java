package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletAmountHlr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import org.lsj.gs.math.core.fish.bulletMgr.module.awardBulletHlr.module.AwardBulletHlr;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.client.BulletCostExtendFree;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.enity.client.BulletTypeExtendNormal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 子彈數量處理者有限測試
@ExtendWith(MockitoExtension.class)
class BulletAmountHlrFiniteTest {
    BulletAmountHlrFinite bulletAmountHlrFinite; // 子彈數量處理者有限
    @Mock AwardBulletHlr mockAwardBulletHlr; // 獎勵子彈處理者

    // 給定不同子彈代碼，當檢查存在性，預期噴錯
    @Test
    void test_given_differentBulletIndex_when_checkBulletExistence_then_return_errorCode() {
        // 1. given
        this.bulletAmountHlrFinite = new BulletAmountHlrFinite(ConstMathFish.BulletAmountType.FINITE);
        ClientBullet clientBullet = new ClientBullet(1, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree());
        Mockito.when(mockAwardBulletHlr.getObtainedAwardBulletIndex()).thenReturn(2);

        // 2. when
        ConstMathCommon.TableProtocolCode tableProtocolCode = this.bulletAmountHlrFinite.checkBulletExistence(clientBullet, mockAwardBulletHlr);

        // 3. then
        assertEquals(tableProtocolCode, ConstMathCommon.TableProtocolCode.FISH_BULLET_INDEX_NOT_EXIST);
    }

    // 給定非法數量，當檢查存在性，預期噴錯
    @Test
    void test_given_invalidBulletAmount_when_checkBulletExistence_then_return_errorCode() {
        // 1. given
        this.bulletAmountHlrFinite = new BulletAmountHlrFinite(ConstMathFish.BulletAmountType.FINITE);
        ClientBullet clientBullet = new ClientBullet(1, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree());
        Mockito.when(mockAwardBulletHlr.getObtainedAwardBulletIndex()).thenReturn(1);
        Mockito.when(mockAwardBulletHlr.getObtainedAwardBulletAmount()).thenReturn(0);

        // 2. when
        ConstMathCommon.TableProtocolCode tableProtocolCode = this.bulletAmountHlrFinite.checkBulletExistence(clientBullet, mockAwardBulletHlr);

        // 3. then
        assertEquals(tableProtocolCode, ConstMathCommon.TableProtocolCode.FISH_BULLET_AMOUNT_NOT_ENOUGH);
    }
}
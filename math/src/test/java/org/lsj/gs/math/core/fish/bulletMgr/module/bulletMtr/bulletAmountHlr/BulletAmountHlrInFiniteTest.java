package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletAmountHlr;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import org.lsj.gs.math.core.fish.bulletMgr.module.awardBulletHlr.module.AwardBulletHlr;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.client.BulletCostExtendRatio;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.enity.client.BulletTypeExtendNormal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// 子彈數量處理者無限測試
@ExtendWith(MockitoExtension.class)
class BulletAmountHlrInFiniteTest {
    BulletAmountHlrInFinite bulletAmountHlrInFinite; // 子彈數量處理者有限
    @Mock AwardBulletHlr mockAwardBulletHlr; // 獎勵子彈處理者

    // 給定存在獎勵子彈，使用一般子彈，回傳錯誤代碼
    @Test
    void test_given_existAwardBulletAndInfiniteBullet_when_checkBulletExistence_then_return_errorCode() {
        // 1. given
        this.bulletAmountHlrInFinite = new BulletAmountHlrInFinite(ConstMathFish.BulletAmountType.INFINITE);
        ClientBullet clientBullet = new ClientBullet(1, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.RATIO, new BulletCostExtendRatio(2.0));
        Mockito.when(mockAwardBulletHlr.getObtainedAwardBulletAmount()).thenReturn(5);

        ConstMathCommon.TableProtocolCode tableProtocolCode = ConstMathCommon.TableProtocolCode.COMMON_SUCCESS;
        // 2. when
        try {
            tableProtocolCode = this.bulletAmountHlrInFinite.checkBulletExistence(clientBullet, mockAwardBulletHlr);
        }catch (Exception e) {
            fail();
        }

        // 3. then
        assertEquals(ConstMathCommon.TableProtocolCode.FISH_BULLET_STATE_ERROR, tableProtocolCode);
    }
}
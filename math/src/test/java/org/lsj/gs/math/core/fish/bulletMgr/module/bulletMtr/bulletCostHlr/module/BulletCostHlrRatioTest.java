package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import org.lsj.gs.math.core.fish.bulletMgr.module.awardBulletHlr.module.AwardBulletHlr;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.client.BulletCostExtendRatio;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.sever.BulletCostExchange;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.sever.bulletCostConfigExtend.BulletCostConfigExtendRatio;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.sever.bulletCostListConfigExtend.BulletCostListConfigExtendTenMultiTen;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.enity.client.BulletTypeExtendNormal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 子彈成本處理者比例測試
@ExtendWith(MockitoExtension.class)
class BulletCostHlrRatioTest {
    BulletCostHlrRatio bulletCostHlrRatio; // 子彈成本處理者比例

    //* 檢查成本 *//
    // 給定不一致子彈成本類型，當檢查子彈，預期噴錯
    @Test
    void test_given_differentBulletCostType_when_checkBulletCompleteness_then_return_errorCode() {
        // 1. given
        this.bulletCostHlrRatio = new BulletCostHlrRatio(1.0, ConstMathFish.BulletCostType.RATIO, new BulletCostConfigExtendRatio(ConstMathFish.BulletCostListType.TEN_MULTI_TEN, new BulletCostListConfigExtendTenMultiTen(), new BulletCostExchange(1.0, 1.0)));

        // 2. when
        ConstMathCommon.TableProtocolCode tableProtocolCode = this.bulletCostHlrRatio.checkBulletCompleteness(new ClientBullet());

        // 3. then
        assertEquals(tableProtocolCode, ConstMathCommon.TableProtocolCode.FISH_BULLET_NOT_CONSISTENCY);
    }

    // 給定空的子彈額外成本資訊，當檢查子彈，預期噴錯
    @Test
    void test_given_null_bulletCostConfigExtendFree_when_checkBulletCompleteness_then_return_errorCode() {
        // 1. given
        this.bulletCostHlrRatio = new BulletCostHlrRatio(1.0, ConstMathFish.BulletCostType.RATIO, new BulletCostConfigExtendRatio(ConstMathFish.BulletCostListType.TEN_MULTI_TEN, new BulletCostListConfigExtendTenMultiTen(), new BulletCostExchange(1.0, 1.0)));

        // 2. when
        ConstMathCommon.TableProtocolCode tableProtocolCode = this.bulletCostHlrRatio.checkBulletCompleteness(new ClientBullet(1, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.RATIO, null));

        // 3. then
        assertEquals(tableProtocolCode, ConstMathCommon.TableProtocolCode.FISH_BULLET_NOT_COMPLETE);
    }

    //* 子彈價值 *//
    // 給定子彈成本3，設定子彈基礎成本3，基礎價值1，當計算子彈價值，預期子彈價值1
    @Test
    void test_given_clientBulletCost_3_and_when_calculateBulletBaseBet_then_bulletBaseBet_1() {
        // 1. given
        this.bulletCostHlrRatio = new BulletCostHlrRatio(1.0, ConstMathFish.BulletCostType.RATIO, new BulletCostConfigExtendRatio(ConstMathFish.BulletCostListType.TEN_MULTI_TEN, new BulletCostListConfigExtendTenMultiTen(), new BulletCostExchange(3.0, 1.0)));
        ClientBullet clientBullet = new ClientBullet(1, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.RATIO, new BulletCostExtendRatio(3));

        // 2. when
        double bulletBaseBet = this.bulletCostHlrRatio.calculateBulletBaseBet(clientBullet, new AwardBulletHlr());

        // 3. then
        assertEquals(1.0, bulletBaseBet);
    }
}
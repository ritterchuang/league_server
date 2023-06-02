package org.lsj.gs.math.core.fish.bulletMgr.module.awardBulletHlr.module;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.bulletMgr.module.awardBulletHlr.enity.AwardBulletInfo;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.client.BulletCostExtendFree;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.enity.client.BulletTypeExtendNormal;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 扣除獎勵子彈操作者測試
@ExtendWith(MockitoExtension.class)
class MinusAwardBulletOprTest {
    MinusAwardBulletOpr minusAwardBulletOpr; // 扣除獎勵子彈操作者

    @BeforeEach
    void setUp() {
        this.minusAwardBulletOpr = new MinusAwardBulletOpr();
    }

    // 給定不同子彈，當要進行操作，不做事
    @Test
    void test_given_different_bulletIndex_when_operateAwardBulletInfo_then_doNothing() {
        // 1. given
        AwardBulletInfo awardBulletInfo = new AwardBulletInfo(
                new ClientBullet(2, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree()),
                15,
                3.5);
        Bullet bullet = new Bullet(1, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree(), 0, 3.5);
        AwardBulletInfo expectResult = new AwardBulletInfo(
                new ClientBullet(2, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree()),
                15,
                3.5);

        // 2. when
        this.minusAwardBulletOpr.operateAwardBulletInfo(awardBulletInfo, bullet);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(awardBulletInfo);
        assertEquals(expectString, actualString);
    }

    // 給定不同子彈，當要進行操作，扣除數量
    @Test
    void test_given_same_bulletIndex_when_operateAwardBulletInfo_then_minusAmount() {
        // 1. given
        AwardBulletInfo awardBulletInfo = new AwardBulletInfo(
                new ClientBullet(2, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree()),
                15,
                3.5);
        Bullet bullet = new Bullet(2, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree(), 0, 3.5);
        AwardBulletInfo expectResult = new AwardBulletInfo(
                new ClientBullet(2, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree()),
                14,
                3.5);

        // 2. when
        this.minusAwardBulletOpr.operateAwardBulletInfo(awardBulletInfo, bullet);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(awardBulletInfo);
        assertEquals(expectString, actualString);
    }
}
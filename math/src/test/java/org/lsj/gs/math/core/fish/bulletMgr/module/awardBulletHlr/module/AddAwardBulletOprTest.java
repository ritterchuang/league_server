package org.lsj.gs.math.core.fish.bulletMgr.module.awardBulletHlr.module;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.bulletMgr.module.awardBulletHlr.enity.AwardBulletInfo;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.client.BulletCostExtendFree;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.enity.client.BulletTypeExtendNormal;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.client.awardBullet.AwardBulletExtendNone;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.client.awardBullet.AwardBulletExtendOneType;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 新增獎勵子彈操作者測試
@ExtendWith(MockitoExtension.class)
class AddAwardBulletOprTest {
    AddAwardBulletOpr addAwardBulletOpr; // 新增獎勵子彈操作者

    @BeforeEach
    void setUp() {
        this.addAwardBulletOpr = new AddAwardBulletOpr();
    }

    // 無設定獎勵子彈，當要進行操作，不做事
    @Test
    void test_given_awardBulletTypeNone_when_operateAwardBulletInfo_then_doNothing() {
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
        this.addAwardBulletOpr.operateAwardBulletInfo(awardBulletInfo, bullet, ConstMathFish.AwardBulletType.NONE, new AwardBulletExtendNone());

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(awardBulletInfo);
        assertEquals(expectString, actualString);
    }

    // 獲得獎勵子彈個數小於等於0，當要進行操作，不做事
    @Test
    void test_given_awardBulletAmount_smallerThanZero_when_operateAwardBulletInfo_then_doNothing() {
        // 1. given
        AwardBulletInfo awardBulletInfo = new AwardBulletInfo(
                new ClientBullet(2, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree()),
                15,
                3.5);
        Bullet bullet = new Bullet(1, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree(), 0, 3.5);
        AwardBulletExtendOneType awardBulletExtendOneType = new AwardBulletExtendOneType(new ClientBullet(), -1);
        AwardBulletInfo expectResult = new AwardBulletInfo(
                new ClientBullet(2, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree()),
                15,
                3.5);

        // 2. when
        this.addAwardBulletOpr.operateAwardBulletInfo(awardBulletInfo, bullet, ConstMathFish.AwardBulletType.ONE_TYPE, awardBulletExtendOneType);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(awardBulletInfo);
        assertEquals(expectString, actualString);
    }

    // 獲得獎勵子彈個數大於0，且與記錄的子彈為相同代碼，當要進行操作，數量為 原本紀錄 + 新獲得
    @Test
    void test_given_awardBulletAmount_biggerThanZero_withSameBulletIndex_when_operateAwardBulletInfo_then_updateCorrect() {
        // 1. given
        AwardBulletInfo awardBulletInfo = new AwardBulletInfo(
                new ClientBullet(2, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree()),
                15,
                3.5);
        Bullet bullet = new Bullet(2, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree(), 0, 3.5);
        AwardBulletExtendOneType awardBulletExtendOneType = new AwardBulletExtendOneType(new ClientBullet(2, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree()), 9);
        AwardBulletInfo expectResult = new AwardBulletInfo(
                new ClientBullet(2, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree()),
                24,
                3.5);

        // 2. when
        this.addAwardBulletOpr.operateAwardBulletInfo(awardBulletInfo, bullet, ConstMathFish.AwardBulletType.ONE_TYPE, awardBulletExtendOneType);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(awardBulletInfo);
        assertEquals(expectString, actualString);
    }

    // 獲得獎勵子彈個數大於0，且與記錄的子彈為不同代碼，當要進行操作，數量為 新獲得
    @Test
    void test_given_awardBulletAmount_biggerThanZero_withDifferentBulletIndex_when_operateAwardBulletInfo_then_updateCorrect() {
        // 1. given
        AwardBulletInfo awardBulletInfo = new AwardBulletInfo(
                new ClientBullet(2, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree()),
                15,
                3.5);
        Bullet bullet = new Bullet(2, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree(), 0, 3.5);
        AwardBulletExtendOneType awardBulletExtendOneType = new AwardBulletExtendOneType(new ClientBullet(3, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree()), 9);
        AwardBulletInfo expectResult = new AwardBulletInfo(
                new ClientBullet(3, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree()),
                9,
                3.5);

        // 2. when
        this.addAwardBulletOpr.operateAwardBulletInfo(awardBulletInfo, bullet, ConstMathFish.AwardBulletType.ONE_TYPE, awardBulletExtendOneType);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(awardBulletInfo);
        assertEquals(expectString, actualString);
    }
}
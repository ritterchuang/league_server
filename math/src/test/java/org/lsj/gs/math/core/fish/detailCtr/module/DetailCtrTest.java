package org.lsj.gs.math.core.fish.detailCtr.module;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.client.BulletCostExtendFree;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.enity.client.BulletTypeExtendNormal;
import org.lsj.gs.math.core.fish.detailCtr.enity.client.DetailAwardBullet;
import org.lsj.gs.math.core.fish.detailCtr.enity.client.DetailFish;
import org.lsj.gs.math.core.fish.detailCtr.enity.client.DetailFishReturn;
import org.lsj.gs.math.core.fish.detailCtr.enity.client.DetailSpecialFeature;
import org.lsj.gs.math.core.fish.detailCtr.enity.system.DetailCtrConfig;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendFixedOdds;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.client.awardBullet.AwardBulletExtendNone;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.client.awardBullet.AwardBulletExtendOneType;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureResultExtend.SpecialFeatureResultExtendNone;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureResultExtend.SpecialFeatureResultExtendRedEnvelope;
import org.lsj.gs.math.games.csby_java.enity.ConstCsbyJava;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// 詳細記錄計算者測試
@ExtendWith(MockitoExtension.class)
class DetailCtrTest {
    DetailCtr detailCtr; // 詳細記錄計算者
    DetailCtrConfig config; // 詳細記錄計算者設定
    @Mock BulletMgr mockBulletMgr;

    @BeforeEach
    void setUp() {
        this.config = this.generateDetailCtrConfig();
        this.detailCtr = new DetailCtr(this.config);
    }

    //* 目標詳細資訊 *//
    // 目標無死，當計算詳細記錄，回傳Empty
    @Test
    void test_given_targetNoKilled_when_calculateDetailFish_then_return_empty() {
        // 1. given
        Bullet bullet = new Bullet(2, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree(), 0, 10);
        ClientTarget clientTarget = new ClientTarget(10);
        HitResult hitResult = new HitResult(false, 0, 0.0, 0.0, 0.0, ConstMathFish.AwardBulletType.NONE, new AwardBulletExtendNone(), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureResultExtendNone(), ConstMathFish.HitType.FIXED_ODDS, new HitResultExtendFixedOdds());

        // 2. when
        Optional<DetailFish> optionalDetailFish = this.detailCtr.calculateDetailFish(bullet, clientTarget, hitResult);

        // 3. then
        assertTrue(optionalDetailFish.isEmpty());
    }

    // 目標死，當計算詳細記錄，回傳正確資訊
    @Test
    void test_given_targetKilled_when_calculateDetailFish_then_return_detailFish() {
        // 1. given
        Bullet bullet = new Bullet(2, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree(), 0, 10);
        ClientTarget clientTarget = new ClientTarget(10);
        HitResult hitResult = new HitResult(true, 3, 20.0, 60.0, 6.0, ConstMathFish.AwardBulletType.NONE, new AwardBulletExtendNone(), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureResultExtendNone(), ConstMathFish.HitType.FIXED_ODDS, new HitResultExtendFixedOdds());
        Optional<DetailFish> expectResult = Optional.of(new DetailFish(this.config.getTargetIndexNameMap().get(clientTarget.getTargetIndex()),
                                                                            2.0,
                                                                            3,
                                                                            this.config.getBulletIndexNameMap().get(bullet.getBulletIndex()),
                                                                            10.0,
                                                                            0));

        // 2. when
        Optional<DetailFish> optionalDetailFish = this.detailCtr.calculateDetailFish(bullet, clientTarget, hitResult);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult.get());
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(optionalDetailFish.get());
        assertEquals(expectString, actualString);
    }

    //* 獎勵子彈詳細資訊 *//
    // 無設定獎勵子彈，回傳Empty
    @Test
    void test_given_noAwardBulletSetting_when_calculateDetailAwardBullet_then_return_empty() {
        // 1. given
        Bullet bullet = new Bullet(2, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree(), 0, 10);
        HitResult hitResult = new HitResult(false, 0, 0.0, 0.0, 0.0, ConstMathFish.AwardBulletType.NONE, new AwardBulletExtendNone(), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureResultExtendNone(), ConstMathFish.HitType.FIXED_ODDS, new HitResultExtendFixedOdds());

        // 2. when
        Optional<DetailAwardBullet> optionalDetailAwardBullet = this.detailCtr.calculateDetailAwardBullet(bullet, hitResult);

        // 3. then
        assertTrue(optionalDetailAwardBullet.isEmpty());
    }

    // 獎勵子彈獲得個數小於0，回傳Empty
    @Test
    void test_given_obtainedAwardBulletAmount_lessThanZero_when_calculateDetailAwardBullet_then_return_empty() {
        // 1. given
        Bullet bullet = new Bullet(2, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree(), 0, 10);
        HitResult hitResult = new HitResult(false, 0, 0.0, 0.0, 0.0, ConstMathFish.AwardBulletType.ONE_TYPE, new AwardBulletExtendOneType(), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureResultExtendNone(), ConstMathFish.HitType.FIXED_ODDS, new HitResultExtendFixedOdds());

        // 2. when
        Optional<DetailAwardBullet> optionalDetailAwardBullet = this.detailCtr.calculateDetailAwardBullet(bullet, hitResult);

        // 3. then
        assertTrue(optionalDetailAwardBullet.isEmpty());
    }

    // 獲得獎勵子彈，回傳正確資訊
    @Test
    void test_given_obtainedAwardBullet_when_calculateDetailAwardBullet_then_return_DetailAwardBullet() {
        // 1. given
        Bullet bullet = new Bullet(2, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree(), 0, 10);
        ClientBullet clientBullet = new ClientBullet(2, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree());
        HitResult hitResult = new HitResult(true, 3, 20.0, 60.0, 6.0, ConstMathFish.AwardBulletType.ONE_TYPE, new AwardBulletExtendOneType(clientBullet, 5), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureResultExtendNone(), ConstMathFish.HitType.FIXED_ODDS, new HitResultExtendFixedOdds());
        Optional<DetailAwardBullet> expectResult = Optional.of(new DetailAwardBullet(this.config.getBulletIndexNameMap().get(2), 5, 10));

        // 2. when
        Optional<DetailAwardBullet> optionalDetailAwardBullet = this.detailCtr.calculateDetailAwardBullet(bullet, hitResult);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult.get());
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(optionalDetailAwardBullet.get());
        assertEquals(expectString, actualString);
    }

    //* 特殊事件詳細資訊 *//
    // 無設定特殊事件，回傳Empty
    @Test
    void test_given_noSpecialFeatureSetting_when_calculateDetailSpecialFeature_then_return_empty() {
        // 1. given
        Bullet bullet = new Bullet(2, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree(), 0, 10);
        HitResult hitResult = new HitResult(true, 2, 20.0, 40.0, 4.0, ConstMathFish.AwardBulletType.NONE, new AwardBulletExtendNone(), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureResultExtendNone(), ConstMathFish.HitType.FIXED_ODDS, new HitResultExtendFixedOdds());

        // 2. when
        Optional<DetailSpecialFeature> optionalDetailSpecialFeature = this.detailCtr.calculateDetailSpecialFeature(bullet, hitResult);

        // 3. then
        assertTrue(optionalDetailSpecialFeature.isEmpty());
    }

    // 無觸發特殊事件，回傳Empty
    @Test
    void test_given_noTriggerSpecialFeature_when_calculateDetailSpecialFeature_then_return_empty() {
        // 1. given
        Bullet bullet = new Bullet(2, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree(), 0, 10);
        HitResult hitResult = new HitResult(true, 2, 20.0, 40.0, 4.0, ConstMathFish.AwardBulletType.NONE, new AwardBulletExtendNone(), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, new SpecialFeatureResultExtendRedEnvelope(), ConstMathFish.HitType.FIXED_ODDS, new HitResultExtendFixedOdds());

        // 2. when
        Optional<DetailSpecialFeature> optionalDetailSpecialFeature = this.detailCtr.calculateDetailSpecialFeature(bullet, hitResult);

        // 3. then
        assertTrue(optionalDetailSpecialFeature.isEmpty());
    }

    // 觸發特殊事件，回傳正確資訊
    @Test
    void test_given_triggerSpecialFeature_when_calculateDetailSpecialFeature_then_return_DetailSpecialFeature() {
        // 1. given
        Bullet bullet = new Bullet(2, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree(), 0, 10);
        SpecialFeatureResultExtendRedEnvelope specialFeatureResultExtendRedEnvelope = new SpecialFeatureResultExtendRedEnvelope(true, 1, 20, 20, 1, new int[0], new double[0]);
        HitResult hitResult = new HitResult(true, 2, 20.0, 60.0, 6.0, ConstMathFish.AwardBulletType.NONE, new AwardBulletExtendNone(), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, specialFeatureResultExtendRedEnvelope, ConstMathFish.HitType.FIXED_ODDS, new HitResultExtendFixedOdds());
        Optional<DetailSpecialFeature> expectResult = Optional.of(new DetailSpecialFeature(ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE.getName(), 1, 2, this.config.getBulletIndexNameMap().get(bullet.getBulletIndex()), bullet.getBet(), bullet.getCost()));

        // 2. when
        Optional<DetailSpecialFeature> optionalDetailSpecialFeature = this.detailCtr.calculateDetailSpecialFeature(bullet, hitResult);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult.get());
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(optionalDetailSpecialFeature.get());
        assertEquals(expectString, actualString);
    }

    //* 返還結果詳細資訊 *//
    // 無返還，回傳Empty
    @Test
    void test_given_noReturnValue_when_calculateDetailFishReturn_then_return_empty() {
        // 1. given
        Mockito.when(mockBulletMgr.calculateReturnValue()).thenReturn(0.0);

        // 2. when
        Optional<DetailFishReturn> optionalDetailFishReturn = this.detailCtr.calculateDetailFishReturn(mockBulletMgr);

        // 3. then
        assertTrue(optionalDetailFishReturn.isEmpty());
    }

    // 有返還，回傳正確資訊
    @Test
    void test_given_returnValue_when_calculateDetailFishReturn_then_return_DetailFishReturn() {
        // 1. given
        Mockito.when(mockBulletMgr.calculateReturnValue()).thenReturn(30.0);
        Mockito.when(mockBulletMgr.getObtainedAwardBulletIndex()).thenReturn(2);
        Mockito.when(mockBulletMgr.getRecordAwardBulletAmount()).thenReturn(2);
        Mockito.when(mockBulletMgr.getObtainedAwardBulletBet()).thenReturn(15.0);
        Mockito.when(mockBulletMgr.getObtainedAwardBulletCost()).thenReturn(0.0);
        Optional<DetailFishReturn> expectResult = Optional.of(new DetailFishReturn(this.config.getBulletIndexNameMap().get(2), 2, 15, 0));

        // 2. when
        Optional<DetailFishReturn> optionalDetailFishReturn = this.detailCtr.calculateDetailFishReturn(mockBulletMgr);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult.get());
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(optionalDetailFishReturn.get());
        assertEquals(expectString, actualString);
    }

    // 產生設定檔
    private DetailCtrConfig generateDetailCtrConfig() {
        // 1. 產出子彈代碼名稱對應表
        Map<Integer, String> bulletIndexNameMap = ConstCsbyJava.BulletEnumCsbyJava.getBulletIndexStringMap();

        // 2. 產出目標代碼名稱對應表
        Map<Integer, String> targetIndexNameMap = ConstCsbyJava.TargetEnumCsbyJava.getTargetIndexStringMap();

        return new DetailCtrConfig(bulletIndexNameMap, targetIndexNameMap);
    }
}
package org.lsj.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.module;

import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.enity.DisplayResult;
import org.lsj.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.enity.DisplayResultCollection;
import org.lsj.gs.math.core.fish.clientHitResultPgr.displayResultCollectionCtr.enity.displayResultExtend.DisplayResultExtendRedEnvelope;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendFixedOdds;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendRedEnvelope;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.client.awardBullet.AwardBulletExtendNone;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureResultExtend.SpecialFeatureResultExtendNone;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureResultExtend.SpecialFeatureResultExtendRedEnvelope;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// 表演結果聚合計算者測試
@ExtendWith(MockitoExtension.class)
class DisplayResultCollectionCtrTest {
    DisplayResultCollectionCtr displayResultCollectionCtr; // 表演結果聚合計算者

    // 無特殊事件、無額外打擊事件，回傳空陣列
    @Test
    void test_given_noSpecialFeature_and_noExtendHit_when_calculateDisplayResultCollection_then_return_empty_array() {
        // 1. given
        this.displayResultCollectionCtr = new DisplayResultCollectionCtr();
        HitResultExtendFixedOdds hitResultExtend = new HitResultExtendFixedOdds();
        SpecialFeatureResultExtendNone specialFeatureResult = new SpecialFeatureResultExtendNone();
        HitResult hitResult = new HitResult(false, 0, 0.0, 0.0, 0.0, ConstMathFish.AwardBulletType.NONE, new AwardBulletExtendNone(), ConstMathFish.SpecialFeatureEnumType.NONE, specialFeatureResult, ConstMathFish.HitType.FIXED_ODDS, hitResultExtend);

        // 2. when
        DisplayResultCollection displayResultCollection = this.displayResultCollectionCtr.calculateDisplayResultCollection(hitResult);

        // 3. then
        assertTrue(displayResultCollection.getDisplayResultArray().length == 0);
    }

    // 特殊事件、無額外打擊事件，回傳正確資訊
    @Test
    void test_given_specialFeature_and_noExtendHit_when_calculateDisplayResultCollection_then_return_correct_result() {
        // 1. given
        this.displayResultCollectionCtr = new DisplayResultCollectionCtr();
        HitResultExtendFixedOdds hitResultExtend = new HitResultExtendFixedOdds();
        SpecialFeatureResultExtendRedEnvelope specialFeatureResult = new SpecialFeatureResultExtendRedEnvelope(true, 1, 20.0, 20.0, 1, new int[]{2, 1}, new double[]{20, 10});
        HitResult hitResult = new HitResult(false, 0, 0.0, 20.0, 20.0, ConstMathFish.AwardBulletType.NONE, new AwardBulletExtendNone(), ConstMathFish.SpecialFeatureEnumType.RED_ENVELOPE, specialFeatureResult, ConstMathFish.HitType.FIXED_ODDS, hitResultExtend);
        DisplayResultCollection expectResult = new DisplayResultCollection(new DisplayResult[]{new DisplayResult(ConstMathFish.DisplayType.RED_ENVELOPE, new DisplayResultExtendRedEnvelope(specialFeatureResult))});

        // 2. when
        DisplayResultCollection displayResultCollection = this.displayResultCollectionCtr.calculateDisplayResultCollection(hitResult);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(displayResultCollection);
        assertEquals(expectString, actualString);
    }

    // 無特殊事件、額外打擊事件，回傳正確資訊
    @Test
    void test_given_noSpecialFeature_and_extendHit_when_calculateDisplayResultCollection_then_return_correct_result() {
        // 1. given
        this.displayResultCollectionCtr = new DisplayResultCollectionCtr();
        HitResultExtendRedEnvelope hitResultExtend = new HitResultExtendRedEnvelope(1, new int[]{2, 1}, new double[]{20, 10});
        SpecialFeatureResultExtendNone specialFeatureResult = new SpecialFeatureResultExtendNone();
        HitResult hitResult = new HitResult(true, 1, 20.0, 20.0, 20.0, ConstMathFish.AwardBulletType.NONE, new AwardBulletExtendNone(), ConstMathFish.SpecialFeatureEnumType.NONE, specialFeatureResult, ConstMathFish.HitType.RED_ENVELOPE, hitResultExtend);
        DisplayResultCollection expectResult = new DisplayResultCollection(new DisplayResult[]{new DisplayResult(ConstMathFish.DisplayType.RED_ENVELOPE, new DisplayResultExtendRedEnvelope(1, 20.0, hitResultExtend))});

        // 2. when
        DisplayResultCollection displayResultCollection = this.displayResultCollectionCtr.calculateDisplayResultCollection(hitResult);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(displayResultCollection);
        assertEquals(expectString, actualString);
    }
}
package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr;

import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.entity.PoolConfig;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.hitTypeExtend.HitTypeExtendMultiTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitCtrConfig;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendMultiTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.RobotHitResultLimitBase;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrConfigExtend.AwardBulletGtrConfigExtendNone;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.rtpChoiceHlr.enity.RtpChoiceHlrConfigExtendCompanyAdjust;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureCtrConfigExtend.SpecialFeatureCtrConfigExtendNone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// 多重目標打擊計算器測試
@ExtendWith(MockitoExtension.class)
class HitCtrMultiTargetTest {
    HitCtrMultiTarget hitCtrMultiTarget; // 多重目標打擊計算器
    @Mock HitCtrConfig mockConfig; // 打擊計算器設定
    @Mock
    GamePlayerHlr mockGamePlayerHlr; // 遊戲玩家處理器
    @Mock
    PoolCtr mockPoolCtr; // 水池控制器
    @Mock ITableUtil mockTableUtil; // 牌桌工具包

    @BeforeEach
    void setUp() {
        this.mockHitCtrMembers();
    }

    // 給定 null 目標訊息，當檢查打擊訊息，預期回傳 false
    @Test
    void test_given_null_targetCountMap_when_checkComplete_then_return_false() {
        // 1. given
        Mockito.when(mockConfig.getHitTypeConfigExtend()).thenReturn(new HitTypeConfigExtendMultiTarget(new HashMap<>(), new RobotHitResultLimitBase(100, 500)));
        this.hitCtrMultiTarget = new HitCtrMultiTarget(mockConfig, mockGamePlayerHlr, mockPoolCtr, mockTableUtil);
        HitTypeExtendMultiTarget hitTypeExtendMultiTarget = new HitTypeExtendMultiTarget(null);

        // 2. when
        boolean result = this.hitCtrMultiTarget.checkComplete(hitTypeExtendMultiTarget);

        // 3. then
        assertFalse(result);
    }

    // 給定空map，當檢查打擊訊息，預期回傳 false
    @Test
    void test_given_sizeZero_targetCountMap_when_checkComplete_then_return_false() {
        // 1. given
        Mockito.when(mockConfig.getHitTypeConfigExtend()).thenReturn(new HitTypeConfigExtendMultiTarget(new HashMap<>(), new RobotHitResultLimitBase(100, 500)));
        this.hitCtrMultiTarget = new HitCtrMultiTarget(mockConfig, mockGamePlayerHlr, mockPoolCtr, mockTableUtil);
        HitTypeExtendMultiTarget hitTypeExtendMultiTarget = new HitTypeExtendMultiTarget(new HashMap<>());

        // 2. when
        boolean result = this.hitCtrMultiTarget.checkComplete(hitTypeExtendMultiTarget);

        // 3. then
        assertTrue(result);
    }

    // 給定不存在目標，當檢查打擊訊息，預期回傳 false
    @Test
    void test_given_nonExistTarget_when_checkComplete_then_return_false() {
        // 1. given
        Mockito.when(mockConfig.getHitTypeConfigExtend()).thenReturn(new HitTypeConfigExtendMultiTarget(new HashMap<>(){{put(1,2);put(2,4);put(3,6);put(4,8);put(5,10);}}, new RobotHitResultLimitBase(100, 500)));
        this.hitCtrMultiTarget = new HitCtrMultiTarget(mockConfig, mockGamePlayerHlr, mockPoolCtr, mockTableUtil);
        HitTypeExtendMultiTarget hitTypeExtendMultiTarget = new HitTypeExtendMultiTarget(new HashMap<>(){{put(6,3);}});

        // 2. when
        boolean result = this.hitCtrMultiTarget.checkComplete(hitTypeExtendMultiTarget);

        // 3. then
        assertFalse(result);
    }

    // 給定目標數量負數，當檢查打擊訊息，預期回傳 false
    @Test
    void test_given_negativeTargetAmount_when_checkComplete_then_return_false() {
        // 1. given
        Mockito.when(mockConfig.getHitTypeConfigExtend()).thenReturn(new HitTypeConfigExtendMultiTarget(new HashMap<>(){{put(1,2);put(2,4);put(3,6);put(4,8);put(5,10);}}, new RobotHitResultLimitBase(100, 500)));
        this.hitCtrMultiTarget = new HitCtrMultiTarget(mockConfig, mockGamePlayerHlr, mockPoolCtr, mockTableUtil);
        HitTypeExtendMultiTarget hitTypeExtendMultiTarget = new HitTypeExtendMultiTarget(new HashMap<>(){{put(2,-2);}});

        // 2. when
        boolean result = this.hitCtrMultiTarget.checkComplete(hitTypeExtendMultiTarget);

        // 3. then
        assertFalse(result);
    }

    // mock
    private void mockHitCtrMembers() {
        Mockito.when(mockConfig.getHitType()).thenReturn(ConstMathFish.HitType.MULTI_TARGET);

        Mockito.when(mockConfig.getAwardBulletType()).thenReturn(ConstMathFish.AwardBulletType.NONE);
        Mockito.when(mockConfig.getAwardBulletGtrConfigExtend()).thenReturn(new AwardBulletGtrConfigExtendNone());

        Mockito.when(mockConfig.getSpecialFeatureType()).thenReturn(ConstMathFish.SpecialFeatureEnumType.NONE);
        Mockito.when(mockConfig.getSpecialFeatureCtrConfigExtend()).thenReturn(new SpecialFeatureCtrConfigExtendNone());

        Mockito.when(mockConfig.getRtpChoiceType()).thenReturn(ConstMathFish.RtpChoiceType.COMPANY_ADJUST);
        Mockito.when(mockConfig.getRtpChoiceHlrConfigExtend()).thenReturn(new RtpChoiceHlrConfigExtendCompanyAdjust());

        Mockito.when(mockPoolCtr.getPoolConfig()).thenReturn(new PoolConfig(null, null));
    }
}
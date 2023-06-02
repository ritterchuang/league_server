package org.lsj.gs.math.core.fish.hitCtrMgr.module;

import org.lsj.gs.math.core.common.ConstMathCommon;
import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.math.core.common.gamePlayerHlr.entity.GamePlayer;
import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.entity.PoolConfig;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.fish.ConstMathFish;
import org.lsj.gs.math.core.fish.bulletMgr.BulletMgr;
import org.lsj.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.client.BulletCostExtendFree;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.client.BulletCostExtendRatio;
import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.enity.client.BulletTypeExtendNormal;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.ClientHit;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitCtrConfig;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitCtrMgrConfig;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.HitResult;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendFixedOdds;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendFixedOdds;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.client.awardBullet.AwardBulletExtendNone;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrConfigExtend.AwardBulletGtrConfigExtendNone;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.rtpChoiceHlr.enity.RtpChoiceHlrConfigExtendCompanyAdjust;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureCtrConfigExtend.SpecialFeatureCtrConfigExtendNone;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.specialFeatureCtr.enity.specialFeatureResultExtend.SpecialFeatureResultExtendNone;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 打擊計算器管理者測試
@ExtendWith(MockitoExtension.class)
class HitCtrMgrTest {
    HitCtrMgr hitCtrMgr; // 打擊計算器管理者
    HitCtrMgrConfig config; // 打擊計算器管理者
    @Mock GamePlayerHlr mockGamePlayerHlr; // 遊戲玩家處理器
    @Mock PoolCtr mockPoolCtr; // 水池控制器
    @Mock ITableUtil mockTableUtil; // 牌桌工具包
    @Mock GamePlayer mockGamePlayer; // 玩家
    @Mock BulletMgr mockBulletMgr; // 子彈管理者


    @BeforeEach
    void setUp() {
        this.config = new HitCtrMgrConfig(new HashMap<>(){
            {put(1, new HashMap<>(){
                {put(1, new HitCtrConfig(ConstMathFish.HitType.FIXED_ODDS, new HitTypeConfigExtendFixedOdds(2),
                        ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureCtrConfigExtendNone(),
                        ConstMathFish.AwardBulletType.NONE, new AwardBulletGtrConfigExtendNone(),
                        ConstMathFish.RtpChoiceType.COMPANY_ADJUST, new RtpChoiceHlrConfigExtendCompanyAdjust()));}
            });}
        });

        Mockito.when(mockPoolCtr.getPoolConfig()).thenReturn(new PoolConfig(null, null));
        this.hitCtrMgr = new HitCtrMgr(this.config, mockGamePlayerHlr, mockPoolCtr, mockTableUtil);
    }

    // 給定不存在子彈，預期噴錯
    @Test
    void test_given_nonExistBullet_when_checkClientHit_then_return_errorCode() {
        // 1. given
        ClientBullet clientBullet = new ClientBullet();
        ClientTarget clientTarget = new ClientTarget();
        ClientHit clientHit = new ClientHit();

        // 2. when
        ConstMathCommon.TableProtocolCode tableProtocolCode = this.hitCtrMgr.checkClientHit(clientBullet, clientTarget, clientHit);

        // 3. then
        assertEquals(tableProtocolCode, ConstMathCommon.TableProtocolCode.FISH_HIT_NOT_EXIST);
    }

    // 給定不存在 <子彈、目標> 對應，預期噴錯
    @Test
    void test_given_nonExistBulletAndTarget_when_checkClientHit_then_return_errorCode() {
        // 1. given
        ClientBullet clientBullet = new ClientBullet(1, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.RATIO, new BulletCostExtendRatio());
        ClientTarget clientTarget = new ClientTarget(3);
        ClientHit clientHit = new ClientHit();

        // 2. when
        ConstMathCommon.TableProtocolCode tableProtocolCode = this.hitCtrMgr.checkClientHit(clientBullet, clientTarget, clientHit);

        // 3. then
        assertEquals(tableProtocolCode, ConstMathCommon.TableProtocolCode.FISH_HIT_NOT_EXIST);
    }

    // 給定錯誤打擊類型，預期噴錯
    @Test
    void test_given_errorHitType_when_checkClientHit_then_return_errorCode() {
        // 1. given
        ClientBullet clientBullet = new ClientBullet(1, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.RATIO, new BulletCostExtendRatio());
        ClientTarget clientTarget = new ClientTarget(1);
        ClientHit clientHit = new ClientHit();

        // 2. when
        ConstMathCommon.TableProtocolCode tableProtocolCode = this.hitCtrMgr.checkClientHit(clientBullet, clientTarget, clientHit);

        // 3. then
        assertEquals(tableProtocolCode, ConstMathCommon.TableProtocolCode.FISH_HIT_NOT_CONSISTENCY);
    }

    // 給定錯誤打擊額外設定，預期噴錯
    @Test
    void test_given_errorHitTypeExtend_when_checkClientHit_then_return_errorCode() {
        // 1. given
        ClientBullet clientBullet = new ClientBullet(1, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.RATIO, new BulletCostExtendRatio());
        ClientTarget clientTarget = new ClientTarget(1);
        ClientHit clientHit = new ClientHit(ConstMathFish.HitType.FIXED_ODDS, null);

        // 2. when
        ConstMathCommon.TableProtocolCode tableProtocolCode = this.hitCtrMgr.checkClientHit(clientBullet, clientTarget, clientHit);

        // 3. then
        assertEquals(tableProtocolCode, ConstMathCommon.TableProtocolCode.FISH_HIT_NOT_COMPLETE);
    }

    // 給定免費子彈打死魚，計算玩家得分對應表，預期正確
    @Test
    void test_given_killFishResultWithFreeBullet_when_calculateUidScoreMap_then_correct() {
       // 1. given
       Mockito.when(mockGamePlayer.getUid()).thenReturn(12345);
       Mockito.when(mockGamePlayer.getChairIndex()).thenReturn(0);

       Bullet bullet = new Bullet(0, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree(), 0, 10);
       HitResult hitResult = new HitResult(true, 1, 100.0, 100.0, 10.0, ConstMathFish.AwardBulletType.NONE, new AwardBulletExtendNone(), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureResultExtendNone(), ConstMathFish.HitType.FIXED_ODDS, new HitResultExtendFixedOdds());

       // 2. when
       Map<Integer, UidScore> actualResult = this.hitCtrMgr.calculateUidScoreMap(mockGamePlayer, bullet, hitResult);
       Map<Integer, UidScore> expectResult = new HashMap<>(){
           {
               put(0, new UidScore(0, 12345, 0, 0, 100.0, 100.0, 0));
           }
       };

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定一般子彈打死魚，計算玩家得分對應表，預期正確
    @Test
    void test_given_killFishResultWithRatioBullet_when_calculateUidScoreMap_then_correct() {
        // 1. given
        Mockito.when(mockGamePlayer.getUid()).thenReturn(12345);
        Mockito.when(mockGamePlayer.getChairIndex()).thenReturn(0);

        Bullet bullet = new Bullet(0, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.RATIO, new BulletCostExtendRatio(10), 10, 10);
        HitResult hitResult = new HitResult(true, 1, 100.0, 100.0, 10.0, ConstMathFish.AwardBulletType.NONE, new AwardBulletExtendNone(), ConstMathFish.SpecialFeatureEnumType.NONE, new SpecialFeatureResultExtendNone(), ConstMathFish.HitType.FIXED_ODDS, new HitResultExtendFixedOdds());

        // 2. when
        Map<Integer, UidScore> actualResult = this.hitCtrMgr.calculateUidScoreMap(mockGamePlayer, bullet, hitResult);
        Map<Integer, UidScore> expectResult = new HashMap<>(){
            {
                put(0, new UidScore(0, 12345, 10, 10, 100.0, 90.0, 0));
            }
        };

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定免費子彈打死魚，計算玩家返還得分，預期正確
    @Test
    void test_given_killFishResultWithFreeBullet_when_calculateReturnUidScore_then_correct() {
        // 1. given
        Mockito.when(mockGamePlayer.getUid()).thenReturn(12345);
        Mockito.when(mockGamePlayer.getChairIndex()).thenReturn(0);
        Mockito.when(mockBulletMgr.getObtainedAwardBulletCost()).thenReturn(0.0);
        double returnValue = 100.0;


        // 2. when
        UidScore actualResult = this.hitCtrMgr.calculateReturnUidScore(mockBulletMgr, mockGamePlayer, returnValue);
        UidScore expectResult = new UidScore(0, 12345, 0, 0, 100.0, 100.0, 0);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }
}
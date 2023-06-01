package com.lx.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.module.awardBulletCtr;

import com.lx.gs.math.core.common.table.module.tableUtil.ITableUtil;
import com.lx.gs.math.core.common.table.module.tableUtil.random.IRandomUtilMain;
import com.lx.gs.math.core.fish.ConstMathFish;
import com.lx.gs.math.core.fish.bulletMgr.BulletMgr;
import com.lx.gs.math.core.fish.bulletMgr.entity.client.ClientBullet;
import com.lx.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import com.lx.gs.math.core.fish.bulletMgr.module.bulletMtr.BulletMtr;
import com.lx.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.client.BulletCostExtendFree;
import com.lx.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.client.BulletCostExtendRatio;
import com.lx.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletTypeHlr.enity.client.BulletTypeExtendNormal;
import com.lx.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrConfigExtend.awardBulletCtrConfigExtend.AwardBulletCtrConfigExtendNormal;
import com.lx.gs.math.core.fish.hitCtrMgr.module.hitCtr.awardBulletGtr.enity.sever.awardBulletGtrResult.awardBulletCtrResult.AwardBulletCtrResult;
import com.lx.utils.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 獎勵子彈計算者一般測試
@ExtendWith(MockitoExtension.class)
class AwardBulletCtrNormalTest {
    AwardBulletCtrNormal awardBulletCtrNormal; // 獎勵子彈計算者一般
    @Mock BulletMgr mockBulletMgr; // 子彈管理者
    @Mock BulletMtr mockBulletMtr; // 子彈仲介者
    @Mock ITableUtil mockTableUtil; // 牌桌工具包
    @Mock IRandomUtilMain mockRandomUtilMain; // 隨機工具包

    @BeforeEach
    void setUp() {
        this.awardBulletCtrNormal = new AwardBulletCtrNormal(ConstMathFish.AwardBulletCtrType.NORMAL, new AwardBulletCtrConfigExtendNormal(), mockTableUtil);
    }

    // 無擊殺，不回傳獎勵子彈資訊
    @Test
    void test_given_noKill_when_calculateAwardBulletResult_then_return_result_amount_zero() {
        // 1. given
        int killCount = 0;
        Bullet currentBullet = new Bullet(1, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.RATIO, new BulletCostExtendRatio(10.0), 10.0, 10.0);

        // 2. when
        AwardBulletCtrResult awardBulletCtrResult = this.awardBulletCtrNormal.calculateAwardBulletCtrResult(killCount, 100, 1, currentBullet, mockBulletMgr);
        AwardBulletCtrResult expectResult = new AwardBulletCtrResult(new ClientBullet(), 0);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(awardBulletCtrResult);
        assertEquals(expectString, actualString);
    }


    // 擊殺，獎勵個數不會超過最大上限
    @Test
    void test_given_killOneTime_when_calculateAwardBulletResult_then_return_result_amount_smallerThanMaxLimit() {
        // 1. given
        int killCount = 1;
        Bullet currentBullet = new Bullet(2, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree(), 0.0, 10.0);
        ClientBullet awardClientBullet = new ClientBullet(2, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree());
        this.updateMockAwardBulletAmount();
        Mockito.when(mockBulletMgr.getRecordAwardBulletAmount()).thenReturn(90);
        Mockito.when(mockBulletMgr.calculateAwardClientBullet(Mockito.anyInt())).thenReturn(awardClientBullet);

        // 2. when
        AwardBulletCtrResult awardBulletCtrResult = this.awardBulletCtrNormal.calculateAwardBulletCtrResult(killCount, 100, 2, currentBullet, mockBulletMgr);
        AwardBulletCtrResult expectResult = new AwardBulletCtrResult(awardClientBullet, 11);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(awardBulletCtrResult);
        assertEquals(expectString, actualString);
    }

    // 擊殺次數2，獎勵個數不會超過最大上限
    @Test
    void test_given_killTwoTime_when_calculateAwardBulletResult_then_return_result_amount_smallerThanMaxLimit() {
        // 1. given
        int killCount = 2;
        Bullet currentBullet = new Bullet(2, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree(), 0.0, 10.0);
        ClientBullet awardClientBullet = new ClientBullet(2, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree());
        this.updateMockAwardBulletAmount();
        Mockito.when(mockBulletMgr.getRecordAwardBulletAmount()).thenReturn(90);
        Mockito.when(mockBulletMgr.calculateAwardClientBullet(Mockito.anyInt())).thenReturn(awardClientBullet);

        // 2. when
        AwardBulletCtrResult awardBulletCtrResult = this.awardBulletCtrNormal.calculateAwardBulletCtrResult(killCount, 100, 2, currentBullet, mockBulletMgr);
        AwardBulletCtrResult expectResult = new AwardBulletCtrResult(awardClientBullet, 11);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(awardBulletCtrResult);
        assertEquals(expectString, actualString);
    }

    // 更新獎勵子彈計算者記錄數量
    private void updateMockAwardBulletAmount() {
        // 1. mock
        Mockito.when(mockTableUtil.getMainRandomUtil()).thenReturn(mockRandomUtilMain);
        Mockito.when(mockRandomUtilMain.getArrayIndexByWeightWithAccuracy(Mockito.anyList(), Mockito.any())).thenReturn(0);
        Mockito.when(mockBulletMgr.getBulletMtr(Mockito.anyInt())).thenReturn(mockBulletMtr);
        Mockito.when(mockBulletMtr.getBulletRtp()).thenReturn(0.0);
        Bullet currentBullet = new Bullet(2, ConstMathFish.BulletType.NORMAL, new BulletTypeExtendNormal(), ConstMathFish.BulletCostType.FREE, new BulletCostExtendFree(), 0, 10.0);

        // 2. 計算預期倍數
        double expectOdds = this.awardBulletCtrNormal.calculateExpectTotalOdds(100, new int[]{20}, new int[]{1}, 2, currentBullet, mockBulletMgr);
    }
}
package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.module;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.MathRandomUtil;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.clientTarget.ClientTarget;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.hitTypeExtend.HitTypeExtendDragonTreasure;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendDragonTreasure;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendDragonTreasure;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.HitOddsInfo;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.hitOddsInfoExtend.HitOddsInfoExtendDragonTreasure;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 神龍尋珠打擊計算器工具包測試
@ExtendWith(MockitoExtension.class)
class HitCtrUtilDragonTreasureTest {
    HitCtrUtilDragonTreasure hitCtrUtilDragonTreasure;
    @Mock
    HitTypeConfigExtendDragonTreasure mockConfig;
    @Mock
    ITableUtil mockTableUtil; // 牌桌工具包
    @Spy
    MathRandomUtil spyRandomUtil; // 隨機工具包
    @Mock
    Bullet mockBullet; // 子彈

    @BeforeEach
    void setUp() {
        this.hitCtrUtilDragonTreasure = new HitCtrUtilDragonTreasure(mockConfig, mockTableUtil);
    }

    // 給定沒神龍條件，計算倍數，回傳正確結果
    @Test
    void test_given_noTriggerDragon_when_calculateHitOddsInfo_then_return_correctResult() {
        // 1. given
        HitTypeExtendDragonTreasure hitTypeExtendDragonTreasure = new HitTypeExtendDragonTreasure();
        Mockito.when(mockTableUtil.getMainRandomUtil()).thenReturn(spyRandomUtil);
        Mockito.when(this.mockConfig.getDragonBallOddsArray()).thenReturn(new int[]{1, 2, 3, 4, 5, 6, 7});
        Mockito.when(this.mockConfig.getDragonBallEndCountAndWeightArray()).thenReturn(new int[][]{{3, 7}, {0, 1}});
        Mockito.when(this.mockConfig.getEndBallCountToShowOddsWeightArrayMap()).thenReturn(new HashMap<>(){
            {
                put(3, new int[]{1,1,1});
                put(7, new int[]{1,0});
            }
        });
        Mockito.when(this.mockConfig.getEndBallCountToShowOddsListMap()).thenReturn(new HashMap<>(){
            {
                put(3, new ArrayList<>(){
                    {
                        add(new int[]{3, 3, 4});
                        add(new int[]{5, 5, 4});
                        add(new int[]{6, 6, 4});
                    }
                });
                put(7, new ArrayList<>(){
                    {
                        add(new int[]{2, 2, 3, 4, 5, 6, 7});
                        add(new int[]{1, 2, 3, 4, 5, 6, 7});
                    }
                });
            }
        });

        // 2. when
        HitOddsInfo expectResult = new HitOddsInfo(203, new HitOddsInfoExtendDragonTreasure(new int[]{2, 2, 3, 4, 5, 6, 7}, 7, 1));
        HitOddsInfo actualResult = this.hitCtrUtilDragonTreasure.calculateHitOddsInfo(hitTypeExtendDragonTreasure, new ClientTarget());

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定神龍條件，計算倍數，回傳正確結果
    @Test
    void test_given_triggerDragon_when_calculateHitOddsInfo_then_return_correctResult() {
        // 1. given
        HitTypeExtendDragonTreasure hitTypeExtendDragonTreasure = new HitTypeExtendDragonTreasure();
        Mockito.when(mockTableUtil.getMainRandomUtil()).thenReturn(spyRandomUtil);
        Mockito.when(this.mockConfig.getDragonBallOddsArray()).thenReturn(new int[]{1, 2, 3, 4, 5, 6, 7});
        Mockito.when(this.mockConfig.getDragonBallEndCountAndWeightArray()).thenReturn(new int[][]{{3, 7}, {0, 1}});
        Mockito.when(this.mockConfig.getDragonExtraMultiplierAndWeightArray()).thenReturn(new int[][]{{2, 4}, {0, 1}});
        Mockito.when(this.mockConfig.getEndBallCountToShowOddsWeightArrayMap()).thenReturn(new HashMap<>(){
            {
                put(3, new int[]{1,1,1});
                put(7, new int[]{0,1});
            }
        });
        Mockito.when(this.mockConfig.getEndBallCountToShowOddsListMap()).thenReturn(new HashMap<>(){
            {
                put(3, new ArrayList<>(){
                    {
                        add(new int[]{3, 3, 4});
                        add(new int[]{5, 5, 4});
                        add(new int[]{6, 6, 4});
                    }
                });
                put(7, new ArrayList<>(){
                    {
                        add(new int[]{2, 2, 3, 4, 5, 6, 7});
                        add(new int[]{1, 2, 3, 4, 5, 6, 7});
                    }
                });
            }
        });

        // 2. when
        HitOddsInfo expectResult = new HitOddsInfo(784, new HitOddsInfoExtendDragonTreasure(new int[]{1, 2, 3, 4, 5, 6, 7}, 7, 4));
        HitOddsInfo actualResult = this.hitCtrUtilDragonTreasure.calculateHitOddsInfo(hitTypeExtendDragonTreasure, new ClientTarget());

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定未擊殺，回傳空殼
    @Test
    void test_given_noKill_when_calculateHitResultExtend_then_return_empty_result() {
        // 1. given
        HitTypeExtendDragonTreasure hitTypeExtendDragonTreasure = new HitTypeExtendDragonTreasure();

        // 2. when
        HitResultExtendDragonTreasure expectResult = new HitResultExtendDragonTreasure();
        HitResultExtendDragonTreasure actualResult = this.hitCtrUtilDragonTreasure.calculateHitResultExtend(false, new HitOddsInfo(), hitTypeExtendDragonTreasure, mockBullet);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定擊殺為觸發神龍，回傳正確結果
    @Test
    void test_given_killAndNoTrigger_when_calculateHitResultExtend_then_return_correct_result() {
        // 1. given
        HitTypeExtendDragonTreasure hitTypeExtendDragonTreasure = new HitTypeExtendDragonTreasure();
        Mockito.when(mockTableUtil.getMainRandomUtil()).thenReturn(spyRandomUtil);
        Mockito.when(this.mockConfig.getDragonBallOddsArray()).thenReturn(new int[]{1, 2, 3, 4, 5, 6, 7});
        Mockito.doNothing().when(this.spyRandomUtil).shuffleList(Mockito.anyList());
        HitOddsInfo hitOddsInfo = new HitOddsInfo(203, new HitOddsInfoExtendDragonTreasure(new int[]{2, 2, 3, 4, 5, 6, 7}, 7, 1));

        // 2. when
        HitResultExtendDragonTreasure expectResult = new HitResultExtendDragonTreasure(false, 7, 7, 1, 203, new int[]{2, 3, 4, 5, 6, 7, 2});
        HitResultExtendDragonTreasure actualResult = this.hitCtrUtilDragonTreasure.calculateHitResultExtend(true, hitOddsInfo, hitTypeExtendDragonTreasure, mockBullet);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定擊殺為觸發神龍，回傳正確結果
    @Test
    void test_given_killAndTrigger_when_calculateHitResultExtend_then_return_correct_result() {
        // 1. given
        HitTypeExtendDragonTreasure hitTypeExtendDragonTreasure = new HitTypeExtendDragonTreasure();
        Mockito.when(mockTableUtil.getMainRandomUtil()).thenReturn(spyRandomUtil);
        Mockito.doNothing().when(this.spyRandomUtil).shuffleList(Mockito.anyList());
        HitOddsInfo hitOddsInfo = new HitOddsInfo(784, new HitOddsInfoExtendDragonTreasure(new int[]{1, 2, 3, 4, 5, 6, 7}, 7, 4));

        // 2. when
        HitResultExtendDragonTreasure expectResult = new HitResultExtendDragonTreasure(true, 7, 7, 4, 784, new int[]{1, 2, 3, 4, 5, 6, 7});
        HitResultExtendDragonTreasure actualResult = this.hitCtrUtilDragonTreasure.calculateHitResultExtend(true, hitOddsInfo, hitTypeExtendDragonTreasure, mockBullet);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }
}
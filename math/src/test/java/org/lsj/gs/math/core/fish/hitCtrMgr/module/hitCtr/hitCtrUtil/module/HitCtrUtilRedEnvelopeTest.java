package org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.module;

import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.common.table.module.tableUtil.random.MathRandomUtil;
import org.lsj.gs.math.core.fish.bulletMgr.entity.server.Bullet;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.client.hitTypeExtend.HitTypeExtendRedEnvelope;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitResultExtend.HitResultExtendRedEnvelope;
import org.lsj.gs.math.core.fish.hitCtrMgr.entity.server.hitTypeConfigExtend.HitTypeConfigExtendRedEnvelope;
import org.lsj.gs.math.core.fish.hitCtrMgr.module.hitCtr.hitCtrUtil.enity.HitOddsInfo;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 紅包目標打擊計算器工具包測試
@ExtendWith(MockitoExtension.class)
class HitCtrUtilRedEnvelopeTest {
    HitCtrUtilRedEnvelope hitCtrUtilRedEnvelope; // 紅包打擊計算器工具包
    @Mock
    HitTypeConfigExtendRedEnvelope mockConfig; // 紅包設定檔
    @Mock
    ITableUtil mockTableUtil; // 牌桌工具包
    @Spy
    MathRandomUtil spyRandomUtil; // 隨機工具包
    @Mock
    Bullet mockBullet; // 子彈

    @BeforeEach
    void setUp() {
        this.hitCtrUtilRedEnvelope = new HitCtrUtilRedEnvelope(mockConfig, mockTableUtil);
    }

    // 給定未擊殺，回傳空殼
    @Test
    void test_given_noKill_when_calculateHitResultExtend_return_empty_result() {
        // 1. given
        HitTypeExtendRedEnvelope hitTypeExtendRedEnvelope = new HitTypeExtendRedEnvelope();

        // 2. when
        HitResultExtendRedEnvelope actualResult = this.hitCtrUtilRedEnvelope.calculateHitResultExtend(false, new HitOddsInfo(), hitTypeExtendRedEnvelope, mockBullet);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(new HitResultExtendRedEnvelope());
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定表演個數 5，當擊殺只有5個選項，會帶出五個表演選項，第一個為結果
    @Test
    void test_given_killAndExactShowCount_when_adjustShowOddsList_return_correctResult() {
        // 1. given
        int showCount = 5;
        List<Integer> showOddsList = new ArrayList<>(){{
            add(10);
            add(20);
            add(30);
            add(40);
            add(50);
        }};

        // 2. when
        this.hitCtrUtilRedEnvelope.adjustShowOddsList(showOddsList, showCount);

        // 3. then
        assertEquals(showOddsList.size(), showCount);
    }

    // 給定表演個數 5，當擊殺只有4個選項，會帶出五個表演選項，第一個為結果
    @Test
    void test_given_killAndLessShowCount_when_adjustShowOddsList_return_correctResultCount() {
        // 1. given
        int showCount = 5;
        int[] awardOddsArray = new int[]{10, 20, 30, 40, 25, 35};
        Mockito.when(mockConfig.getAwardOddsArray()).thenReturn(awardOddsArray);
        Mockito.when(mockTableUtil.getMainRandomUtil()).thenReturn(spyRandomUtil);
        List<Integer> showOddsList = new ArrayList<>(){{
            add(10);
            add(20);
            add(30);
            add(40);
        }};

        // 2. when
        this.hitCtrUtilRedEnvelope.adjustShowOddsList(showOddsList, showCount);

        // 3. then
        assertEquals(showOddsList.size(), showCount);
    }

    // 給定表演個數 5 ， 當擊殺只有6個選項，會帶出五個表演選項，第一個為結果
    @Test
    void test_given_killAndMoreShowCount_when_adjustShowOddsList_return_correctResult() {
        // 1. given
        int showCount = 5;
        List<Integer> showOddsList = new ArrayList<>(){{
            add(10);
            add(20);
            add(30);
            add(40);
            add(50);
            add(60);
        }};

        // 2. when
        this.hitCtrUtilRedEnvelope.adjustShowOddsList(showOddsList, showCount);

        // 3. then
        assertEquals(showOddsList.size(), showCount);
    }
}
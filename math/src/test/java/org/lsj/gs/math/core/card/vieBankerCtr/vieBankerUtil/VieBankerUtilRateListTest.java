package org.lsj.gs.math.core.card.vieBankerCtr.vieBankerUtil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 搶莊工具包測試
class VieBankerUtilRateListTest {
    VieBankerUtilRateList vieBankerUtilRateList; // 搶莊工具包

    @BeforeEach
    void setUp() {
        this.vieBankerUtilRateList = new VieBankerUtilRateList();
    }

    // 給定最大搶莊倍數0，當計算莊家倍數，回傳1
    @Test
    void test_given_maxVieRate0_when_calculateBankerRate_then_return_1() {
        // 1. given
        int maxVieRate = 0;

        // 2. when
        int actualResult = this.vieBankerUtilRateList.calculateBankerRate(maxVieRate, null);
        int expectResult = 1;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // 給定最大搶莊倍數2，當計算莊家倍數，回傳2
    @Test
    void test_given_maxVieRate2_when_calculateBankerRate_then_return_2() {
        // 1. given
        int maxVieRate = 2;

        // 2. when
        int actualResult = this.vieBankerUtilRateList.calculateBankerRate(maxVieRate, null);
        int expectResult = 2;

        // 3. then
        assertEquals(expectResult, actualResult);
    }
}
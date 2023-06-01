package org.lsj.gs.math.core.card.vieBankerCtr.vieBankerUtil;

import org.lsj.gs.math.core.card.ConstMathCard;
import org.lsj.gs.math.core.card.vieBankerCtr.VieBankerCtrConfig;
import org.lsj.gs.math.core.card.vieBankerCtr.VieBankerCtrConfigMaxRate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 搶莊工具包測試
class VieBankerUtilMaxRateTest {
    VieBankerUtilMaxRate vieBankerUtilMaxRate; // 搶莊工具包

    @BeforeEach
    void setUp() {
        this.vieBankerUtilMaxRate = new VieBankerUtilMaxRate();
    }

    // 給定最大搶莊倍數0，當計算莊家倍數，回傳1
    @Test
    void test_given_maxVieRate0_when_calculateBankerRate_then_return_1() {
        // 1. given
        int maxVieRate = 0;

        // 2. when
        int actualResult = this.vieBankerUtilMaxRate.calculateBankerRate(maxVieRate, this.generateConfig());
        int expectResult = 9;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // 給定最大搶莊倍數2，當計算莊家倍數，回傳2
    @Test
    void test_given_maxVieRate2_when_calculateBankerRate_then_return_2() {
        // 1. given
        int maxVieRate = 10;

        // 2. when
        int actualResult = this.vieBankerUtilMaxRate.calculateBankerRate(maxVieRate, this.generateConfig());
        int expectResult = 10;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    private VieBankerCtrConfig generateConfig() {
        return new VieBankerCtrConfigMaxRate(ConstMathCard.BankerType.MAX_RATE, 4, 3.0, 3, 200, 3);
    }
}
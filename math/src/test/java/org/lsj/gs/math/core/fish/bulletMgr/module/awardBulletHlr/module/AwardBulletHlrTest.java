package org.lsj.gs.math.core.fish.bulletMgr.module.awardBulletHlr.module;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 獎勵子彈處理者測試
@ExtendWith(MockitoExtension.class)
class AwardBulletHlrTest {
    AwardBulletHlr awardBulletHlr; // 獎勵子彈處理者

    // 給定不存在獎勵子彈，當計算獎勵子彈成本，預期回傳0
    @Test
    void test_given_nonExistBulletIndex_when_getObtainedAwardBulletCost_then_return_zero() {
        // 1. given
        this.awardBulletHlr = new AwardBulletHlr();

        // 2. when
        double obtainedAwardBulletCost = this.awardBulletHlr.getObtainedAwardBulletCost(new HashMap<>());

        // 3. then
        assertEquals(0.0, obtainedAwardBulletCost);
    }

    // 給定不存在獎勵子彈，當計算返還結果，預期回傳0
    @Test
    void test_given_nonExistBulletIndex_when_calculateReturnValue_then_return_zero() {
        // 1. given
        this.awardBulletHlr = new AwardBulletHlr();

        // 2. when
        double returnValue = this.awardBulletHlr.calculateReturnValue(new HashMap<>());

        // 3. then
        assertEquals(0.0, returnValue);
    }

}
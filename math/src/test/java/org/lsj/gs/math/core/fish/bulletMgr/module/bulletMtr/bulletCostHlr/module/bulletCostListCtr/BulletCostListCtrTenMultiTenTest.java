package org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.module.bulletCostListCtr;

import org.lsj.gs.math.core.fish.bulletMgr.module.bulletMtr.bulletCostHlr.enity.sever.bulletCostListConfigExtend.BulletCostListConfigExtendTenMultiTen;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// 十等份再切十等份子彈成本列表計算器測試
@ExtendWith(MockitoExtension.class)
class BulletCostListCtrTenMultiTenTest {
    BulletCostListCtrTenMultiTen bulletCostListCtrTenMultiTen; // 十等份再切十等份子彈成本列表計算器

    // 押注在成本列表中，回傳 true
    @Test
    void test_given_validBulletCost_when_isValidBulletCost_then_return_true() {
        // 1. given
        this.bulletCostListCtrTenMultiTen = new BulletCostListCtrTenMultiTen(new BulletCostListConfigExtendTenMultiTen());

        // 2. when
        boolean isValidBulletCost = this.bulletCostListCtrTenMultiTen.isValidBulletCost(1.0, 1.0);

        // 3. then
        assertTrue(isValidBulletCost);
    }

    // 押注不在成本列表中，回傳 false
    @Test
    void test_given_inValidBulletCost_when_isValidBulletCost_then_return_false() {
        // 1. given
        this.bulletCostListCtrTenMultiTen = new BulletCostListCtrTenMultiTen(new BulletCostListConfigExtendTenMultiTen());

        // 2. when
        boolean isValidBulletCost = this.bulletCostListCtrTenMultiTen.isValidBulletCost(3.5, 1.0);

        // 3. then
        assertFalse(isValidBulletCost);
    }
}
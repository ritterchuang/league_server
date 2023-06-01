package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrWay.wayHitUtil;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 路 計算連線數工具包測試
@ExtendWith(MockitoExtension.class)
class WayHitNumberUtilTest {
    WayHitNumberUtil wayHitNumberUtil; // 路 計算連線數工具包

    @BeforeEach
    void initUtil() {
        this.wayHitNumberUtil = new WayHitNumberUtil(this.generateSymbolConfig());
    }

    // 給定單一 wild, 計算連線數正常
    @Test
    void test_given_screenWithSingleWild_when_calculateSingleWildSymbolHitNumber_then_correct() {
        // 1. given
        List<Integer> payComboTargetSymbolIdList = List.of(1);
        List<List<Integer>> hitScreen = new ArrayList<>(){
            {
                add(List.of(-1, -1, -1));
                add(List.of(-1, -1, -1));
                add(List.of(-1, -1, -1));
                add(List.of(1, 1, 1));
                add(List.of(1, 1, 1));
            }
        };

        // 2. when
        int actualResult = this.wayHitNumberUtil.calculateWildSymbolHitNumber(hitScreen, payComboTargetSymbolIdList);
        int expectResult = 0;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // 給定混 wild, 計算連線數正常
    @Test
    void test_given_screenWithMixWild_when_calculateMixWildSymbolHitNumber_then_correct() {
        // 1. given
        List<Integer> payComboTargetSymbolIdList = List.of(1, 2);
        List<List<Integer>> hitScreen = new ArrayList<>(){
            {
                add(List.of(1, 1, 1));
                add(List.of(1, 1, 1));
                add(List.of(1, 2, 1));
                add(List.of(-1, 3, 4));
                add(List.of(1, 1, 1));
            }
        };

        // 2. when
        int actualResult = this.wayHitNumberUtil.calculateWildSymbolHitNumber(hitScreen, payComboTargetSymbolIdList);
        int expectResult = 3;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // 給定單一 標誌和wild, 計算連線數正常
    @Test
    void test_given_screenWithSingleSymbolAndWild_when_calculateSingleSymbolHitNumber_then_correct() {
        // 1. given
        List<Integer> payComboTargetSymbolIdList = List.of(6);
        List<List<Integer>> hitScreen = new ArrayList<>(){
            {
                add(List.of(1, 6, 1));
                add(List.of(1, 6, 1));
                add(List.of(1, 6, 1));
                add(List.of(-1, 2, 4));
                add(List.of(9, 9, 8));
            }
        };

        // 2. when
        int actualResult = this.wayHitNumberUtil.calculateSymbolAndWildSymbolHitNumber(hitScreen, payComboTargetSymbolIdList);
        int expectResult = 4;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // 給定混 標誌和wild, 計算連線數正常
    @Test
    void test_given_screenWithMixSymbolAndWild_when_calculateSingleSymbolHitNumber_then_correct() {
        // 1. given
        List<Integer> payComboTargetSymbolIdList = List.of(4, 5);
        List<List<Integer>> hitScreen = new ArrayList<>(){
            {
                add(List.of(4, 1, 1));
                add(List.of(1, 5, 1));
                add(List.of(1, 2, 1));
                add(List.of(-1, 2, -1));
                add(List.of(4, 4, 4));
            }
        };

        // 2. when
        int actualResult = this.wayHitNumberUtil.calculateSymbolAndWildSymbolHitNumber(hitScreen, payComboTargetSymbolIdList);
        int expectResult = 5;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // 給訂畫面相異標誌個數大於目標標誌個數，計算混搭正常
    @Test
    void test_given_screenDistinctCountBiggerThanTargetListSize_when_calculateSingleSymbolHitNumber_then_correct() {
        // 1. given
        List<Integer> payComboTargetSymbolIdList = List.of(4, 5);
        List<List<Integer>> hitScreen = new ArrayList<>(){
            {
                add(List.of(4, 1, 1));
                add(List.of(1, 5, 1));
                add(List.of(1, 2, 1));
                add(List.of(-1, 2, -1));
                add(List.of(6, 6, 3));
            }
        };

        // 2. when
        int actualResult = this.wayHitNumberUtil.calculateSymbolAndWildSymbolHitNumber(hitScreen, payComboTargetSymbolIdList);
        int expectResult = 4;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // 給定前三軸都是Wild1第四軸斷掉，計算混搭Wild(1,2)連線數，回傳0
    @Test
    void test_given_mixSymbolScreen_when_calculateMixSymbolAndWildSymbolHitNumber_then_return_0() {
        // 1. given
        List<Integer> payComboTargetSymbolIdList = List.of(1, 2);
        List<List<Integer>> hitScreen = new ArrayList<>(){
            {
                add(List.of(1, 1, 1));
                add(List.of(1, 1, 1));
                add(List.of(1, 1, 1));
                add(List.of(-1, -1, -1));
                add(List.of(2, -1, -1));
            }
        };

        // 2. when
        int actualResult = this.wayHitNumberUtil.calculateWildSymbolHitNumber(hitScreen, payComboTargetSymbolIdList);
        int expectResult = 0;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // 給定前三軸都是Wild1第四軸斷掉，計算單一標誌(4)連線數，回傳0
    @Test
    void test_given_singleSymbolScreen_when_calculateSingleSymbolHitNumber_then_correct2() {
        // 1. given
        List<Integer> payComboTargetSymbolIdList = List.of(4);
        List<List<Integer>> hitScreen = new ArrayList<>(){
            {
                add(List.of(1, 1, 1));
                add(List.of(1, -1, 1));
                add(List.of(-1, 1, 1));
                add(List.of(-1, -1, -1));
                add(List.of(4, -1, -1));
            }
        };

        // 2. when
        int actualResult = this.wayHitNumberUtil.calculateSymbolAndWildSymbolHitNumber(hitScreen, payComboTargetSymbolIdList);
        int expectResult = 0;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // 給訂畫面相異標誌個數大於目標標誌個數，計算混搭正常
    @Test
    void test_given_screenDistinctCountBiggerThanTargetListSize_when_calculateSingleSymbolHitNumber_then_correct3() {
        // 1. given
        List<Integer> payComboTargetSymbolIdList = List.of(4,5);
        List<List<Integer>> hitScreen = new ArrayList<>(){
            {
                add(List.of(1, 1, 1));
                add(List.of(1, -1, 1));
                add(List.of(-1, 4, 1));
                add(List.of(-1, -1, -1));
                add(List.of(5, -1, -1));
            }
        };

        // 2. when
        int actualResult = this.wayHitNumberUtil.calculateSymbolAndWildSymbolHitNumber(hitScreen, payComboTargetSymbolIdList);
        int expectResult = 0;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // 產出標誌設定檔
    private SymbolConfig generateSymbolConfig() {
        return new SymbolConfig(
                List.of(
                        ConstMathSlot.SymbolAttribute.WILD_01,
                        ConstMathSlot.SymbolAttribute.WILD_02,
                        ConstMathSlot.SymbolAttribute.WILD_03,
                        ConstMathSlot.SymbolAttribute.FREE_GAME_03,
                        ConstMathSlot.SymbolAttribute.M1,
                        ConstMathSlot.SymbolAttribute.M2,
                        ConstMathSlot.SymbolAttribute.M3,
                        ConstMathSlot.SymbolAttribute.KING,
                        ConstMathSlot.SymbolAttribute.QUEEN,
                        ConstMathSlot.SymbolAttribute.JOKER
                ),
                List.of(
                        ConstMathSlot.SymbolAttributeType.WILD_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.WILD_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.WILD_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.FREE_GAME_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.BASE_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.BASE_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.BASE_SYMBOL
                ));
    }

}
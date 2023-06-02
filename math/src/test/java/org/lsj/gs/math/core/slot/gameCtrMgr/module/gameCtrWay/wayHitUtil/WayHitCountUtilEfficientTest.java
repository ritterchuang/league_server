package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrWay.wayHitUtil;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.way.WayCtrWinResult;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayCombo;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayTableConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 路 計算堆疊工具包測試
@ExtendWith(MockitoExtension.class)
class WayHitCountUtilEfficientTest {
    WayHitCountUtilEfficient wayHitCountUtilEfficient; // 路 計算堆疊工具包

    @BeforeEach
    void initUtil() {
        this.wayHitCountUtilEfficient = new WayHitCountUtilEfficient(this.generateSymbolConfig(), this.generatePayTableConfig());
    }

    // 給定畫面、連線數、連線結果，計算單一wild結果，正確
    @Test
    void test_given_hitScreen_when_calculateSingleWildSymbolHitCount_then_correct() {
        // 1. given
        List<List<Integer>> hitScreen = new ArrayList<>(){
            {
                add(List.of(0, -1, 0));
                add(List.of(0, -1, 0));
                add(List.of(0, 0, 0));
                add(List.of(-1, -1, -1));
                add(List.of(0, -1, 0));
            }
        };
        int hitNumber = 3;

        // 2. when
        int actualResult = this.wayHitCountUtilEfficient.calculateSingleWildSymbolHitCount(hitScreen, hitNumber);
        int expectResult = 12;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // 給定畫面、連線數、連線結果，計算混wild結果，正確
    @Test
    void test_given_hitScreen_when_calculateMixWildSymbolHitCount_then_correct() {
        // 1. given
        List<List<Integer>> hitScreen = new ArrayList<>(){
            {
                add(List.of(0, -1, 0));
                add(List.of(0, -1, 0));
                add(List.of(0, 0, 1));
                add(List.of(1, -1, 0));
                add(List.of(-1, -1, -1));
            }
        };
        List<Integer> targetSymbolIdList = List.of(0, 1);
        int hitNumber = 4;
        List<WayCtrWinResult> wayCtrWinResultList = new ArrayList<>(){
            {
                add(new WayCtrWinResult(0, 4, 8, 500, 500, new ArrayList<>(), new ArrayList<>()));
            }
        };

        // 2. when
        int actualResult = this.wayHitCountUtilEfficient.calculateMixWildSymbolHitCount(hitScreen, targetSymbolIdList, hitNumber, wayCtrWinResultList);
        int expectResult = 16;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // 給定畫面、連線數、連線結果，計算單一標誌結果，正確
    @Test
    void test_given_hitScreen_when_calculateSingleNormalSymbolAndWildSymbolHitCount_then_correct() {
        // 1. given
        List<List<Integer>> hitScreen = new ArrayList<>(){
            {
                add(List.of(0, -1, 0));
                add(List.of(0, -1, 0));
                add(List.of(0, 0, 1));
                add(List.of(1, 5, 0));
                add(List.of(-1, -1, -1));
            }
        };
        List<Integer> targetSymbolIdList = List.of(5);
        int hitNumber = 4;
        List<WayCtrWinResult> wayCtrWinResultList = new ArrayList<>(){
            {
                add(new WayCtrWinResult(0, 4, 8, 500, 500, new ArrayList<>(), new ArrayList<>()));
                add(new WayCtrWinResult(3, 4, 16, 200, 500, new ArrayList<>(), new ArrayList<>()));
            }
        };

        // 2. when
        int actualResult = this.wayHitCountUtilEfficient.calculateSingleNormalSymbolAndWildSymbolHitCount(hitScreen, targetSymbolIdList, hitNumber, wayCtrWinResultList);
        int expectResult = 12;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // 給定畫面、連線數、連線結果，計算混搭結果，正確
    @Test
    void test_given_hitScreen_when_calculateMixNormalSymbolAndWildSymbolHitCount_then_correct() {
        // 1. given
        List<List<Integer>> hitScreen = new ArrayList<>(){
            {
                add(List.of(0, -1, 0));
                add(List.of(0, 4, 0));
                add(List.of(0, 0, 1));
                add(List.of(1, 5, 0));
                add(List.of(-1, -1, -1));
            }
        };
        List<Integer> targetSymbolIdList = List.of(4, 5);
        int hitNumber = 4;
        List<WayCtrWinResult> wayCtrWinResultList = new ArrayList<>(){
            {
                add(new WayCtrWinResult(0, 4, 8, 500, 500, new ArrayList<>(), new ArrayList<>()));
                add(new WayCtrWinResult(3, 4, 16, 200, 3200, new ArrayList<>(), new ArrayList<>()));
                add(new WayCtrWinResult(7, 4, 12, 100, 1200, new ArrayList<>(), new ArrayList<>()));
                add(new WayCtrWinResult(8, 4, 12, 100, 1200, new ArrayList<>(), new ArrayList<>()));
            }
        };

        // 2. when
        int actualResult = this.wayHitCountUtilEfficient.calculateMixNormalSymbolAndWildSymbolHitCount(hitScreen, targetSymbolIdList, hitNumber, wayCtrWinResultList);
        int expectResult = 6;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // TODO 重複多次
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

    private PayTableConfig generatePayTableConfig() {
        return new PayTableConfig(
                List.of(
                        // single wild
                        new PayCombo(List.of(0), List.of(0, 0, 100, 500, 1000)),    // 0
                        new PayCombo(List.of(1), List.of(0, 0, 100, 400, 1000)),    // 1
                        new PayCombo(List.of(2), List.of(0, 0, 100, 300, 1000)),    // 2
                        // mix Wild
                        new PayCombo(List.of(0, 1), List.of(0, 0, 100, 200, 500)),  // 3
                        new PayCombo(List.of(0, 2, 1), List.of(0, 0, 30, 50, 400)), // 4
                        new PayCombo(List.of(0, 2), List.of(0, 0, 100, 200, 500)),  // 5
                        new PayCombo(List.of(1, 2), List.of(0, 0, 100, 200, 500)),  // 6

                        // single Symbol
                        new PayCombo(List.of(4), List.of(0, 0, 100, 200, 500)),     // 7
                        new PayCombo(List.of(5), List.of(0, 0, 100, 200, 500)),     // 8
                        new PayCombo(List.of(6), List.of(0, 0, 50, 100, 400)),      // 9
                        new PayCombo(List.of(7), List.of(0, 0, 50, 100, 400)),      // 10
                        new PayCombo(List.of(8), List.of(0, 0, 50, 100, 400)),      // 11
                        new PayCombo(List.of(9), List.of(0, 0, 50, 100, 400)),      // 12
                        // mix Symbol
                        new PayCombo(List.of(4, 5, 7), List.of(0, 0, 10, 20, 50)),  // 13
                        new PayCombo(List.of(4, 5, 6, 7), List.of(0, 0, 0, 20, 50)),// 14
                        new PayCombo(List.of(5, 6), List.of(0, 0, 50, 100, 400)),   // 15
                        new PayCombo(List.of(4, 5), List.of(0, 0, 50, 100, 400)),   // 16
                        new PayCombo(List.of(4, 6), List.of(0, 0, 100, 200, 500)),  // 17
                        new PayCombo(List.of(5, 7), List.of(0, 0, 100, 200, 500)),  // 18
                        new PayCombo(List.of(4, 7), List.of(0, 0, 100, 200, 500)),  // 19
                        new PayCombo(List.of(6, 7), List.of(0, 0, 100, 200, 500)),  // 20
                        new PayCombo(List.of(4, 5, 6), List.of(0, 0, 10, 200, 500)),// 21
                        new PayCombo(List.of(4, 6, 7), List.of(0, 0, 10, 200, 500)),// 22
                        new PayCombo(List.of(5, 6, 7), List.of(0, 0, 10, 200, 500)) // 23
                )
        );
    }
}
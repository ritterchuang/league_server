package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrWay.wayHitUtil;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayCombo;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayTableConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 路 計算堆疊工具包測試
@ExtendWith(MockitoExtension.class)
class WayHitCountUtilExhaustTest {
    WayHitCountUtilExhaust wayHitCountUtilExhaust; // 路 計算堆疊工具包

    @BeforeEach
    void initUtil() {
        this.wayHitCountUtilExhaust = new WayHitCountUtilExhaust(this.generateSymbolConfig(), this.generatePayTableConfig());
    }

    // [[0,1,5],[0,0,0],[0,1,6],[7,0,4],[6,4,2]] 給定混搭wild目標標誌列表，計算堆疊正確
    @Test
    void test_given_screenAndMixWildTargetSymbolIdListWithoutMultiplier_when_calculateHitCount_then_correct() {
        // 1. given
        List<List<Integer>> hitScreen = new ArrayList<>(){
            {
                add(List.of(0, 1, -1));
                add(List.of(0, 0, 0));
                add(List.of(0, 1, -1));
                add(List.of(-1, 0, -1));
                add(List.of(-1, -1, 2));
            }
        };
        List<Integer> targetSymbolIdList = List.of(2, 1, 0);

        // 2. when
        int actualResult = this.wayHitCountUtilExhaust.calculateHitCount(hitScreen, targetSymbolIdList, 5, 0, new HashMap<>(), new ArrayList<>());
        int expectResult = 9;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // [[0,1,5],[0,0,0],[0,1,6],[7,0,4],[8,4,0]] 給定混搭目標標誌列表，計算堆疊正確
    @Test
    void test_given_screenAndMixSymbolTargetSymbolIdListWithoutMultiplier_when_calculateHitCount_then_correct() {
        // 1. given
        List<List<Integer>> hitScreen = new ArrayList<>(){
            {
                add(List.of(0, 1, 5));
                add(List.of(0, 0, 0));
                add(List.of(0, 1, 6));
                add(List.of(-1, 0, -1));
                add(List.of(-1, -1, 0));
            }
        };
        List<Integer> targetSymbolIdList = List.of(5, 6);

        // 2. when
        int actualResult = this.wayHitCountUtilExhaust.calculateHitCount(hitScreen, targetSymbolIdList, 5, 0, new HashMap<>(), new ArrayList<>());
        int expectResult = 3;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // [[0,1,5],[0,0,0],[0,1,6],[7,0,4],[8,4,0]] 給定單一目標標誌列表，計算堆疊正確
    @Test
    void test_given_screenAndSingleSymbolTargetSymbolIdListWithoutMultiplier_when_calculateHitCount_then_correct() {
        // 1. given
        List<List<Integer>> hitScreen = new ArrayList<>(){
            {
                add(List.of(0, 1, 5));
                add(List.of(0, 0, 0));
                add(List.of(0, 1, -1));
                add(List.of(-1, 0, -1));
                add(List.of(-1, -1, 0));
            }
        };
        List<Integer> targetSymbolIdList = List.of(5);

        // 2. when
        int actualResult = this.wayHitCountUtilExhaust.calculateHitCount(hitScreen, targetSymbolIdList, 5, 0, new HashMap<>(), new ArrayList<>());
        int expectResult = 6;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // [[0,1,5],[0,0,0],[0,1,6],[7,5,4],[8,4,0]] 給定混搭目標標誌列表，標誌倍數，計算堆疊正確
    @Test
    void test_given_screenAndMixSymbolTargetSymbolIdListWithSymbolMultiplier_when_calculateHitCount_then_correct() {
        // 1. given
        List<List<Integer>> hitScreen = new ArrayList<>(){
            {
                add(List.of(0, 1, 5));
                add(List.of(0, 0, 0));
                add(List.of(0, 1, 6));
                add(List.of(-1, 5, -1));
                add(List.of(-1, -1, 0));
            }
        };
        List<Integer> targetSymbolIdList = List.of(5, 6);
        Map<Integer, Integer> symbolIdToMultiplierMap = new HashMap<>(){{put(5, 3);}};

        // 2. when
        int actualResult = this.wayHitCountUtilExhaust.calculateHitCount(hitScreen, targetSymbolIdList, 5, 0, symbolIdToMultiplierMap, new ArrayList<>());
        int expectResult = 27;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // [[0,1,5],[0,0,0],[0,1,6],[7,6,4],[8,4,0]] 給定混搭目標標誌列表，倍數陣列，計算堆疊正確
    @Test
    void test_given_screenAndMixSymbolTargetSymbolIdListWithMultiplierMatrix_when_calculateHitCount_then_correct() {
        // 1. given
        List<List<Integer>> hitScreen = new ArrayList<>(){
            {
                add(List.of(0, 1, 5));
                add(List.of(0, 0, 0));
                add(List.of(0, 1, 6));
                add(List.of(-1, 6, -1));
                add(List.of(-1, -1, 0));
            }
        };
        List<Integer> targetSymbolIdList = List.of(5, 6);
        List<List<Integer>> multiplierMatrix = List.of(
                List.of(0,0,0),
                List.of(0,0,0),
                List.of(0,0,4),
                List.of(0,3,0),
                List.of(0,0,0)
        );

        // 2. when
        int actualResult = this.wayHitCountUtilExhaust.calculateHitCount(hitScreen, targetSymbolIdList, 5, 0, new HashMap<>(), multiplierMatrix);
        int expectResult = 54;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // [[0,1,5],[0,0,0],[0,1,6],[7,6,4],[8,4,0]] 給定混搭目標標誌列表，全屏倍數，計算堆疊正確
    @Test
    void test_given_screenAndMixSymbolTargetSymbolIdListWithScreenMultiplier_when_calculateHitCount_then_correct() {
        // 1. given
        List<List<Integer>> hitScreen = new ArrayList<>(){
            {
                add(List.of(0, 1, 5));
                add(List.of(0, 0, 0));
                add(List.of(0, 1, 6));
                add(List.of(-1, 6, -1));
                add(List.of(-1, -1, 0));
            }
        };
        List<Integer> targetSymbolIdList = List.of(5, 6);
        int screenMultiplier = 3;

        // 2. when
        int actualResult = this.wayHitCountUtilExhaust.calculateHitCount(hitScreen, targetSymbolIdList, 5, screenMultiplier, new HashMap<>(), new ArrayList<>());
        int expectResult = 27;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // [[0,1,5],[0,0,0],[0,1,6],[7,6,4],[8,4,0]] 給定混搭目標標誌列表，混合倍數，計算堆疊正確
    @Test
    void test_given_screenAndMixSymbolTargetSymbolIdListWithAllMultiplier_when_calculateHitCount_then_correct() {
        // 1. given
        List<List<Integer>> hitScreen = new ArrayList<>(){
            {
                add(List.of(0, 1, 5));
                add(List.of(0, 0, 0));
                add(List.of(0, 1, 6));
                add(List.of(-1, 6, -1));
                add(List.of(-1, -1, 0));
            }
        };
        List<Integer> targetSymbolIdList = List.of(5, 6);
        int screenMultiplier = 3;
        Map<Integer, Integer> symbolIdToMultiplierMap = new HashMap<>(){{put(5, 2);}};
        List<List<Integer>> multiplierMatrix = List.of(
                List.of(0,0,0),
                List.of(0,0,0),
                List.of(0,0,4),
                List.of(0,3,0),
                List.of(0,0,0)
        );

        // 2. when
        int actualResult = this.wayHitCountUtilExhaust.calculateHitCount(hitScreen, targetSymbolIdList, 5, screenMultiplier, symbolIdToMultiplierMap, multiplierMatrix);
        int expectResult = 324;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // 給定標誌6 三連線，第五軸又有目標標誌，預期不可給倍數
    @Test
    void test_given_symbolId6WithHitNumber3AndColumn5AppearWild_when_calculateHitCount_then_correct() {
        // 1. given
        List<List<Integer>> hitScreen = new ArrayList<>(){
            {
                add(List.of(6, -1, 6));
                add(List.of(6, 6, -1));
                add(List.of(6, -1, -1));
                add(List.of(-1, -1, -1));
                add(List.of(6, 6, 0));
            }
        };
        List<Integer> targetSymbolIdList = List.of(6);
        Map<Integer, Integer> symbolIdToMultiplierMap = new HashMap<>(){{put(0, 2);}};
        List<List<Integer>> multiplierMatrix = List.of(
                List.of(0,0,0),
                List.of(0,0,0),
                List.of(0,0,0),
                List.of(0,0,0),
                List.of(0,0,3)
        );

        // 2. when
        int actualResult = this.wayHitCountUtilExhaust.calculateHitCount(hitScreen, targetSymbolIdList, 3, 0, symbolIdToMultiplierMap, multiplierMatrix);
        int expectResult = 4;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // TODO
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

                        // single Symbol
                        new PayCombo(List.of(4), List.of(0, 0, 100, 200, 500)),     // 6
                        new PayCombo(List.of(5), List.of(0, 0, 100, 200, 500)),     // 7
                        new PayCombo(List.of(6), List.of(0, 0, 50, 100, 400)),      // 8
                        new PayCombo(List.of(7), List.of(0, 0, 50, 100, 400)),      // 9
                        new PayCombo(List.of(8), List.of(0, 0, 50, 100, 400)),      // 10
                        new PayCombo(List.of(9), List.of(0, 0, 50, 100, 400)),      // 11
                        // mix Symbol
                        new PayCombo(List.of(4, 5, 7), List.of(0, 0, 10, 20, 50)),  // 12
                        new PayCombo(List.of(4, 5, 6, 7), List.of(0, 0, 0, 20, 50)),// 13
                        new PayCombo(List.of(5, 6), List.of(0, 0, 50, 100, 400)),    // 14
                        new PayCombo(List.of(4, 5), List.of(0, 0, 50, 100, 400)),    // 15
                        new PayCombo(List.of(4, 6), List.of(0, 0, 100, 200, 500))   // 16
                )
        );
    }
}
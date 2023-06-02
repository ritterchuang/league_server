package org.lsj.gs.math.core.slot.gameCtrMgr.module;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.commonUtil.PayComboClassifier;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayCombo;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayTableConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 路賠率表分類者測試
@ExtendWith(MockitoExtension.class)
class PayComboClassifierTest {
    PayComboClassifier payComboClassifier; // 路賠率表分類者

    @BeforeEach
    void initUtil() {
        this.payComboClassifier = new PayComboClassifier(this.generateSymbolConfig(), this.generatePayTableConfig());
    }

    // 給訂畫面僅1個標誌和 1個wild，預期只有 singleWild、singleSymbol 有值
    @Test
    void test_given_singleNormalSymbolAndSingleWildSymbol_when_calculateWayCalculateTypeToPayComboIdListMap_then_correct() {
        // 1.given
        List<List<Integer>> screenSymbol = new ArrayList<>(){
            {
                add(List.of(8,8,8));
                add(List.of(8,8,8));
                add(List.of(8,8,8));
                add(List.of(8,8,0));
                add(List.of(8,8,8));
            }
        };

        // 2.when
        Map<ConstMathSlot.GameCalculateType, List<Integer>> actualResult = this.payComboClassifier.calculateGameCalculateTypeToPayComboIdListMapByScreenSymbol(screenSymbol);
        Map<ConstMathSlot.GameCalculateType, List<Integer>> expectResult = new HashMap<>(){
            {
                put(ConstMathSlot.GameCalculateType.SINGLE_WILD, List.of(0));
                put(ConstMathSlot.GameCalculateType.MIX_WILD, new ArrayList<>());
                put(ConstMathSlot.GameCalculateType.SINGLE_NORMAL, List.of(10));
                put(ConstMathSlot.GameCalculateType.MIX_NORMAL, new ArrayList<>());
            }
        };

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給訂畫面僅1個標誌和 2個wild[無混搭]，預期只有 singleWild、singleSymbol 有值
    @Test
    void test_given_singleNormalSymbolAndTwoSingleWildSymbol_when_calculateWayCalculateTypeToPayComboIdListMap_then_correct() {
        // 1.given
        List<List<Integer>> screenSymbol = new ArrayList<>(){
            {
                add(List.of(8,8,8));
                add(List.of(8,8,8));
                add(List.of(8,2,8));
                add(List.of(8,8,1));
                add(List.of(8,8,8));
            }
        };

        // 2.when
        Map<ConstMathSlot.GameCalculateType, List<Integer>> actualResult = this.payComboClassifier.calculateGameCalculateTypeToPayComboIdListMapByScreenSymbol(screenSymbol);
        Map<ConstMathSlot.GameCalculateType, List<Integer>> expectResult = new HashMap<>(){
            {
                put(ConstMathSlot.GameCalculateType.SINGLE_WILD, List.of(1, 2));
                put(ConstMathSlot.GameCalculateType.MIX_WILD, new ArrayList<>());
                put(ConstMathSlot.GameCalculateType.SINGLE_NORMAL, List.of(10));
                put(ConstMathSlot.GameCalculateType.MIX_NORMAL, new ArrayList<>());
            }
        };

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給訂畫面僅1個標誌和 3個wild[混搭]，預期只有 singleWild、mixWild、singleSymbol 有值
    @Test
    void test_given_singleNormalSymbolAndThreeMixWildSymbol_when_calculateWayCalculateTypeToPayComboIdListMap_then_correct() {
        // 1.given
        List<List<Integer>> screenSymbol = new ArrayList<>(){
            {
                add(List.of(8,8,8));
                add(List.of(8,8,8));
                add(List.of(8,2,8));
                add(List.of(8,0,1));
                add(List.of(8,8,8));
            }
        };

        // 2.when
        Map<ConstMathSlot.GameCalculateType, List<Integer>> actualResult = this.payComboClassifier.calculateGameCalculateTypeToPayComboIdListMapByScreenSymbol(screenSymbol);
        Map<ConstMathSlot.GameCalculateType, List<Integer>> expectResult = new HashMap<>(){
            {
                put(ConstMathSlot.GameCalculateType.SINGLE_WILD, List.of(0, 1, 2));
                put(ConstMathSlot.GameCalculateType.MIX_WILD, List.of(3, 5, 4));
                put(ConstMathSlot.GameCalculateType.SINGLE_NORMAL, List.of(10));
                put(ConstMathSlot.GameCalculateType.MIX_NORMAL, new ArrayList<>());
            }
        };

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給畫面有混搭，預期有 singleWild、mixWild、singleSymbol 有值
    @Test
    void test_given_mixNormalSymbolAndMixWildSymbol_when_calculateWayCalculateTypeToPayComboIdListMap_then_correct() {
        // 1.given
        List<List<Integer>> screenSymbol = new ArrayList<>(){
            {
                add(List.of(7,8,8));
                add(List.of(8,5,8));
                add(List.of(8,2,8));
                add(List.of(8,4,1));
                add(List.of(8,8,8));
            }
        };

        // 2.when
        Map<ConstMathSlot.GameCalculateType, List<Integer>> actualResult = this.payComboClassifier.calculateGameCalculateTypeToPayComboIdListMapByScreenSymbol(screenSymbol);
        Map<ConstMathSlot.GameCalculateType, List<Integer>> expectResult = new HashMap<>(){
            {
                put(ConstMathSlot.GameCalculateType.SINGLE_WILD, List.of(1, 2));
                put(ConstMathSlot.GameCalculateType.MIX_WILD, new ArrayList<>());
                put(ConstMathSlot.GameCalculateType.SINGLE_NORMAL, List.of(6, 7, 9, 10));
                put(ConstMathSlot.GameCalculateType.MIX_NORMAL, List.of(15, 12));
            }
        };

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
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
                        new PayCombo(List.of(5,6), List.of(0, 0, 50, 100, 400)),    // 14
                        new PayCombo(List.of(4,5), List.of(0, 0, 50, 100, 400)),    // 15
                        new PayCombo(List.of(4, 6), List.of(0, 0, 100, 200, 500))   // 16
                )
        );
    }

}
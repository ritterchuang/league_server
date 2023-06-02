package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrLine.lineHitUtil;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 線共用工具包測試
@ExtendWith(MockitoExtension.class)
class LineCtrCommonUtilTest {
    LineCtrCommonUtil lineCtrCommonUtil; // 線共用工具包

    // 初始化 線共用工具包
    @BeforeEach
    void initUtil() {
        this.lineCtrCommonUtil = new LineCtrCommonUtil(
                null,
                null,
                new ArrayList<>(){
                    {
                        add(List.of(0,0,0,0,0));
                        add(List.of(1,1,1,1,1));
                        add(List.of(2,2,2,2,2));
                    }
                }
        );
    }

    // 給定負數線ID，當計算該線畫面，回傳空陣列
    @Test
    void test_given_negativeLineId_when_calculateScreenSymbolPerLine_then_return_emptyList() {
        // 1. given
        int lineId = -1;
        List<List<Integer>> screenSymbol = new ArrayList<>(){
            {
                add(List.of(1,2,3));
                add(List.of(1,2,3));
                add(List.of(1,2,3));
                add(List.of(1,2,3));
                add(List.of(1,2,3));
            }
        };

        // 2. when
        List<Integer> actualResult = this.lineCtrCommonUtil.calculateScreenSymbolPerLine(lineId, screenSymbol);
        List<Integer> expectResult = Collections.emptyList();

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定線ID大於設定設數，當計算該線畫面，回傳空陣列
    @Test
    void test_given_lineIdBiggerThanLineConfigSize_when_calculateScreenSymbolPerLine_then_return_emptyList() {
        // 1. given
        int lineId = 3;
        List<List<Integer>> screenSymbol = new ArrayList<>(){
            {
                add(List.of(1,2,3));
                add(List.of(1,2,3));
                add(List.of(1,2,3));
                add(List.of(1,2,3));
                add(List.of(1,2,3));
            }
        };

        // 2. when
        List<Integer> actualResult = this.lineCtrCommonUtil.calculateScreenSymbolPerLine(lineId, screenSymbol);
        List<Integer> expectResult = Collections.emptyList();

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定正確線ID，當計算該線畫面，回傳正確陣列
    @Test
    void test_given_validLineId_when_calculateScreenSymbolPerLine_then_return_correctList() {
        // 1. given
        int lineId = 2;
        List<List<Integer>> screenSymbol = new ArrayList<>(){
            {
                add(List.of(1,2,3));
                add(List.of(1,2,3));
                add(List.of(1,2,3));
                add(List.of(1,2,3));
                add(List.of(1,2,3));
            }
        };

        // 2. when
        List<Integer> actualResult = this.lineCtrCommonUtil.calculateScreenSymbolPerLine(lineId, screenSymbol);
        List<Integer> expectResult = List.of(3,3,3,3,3);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定空白畫面，當計算畫面擊中位置，回傳空陣列
    @Test
    void test_given_emptyScreenSymbol_when_calculateScreenHitPosition_then_return_emptyList() {
        // 1. given
        List<List<Integer>> screenSymbol = Collections.emptyList();
        int lineId = 2;
        int hitNumber = 3;
        ConstMathSlot.GameHitDirectionType gameHitDirectionType = ConstMathSlot.GameHitDirectionType.LEFT_TO_RIGHT;

        // 2. when
        List<List<Boolean>> actualResult = this.lineCtrCommonUtil.calculateScreenHitPosition(screenSymbol, lineId, hitNumber, gameHitDirectionType);
        List<List<Boolean>> expectResult = Collections.emptyList();

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定負數線ID，當計算畫面擊中位置，回傳空陣列
    @Test
    void test_given_negativeLineId_when_calculateScreenHitPosition_then_return_emptyList() {
        // 1. given
        List<List<Integer>> screenSymbol = new ArrayList<>(){
            {
                add(List.of(1,2,3));
                add(List.of(1,2,3));
                add(List.of(1,2,3));
                add(List.of(1,2,3));
                add(List.of(1,2,3));
            }
        };
        int lineId = -2;
        int hitNumber = 3;
        ConstMathSlot.GameHitDirectionType gameHitDirectionType = ConstMathSlot.GameHitDirectionType.LEFT_TO_RIGHT;

        // 2. when
        List<List<Boolean>> actualResult = this.lineCtrCommonUtil.calculateScreenHitPosition(screenSymbol, lineId, hitNumber, gameHitDirectionType);
        List<List<Boolean>> expectResult = Collections.emptyList();

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定線ID大於設定個數，當計算畫面擊中位置，回傳空陣列
    @Test
    void test_given_lineIdBiggerThanConfigSize_when_calculateScreenHitPosition_then_return_emptyList() {
        // 1. given
        List<List<Integer>> screenSymbol = new ArrayList<>(){
            {
                add(List.of(1,2,3));
                add(List.of(1,2,3));
                add(List.of(1,2,3));
                add(List.of(1,2,3));
                add(List.of(1,2,3));
            }
        };
        int lineId = 8;
        int hitNumber = 3;
        ConstMathSlot.GameHitDirectionType gameHitDirectionType = ConstMathSlot.GameHitDirectionType.LEFT_TO_RIGHT;

        // 2. when
        List<List<Boolean>> actualResult = this.lineCtrCommonUtil.calculateScreenHitPosition(screenSymbol, lineId, hitNumber, gameHitDirectionType);
        List<List<Boolean>> expectResult = Collections.emptyList();

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定連線數為0，當計算畫面擊中位置，回傳空陣列
    @Test
    void test_given_hitNumber0_when_calculateScreenHitPosition_then_return_emptyList() {
        // 1. given
        List<List<Integer>> screenSymbol = new ArrayList<>(){
            {
                add(List.of(1,2,3));
                add(List.of(1,2,3));
                add(List.of(1,2,3));
                add(List.of(1,2,3));
                add(List.of(1,2,3));
            }
        };
        int lineId = 2;
        int hitNumber = 0;
        ConstMathSlot.GameHitDirectionType gameHitDirectionType = ConstMathSlot.GameHitDirectionType.LEFT_TO_RIGHT;

        // 2. when
        List<List<Boolean>> actualResult = this.lineCtrCommonUtil.calculateScreenHitPosition(screenSymbol, lineId, hitNumber, gameHitDirectionType);
        List<List<Boolean>> expectResult = Collections.emptyList();

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定連線數大於畫面欄數，當計算畫面擊中位置，回傳空陣列
    @Test
    void test_given_hitNumberBiggerThanScreenColumn_when_calculateScreenHitPosition_then_return_emptyList() {
        // 1. given
        List<List<Integer>> screenSymbol = new ArrayList<>(){
            {
                add(List.of(1,2,3));
                add(List.of(1,2,3));
                add(List.of(1,2,3));
                add(List.of(1,2,3));
                add(List.of(1,2,3));
            }
        };
        int lineId = 2;
        int hitNumber = 13;
        ConstMathSlot.GameHitDirectionType gameHitDirectionType = ConstMathSlot.GameHitDirectionType.LEFT_TO_RIGHT;

        // 2. when
        List<List<Boolean>> actualResult = this.lineCtrCommonUtil.calculateScreenHitPosition(screenSymbol, lineId, hitNumber, gameHitDirectionType);
        List<List<Boolean>> expectResult = Collections.emptyList();

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定正確畫面，由左至右計算，當計算畫面擊中位置，回傳正確陣列
    @Test
    void test_given_validParameterWithLeftToRight_when_calculateScreenHitPosition_then_return_correctList() {
        // 1. given
        List<List<Integer>> screenSymbol = new ArrayList<>(){
            {
                add(List.of(1,2,3));
                add(List.of(1,2,3));
                add(List.of(1,2,3));
                add(List.of(1,5,3));
                add(List.of(1,4,3));
            }
        };
        int lineId = 1;
        int hitNumber = 3;
        ConstMathSlot.GameHitDirectionType gameHitDirectionType = ConstMathSlot.GameHitDirectionType.LEFT_TO_RIGHT;

        // 2. when
        List<List<Boolean>> actualResult = this.lineCtrCommonUtil.calculateScreenHitPosition(screenSymbol, lineId, hitNumber, gameHitDirectionType);
        List<List<Boolean>> expectResult = new ArrayList<>(){
            {
                add(List.of(false, true, false));
                add(List.of(false, true, false));
                add(List.of(false, true, false));
                add(List.of(false, false, false));
                add(List.of(false, false, false));
            }
        };

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定正確畫面，由右至左計算，當計算畫面擊中位置，回傳正確陣列
    @Test
    void test_given_validParameterWithRightToLeft_when_calculateScreenHitPosition_then_return_correctList() {
        // 1. given
        List<List<Integer>> screenSymbol = new ArrayList<>(){
            {
                add(List.of(1,3,3));
                add(List.of(1,2,3));
                add(List.of(1,2,3));
                add(List.of(1,2,3));
                add(List.of(1,2,3));
            }
        };
        int lineId = 1;
        int hitNumber = 4;
        ConstMathSlot.GameHitDirectionType gameHitDirectionType = ConstMathSlot.GameHitDirectionType.RIGHT_TO_LEFT;

        // 2. when
        List<List<Boolean>> actualResult = this.lineCtrCommonUtil.calculateScreenHitPosition(screenSymbol, lineId, hitNumber, gameHitDirectionType);
        List<List<Boolean>> expectResult = new ArrayList<>(){
            {
                add(List.of(false, false, false));
                add(List.of(false, true, false));
                add(List.of(false, true, false));
                add(List.of(false, true, false));
                add(List.of(false, true, false));
            }
        };

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }
}
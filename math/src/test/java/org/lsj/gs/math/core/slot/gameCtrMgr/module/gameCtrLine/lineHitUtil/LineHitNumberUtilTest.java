package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrLine.lineHitUtil;

import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 線連線數工具包測試
@ExtendWith(MockitoExtension.class)
class LineHitNumberUtilTest {
    LineHitNumberUtil lineHitNumberUtil; // 線連線數工具包
    @Mock
    SymbolConfig mockSymbolConfig; // 標誌設定黨

    // 給定2連線畫面，當計算單一wild連線數，回傳2
    @Test
    void test_given_screenPerLineWithSingleWild_when_calculateSingleWildSymbolHitNumber_then_return_2() {
        // 1. given
        this.lineHitNumberUtil = new LineHitNumberUtil(mockSymbolConfig);
        List<Integer> hitScreenPerLine = List.of(0,0,-1,0,-1);
        List<Integer> payComboTargetSymbolIdList = List.of(0);

        // 2. when
        int actualResult = this.lineHitNumberUtil.calculateWildSymbolHitNumber(hitScreenPerLine, payComboTargetSymbolIdList);
        int expectResult = 2;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // 給定3連線畫面，當計算混wild連線數，回傳3
    @Test
    void test_given_screenPerLineWithMixWild_when_calculateMixWildSymbolHitNumber_then_return_3() {
        // 1. given
        this.lineHitNumberUtil = new LineHitNumberUtil(mockSymbolConfig);
        List<Integer> hitScreenPerLine = List.of(0,0,1,-1,-1);
        List<Integer> payComboTargetSymbolIdList = List.of(0,1);

        // 2. when
        int actualResult = this.lineHitNumberUtil.calculateWildSymbolHitNumber(hitScreenPerLine, payComboTargetSymbolIdList);
        int expectResult = 3;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // 給定2連線畫面，當計算單一標誌連線數，回傳2
    @Test
    void test_given_screenPerLineWithSingleSymbolAndWild_when_calculateSingleSymbolAndWildSymbolHitNumber_then_return_2() {
        // 1. given
        Mockito.when(mockSymbolConfig.isWildSymbol(0)).thenReturn(true);
        Mockito.when(mockSymbolConfig.isWildSymbol(2)).thenReturn(false);
        Mockito.when(mockSymbolConfig.isWildSymbol(-1)).thenReturn(false);
        this.lineHitNumberUtil = new LineHitNumberUtil(mockSymbolConfig);
        List<Integer> hitScreenPerLine = List.of(0,2,-1,-1,-1);
        List<Integer> payComboTargetSymbolIdList = List.of(2);

        // 2. when
        int actualResult = this.lineHitNumberUtil.calculateSymbolAndWildSymbolHitNumber(hitScreenPerLine, payComboTargetSymbolIdList);
        int expectResult = 2;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // 給定4連線畫面，當計算混標誌連線數，回傳4
    @Test
    void test_given_screenPerLineWithMixSymbolAndWild_when_calculateSingleSymbolAndWildSymbolHitNumber_then_return_4() {
        // 1. given
        Mockito.when(mockSymbolConfig.isWildSymbol(0)).thenReturn(true);
        Mockito.when(mockSymbolConfig.isWildSymbol(2)).thenReturn(false);
        Mockito.when(mockSymbolConfig.isWildSymbol(3)).thenReturn(false);
        Mockito.when(mockSymbolConfig.isWildSymbol(-1)).thenReturn(false);
        this.lineHitNumberUtil = new LineHitNumberUtil(mockSymbolConfig);
        List<Integer> hitScreenPerLine = List.of(0,2,3,0,-1);
        List<Integer> payComboTargetSymbolIdList = List.of(2,3);

        // 2. when
        int actualResult = this.lineHitNumberUtil.calculateSymbolAndWildSymbolHitNumber(hitScreenPerLine, payComboTargetSymbolIdList);
        int expectResult = 4;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // 給定畫面[0,0,0,-1,2]，當計算標誌2連線數，回傳0
    @Test
    void test_given_screenPerLineWithSingleSymbolAndWild_when_calculateSingleSymbolAndWildSymbolHitNumber_then_return_0() {
        // 1. given
        Mockito.when(mockSymbolConfig.isWildSymbol(0)).thenReturn(true);
        Mockito.when(mockSymbolConfig.isWildSymbol(-1)).thenReturn(false);
        this.lineHitNumberUtil = new LineHitNumberUtil(mockSymbolConfig);
        List<Integer> hitScreenPerLine = List.of(0,0,0,-1,2);
        List<Integer> payComboTargetSymbolIdList = List.of(2);

        // 2. when
        int actualResult = this.lineHitNumberUtil.calculateSymbolAndWildSymbolHitNumber(hitScreenPerLine, payComboTargetSymbolIdList);
        int expectResult = 0;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // 給定畫面[0,0,0,-1,2]，當計算混Wild[0,2]連線數，回傳0
    @Test
    void test_given_screenPerLineWithMixWild_when_calculateMixWildSymbolHitNumber_then_return_0() {
        // 1. given
        this.lineHitNumberUtil = new LineHitNumberUtil(mockSymbolConfig);
        List<Integer> hitScreenPerLine = List.of(0,0,0,-1,2);
        List<Integer> payComboTargetSymbolIdList = List.of(0,2);

        // 2. when
        int actualResult = this.lineHitNumberUtil.calculateWildSymbolHitNumber(hitScreenPerLine, payComboTargetSymbolIdList);
        int expectResult = 0;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // 給定畫面[0,0,1,-1,2]，當計算混搭[1,2]連線數，回傳0
    @Test
    void test_given_screenPerLineWith1MixWild_when_calculateMixWildSymbolHitNumber_then_return_0() {
        // 1. given
        this.lineHitNumberUtil = new LineHitNumberUtil(mockSymbolConfig);
        Mockito.when(mockSymbolConfig.isWildSymbol(0)).thenReturn(true);
        Mockito.when(mockSymbolConfig.isWildSymbol(1)).thenReturn(false);
        Mockito.when(mockSymbolConfig.isWildSymbol(-1)).thenReturn(false);
        List<Integer> hitScreenPerLine = List.of(0,0,1,-1,2);
        List<Integer> payComboTargetSymbolIdList = List.of(1,2);

        // 2. when
        int actualResult = this.lineHitNumberUtil.calculateSymbolAndWildSymbolHitNumber(hitScreenPerLine, payComboTargetSymbolIdList);
        int expectResult = 0;

        // 3. then
        assertEquals(expectResult, actualResult);
    }
}
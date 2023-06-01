package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrWay.wayHitUtil;

import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class AbstractWayUtilTest {

    // 給定畫面，hitNumber = 3，計算總堆疊數正常
    @Test
    void test_given_simplifyScreenAndHitNumber1_when_calculateTotalHitCount_then_correct() {
        // 1. given
        AbstractWayUtil abstractWayUtil = Mockito.mock(AbstractWayUtil.class, Mockito.CALLS_REAL_METHODS);
        List<List<Integer>> simplifyHitScreen = new ArrayList<>() {
            {
                add(List.of(1, 2, 3));
                add(List.of(1, 2, 3));
                add(List.of(1, 2, 3));
                add(List.of(1, 2, 3));
                add(List.of(1, 2, 3));
            }
        };
        int hitNumber = 3;

        // 2. when
        int actualResult = abstractWayUtil.calculateTotalHitCount(simplifyHitScreen, hitNumber);
        int expectResult = 27;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // 給定畫面，hitNumber = 4，計算簡易版打擊畫面
    @Test
    void test_given_hitScreenAndHitNumber3_when_calculateSimplifyHitScreenSymbol_then_correct() {
        // 1. given
        AbstractWayUtil abstractWayUtil = Mockito.mock(AbstractWayUtil.class, Mockito.CALLS_REAL_METHODS);
        List<List<Integer>> simplifyHitScreen = new ArrayList<>() {
            {
                add(List.of(1, 2, 3));
                add(List.of(1, 2, 3));
                add(List.of(1, 2, 3));
                add(List.of(-1, -1, -1));
                add(List.of(1, 2, 3));
            }
        };
        int hitNumber = 3;

        // 2. when
        List<List<Integer>> actualResult = abstractWayUtil.calculateSimplifyHitScreen(simplifyHitScreen, hitNumber);
        List<List<Integer>> expectResult = new ArrayList<>(){
            {
                add(List.of(1, 2, 3));
                add(List.of(1, 2, 3));
                add(List.of(1, 2, 3));
            }
        };

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }
}
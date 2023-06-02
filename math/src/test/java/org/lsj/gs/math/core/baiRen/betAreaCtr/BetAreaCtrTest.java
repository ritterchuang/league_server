package org.lsj.gs.math.core.baiRen.betAreaCtr;

import org.lsj.gs.math.core.common.gamePlayerHlr.module.GamePlayerHlr;
import org.lsj.gs.math.core.common.poolCtr.module.PoolCtr;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class BetAreaCtrTest {
    BetAreaCtr betAreaCtr;

    @Mock
    BetAreaCtrConfig config;
    GamePlayerHlr gamePlayerHlr;
    PoolCtr poolCtr;
    ITableUtil tableUtil;

    @BeforeEach
    void setUp() {
        Mockito.when(this.config.getChipsOddsList()).thenReturn(new ArrayList<>(){{
            add(1);
        }});
    }

    // 給定底注小於1，執行取得籌碼列表，則籌碼為1
    @Test
    void test_given_base_small_than_1_when_getChipsList_then_1() {
        // 1. given
        Mockito.when(this.config.getBaseScore()).thenReturn(-1.2);
        this.betAreaCtr = new BetAreaCtr(config, gamePlayerHlr, poolCtr, tableUtil);

        // 2. when
        List<Integer> chipsList =  this.betAreaCtr.getChipsList();

        // 3. then
        assertEquals(new ArrayList<>(){{
            add(1);
        }}, chipsList);
    }

    // 給定底注9.9，執行取得籌碼列表，則籌碼為9
    @Test
    void test_given_base_9_when_getChipsList_then_9() {
        // 1. given
        Mockito.when(this.config.getBaseScore()).thenReturn(9.9);
        this.betAreaCtr = new BetAreaCtr(config, gamePlayerHlr, poolCtr, tableUtil);

        // 2. when
        List<Integer> chipsList =  this.betAreaCtr.getChipsList();

        // 3. then
        assertEquals(new ArrayList<>(){{
            add(9);
        }}, chipsList);
    }

    // 給定底注99，執行取得籌碼列表，則籌碼為90
    @Test
    void test_given_base_99_when_getChipsList_then_90() {
        // 1. given
        Mockito.when(this.config.getBaseScore()).thenReturn(99.0);
        this.betAreaCtr = new BetAreaCtr(config, gamePlayerHlr, poolCtr, tableUtil);

        // 2. when
        List<Integer> chipsList =  this.betAreaCtr.getChipsList();

        // 3. then
        assertEquals(new ArrayList<>(){{
            add(90);
        }}, chipsList);
    }

    // 給定底注999，執行取得籌碼列表，則籌碼為900
    @Test
    void test_given_base_990_when_getChipsList_then_900() {
        // 1. given
        Mockito.when(this.config.getBaseScore()).thenReturn(999.0);
        this.betAreaCtr = new BetAreaCtr(config, gamePlayerHlr, poolCtr, tableUtil);

        // 2. when
        List<Integer> chipsList =  this.betAreaCtr.getChipsList();

        // 3. then
        assertEquals(new ArrayList<>(){{
            add(900);
        }}, chipsList);
    }

    // 給定底注99999，執行取得籌碼列表，則籌碼為99000
    @Test
    void test_given_base_99999_when_getChipsList_then_99000() {
        // 1. given
        Mockito.when(this.config.getBaseScore()).thenReturn(99999.0);
        this.betAreaCtr = new BetAreaCtr(config, gamePlayerHlr, poolCtr, tableUtil);

        // 2. when
        List<Integer> chipsList =  this.betAreaCtr.getChipsList();

        // 3. then
        assertEquals(new ArrayList<>(){{
            add(99000);
        }}, chipsList);
    }

    // 給定3個下注區域，玩家1下注 <0, 100>; <2, 3>，當更新玩家下注，正確紀錄
    @Test
    void test_given_3BetArea_playerBetTwice_when_updateAreaBet_then_getCorrectAreaIdToChairBetMap() {
        // 1. given
        this.betAreaCtr = new BetAreaCtr(config, gamePlayerHlr, poolCtr, tableUtil);
        this.betAreaCtr.updateAreaBet(1, 0, 100);
        this.betAreaCtr.updateAreaBet(1, 2, 3);

        // 2. when
        Map<Integer, Map<Integer, Integer>> actualResult =  this.betAreaCtr.getChairToAreaBetMap();
        Map<Integer, Map<Integer, Integer>> expectResult =  new HashMap<>(){
            {
                put(1, new HashMap<>(){
                    {
                        put(0, 100);
                        put(2, 3);
                    }
                });
            }
        };

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定3個下注區域，玩家1下注 <0, 100>; <2, 3>，玩家2下注<2, 10>，當取得所有下注區域下注金額，回傳正確陣列
    @Test
    void test_given_3BetArea_when_getAreaBetArray_then_return_correctArray() {
        // 1. given
        Mockito.when(this.config.getBetAreaCount()).thenReturn(3);
        this.betAreaCtr = new BetAreaCtr(config, gamePlayerHlr, poolCtr, tableUtil);
        this.betAreaCtr.updateAreaBet(1, 0, 100);
        this.betAreaCtr.updateAreaBet(1, 2, 3);
        this.betAreaCtr.updateAreaBet(2, 2, 10);

        // 2. when
        int[] actualResult = this.betAreaCtr.getAreaBetArray();
        int[] expectResult = new int[]{100, 0, 13};

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定3個下注區域，玩家1下注 <0, 100>; <2, 3>，玩家2沒下注，當取得玩家2所有下注金額，回傳0
    @Test
    void test_given_onlyPlayer1Bet_when_getPlayerTotalAreaBetForPlayer2_then_return_0() {
        // 1. given
        this.betAreaCtr = new BetAreaCtr(config, gamePlayerHlr, poolCtr, tableUtil);
        this.betAreaCtr.updateAreaBet(1, 0, 100);
        this.betAreaCtr.updateAreaBet(1, 2, 3);

        // 2. when
        int actualResult = this.betAreaCtr.getPlayerTotalAreaBet(2);
        int expectResult = 0;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // 給定3個下注區域，玩家1下注 <0, 100>; <2, 3>，玩家2沒下注，當取得玩家2所有下注金額，回傳103
    @Test
    void test_given_onlyPlayer1Bet_when_getPlayerTotalAreaBetForPlayer2_then_return_103() {
        // 1. given
        Mockito.when(this.config.getBetAreaCount()).thenReturn(3);
        this.betAreaCtr = new BetAreaCtr(config, gamePlayerHlr, poolCtr, tableUtil);
        this.betAreaCtr.updateAreaBet(1, 0, 100);
        this.betAreaCtr.updateAreaBet(1, 2, 3);

        // 2. when
        int actualResult = this.betAreaCtr.getPlayerTotalAreaBet(1);
        int expectResult = 103;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // 給定3個下注區域，玩家1下注 <0, 100>; <2, 3>，玩家2沒下注，當取得玩家2所有下注陣列，回傳空陣列
    @Test
    void test_given_onlyPlayer1Bet_when_getPlayerAreaBetArrayForPlayer2_then_return_zeroArray() {
        // 1. given
        Mockito.when(this.config.getBetAreaCount()).thenReturn(3);
        this.betAreaCtr = new BetAreaCtr(config, gamePlayerHlr, poolCtr, tableUtil);
        this.betAreaCtr.updateAreaBet(1, 0, 100);
        this.betAreaCtr.updateAreaBet(1, 2, 3);

        // 2. when
        int[] actualResult = this.betAreaCtr.getPlayerAreaBetArray(2);
        int[] expectResult = new int[]{0, 0, 0};

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定3個下注區域，玩家1下注 <0, 100>; <2, 3>，玩家2沒下注，當取得玩家2所有下注陣列，回傳正確陣列
    @Test
    void test_given_onlyPlayer1Bet_when_getPlayerAreaBetArrayForPlayer1_then_return_correctArray() {
        // 1. given
        Mockito.when(this.config.getBetAreaCount()).thenReturn(3);
        this.betAreaCtr = new BetAreaCtr(config, gamePlayerHlr, poolCtr, tableUtil);
        this.betAreaCtr.updateAreaBet(1, 0, 100);
        this.betAreaCtr.updateAreaBet(1, 2, 3);

        // 2. when
        int[] actualResult = this.betAreaCtr.getPlayerAreaBetArray(1);
        int[] expectResult = new int[]{100, 0, 3};

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }
}
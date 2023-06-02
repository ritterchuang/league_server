package org.lsj.gs.math.core.slot.gameCtrMgr.module;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.commonUtil.AbstractCtrCommonUtil;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayCombo;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayTableConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 虎機抽象計算共同工具包 測試
@ExtendWith(MockitoExtension.class)
class AbstractCtrCommonUtilTest {

    // 給定 負數賠率指標，當計算賠率標誌列表，回傳空
    @Test
    void test_given_negativePayComboId_when_getPayComboTargetSymbolIdList_then_return_emptyList() {
        // 1. given
        SymbolConfig symbolConfig = this.generateSymbolConfig();
        PayTableConfig payTableConfig = this.generatePayTableConfig();
        AbstractCtrCommonUtil abstractCtrCommonUtil = Mockito.mock(AbstractCtrCommonUtil.class,
                Mockito.withSettings().useConstructor(symbolConfig, payTableConfig).defaultAnswer(Mockito.CALLS_REAL_METHODS));

        // 2. when
        List<Integer> actualResult = abstractCtrCommonUtil.getPayComboTargetSymbolIdList(-1);
        List<Integer> expectResult = Collections.emptyList();

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定 賠率指標大於賠率標誌列表個數，當計算賠率標誌列表，回傳空
    @Test
    void test_given_payComboIdBiggerThanPayComboListSize_when_getPayComboTargetSymbolIdList_then_return_emptyList() {
        // 1. given
        SymbolConfig symbolConfig = this.generateSymbolConfig();
        PayTableConfig payTableConfig = this.generatePayTableConfig();
        AbstractCtrCommonUtil abstractCtrCommonUtil = Mockito.mock(AbstractCtrCommonUtil.class,
                Mockito.withSettings().useConstructor(symbolConfig, payTableConfig).defaultAnswer(Mockito.CALLS_REAL_METHODS));

        // 2. when
        List<Integer> actualResult = abstractCtrCommonUtil.getPayComboTargetSymbolIdList(6);
        List<Integer> expectResult = Collections.emptyList();

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定 正常賠率指標，當計算賠率標誌列表，回傳正確列表
    @Test
    void test_given_validPayCombo_when_getPayComboTargetSymbolIdList_then_return_correctList() {
        // 1. given
        SymbolConfig symbolConfig = this.generateSymbolConfig();
        PayTableConfig payTableConfig = this.generatePayTableConfig();
        AbstractCtrCommonUtil abstractCtrCommonUtil = Mockito.mock(AbstractCtrCommonUtil.class,
                Mockito.withSettings().useConstructor(symbolConfig, payTableConfig).defaultAnswer(Mockito.CALLS_REAL_METHODS));

        // 2. when
        List<Integer> actualResult = abstractCtrCommonUtil.getPayComboTargetSymbolIdList(3);
        List<Integer> expectResult = List.of(2,3);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定 負數賠率指標，當計算打擊倍數，回傳0
    @Test
    void test_given_negativePayComboId_when_calculateHitOdds_then_return_0() {
        // 1. given
        SymbolConfig symbolConfig = this.generateSymbolConfig();
        PayTableConfig payTableConfig = this.generatePayTableConfig();
        AbstractCtrCommonUtil abstractCtrCommonUtil = Mockito.mock(AbstractCtrCommonUtil.class,
                Mockito.withSettings().useConstructor(symbolConfig, payTableConfig).defaultAnswer(Mockito.CALLS_REAL_METHODS));

        // 2. when
        int actualResult = abstractCtrCommonUtil.calculateHitOdds(-1,2);
        int expectResult = 0;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // 給定 賠率指標大於賠率標誌列表個數，當計算打擊倍數，回傳0
    @Test
    void test_given_payComboIdBiggerThanPayComboListSize_when_calculateHitOdds_then_return_0() {
        // 1. given
        SymbolConfig symbolConfig = this.generateSymbolConfig();
        PayTableConfig payTableConfig = this.generatePayTableConfig();
        AbstractCtrCommonUtil abstractCtrCommonUtil = Mockito.mock(AbstractCtrCommonUtil.class,
                Mockito.withSettings().useConstructor(symbolConfig, payTableConfig).defaultAnswer(Mockito.CALLS_REAL_METHODS));

        // 2. when
        int actualResult = abstractCtrCommonUtil.calculateHitOdds(6,2);
        int expectResult = 0;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // 給定 負數打擊連線數，當計算打擊倍數，回傳0
    @Test
    void test_given_negativeHitNumber_when_calculateHitOdds_then_return_0() {
        // 1. given
        SymbolConfig symbolConfig = this.generateSymbolConfig();
        PayTableConfig payTableConfig = this.generatePayTableConfig();
        AbstractCtrCommonUtil abstractCtrCommonUtil = Mockito.mock(AbstractCtrCommonUtil.class,
                Mockito.withSettings().useConstructor(symbolConfig, payTableConfig).defaultAnswer(Mockito.CALLS_REAL_METHODS));

        // 2. when
        int actualResult = abstractCtrCommonUtil.calculateHitOdds(3,-1);
        int expectResult = 0;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // 給定 打擊連線數大於標誌賠率列表數，當計算打擊倍數，回傳0
    @Test
    void test_given_hitNumberBiggerThanPayOddsListSize_when_calculateHitOdds_then_return_0() {
        // 1. given
        SymbolConfig symbolConfig = this.generateSymbolConfig();
        PayTableConfig payTableConfig = this.generatePayTableConfig();
        AbstractCtrCommonUtil abstractCtrCommonUtil = Mockito.mock(AbstractCtrCommonUtil.class,
                Mockito.withSettings().useConstructor(symbolConfig, payTableConfig).defaultAnswer(Mockito.CALLS_REAL_METHODS));

        // 2. when
        int actualResult = abstractCtrCommonUtil.calculateHitOdds(3,8);
        int expectResult = 0;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // 給定 正確賠率指標、打擊連線數，當計算打擊倍數，回傳2
    @Test
    void test_given_validPayComboIdAndValidHitNumber_when_calculateHitOdds_then_return_2() {
        // 1. given
        SymbolConfig symbolConfig = this.generateSymbolConfig();
        PayTableConfig payTableConfig = this.generatePayTableConfig();
        AbstractCtrCommonUtil abstractCtrCommonUtil = Mockito.mock(AbstractCtrCommonUtil.class,
                Mockito.withSettings().useConstructor(symbolConfig, payTableConfig).defaultAnswer(Mockito.CALLS_REAL_METHODS));

        // 2. when
        int actualResult = abstractCtrCommonUtil.calculateHitOdds(3,3);
        int expectResult = 2;

        // 3. then
        assertEquals(expectResult, actualResult);
    }

    // TODO
    private SymbolConfig generateSymbolConfig() {
        return new SymbolConfig(
                List.of(
                        ConstMathSlot.SymbolAttribute.WILD_01,
                        ConstMathSlot.SymbolAttribute.FREE_GAME_01,
                        ConstMathSlot.SymbolAttribute.M1,
                        ConstMathSlot.SymbolAttribute.M2,
                        ConstMathSlot.SymbolAttribute.JOKER
                ),
                List.of(
                        ConstMathSlot.SymbolAttributeType.WILD_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.FREE_GAME_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.BASE_SYMBOL
                ));
    }

    // TODO
    private PayTableConfig generatePayTableConfig() {
        return new PayTableConfig(
                new ArrayList<>(){
                    {
                        add(new PayCombo(List.of(0), List.of(0,0,10,100,1000)));
                        add(new PayCombo(List.of(2), List.of(0,0,5,50,800)));
                        add(new PayCombo(List.of(3), List.of(0,0,5,50,500)));
                        add(new PayCombo(List.of(2,3), List.of(0,0,2,10,100)));
                        add(new PayCombo(List.of(4), List.of(0,0,0,5,20)));
                    }
                }
        );
    }
}
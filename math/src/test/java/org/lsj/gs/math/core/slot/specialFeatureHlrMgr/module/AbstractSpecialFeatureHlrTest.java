package org.lsj.gs.math.core.slot.specialFeatureHlrMgr.module;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// 抽象特殊事件處理者測試
@ExtendWith(MockitoExtension.class)
class AbstractSpecialFeatureHlrTest {

    // 給定錯誤目標標誌，當判斷是否為目標標誌，回傳錯誤
    @Test
    void test_given_wrongTargetSymbolAttribute_when_isTargetSymbolAttribute_then_return_false() {
        // 1. given
        List<ConstMathSlot.SymbolAttribute> targetSymbolAttributeList = List.of(ConstMathSlot.SymbolAttribute.FREE_GAME_01, ConstMathSlot.SymbolAttribute.WILD_02);
        AbstractSpecialFeatureHlr abstractSpecialFeatureHlr = Mockito.mock(AbstractSpecialFeatureHlr.class,
                Mockito.withSettings().useConstructor(targetSymbolAttributeList).defaultAnswer(Mockito.CALLS_REAL_METHODS));
        ConstMathSlot.SymbolAttribute targetSymbolAttribute = ConstMathSlot.SymbolAttribute.M1;

        // 2. when
        boolean actualResult = abstractSpecialFeatureHlr.isTargetSymbolAttribute(targetSymbolAttribute);

        // 3. then
        assertFalse(actualResult);
    }

    // 給定正確目標標誌，當判斷是否為目標標誌，回傳正確
    @Test
    void test_given_correctTargetSymbolAttribute_when_isTargetSymbolAttribute_then_return_true() {
        // 1. given
        List<ConstMathSlot.SymbolAttribute> targetSymbolAttributeList = List.of(ConstMathSlot.SymbolAttribute.FREE_GAME_01, ConstMathSlot.SymbolAttribute.WILD_02);
        AbstractSpecialFeatureHlr abstractSpecialFeatureHlr = Mockito.mock(AbstractSpecialFeatureHlr.class,
                Mockito.withSettings().useConstructor(targetSymbolAttributeList).defaultAnswer(Mockito.CALLS_REAL_METHODS));
        ConstMathSlot.SymbolAttribute targetSymbolAttribute = ConstMathSlot.SymbolAttribute.FREE_GAME_01;

        // 2. when
        boolean actualResult = abstractSpecialFeatureHlr.isTargetSymbolAttribute(targetSymbolAttribute);

        // 3. then
        assertTrue(actualResult);
    }
}
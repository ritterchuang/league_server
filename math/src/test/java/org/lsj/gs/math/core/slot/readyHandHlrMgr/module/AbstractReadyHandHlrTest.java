package org.lsj.gs.math.core.slot.readyHandHlrMgr.module;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfigExtendFix;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 抽象聽牌處理者測試
@ExtendWith(MockitoExtension.class)
class AbstractReadyHandHlrTest {

    // 給定第二軸有兩個 FG，當計算畫面特定欄數FG標誌，預期得到2
    @Test
    void test_given_screenAndColumnIndex1_when_calculateTargetSymbolCountPerColumn_then_return_2() {
        // 1. given
        FrameConfig frameConfig = this.generateFrameConfig();
        SymbolConfig symbolConfig = this.generateSymbolConfig();
        AbstractReadyHandHlr abstractReadyHandHlr = Mockito.mock(AbstractReadyHandHlr.class,
                Mockito.withSettings().useConstructor(frameConfig, symbolConfig).defaultAnswer(Mockito.CALLS_REAL_METHODS));

        List<List<Integer>> screenSymbol = new ArrayList<>(){
            {
                add(List.of(1,2,3));
                add(List.of(3,2,3));
                add(List.of(1,2,3));
                add(List.of(1,2,3));
                add(List.of(1,2,3));
            }
        };

        // 2. when
        int actualResult = abstractReadyHandHlr.calculateTargetSymbolCountPerColumn(screenSymbol, ConstMathSlot.SymbolAttribute.FREE_GAME_01, 1);
        int expectResult = 2;

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
                        ConstMathSlot.SymbolAttribute.FREE_GAME_01,
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

    // TODO
    private FrameConfig generateFrameConfig() {
        return new FrameConfig(ConstMathSlot.FrameType.FIX, new FrameConfigExtendFix(List.of(3,3,3,3,3)));
    }
}
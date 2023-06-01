package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrWay.wayHitUtil;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 路 計算擊中畫面工具包測試
@ExtendWith(MockitoExtension.class)
class WayHitScreenUtilTest {
    WayHitScreenUtil wayHitScreenUtil; // 路 計算擊中畫面工具包

    @BeforeEach
    void initUtil() {
        this.wayHitScreenUtil = new WayHitScreenUtil(this.generateSymbolConfig());
    }

    // 依照給訂畫面，計算擊中畫面
    @Test
    void test_given_hitScreen_when_calculateWildSymbolHitScreen_then_correct() {
        // 1. given
        List<List<Integer>> hitScreen = new ArrayList<>(){
            {
                add(List.of(0,1,2));
                add(List.of(0,1,2));
                add(List.of(3,5,6));
                add(List.of(2,8,9));
                add(List.of(0,1,2));
            }
        };
        List<Integer> targetSymbolIdList = List.of(0, 2);

        // 2. when
        List<List<Integer>> actualResult = this.wayHitScreenUtil.calculateWildSymbolHitScreen(hitScreen, targetSymbolIdList);
        List<List<Integer>> expectResult = new ArrayList<>(){
            {
                add(List.of(0, -1, 2));
                add(List.of(0, -1, 2));
                add(List.of(-1, -1, -1));
                add(List.of(2, -1, -1));
                add(List.of(0, -1, 2));
            }
        };

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 依照給訂畫面，計算擊中畫面
    @Test
    void test_given_hitScreen_when_calculateWildSymbolAndNormalSymbolHitScreen_then_correct() {
        // 1. given
        List<List<Integer>> hitScreen = new ArrayList<>(){
            {
                add(List.of(0,1,2));
                add(List.of(0,3,2));
                add(List.of(3,5,6));
                add(List.of(2,8,5));
                add(List.of(0,4,2));
            }
        };
        List<Integer> targetSymbolIdList = List.of(5, 4);

        // 2. when
        List<List<Integer>> actualResult = this.wayHitScreenUtil.calculateWildSymbolAndNormalSymbolHitScreen(hitScreen, targetSymbolIdList);
        List<List<Integer>> expectResult = new ArrayList<>(){
            {
                add(List.of(0, 1, 2));
                add(List.of(0, -1, 2));
                add(List.of(-1, 5, -1));
                add(List.of(2, -1, 5));
                add(List.of(0, 4, 2));
            }
        };

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 產出標誌設定檔
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
}
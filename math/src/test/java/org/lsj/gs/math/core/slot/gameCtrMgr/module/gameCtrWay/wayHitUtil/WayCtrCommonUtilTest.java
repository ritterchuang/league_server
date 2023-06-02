package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrWay.wayHitUtil;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayTableConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.utils.JsonUtil;
import io.quarkus.test.Mock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 路 計算共同工具包測試
@ExtendWith(MockitoExtension.class)
class WayCtrCommonUtilTest {
    WayCtrCommonUtil wayCtrCommonUtil; // 路共同工具包
    @Mock
    SymbolConfig mockSymbolConfig; // 標誌設定
    @Mock
    PayTableConfig mockPayTableConfig; // 賠率表設定

    // 給畫面連線4軸連線，斷第三軸，計算時由左至右第4軸之後全為 false
    @Test
    void test_give_hitScreenAndHitNumberAndLTR_when_calculateScreenHitPosition_then_correct() {
        // 1. given
        this.wayCtrCommonUtil = new WayCtrCommonUtil(this.mockSymbolConfig, this.mockPayTableConfig);
        List<List<Integer>> hitScreen = new ArrayList<>(){
            {
                add(List.of(1, 2, 3));
                add(List.of(1, 2, -1));
                add(List.of(1, 2, 3));
                add(List.of(-1, -1, -1));
                add(List.of(1, 2, 3));

            }
        };
        int hitNumber = 4;
        ConstMathSlot.GameHitDirectionType gameHitDirectionType = ConstMathSlot.GameHitDirectionType.LEFT_TO_RIGHT;

        // 2. when
        List<List<Boolean>> actualResult = this.wayCtrCommonUtil.calculateScreenHitPosition(hitScreen, hitNumber, gameHitDirectionType);
        List<List<Boolean>> expectResult = new ArrayList<>(){
            {
                add(List.of(true, true, true));
                add(List.of(true, true, false));
                add(List.of(true, true, true));
                add(List.of(false, false, false));
                add(List.of(false, false, false));
            }
        };

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給畫面連線4軸連線，斷第三軸，計算時由右至左，前兩軸全為 false
    @Test
    void test_give_hitScreenAndHitNumberAndRTL_when_calculateScreenHitPosition_then_correct() {
        // 1. given
        this.wayCtrCommonUtil = new WayCtrCommonUtil(this.mockSymbolConfig, this.mockPayTableConfig);
        List<List<Integer>> hitScreen = new ArrayList<>(){
            {
                add(List.of(1, 2, 3));
                add(List.of(1, 2, -1));
                add(List.of(1, 2, 3));
                add(List.of(-1, -1, -1));
                add(List.of(1, 2, 3));

            }
        };
        int hitNumber = 4;
        ConstMathSlot.GameHitDirectionType gameHitDirectionType = ConstMathSlot.GameHitDirectionType.RIGHT_TO_LEFT;

        // 2. when
        List<List<Boolean>> actualResult = this.wayCtrCommonUtil.calculateScreenHitPosition(hitScreen, hitNumber, gameHitDirectionType);
        List<List<Boolean>> expectResult = new ArrayList<>(){
            {
                add(List.of(false, false, false));
                add(List.of(false, false, false));
                add(List.of(true, true, true));
                add(List.of(true, true, false));
                add(List.of(true, true, true));
            }
        };

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }
}
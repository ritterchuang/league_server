package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrLine.lineHitUtil;

import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 線打擊畫面工具包測試
@ExtendWith(MockitoExtension.class)
class LineHitScreenUtilTest {
    LineHitScreenUtil lineHitScreenUtil; // 線打擊畫面工具包
    @Mock
    SymbolConfig mockSymbolConfig; // 標誌設定黨

    // 給定單線畫面以及wild賠率表標誌列表，計算打擊畫面，返回正確
    @Test
    void test_given_screenSymbolPerLineAndWildPayComboTargetSymbolIdList_when_calculateWildSymbolHitScreenPerLine_then_correct() {
        // 1. given
        this.lineHitScreenUtil = new LineHitScreenUtil(this.mockSymbolConfig);
        List<Integer> screenSymbolPerLine = List.of(0,2,3,1,0);
        List<Integer> payComboTargetSymbolIdList = List.of(0,1);

        // 2. when
        List<Integer> actualResult = this.lineHitScreenUtil.calculateWildSymbolHitScreenPerLine(screenSymbolPerLine, payComboTargetSymbolIdList);
        List<Integer> expectResult = List.of(0,-1,-1,1,0);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定單線畫面以及wild賠率表標誌列表，計算打擊畫面，返回正確
    @Test
    void test_given_screenSymbolPerLineAndNormalPayComboTargetSymbolIdList_when_calculateWildSymbolAndNormalSymbolHitScreenPerLine_then_correct() {
        // 1. given
        Mockito.when(this.mockSymbolConfig.isWildSymbol(0)).thenReturn(true);
        Mockito.when(this.mockSymbolConfig.isWildSymbol(1)).thenReturn(true);
        Mockito.when(this.mockSymbolConfig.isWildSymbol(3)).thenReturn(false);
        this.lineHitScreenUtil = new LineHitScreenUtil(this.mockSymbolConfig);
        List<Integer> screenSymbolPerLine = List.of(0,2,3,1,0);
        List<Integer> payComboTargetSymbolIdList = List.of(2);

        // 2. when
        List<Integer> actualResult = this.lineHitScreenUtil.calculateWildSymbolAndNormalSymbolHitScreenPerLine(screenSymbolPerLine, payComboTargetSymbolIdList);
        List<Integer> expectResult = List.of(0,2,-1,1,0);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }
}
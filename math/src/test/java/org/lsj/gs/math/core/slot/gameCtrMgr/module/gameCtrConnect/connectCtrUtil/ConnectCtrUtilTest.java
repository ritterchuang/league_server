package org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrConnect.connectCtrUtil;

import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.connect.ConnectCtrWinResult;
import org.lsj.gs.math.core.slot.gameCtrMgr.module.gameCtrConnect.screenPartionUtil.ScreenPositionCluster;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayCombo;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.payTableConfig.PayTableConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.frameCtr.frameResult.FrameResultExtendFix;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.DampCtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.ReelCtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.ReelStopResultExtendStopByScreen;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// 連接計算器工具包測試
class ConnectCtrUtilTest {
    ConnectCtrUtil connectCtrUtil; // 連接計算器工具包

    @BeforeEach
    void setUp() {
        this.connectCtrUtil = new ConnectCtrUtil(this.generateSymbolConfig(), this.generatePayTableConfig());
    }

    // 給定不存在的 標誌列表，當計算連接得分，回傳 empty
    @Test
    void test_given_noExistSymbolIdList_when_calculateConnectCtrWinResult_then_expect_emptyResult() {
        // 1. given
        List<Integer> symbolIdList = List.of(7);
        ScreenPositionCluster screenPositionCluster = new ScreenPositionCluster();

        // 2. when
        Optional<ConnectCtrWinResult> actualResult = this.connectCtrUtil.calculateConnectCtrWinResult(symbolIdList, screenPositionCluster, 3.0, null);

        // 3. then
        assertTrue(actualResult.isEmpty());
    }

    // 給定空的畫面分類，當計算連接得分，回傳 empty
    @Test
    void test_given_emptyScreenPositionCluster_when_calculateConnectCtrWinResult_then_expect_emptyResult() {
        // 1. given
        List<Integer> symbolIdList = List.of(1);
        ScreenPositionCluster screenPositionCluster = new ScreenPositionCluster();

        // 2. when
        Optional<ConnectCtrWinResult> actualResult = this.connectCtrUtil.calculateConnectCtrWinResult(symbolIdList, screenPositionCluster, 3.0, null);

        // 3. then
        assertTrue(actualResult.isEmpty());
    }

    // 給定取得倍數為 0，當計算連接得分，回傳 empty
    @Test
    void test_given_payOddsIsZero_when_calculateConnectCtrWinResult_then_expect_emptyResult() {
        // 1. given
        List<Integer> symbolIdList = List.of(1);
        ScreenPositionCluster screenPositionCluster = new ScreenPositionCluster(List.of(new int[]{0, 0}, new int[]{0, 1}));

        // 2. when
        Optional<ConnectCtrWinResult> actualResult = this.connectCtrUtil.calculateConnectCtrWinResult(symbolIdList, screenPositionCluster, 3.0, null);

        // 3. then
        assertTrue(actualResult.isEmpty());
    }

    // 給定正確資訊，當計算連結得分，回傳正確結果
    @Test
    void test_given_exactWinScreen_when_calculateConnectCtrWinResult_then_expect_correctResult() {
        // 1. given
        List<Integer> symbolIdList = List.of(1);
        ScreenPositionCluster screenPositionCluster = new ScreenPositionCluster(List.of(new int[]{0, 0}, new int[]{0, 1}, new int[]{0, 2}));

        // 2. when
        Optional<ConnectCtrWinResult> actualResult = this.connectCtrUtil.calculateConnectCtrWinResult(symbolIdList, screenPositionCluster, 3.0, this.generateScreenGtrResult());
        ConnectCtrWinResult expectResult = this.generateConnectCtrWinResult(1, 240.0);

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult.get());
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }

    // 給定正確資訊，當計算畫面倍數連結得分，回傳正確結果
    @Test
    void test_given_exactWinScreen_when_calculateConnectCtrWinResultWithScreenMultiplier_then_expect_correctResult() {
        // 1. given
        List<Integer> symbolIdList = List.of(1);
        int screenMultiplier = 5;
        ScreenPositionCluster screenPositionCluster = new ScreenPositionCluster(List.of(new int[]{0, 0}, new int[]{0, 1}, new int[]{0, 2}));

        // 2. when
        Optional<ConnectCtrWinResult> actualResult = this.connectCtrUtil.calculateConnectCtrWinResultWithScreenMultiplier(symbolIdList, screenPositionCluster, screenMultiplier, 3.0, this.generateScreenGtrResult());
        ConnectCtrWinResult expectResult = this.generateConnectCtrWinResult(screenMultiplier, 1200.0);

        // 3. then
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult.get());
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        assertEquals(expectString, actualString);
    }


    private SymbolConfig generateSymbolConfig() {
        return new SymbolConfig(
                List.of(ConstMathSlot.SymbolAttribute.M1,       // 0
                        ConstMathSlot.SymbolAttribute.M2,       // 1
                        ConstMathSlot.SymbolAttribute.M3,       // 2
                        ConstMathSlot.SymbolAttribute.M4,       // 3
                        ConstMathSlot.SymbolAttribute.M5,       // 4
                        ConstMathSlot.SymbolAttribute.M6),      // 5
                List.of(ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL,
                        ConstMathSlot.SymbolAttributeType.MAIN_SYMBOL)
        );
    }

    private PayTableConfig generatePayTableConfig() {
        return new PayTableConfig(
                List.of(
                        new PayCombo(List.of(0), List.of(0,0,100,500,1000)),
                        new PayCombo(List.of(1), List.of(0,0,80,400,800)),
                        new PayCombo(List.of(2), List.of(0,0,50,100,500)),
                        new PayCombo(List.of(3), List.of(0,0,30,250,500)),
                        new PayCombo(List.of(4), List.of(0,0,30,200,400)),
                        new PayCombo(List.of(5), List.of(0,0,10,50,100))
                )
        );
    }

    private ScreenGtrResult generateScreenGtrResult() {
        return new ScreenGtrResult(
                List.of(List.of(1, 1, 1), List.of(3, 4, 5), List.of(3, 4, 5), List.of(6, 7, 8), List.of(6, 7, 8)),
                new FrameResult(ConstMathSlot.FrameType.FIX, new FrameResultExtendFix(List.of(3, 3, 3, 3, 3))),
                new ReelCtrResult(0, ConstMathSlot.ReelStopType.STOP_BY_SCREEN, new ReelStopResultExtendStopByScreen(new DampCtrResult(), null, null))
        );
    }

    private ConnectCtrWinResult generateConnectCtrWinResult(int multiplier, double totalWin) {
        return new ConnectCtrWinResult(1, 3, 80, multiplier, totalWin,
                List.of(List.of(true, true, true), List.of(false, false, false), List.of(false, false, false), List.of(false, false, false), List.of(false, false, false)),
                List.of());
    }

}
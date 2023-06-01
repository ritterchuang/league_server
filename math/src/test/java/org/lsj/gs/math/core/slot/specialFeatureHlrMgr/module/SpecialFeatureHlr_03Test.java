package org.lsj.gs.math.core.slot.specialFeatureHlrMgr.module;

import org.lsj.gs.math.core.common.table.entity.message.slot.betSpinTypeExtend.BetSpinTypeExtendNoneNormal;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.frameConfig.FrameConfigExtendFix;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.specialFeatureConfig.SpecialFeatureConfig;
import org.lsj.gs.math.core.slot.mathSlotConfigHlr.enity.sever.symbolConfig.SymbolConfig;
import org.lsj.gs.math.core.slot.screenGtrMgr.enity.ScreenGtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.DampCtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.ReelCtrResult;
import org.lsj.gs.math.core.slot.screenGtrMgr.module.reelCtr.reelCtrResult.ReelStopResultExtendStopByDependentReelIndex;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrConfig;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResult;
import org.lsj.utils.JsonUtil;
import io.quarkus.test.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// 特殊事件處理者03 測試 [畫面中恰有 4 欄有 FreeGame_01]
@ExtendWith(MockitoExtension.class)
class SpecialFeatureHlr_03Test {
    ISpecialFeatureHlr specialFeatureHlr; // 特殊事件處理者
    @Mock
    ITableUtil mockTableUtil; // 牌桌工具包

    @BeforeEach
    void initHlr() {
        this.specialFeatureHlr = new SpecialFeatureHlr_03(this.generateSpecialFeatureHlrConfig(), mockTableUtil);
    }

    // 給定觸發畫面，計算特殊事件，結果正確
    @Test
    void test_given_hitScreenWithTrigger_when_calculateSpecialFeatureHlrResult_then_correct() {
        // 1. given
        SpinRequest spinRequest = new SpinRequest(3, 50, ConstMathSlot.BetType.NONE, ConstMathSlot.SpinType.NORMAL, new BetSpinTypeExtendNoneNormal());
        ScreenGtrResult screenGtrResult = new ScreenGtrResult(new ArrayList<>(){
            {
                add(List.of(3, 4, 5));
                add(List.of(6, 4, 5));
                add(List.of(3, 4, 5));
                add(List.of(3, 4, 5));
                add(List.of(3, 4, 5));
            }
        }, null, new ReelCtrResult(0, ConstMathSlot.ReelStopType.STOP_BY_DEPENDENT_REEL_INDEX, new ReelStopResultExtendStopByDependentReelIndex(new DampCtrResult(), new ArrayList<>())));

        // 2. when
        Optional<SpecialFeatureHlrResult> actualResult = this.specialFeatureHlr.calculateSpecialFeatureHlrResult(spinRequest, screenGtrResult, new ArrayList<>());
        SpecialFeatureHlrResult expectResult = new SpecialFeatureHlrResult(
                ConstMathSlot.SpecialFeatureType.SF_01,
                ConstMathSlot.TriggerEvent.TRIGGER_01,
                new ArrayList<>(){
                    {
                        add(List.of(true, false, false));
                        add(List.of(false, false, false));
                        add(List.of(true, false, false));
                        add(List.of(true, false, false));
                        add(List.of(true, false, false));
                    }
                },
                new ArrayList<>(),
                100.0);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult.get());
        assertEquals(expectString, actualString);
    }

    // 給定沒觸發畫面，計算特殊事件，結果正確
    @Test
    void test_given_hitScreenWithNoTrigger_when_calculateSpecialFeatureHlrResult_then_correct() {
        // 1. given
        SpinRequest spinRequest = new SpinRequest(3, 50, ConstMathSlot.BetType.NONE, ConstMathSlot.SpinType.NORMAL, new BetSpinTypeExtendNoneNormal());
        ScreenGtrResult screenGtrResult = new ScreenGtrResult(new ArrayList<>(){
            {
                add(List.of(6, 4, 5));
                add(List.of(6, 4, 5));
                add(List.of(3, 4, 5));
                add(List.of(6, 4, 5));
                add(List.of(6, 4, 5));
            }
        }, null, new ReelCtrResult(0, ConstMathSlot.ReelStopType.STOP_BY_DEPENDENT_REEL_INDEX, new ReelStopResultExtendStopByDependentReelIndex(new DampCtrResult(), new ArrayList<>())));

        // 2. when
        Optional<SpecialFeatureHlrResult> actualResult = this.specialFeatureHlr.calculateSpecialFeatureHlrResult(spinRequest, screenGtrResult, new ArrayList<>());

        // 3. then
        assertTrue(actualResult.isEmpty());
    }

    private SpecialFeatureHlrConfig generateSpecialFeatureHlrConfig() {
        return new SpecialFeatureHlrConfig(
                new FrameConfig(ConstMathSlot.FrameType.FIX, new FrameConfigExtendFix(List.of(3,3,3,3,3))),
                this.generateSymbolConfig(),
                new SpecialFeatureConfig(ConstMathSlot.SpecialFeatureType.SF_01, ConstMathSlot.TriggerEvent.TRIGGER_01, 2));
    }

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
}
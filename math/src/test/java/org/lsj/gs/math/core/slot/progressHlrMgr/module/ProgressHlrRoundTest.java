package org.lsj.gs.math.core.slot.progressHlrMgr.module;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResultNormal;
import org.lsj.gs.math.core.common.table.module.tableUtil.ITableUtil;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.accumulateWinCtr.AccumulateWinCtrResult;
import org.lsj.gs.math.core.slot.commonInputHlr.enity.result.CommonInputResult;
import org.lsj.gs.math.core.slot.gameStateInputHlrMgr.enity.result.GameStateInputResult;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfig;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.config.ProgressConfigExtendRound;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResult;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.ProgressHlrResultExtendRound;
import org.lsj.gs.math.core.slot.progressHlrMgr.enity.result.RoundProgress;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResult;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;
import org.lsj.gs.math.games.pxky_java.enity.result.gameStateResult.GameStateHlrResultExtendPxkyBaseGame;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 進度處理者場次測試
@ExtendWith(MockitoExtension.class)
class ProgressHlrRoundTest {
    ProgressHlrRound progressHlr; // 進度處理者
    @Mock
    ProgressConfig mockConfig; // 設定檔
    @Mock
    ITableUtil mockTableUtil; // 牌桌工具包

    // 給定第一場，上一局觸發 fg，該場Trigger，計算第1場遊戲進度正確
    @Test
    void test_given_firstRoundTriggerAndBeforeGameStateTrigger_when_calculateProgressHlrResult_then_correct() {
        // 1. given
        Mockito.when(this.mockConfig.getProgressType()).thenReturn(ConstMathSlot.ProgressType.ROUND);
        Mockito.when(this.mockConfig.getProgressConfigExtend()).thenReturn(
                new ProgressConfigExtendRound(5, 15, 20));
        this.progressHlr = new ProgressHlrRound(this.mockConfig, this.mockTableUtil);
        int roundIndex = 0;
        GameStateHlrResult beforeGameStateHlrResult = new GameStateHlrResult(
                1,
                0,
                0,
                10.0,
                ConstMathSlot.GameStateType.PXKY_BASEGAME,
                new GameStateHlrResultExtendPxkyBaseGame(),
                ConstMathSlot.RoundType.NORMAL,
                new ArrayList<>(){
                    {
                        add(new RoundHlrResultNormal(
                                0,
                                7.0,
                                new SpecialFeatureHlrResultCluster(0, List.of(new SpecialFeatureHlrResult(ConstMathSlot.SpecialFeatureType.SF_03, ConstMathSlot.TriggerEvent.TRIGGER_03, null, null, 0.0))),
                                null,
                                null,
                                new AccumulateWinCtrResult(0.0, 7.0), null, null, null, null));
                    }
                },
                new CommonInputResult(),
                new GameStateInputResult()
        );
        SpecialFeatureHlrResultCluster specialFeatureHlrResultCluster = new SpecialFeatureHlrResultCluster(
                0.0,
                new ArrayList<>(){
                    {
                        add(new SpecialFeatureHlrResult(ConstMathSlot.SpecialFeatureType.SF_02, ConstMathSlot.TriggerEvent.TRIGGER_02, new ArrayList<>(), new ArrayList<>(), 0.0));
                    }
                }
        );

        // 2. when
        ProgressHlrResult actualResult = this.progressHlr.calculateProgressHlrResult(roundIndex, beforeGameStateHlrResult, new ArrayList<>(), specialFeatureHlrResultCluster);
        ProgressHlrResult expectResult = new ProgressHlrResult(
                false,
                ConstMathSlot.ProgressType.ROUND,
                new ProgressHlrResultExtendRound(new RoundProgress(1,0,5)));

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定第一場，上一局觸發 fg，該場Trigger，計算第1場遊戲進度正確
    @Test
    void test_given_firstRoundReTriggerAndBeforeGameStateTrigger_when_calculateProgressHlrResult_then_correct() {
        // 1. given
        Mockito.when(this.mockConfig.getProgressType()).thenReturn(ConstMathSlot.ProgressType.ROUND);
        Mockito.when(this.mockConfig.getProgressConfigExtend()).thenReturn(
                new ProgressConfigExtendRound(5, 15, 20));
        this.progressHlr = new ProgressHlrRound(this.mockConfig, this.mockTableUtil);
        int roundIndex = 0;
        GameStateHlrResult beforeGameStateHlrResult = new GameStateHlrResult(
                1,
                0,
                0,
                10.0,
                ConstMathSlot.GameStateType.PXKY_BASEGAME,
                new GameStateHlrResultExtendPxkyBaseGame(),
                ConstMathSlot.RoundType.NORMAL,
                new ArrayList<>(){
                    {
                        add(new RoundHlrResultNormal(
                                0,
                                7.0,
                                new SpecialFeatureHlrResultCluster(0, List.of(new SpecialFeatureHlrResult(ConstMathSlot.SpecialFeatureType.SF_03, ConstMathSlot.TriggerEvent.TRIGGER_03, null, null, 0.0))),
                                null,
                                null,
                                new AccumulateWinCtrResult(0.0, 7.0), null, null, null, null));
                    }
                },
                new CommonInputResult(),
                new GameStateInputResult()
        );
        SpecialFeatureHlrResultCluster specialFeatureHlrResultCluster = new SpecialFeatureHlrResultCluster(
                0.0,
                new ArrayList<>(){
                    {
                        add(new SpecialFeatureHlrResult(ConstMathSlot.SpecialFeatureType.SF_02, ConstMathSlot.TriggerEvent.RE_TRIGGER_03, new ArrayList<>(), new ArrayList<>(), 0.0));
                    }
                }
        );

        // 2. when
        ProgressHlrResult actualResult = this.progressHlr.calculateProgressHlrResult(roundIndex, beforeGameStateHlrResult, new ArrayList<>(), specialFeatureHlrResultCluster);
        ProgressHlrResult expectResult = new ProgressHlrResult(
                false,
                ConstMathSlot.ProgressType.ROUND,
                new ProgressHlrResultExtendRound(new RoundProgress(1,15,5)));

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定上一狀態 trigger，第一局 reTrigger，計算第二場遊戲進度正確
    @Test
    void test_given_secondRoundReTriggerAndBeforeGameStateTrigger_when_calculateProgressHlrResult_then_correct() {
        // 1. given
        Mockito.when(this.mockConfig.getProgressType()).thenReturn(ConstMathSlot.ProgressType.ROUND);
        Mockito.when(this.mockConfig.getProgressConfigExtend()).thenReturn(
                new ProgressConfigExtendRound(5, 15, 20));
        this.progressHlr = new ProgressHlrRound(this.mockConfig, this.mockTableUtil);
        int roundIndex = 1;
        GameStateHlrResult beforeGameStateHlrResult = new GameStateHlrResult(
                1,
                0,
                0,
                10.0,
                ConstMathSlot.GameStateType.PXKY_BASEGAME,
                new GameStateHlrResultExtendPxkyBaseGame(),
                ConstMathSlot.RoundType.NORMAL,
                new ArrayList<>(){
                    {
                        add(new RoundHlrResultNormal(
                                0,
                                7.0,
                                new SpecialFeatureHlrResultCluster(0, List.of(new SpecialFeatureHlrResult(ConstMathSlot.SpecialFeatureType.SF_03, ConstMathSlot.TriggerEvent.TRIGGER_03, null, null, 0.0))),
                                null,
                                null,
                                new AccumulateWinCtrResult(0.0, 7.0), null, null, null, null));
                    }
                },
                new CommonInputResult(),
                new GameStateInputResult()
        );
        List<RoundHlrResult> preRoundHlrResultList = new ArrayList<>(){
            {
                add(new RoundHlrResultNormal(0, 0.0, new SpecialFeatureHlrResultCluster(),
                        new ProgressHlrResult(false, ConstMathSlot.ProgressType.ROUND, new ProgressHlrResultExtendRound(
                                new RoundProgress(1, 5, 15)
                        )),
                        null,null, null, null, null, null));
            }
        };
        SpecialFeatureHlrResultCluster specialFeatureHlrResultCluster = new SpecialFeatureHlrResultCluster(
                0.0,
                new ArrayList<>(){
                    {
                        add(new SpecialFeatureHlrResult(ConstMathSlot.SpecialFeatureType.SF_02, ConstMathSlot.TriggerEvent.TRIGGER_02, new ArrayList<>(), new ArrayList<>(), 0.0));
                    }
                }
        );

        // 2. when
        ProgressHlrResult actualResult = this.progressHlr.calculateProgressHlrResult(roundIndex, beforeGameStateHlrResult, preRoundHlrResultList, specialFeatureHlrResultCluster);
        ProgressHlrResult expectResult = new ProgressHlrResult(
                true,
                ConstMathSlot.ProgressType.ROUND,
                new ProgressHlrResultExtendRound(new RoundProgress(2,0,20)));

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }
}
package org.lsj.gs.math.core.slot.accumulateWinCtr;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResultNormal;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.commonInputHlr.enity.result.CommonInputResult;
import org.lsj.gs.math.core.slot.gameStateInputHlrMgr.enity.result.GameStateInputResult;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResult;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;
import org.lsj.gs.math.games.pxky_java.enity.result.gameStateResult.GameStateHlrResultExtendPxkyBaseGame;
import org.lsj.utils.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 累積得分計算者測試
@ExtendWith(MockitoExtension.class)
class AccumulateWinCtrTest {
    AccumulateWinCtr accumulateWinCtr; // 累積得分計算者

    @BeforeEach
    void initCtr() {
        this.accumulateWinCtr = new AccumulateWinCtr();
    }

    // 給定 BaseGame 第一局，計算累積得分正確
    @Test
    void test_given_baseGameFirstRound_when_calculateAccumulateWinCtrResult_then_correct() {
        // 1. given
        int currentRoundIndex = 0;
        double currentRoundWin = 5.0;
        GameStateHlrResult beforeGameStateHlrResult = new GameStateHlrResult();
        List<RoundHlrResult> roundHlrResultList = new ArrayList<>();

        // 2. when
        AccumulateWinCtrResult actualResult = this.accumulateWinCtr.calculateAccumulateWinCtrResultRound(
                currentRoundIndex,
                currentRoundWin,
                beforeGameStateHlrResult,
                roundHlrResultList);
        AccumulateWinCtrResult expectResult = new AccumulateWinCtrResult(0.0, 5.0);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定 FreeGame 第一局，計算累積得分正確
    @Test
    void test_given_freeGameFirstRound_when_calculateAccumulateWinCtrResult_then_correct() {
        // 1. given
        int currentRoundIndex = 0;
        double currentRoundWin = 5.0;
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
                                new SpecialFeatureHlrResultCluster(0, List.of(new SpecialFeatureHlrResult(ConstMathSlot.SpecialFeatureType.SF_01, ConstMathSlot.TriggerEvent.GET_PAY_01, null, null, 0.0))),
                                null,
                                null,
                                new AccumulateWinCtrResult(0.0, 7.0), null, null, null, null));
                        add(new RoundHlrResultNormal(
                                0,
                                3.0,
                                new SpecialFeatureHlrResultCluster(0, List.of(new SpecialFeatureHlrResult(ConstMathSlot.SpecialFeatureType.SF_01, ConstMathSlot.TriggerEvent.GET_PAY_01, null, null, 0.0))),
                                null,
                                null,
                                new AccumulateWinCtrResult(7.0, 10.0), null, null, null, null));
                    }
                },
                new CommonInputResult(),
                new GameStateInputResult()
        );
        List<RoundHlrResult> roundHlrResultList = new ArrayList<>();

        // 2. when
        AccumulateWinCtrResult actualResult = this.accumulateWinCtr.calculateAccumulateWinCtrResultRound(
                currentRoundIndex,
                currentRoundWin,
                beforeGameStateHlrResult,
                roundHlrResultList);
        AccumulateWinCtrResult expectResult = new AccumulateWinCtrResult(10.0, 15.0);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }

    // 給定 FreeGame 第3局，計算累積得分正確
    @Test
    void test_given_freeGameThirdRound_when_calculateAccumulateWinCtrResult_then_correct() {
        // 1. given
        int currentRoundIndex = 2;
        double currentRoundWin = 5.0;
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
                                new SpecialFeatureHlrResultCluster(0, List.of(new SpecialFeatureHlrResult(ConstMathSlot.SpecialFeatureType.SF_01, ConstMathSlot.TriggerEvent.GET_PAY_01, null, null, 0.0))),
                                null,
                                null,
                                new AccumulateWinCtrResult(0.0, 7.0), null, null, null, null));
                        add(new RoundHlrResultNormal(
                                0,
                                3.0,
                                new SpecialFeatureHlrResultCluster(0, List.of(new SpecialFeatureHlrResult(ConstMathSlot.SpecialFeatureType.SF_01, ConstMathSlot.TriggerEvent.GET_PAY_01, null, null, 0.0))),
                                null,
                                null,
                                new AccumulateWinCtrResult(7.0, 10.0), null, null, null, null));
                    }
                },
                new CommonInputResult(),
                new GameStateInputResult()
        );
        List<RoundHlrResult> roundHlrResultList = new ArrayList<>(){
            {
                add(new RoundHlrResultNormal(
                        0,
                        7.0,
                        new SpecialFeatureHlrResultCluster(0, List.of(new SpecialFeatureHlrResult(ConstMathSlot.SpecialFeatureType.SF_01, ConstMathSlot.TriggerEvent.GET_PAY_01, null, null, 0.0))),
                        null,
                        null,
                        new AccumulateWinCtrResult(10.0, 17.0), null, null, null, null));
                add(new RoundHlrResultNormal(
                        0,
                        3.0,
                        new SpecialFeatureHlrResultCluster(0, List.of(new SpecialFeatureHlrResult(ConstMathSlot.SpecialFeatureType.SF_01, ConstMathSlot.TriggerEvent.GET_PAY_01, null, null, 0.0))),
                        null,
                        null,
                        new AccumulateWinCtrResult(17.0, 20.0), null, null, null, null));
            }
        };

        // 2. when
        AccumulateWinCtrResult actualResult = this.accumulateWinCtr.calculateAccumulateWinCtrResultRound(
                currentRoundIndex,
                currentRoundWin,
                beforeGameStateHlrResult,
                roundHlrResultList);
        AccumulateWinCtrResult expectResult = new AccumulateWinCtrResult(20.0, 25.0);

        // 3. then
        String expectString = JsonUtil.getInstance().writeValueAsStringWithoutException(expectResult);
        String actualString = JsonUtil.getInstance().writeValueAsStringWithoutException(actualResult);
        assertEquals(expectString, actualString);
    }
}
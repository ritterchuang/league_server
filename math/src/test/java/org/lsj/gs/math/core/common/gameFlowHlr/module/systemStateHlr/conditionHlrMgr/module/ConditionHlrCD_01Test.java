package org.lsj.gs.math.core.common.gameFlowHlr.module.systemStateHlr.conditionHlrMgr.module;

import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResultNormal;
import org.lsj.gs.math.core.common.table.entity.message.slot.betSpinTypeExtend.BetSpinTypeExtendNoneNormal;
import org.lsj.gs.math.core.slot.ConstMathSlot;
import org.lsj.gs.math.core.slot.clientSpinRequestHlr.enity.SpinRequest;
import org.lsj.gs.math.core.slot.commonInputHlr.enity.result.CommonInputResult;
import org.lsj.gs.math.core.slot.gameStateInputHlrMgr.enity.result.GameStateInputResult;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResult;
import org.lsj.gs.math.core.slot.specialFeatureHlrMgr.enity.SpecialFeatureHlrResultCluster;
import org.lsj.gs.math.games.pxky_java.enity.result.gameStateResult.GameStateHlrResultExtendPxkyBaseGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// 條件處理者01 測試
@ExtendWith(MockitoExtension.class)
class ConditionHlrCD_01Test {
    IConditionHlr conditionHlr; // 條件處理者

    @BeforeEach
    void initHlr() {
        this.conditionHlr = new ConditionHlrCD_01();
    }

    // 給定空陣列，預期回傳 false
    @Test
    void test_given_emptyRoundHlrResultList_when_checkCondition_then_false() {
        // 1. given
        SpinRequest spinRequest = this.generateSpinRequest();
        GameStateHlrResult gameStateHlrResult = new GameStateHlrResult();

        // 2. when
        boolean actualResult = this.conditionHlr.checkCondition(spinRequest, gameStateHlrResult);

        // 3. then
        assertFalse(actualResult);
    }

    // 給定第一場觸發，最後場沒觸發，預期回傳 false
    @Test
    void test_given_firstRoundTriggerAndLastRoundNoTrigger_when_checkCondition_then_false() {
        // 1. given
        SpinRequest spinRequest = this.generateSpinRequest();
        GameStateHlrResult gameStateHlrResult = new GameStateHlrResult(
                1,
                0,
                0,
                10,
                ConstMathSlot.GameStateType.PXKY_BASEGAME,
                new GameStateHlrResultExtendPxkyBaseGame(),
                ConstMathSlot.RoundType.NORMAL,
                List.of(
                        new RoundHlrResultNormal(0, 5, new SpecialFeatureHlrResultCluster(0, List.of(new SpecialFeatureHlrResult(ConstMathSlot.SpecialFeatureType.SF_01, ConstMathSlot.TriggerEvent.TRIGGER_01, null, null, 0.0))), null, null, null, null, null, null, null),
                        new RoundHlrResultNormal(0, 5, new SpecialFeatureHlrResultCluster(0, List.of(new SpecialFeatureHlrResult(ConstMathSlot.SpecialFeatureType.SF_01, ConstMathSlot.TriggerEvent.RE_TRIGGER_01, null, null, 0.0))), null, null, null, null, null, null, null)
                ),
                new CommonInputResult(),
                new GameStateInputResult()
        );

        // 2. when
        boolean actualResult = this.conditionHlr.checkCondition(spinRequest, gameStateHlrResult);

        // 3. then
        assertFalse(actualResult);
    }

    // 給定第一場沒觸發，最後場觸發，預期回傳 true
    @Test
    void test_given_firstRoundNoTriggerAndLastRoundTrigger_when_checkCondition_then_false() {
        // 1. given
        SpinRequest spinRequest = this.generateSpinRequest();
        GameStateHlrResult gameStateHlrResult = new GameStateHlrResult(
                1,
                0,
                0,
                10,
                ConstMathSlot.GameStateType.PXKY_BASEGAME,
                new GameStateHlrResultExtendPxkyBaseGame(),
                ConstMathSlot.RoundType.NORMAL,
                List.of(
                        new RoundHlrResultNormal(0, 5, new SpecialFeatureHlrResultCluster(0, List.of(new SpecialFeatureHlrResult(ConstMathSlot.SpecialFeatureType.SF_01, ConstMathSlot.TriggerEvent.GET_PAY_01, null, null, 0.0))), null, null, null, null, null, null, null),
                        new RoundHlrResultNormal(0, 5, new SpecialFeatureHlrResultCluster(0, List.of(new SpecialFeatureHlrResult(ConstMathSlot.SpecialFeatureType.SF_01, ConstMathSlot.TriggerEvent.TRIGGER_01, null, null, 0.0))), null, null, null, null, null, null, null)
                ),
                new CommonInputResult(),
                new GameStateInputResult()
        );

        // 2. when
        boolean actualResult = this.conditionHlr.checkCondition(spinRequest, gameStateHlrResult);

        // 3. then
        assertTrue(actualResult);
    }

    // 產出 SpinRequest
    private SpinRequest generateSpinRequest() {
        return new SpinRequest(1.0, 100, ConstMathSlot.BetType.NONE, ConstMathSlot.SpinType.NORMAL, new BetSpinTypeExtendNoneNormal());
    }
}
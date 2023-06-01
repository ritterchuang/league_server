package com.lx.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.slot;

import com.lx.gs.math.core.common.gameFlowHlr.enity.result.GameFlowHlrResult;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.GameStateHlrResult;
import com.lx.gs.math.core.slot.ConstMathSlot;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import com.lx.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.AbstractTestCaseCtr;

import java.util.List;

// 抽象虎機測項計算器
public abstract class AbstractTestCaseCtrSlot extends AbstractTestCaseCtr {

    public AbstractTestCaseCtrSlot(TestCaseConfig testCaseConfig) {
        super(testCaseConfig);
    }

    // 是否滿足遊戲狀態
    protected boolean isAnyMatchGameState(GameFlowHlrResult gameFlowHlrResult, ConstMathSlot.GameStateType gameStateType) {
        List<GameStateHlrResult> gameStateHlrResultList = gameFlowHlrResult.getGameStateHlrResultList();

        return gameStateHlrResultList.stream().anyMatch(gameStateHlrResult -> gameStateHlrResult.getGameStateType().equals(gameStateType));
    }

    // 計算遊戲狀態指標
    protected int calculateGameStateId(List<GameStateHlrResult> gameStateHlrResultList, ConstMathSlot.GameStateType gameStateType) {
        for (int gameStateIndex = 0; gameStateIndex < gameStateHlrResultList.size(); gameStateIndex++) {
            if (gameStateHlrResultList.get(gameStateIndex).getGameStateType().equals(gameStateType)) {
                return gameStateIndex;
            }
        }

        return -1;
    }
}

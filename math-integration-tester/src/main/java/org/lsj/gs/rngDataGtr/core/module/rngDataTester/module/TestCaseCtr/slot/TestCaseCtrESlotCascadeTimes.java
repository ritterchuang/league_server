package org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.slot;

import org.lsj.gs.math.core.common.gameFlowHlr.enity.result.GameFlowHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResultCascade;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.slot.GameResultExtendSlotJava;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigESlotCascadeTimes;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;

import java.util.List;

// 測項計算器 消除虎機 消除次數測項
public class TestCaseCtrESlotCascadeTimes extends AbstractTestCaseCtrSlot {
    private final TestCaseConfigESlotCascadeTimes testCaseConfigExtend; // 測項計算器設定

    public TestCaseCtrESlotCascadeTimes(TestCaseConfig testCaseConfig) {
        super(testCaseConfig);
        this.testCaseConfigExtend = (TestCaseConfigESlotCascadeTimes) testCaseConfig.getTestCaseConfigExtend();
    }

    // 計算測項結果
    public TestCaseResult calculateTestCaseResult(BoardGtrResult boardGtrResult){
        return super.calculateTestCaseResult(boardGtrResult, this.calculateIsMatch(boardGtrResult, this.testCaseConfigExtend));
    }

    // 計算符合標籤
    private boolean calculateIsMatch(BoardGtrResult boardGtrResult, TestCaseConfigESlotCascadeTimes testCaseConfigExtend){
        // 1. 取得參數
        GameResultExtendSlotJava gameResultExtend = (GameResultExtendSlotJava)boardGtrResult.getGameResultExtend();
        GameFlowHlrResult gameFlowHlrResult = gameResultExtend.getGameFlowHlrResult();

        // 2. 計算遊戲狀態符合性
        boolean gameStateFlag = super.isAnyMatchGameState(gameFlowHlrResult, testCaseConfigExtend.getGameStateType());
        if (!gameStateFlag) {
            return false;
        }

        // 3. 計算真人下注符合性
        return this.isValidCascadeTimes(gameFlowHlrResult, testCaseConfigExtend);
    }

    // 是否滿足消除次數
    private boolean isValidCascadeTimes(GameFlowHlrResult gameFlowHlrResult, TestCaseConfigESlotCascadeTimes testCaseConfigExtend) {
        // 1. 取得參數
        int gameStateId = super.calculateGameStateId(gameFlowHlrResult.getGameStateHlrResultList(), testCaseConfigExtend.getGameStateType());
        int cascadeTimes = testCaseConfigExtend.getCascadeTimes();
        List<CascadeHlrResult> cascadeHlrResultList = ((RoundHlrResultCascade)gameFlowHlrResult.getGameStateHlrResultList().get(gameStateId).getRoundHlrResultList().get(0))
                                                            .getCascadeClusterHlrResult().getCascadeHlrResultList();

        // 2. 判斷是否符合
        return cascadeTimes == cascadeHlrResultList.size();
    }
}

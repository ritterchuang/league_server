package org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.slot;

import org.lsj.gs.math.core.common.gameFlowHlr.enity.result.GameFlowHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResultCascade;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.way.GameCtrResultExtendWay;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.way.WayCtrWinResult;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.slot.GameResultExtendSlotJava;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigESlotCascadeGameState;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;

// 測項計算器 消除虎機 得分類型測項
public class TestCaseCtrESlotCascadeGameState extends AbstractTestCaseCtrSlot {
    private final TestCaseConfigESlotCascadeGameState testCaseConfigExtend; // 測項計算器設定

    public TestCaseCtrESlotCascadeGameState(TestCaseConfig testCaseConfig) {
        super(testCaseConfig);
        this.testCaseConfigExtend = (TestCaseConfigESlotCascadeGameState) testCaseConfig.getTestCaseConfigExtend();
    }

    // 計算測項結果
    public TestCaseResult calculateTestCaseResult(BoardGtrResult boardGtrResult){
        return super.calculateTestCaseResult(boardGtrResult, this.calculateIsMatch(boardGtrResult, this.testCaseConfigExtend));
    }

    // 計算符合標籤
    private boolean calculateIsMatch(BoardGtrResult boardGtrResult, TestCaseConfigESlotCascadeGameState testCaseConfigExtend){
        // 1. 取得參數
        GameResultExtendSlotJava gameResultExtend = (GameResultExtendSlotJava)boardGtrResult.getGameResultExtend();
        GameFlowHlrResult gameFlowHlrResult = gameResultExtend.getGameFlowHlrResult();

        // 2. 計算遊戲狀態符合性
        return super.isAnyMatchGameState(gameFlowHlrResult, testCaseConfigExtend.getGameStateType());
    }
}

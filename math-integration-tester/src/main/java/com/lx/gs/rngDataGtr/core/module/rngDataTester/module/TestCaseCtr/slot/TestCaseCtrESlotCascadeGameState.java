package com.lx.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.slot;

import com.lx.gs.math.core.common.gameFlowHlr.enity.result.GameFlowHlrResult;
import com.lx.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResultCascade;
import com.lx.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import com.lx.gs.math.core.slot.gameCtrMgr.enity.result.way.GameCtrResultExtendWay;
import com.lx.gs.math.core.slot.gameCtrMgr.enity.result.way.WayCtrWinResult;
import com.lx.gs.mathStr.core.entity.playStr.BoardGtrResult;
import com.lx.gs.mathStr.core.entity.playStr.gameResultExtend.slot.GameResultExtendSlotJava;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigESlotCascadeGameState;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigESlotCascadeWayGame;
import com.lx.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;

import java.util.List;

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

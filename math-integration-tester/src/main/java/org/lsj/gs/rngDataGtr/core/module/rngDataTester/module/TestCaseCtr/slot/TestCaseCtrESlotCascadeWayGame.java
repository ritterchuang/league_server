package org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.slot;

import org.lsj.gs.math.core.common.gameFlowHlr.enity.result.GameFlowHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResultCascade;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.way.GameCtrResultExtendWay;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.way.WayCtrWinResult;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.slot.GameResultExtendSlotJava;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigESlotCascadeWayGame;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;

import java.util.List;

// 測項計算器 消除虎機 得分類型測項
public class TestCaseCtrESlotCascadeWayGame extends AbstractTestCaseCtrSlot {
    private final TestCaseConfigESlotCascadeWayGame testCaseConfigExtend; // 測項計算器設定

    public TestCaseCtrESlotCascadeWayGame(TestCaseConfig testCaseConfig) {
        super(testCaseConfig);
        this.testCaseConfigExtend = (TestCaseConfigESlotCascadeWayGame) testCaseConfig.getTestCaseConfigExtend();
    }

    // 計算測項結果
    public TestCaseResult calculateTestCaseResult(BoardGtrResult boardGtrResult){
        return super.calculateTestCaseResult(boardGtrResult, this.calculateIsMatch(boardGtrResult, this.testCaseConfigExtend));
    }

    // 計算符合標籤
    private boolean calculateIsMatch(BoardGtrResult boardGtrResult, TestCaseConfigESlotCascadeWayGame testCaseConfigExtend){
        // 1. 取得參數
        GameResultExtendSlotJava gameResultExtend = (GameResultExtendSlotJava)boardGtrResult.getGameResultExtend();
        GameFlowHlrResult gameFlowHlrResult = gameResultExtend.getGameFlowHlrResult();

        // 2. 計算遊戲狀態符合性
        boolean gameStateFlag = super.isAnyMatchGameState(gameFlowHlrResult, testCaseConfigExtend.getGameStateType());
        if (!gameStateFlag) {
            return false;
        }

        // 3. 計算標誌連線符合性
        return this.isValidSymbolHit(gameFlowHlrResult, testCaseConfigExtend);
    }

    // 是否滿足連線
    private boolean isValidSymbolHit(GameFlowHlrResult gameFlowHlrResult, TestCaseConfigESlotCascadeWayGame testCaseConfigExtend) {
        // 1. 取得參數
        int gameStateId = super.calculateGameStateId(gameFlowHlrResult.getGameStateHlrResultList(), testCaseConfigExtend.getGameStateType());
        int payComboId = testCaseConfigExtend.getPayComboId();
        int hitNumber = testCaseConfigExtend.getHitNumber();
        List<CascadeHlrResult> cascadeHlrResultList = ((RoundHlrResultCascade)gameFlowHlrResult.getGameStateHlrResultList().get(gameStateId).getRoundHlrResultList().get(0))
                                                            .getCascadeClusterHlrResult().getCascadeHlrResultList();

        // 2. 判斷是否符合
        for (int cadcadeIndex = 0; cadcadeIndex < cascadeHlrResultList.size(); cadcadeIndex++) {
            GameCtrResultExtendWay gameCtrResultExtendWay = (GameCtrResultExtendWay) cascadeHlrResultList.get(cadcadeIndex).getGameCtrResult().getGameCtrResultExtend();
            for (int wayWinIndex = 0; wayWinIndex < gameCtrResultExtendWay.getWayCtrWinResultList().size(); wayWinIndex++) {
                WayCtrWinResult wayCtrWinResult = gameCtrResultExtendWay.getWayCtrWinResultList().get(wayWinIndex);
                if (wayCtrWinResult.getPayComboId() == payComboId && wayCtrWinResult.getHitNumber() == hitNumber) {
                    return true;
                }
            }
        }

        return false;
    }
}

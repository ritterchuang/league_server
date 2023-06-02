package org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.slot;

import org.lsj.gs.math.core.common.gameFlowHlr.enity.result.GameFlowHlrResult;
import org.lsj.gs.math.core.common.gameFlowHlr.module.gameStateHlrMgr.enity.result.roundResult.RoundHlrResultCascade;
import org.lsj.gs.math.core.slot.cascadeClusterHlrMgr.module.cascadeHlr.enity.result.CascadeHlrResult;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.line.GameCtrResultExtendLine;
import org.lsj.gs.math.core.slot.gameCtrMgr.enity.result.line.LineCtrWinResult;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.slot.GameResultExtendSlotJava;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigESlotCascadeLineGame;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;

import java.util.List;

// 測項計算器 消除虎機 得分類型測項
public class TestCaseCtrESlotCascadeLineGame extends AbstractTestCaseCtrSlot {
    private final TestCaseConfigESlotCascadeLineGame testCaseConfigExtend; // 測項計算器設定

    public TestCaseCtrESlotCascadeLineGame(TestCaseConfig testCaseConfig) {
        super(testCaseConfig);
        this.testCaseConfigExtend = (TestCaseConfigESlotCascadeLineGame) testCaseConfig.getTestCaseConfigExtend();
    }

    // 計算測項結果
    public TestCaseResult calculateTestCaseResult(BoardGtrResult boardGtrResult){
        return super.calculateTestCaseResult(boardGtrResult, this.calculateIsMatch(boardGtrResult, this.testCaseConfigExtend));
    }

    // 計算符合標籤
    private boolean calculateIsMatch(BoardGtrResult boardGtrResult, TestCaseConfigESlotCascadeLineGame testCaseConfigExtend){
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
    private boolean isValidSymbolHit(GameFlowHlrResult gameFlowHlrResult, TestCaseConfigESlotCascadeLineGame testCaseConfigExtend) {
        // 1. 取得參數
        int gameStateId = super.calculateGameStateId(gameFlowHlrResult.getGameStateHlrResultList(), testCaseConfigExtend.getGameStateType());
        int payComboId = testCaseConfigExtend.getPayComboId();
        int hitNumber = testCaseConfigExtend.getHitNumber();
        List<CascadeHlrResult> cascadeHlrResultList = ((RoundHlrResultCascade)gameFlowHlrResult.getGameStateHlrResultList().get(gameStateId).getRoundHlrResultList().get(0))
                                                            .getCascadeClusterHlrResult().getCascadeHlrResultList();

        // 2. 判斷是否符合
        for (int cadcadeIndex = 0; cadcadeIndex < cascadeHlrResultList.size(); cadcadeIndex++) {
            GameCtrResultExtendLine gameCtrResultExtendLine = (GameCtrResultExtendLine) cascadeHlrResultList.get(cadcadeIndex).getGameCtrResult().getGameCtrResultExtend();
            for (int lineWinIndex = 0; lineWinIndex < gameCtrResultExtendLine.getLineCtrWinResultList().size(); lineWinIndex++) {
                LineCtrWinResult lineCtrWinResult = gameCtrResultExtendLine.getLineCtrWinResultList().get(lineWinIndex);
                if (lineCtrWinResult.getPayComboId() == payComboId && lineCtrWinResult.getHitNumber() == hitNumber) {
                    return true;
                }
            }
        }

        return false;
    }
}

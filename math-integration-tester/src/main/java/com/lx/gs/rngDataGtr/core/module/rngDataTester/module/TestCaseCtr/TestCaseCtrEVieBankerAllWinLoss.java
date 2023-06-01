package com.lx.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr;

import com.lx.gs.math.core.card.ConstMathCard;
import com.lx.gs.math.core.common.gameAdjust.entity.UidScore;
import com.lx.gs.mathStr.core.entity.playStr.BoardGtrResult;
import com.lx.gs.mathStr.core.entity.playStr.gameResultExtend.card.GameResultExtendBanker;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigEBankerAllWinLoss;
import com.lx.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;

import java.util.Map;

// 測項計算器 搶莊全輸贏測項
public class TestCaseCtrEVieBankerAllWinLoss extends AbstractTestCaseCtr {
    private final TestCaseConfigEBankerAllWinLoss testCaseConfigExtend; // 測項計算器設定

    public TestCaseCtrEVieBankerAllWinLoss(TestCaseConfig testCaseConfig) {
        super(testCaseConfig);
        this.testCaseConfigExtend = (TestCaseConfigEBankerAllWinLoss) testCaseConfig.getTestCaseConfigExtend();
    }

    // 計算測項結果
    public TestCaseResult calculateTestCaseResult(BoardGtrResult boardGtrResult){
        return super.calculateTestCaseResult(boardGtrResult, this.calculateIsMatch(boardGtrResult, this.testCaseConfigExtend));
    }

    // 計算符合標籤
    private boolean calculateIsMatch(BoardGtrResult boardGtrResult, TestCaseConfigEBankerAllWinLoss testCaseConfigExtend){
        GameResultExtendBanker gameResultExtend = (GameResultExtendBanker)boardGtrResult.getGameResultExtend();

        // 1. 計算真人搶莊符合性
        boolean humanVieBankerRateFlag = (gameResultExtend.getHumanVieBankerRate() == testCaseConfigExtend.getHumanVieBankerRate());

        // 2. 計算真人下注符合性
        boolean humaBetRateFlag = (gameResultExtend.getHumanBetRate() == testCaseConfigExtend.getHumanBetRate());

        // 3. 計算全輸贏符合性
        boolean allWinLossFlag = this.calculateAllWinLossFlag(gameResultExtend.getHumanChairIndex(), gameResultExtend.getUidScoreMap(), testCaseConfigExtend.getWinType());

        return (humanVieBankerRateFlag && humaBetRateFlag && allWinLossFlag);
    }

    // 計算全輸贏符合性
    private boolean calculateAllWinLossFlag(int humanChairIndex, Map<Integer, UidScore> uidScoreMap, ConstMathCard.WinType winType) {
        if (winType == ConstMathCard.WinType.WIN) {
            return this.calculateAllWinFlag(humanChairIndex, uidScoreMap);
        }
        return this.calculateAllLossFlag(humanChairIndex, uidScoreMap);
    }

    // 計算全贏符合性
    private boolean calculateAllWinFlag(int humanChairIndex, Map<Integer, UidScore> uidScoreMap) {
        for (Map.Entry<Integer, UidScore> uidScoreEntry: uidScoreMap.entrySet()) {
            if(uidScoreEntry.getKey() != humanChairIndex &&
                    uidScoreEntry.getValue().getScore() > 0.0){
                return false;
            }
        }

        return true;
    }

    // 計算全輸符合性
    private boolean calculateAllLossFlag(int humanChairIndex, Map<Integer, UidScore> uidScoreMap) {
        for (Map.Entry<Integer, UidScore> uidScoreEntry: uidScoreMap.entrySet()) {
            if(uidScoreEntry.getKey() != humanChairIndex &&
                    uidScoreEntry.getValue().getScore() < 0.0){
                return false;
            }
        }

        return true;
    }
}

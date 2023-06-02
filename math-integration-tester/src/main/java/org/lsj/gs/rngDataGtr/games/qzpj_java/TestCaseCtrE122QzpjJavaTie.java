package org.lsj.gs.rngDataGtr.games.qzpj_java;

import org.lsj.gs.math.core.common.gameAdjust.entity.UidScore;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.card.GameResultExtend122QzpjJava;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigE122QzpjJavaTie;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.AbstractTestCaseCtr;

import java.util.Map;

// 測項計算器 新牌九 和局測項
public class TestCaseCtrE122QzpjJavaTie extends AbstractTestCaseCtr {
    private final TestCaseConfigE122QzpjJavaTie testCaseConfigExtend; // 測項計算器設定

    public TestCaseCtrE122QzpjJavaTie(TestCaseConfig testCaseConfig) {
        super(testCaseConfig);
        this.testCaseConfigExtend = (TestCaseConfigE122QzpjJavaTie) testCaseConfig.getTestCaseConfigExtend();
    }

    // 計算測項結果
    public TestCaseResult calculateTestCaseResult(BoardGtrResult boardGtrResult){
        return super.calculateTestCaseResult(boardGtrResult, this.calculateIsMatch(boardGtrResult, this.testCaseConfigExtend));
    }

    // 計算符合標籤
    private boolean calculateIsMatch(BoardGtrResult boardGtrResult, TestCaseConfigE122QzpjJavaTie testCaseConfigExtend){
        GameResultExtend122QzpjJava gameResultExtend = (GameResultExtend122QzpjJava)boardGtrResult.getGameResultExtend();

        // 1. 計算真人搶莊符合性
        boolean humanVieBankerRateFlag = (gameResultExtend.getHumanVieBankerRate() == testCaseConfigExtend.getHumanVieBankerRate());

        // 2. 計算真人下注符合性
        boolean humanBetRateFlag = (gameResultExtend.getHumanBetRate() == testCaseConfigExtend.getHumanBetRate());

        // 3. 計算真人牌型
        boolean tieFlag = this.isExistTie(gameResultExtend.getUidScoreMap());

        // 4. 回傳
        return (humanVieBankerRateFlag && humanBetRateFlag && tieFlag);
    }

    private boolean isExistTie(Map<Integer, UidScore> uidScoreMap) {
        return uidScoreMap.entrySet().stream().anyMatch(entry -> (entry.getValue().getScore() == 0)
                                                            && (entry.getValue().getValidBet() == 0)
                                                            && (entry.getValue().getReturnAward() == 0));
    }
}

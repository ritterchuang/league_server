package org.lsj.gs.rngDataGtr.games.qzpj_java;

import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.card.GameResultExtend122QzpjJava;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigE122QzpjJavaType;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.AbstractTestCaseCtr;

// 測項計算器 新牌九 牌型測項
public class TestCaseCtrE122QzpjJavaPjType extends AbstractTestCaseCtr {
    private final TestCaseConfigE122QzpjJavaType testCaseConfigExtend; // 測項計算器設定

    public TestCaseCtrE122QzpjJavaPjType(TestCaseConfig testCaseConfig) {
        super(testCaseConfig);
        this.testCaseConfigExtend = (TestCaseConfigE122QzpjJavaType) testCaseConfig.getTestCaseConfigExtend();
    }

    // 計算測項結果
    public TestCaseResult calculateTestCaseResult(BoardGtrResult boardGtrResult){
        return super.calculateTestCaseResult(boardGtrResult, this.calculateIsMatch(boardGtrResult, this.testCaseConfigExtend));
    }

    // 計算符合標籤
    private boolean calculateIsMatch(BoardGtrResult boardGtrResult, TestCaseConfigE122QzpjJavaType testCaseConfigExtend){
        GameResultExtend122QzpjJava gameResultExtend = (GameResultExtend122QzpjJava)boardGtrResult.getGameResultExtend();

        // 1. 計算真人搶莊符合性
        boolean humanVieBankerRateFlag = (gameResultExtend.getHumanVieBankerRate() == testCaseConfigExtend.getHumanVieBankerRate());

        // 2. 計算真人下注符合性
        boolean humanBetRateFlag = (gameResultExtend.getHumanBetRate() == testCaseConfigExtend.getHumanBetRate());

        // 3. 計算真人牌型
        boolean pjTypeFlag = (gameResultExtend.getPjStackArray()[gameResultExtend.getHumanChairIndex()] == testCaseConfigExtend.getPjTypeEnumQzpjJava().getType());

        // 4. 回傳
        return (humanVieBankerRateFlag && humanBetRateFlag && pjTypeFlag);
    }
}

package org.lsj.gs.rngDataGtr.games.ebg_java;

import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.card.GameResultExtend114EbgJava;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigE114EbgJavaType;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.AbstractTestCaseCtr;

// 測項計算器 新搶莊二八槓 牌型測項
public class TestCaseCtrE114EbgJavaEbgType extends AbstractTestCaseCtr {
    private final TestCaseConfigE114EbgJavaType testCaseConfigExtend; // 測項計算器設定

    public TestCaseCtrE114EbgJavaEbgType(TestCaseConfig testCaseConfig) {
        super(testCaseConfig);
        this.testCaseConfigExtend = (TestCaseConfigE114EbgJavaType) testCaseConfig.getTestCaseConfigExtend();
    }

    // 計算測項結果
    public TestCaseResult calculateTestCaseResult(BoardGtrResult boardGtrResult){
        return super.calculateTestCaseResult(boardGtrResult, this.calculateIsMatch(boardGtrResult, this.testCaseConfigExtend));
    }

    // 計算符合標籤
    private boolean calculateIsMatch(BoardGtrResult boardGtrResult, TestCaseConfigE114EbgJavaType testCaseConfigExtend){
        GameResultExtend114EbgJava gameResultExtend = (GameResultExtend114EbgJava)boardGtrResult.getGameResultExtend();

        // 1. 計算真人搶莊符合性
        boolean humanVieBankerRateFlag = (gameResultExtend.getHumanVieBankerRate() == testCaseConfigExtend.getHumanVieBankerRate());

        // 2. 計算真人下注符合性
        boolean humanBetRateFlag = (gameResultExtend.getHumanBetRate() == testCaseConfigExtend.getHumanBetRate());

        // 3. 計算真人牌型
        boolean ebgTypeFlag = (gameResultExtend.getEbgStackArray()[gameResultExtend.getHumanChairIndex()] == testCaseConfigExtend.getEbgTypeEnumEbgJava().getType());

        // 4. 回傳
        return (humanVieBankerRateFlag && humanBetRateFlag && ebgTypeFlag);
    }
}

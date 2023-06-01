package org.lsj.gs.rngDataGtr.games.sg_java;

import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.card.GameResultExtend117SgJava;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigE117SgJavaSgType;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.AbstractTestCaseCtr;

// 測項計算器 新三公 牌型測項
public class TestCaseCtrE117SgJavaSgType extends AbstractTestCaseCtr {
    private final TestCaseConfigE117SgJavaSgType testCaseConfigExtend; // 測項計算器設定

    public TestCaseCtrE117SgJavaSgType(TestCaseConfig testCaseConfig) {
        super(testCaseConfig);
        this.testCaseConfigExtend = (TestCaseConfigE117SgJavaSgType) testCaseConfig.getTestCaseConfigExtend();
    }

    // 計算測項結果
    public TestCaseResult calculateTestCaseResult(BoardGtrResult boardGtrResult){
        return super.calculateTestCaseResult(boardGtrResult, this.calculateIsMatch(boardGtrResult, this.testCaseConfigExtend));
    }

    // 計算符合標籤
    private boolean calculateIsMatch(BoardGtrResult boardGtrResult, TestCaseConfigE117SgJavaSgType testCaseConfigExtend){
        GameResultExtend117SgJava gameResultExtend = (GameResultExtend117SgJava)boardGtrResult.getGameResultExtend();

        // 1. 計算真人搶莊符合性
        boolean humanVieBankerRateFlag = (gameResultExtend.getHumanVieBankerRate() == testCaseConfigExtend.getHumanVieBankerRate());

        // 2. 計算真人下注符合性
        boolean humanBetRateFlag = (gameResultExtend.getHumanBetRate() == testCaseConfigExtend.getHumanBetRate());

        // 3. 計算真人牌型
        boolean sgTypeFlag = (gameResultExtend.getSgStackArray()[gameResultExtend.getHumanChairIndex()] == testCaseConfigExtend.getSgTypeEnumSgJava().getType());

        // 4. 回傳
        return (humanVieBankerRateFlag && humanBetRateFlag && sgTypeFlag);
    }
}

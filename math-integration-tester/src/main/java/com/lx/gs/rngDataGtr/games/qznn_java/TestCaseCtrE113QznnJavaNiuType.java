package com.lx.gs.rngDataGtr.games.qznn_java;

import com.lx.gs.mathStr.core.entity.playStr.BoardGtrResult;
import com.lx.gs.mathStr.core.entity.playStr.gameResultExtend.card.GameResultExtend113QznnJava;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigE113QznnJavaNiuType;
import com.lx.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;
import com.lx.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.AbstractTestCaseCtr;

// 測項計算器 新搶莊牛牛 牌型測項
public class TestCaseCtrE113QznnJavaNiuType extends AbstractTestCaseCtr {
    private final TestCaseConfigE113QznnJavaNiuType testCaseConfigExtend; // 測項計算器設定

    public TestCaseCtrE113QznnJavaNiuType(TestCaseConfig testCaseConfig) {
        super(testCaseConfig);
        this.testCaseConfigExtend = (TestCaseConfigE113QznnJavaNiuType) testCaseConfig.getTestCaseConfigExtend();
    }

    // 計算測項結果
    public TestCaseResult calculateTestCaseResult(BoardGtrResult boardGtrResult){
        return super.calculateTestCaseResult(boardGtrResult, this.calculateIsMatch(boardGtrResult, this.testCaseConfigExtend));
    }

    // 計算符合標籤
    private boolean calculateIsMatch(BoardGtrResult boardGtrResult, TestCaseConfigE113QznnJavaNiuType testCaseConfigExtend){
        GameResultExtend113QznnJava gameResultExtend = (GameResultExtend113QznnJava)boardGtrResult.getGameResultExtend();

        // 1. 計算真人搶莊符合性
        boolean humanVieBankerRateFlag = (gameResultExtend.getHumanVieBankerRate() == testCaseConfigExtend.getHumanVieBankerRate());

        // 2. 計算真人下注符合性
        boolean humaBetRateFlag = (gameResultExtend.getHumanBetRate() == testCaseConfigExtend.getHumanBetRate());

        // 3. 計算真人牌型
        boolean nieTypeFlag = gameResultExtend.getNiuStack().getNiuTypeEnumCustom().equals(testCaseConfigExtend.getNiuTypeEnum());

        return (humanVieBankerRateFlag && humaBetRateFlag && nieTypeFlag);
    }
}

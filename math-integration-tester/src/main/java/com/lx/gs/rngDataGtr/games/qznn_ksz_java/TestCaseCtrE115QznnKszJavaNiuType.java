package com.lx.gs.rngDataGtr.games.qznn_ksz_java;

import com.lx.gs.mathStr.core.entity.playStr.BoardGtrResult;
import com.lx.gs.mathStr.core.entity.playStr.gameResultExtend.card.GameResultExtend115QznnKszJava;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigE115QznnKszJavaNiuType;
import com.lx.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;
import com.lx.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.AbstractTestCaseCtr;

// 測項計算器 新看三張搶莊牛牛 牌型測項
public class TestCaseCtrE115QznnKszJavaNiuType extends AbstractTestCaseCtr {
    private final TestCaseConfigE115QznnKszJavaNiuType testCaseConfigExtend; // 測項計算器設定 新看三張搶莊牛牛 牌型測項

    public TestCaseCtrE115QznnKszJavaNiuType(TestCaseConfig testCaseConfig) {
        super(testCaseConfig);
        this.testCaseConfigExtend = (TestCaseConfigE115QznnKszJavaNiuType) testCaseConfig.getTestCaseConfigExtend();
    }

    // 計算測項結果
    public TestCaseResult calculateTestCaseResult(BoardGtrResult boardGtrResult){
        return super.calculateTestCaseResult(boardGtrResult, this.calculateIsMatch(boardGtrResult, this.testCaseConfigExtend));
    }

    // 計算符合標籤
    private boolean calculateIsMatch(BoardGtrResult boardGtrResult, TestCaseConfigE115QznnKszJavaNiuType testCaseConfigExtend){
        GameResultExtend115QznnKszJava gameResultExtend = (GameResultExtend115QznnKszJava)boardGtrResult.getGameResultExtend();

        // 1. 計算真人搶莊符合性
        boolean humanVieBankerRateFlag = (gameResultExtend.getHumanVieBankerRate() == testCaseConfigExtend.getHumanVieBankerRate());

        // 2. 計算真人下注符合性
        boolean humaBetRateFlag = (gameResultExtend.getHumanBetRate() == testCaseConfigExtend.getHumanBetRate());

        // 3. 計算真人牌型
        boolean nieTypeFlag = gameResultExtend.getNiuStack().getNiuTypeEnumCustom().equals(testCaseConfigExtend.getNiuTypeEnum());

        return (humanVieBankerRateFlag && humaBetRateFlag && nieTypeFlag);
    }
}

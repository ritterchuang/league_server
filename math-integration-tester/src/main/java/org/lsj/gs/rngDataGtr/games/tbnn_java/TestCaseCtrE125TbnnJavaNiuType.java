package org.lsj.gs.rngDataGtr.games.tbnn_java;

import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.card.GameResultExtend125TbnnJava;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigE125TbnnJavaNiuType;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.AbstractTestCaseCtr;

// 測項計算器 新通比牛牛 牌型測項
public class TestCaseCtrE125TbnnJavaNiuType extends AbstractTestCaseCtr {
    private final TestCaseConfigE125TbnnJavaNiuType testCaseConfigExtend; // 測項計算器設定

    public TestCaseCtrE125TbnnJavaNiuType(TestCaseConfig testCaseConfig) {
        super(testCaseConfig);
        this.testCaseConfigExtend = (TestCaseConfigE125TbnnJavaNiuType) testCaseConfig.getTestCaseConfigExtend();
    }

    // 計算測項結果
    public TestCaseResult calculateTestCaseResult(BoardGtrResult boardGtrResult){
        return super.calculateTestCaseResult(boardGtrResult, this.calculateIsMatch(boardGtrResult, this.testCaseConfigExtend));
    }

    // 計算符合標籤
    private boolean calculateIsMatch(BoardGtrResult boardGtrResult, TestCaseConfigE125TbnnJavaNiuType testCaseConfigExtend){
        GameResultExtend125TbnnJava gameResultExtend = (GameResultExtend125TbnnJava)boardGtrResult.getGameResultExtend();


        // 1. 計算真人下注符合性
        boolean humaBetRateFlag = (gameResultExtend.getHumanBetRate() == testCaseConfigExtend.getHumanBetRate());

        // 2. 計算真人牌型
        boolean nieTypeFlag = gameResultExtend.getNiuStack().getNiuTypeEnumCustom().equals(testCaseConfigExtend.getNiuTypeEnum());

        return (humaBetRateFlag && nieTypeFlag);
    }
}

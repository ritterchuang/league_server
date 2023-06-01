package com.lx.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr;

import com.lx.gs.mathStr.core.entity.playStr.BoardGtrResult;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import com.lx.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;
import com.lx.gs.rngDataGtr.core.module.rngDataTester.entity.testCaseResultExtend.TestCaseResultExtend;

import java.util.List;

// 抽象測項計算器
public abstract class AbstractTestCaseCtr implements ITestCaseCtr{
    protected final TestCaseConfig testCaseConfig; // 測項設定

    public AbstractTestCaseCtr(TestCaseConfig testCaseConfig) {
        this.testCaseConfig = testCaseConfig;
    }


    // 計算測項結果
    protected TestCaseResult calculateTestCaseResult(BoardGtrResult boardGtrResult, boolean checkRuleMatchFlag){
        // 1. 計算亂數長度符合標籤
        boolean checkRndDataCountFlag = this.checkRndDataCountFlag(boardGtrResult.getGameBetLogResult().getRandomNumberUsedList());

        // 2. 計算亂數列表
        List<Integer> randomNumberUsedList = List.copyOf(boardGtrResult.getGameBetLogResult().getRandomNumberUsedList());

        return new TestCaseResult(testCaseConfig, (checkRuleMatchFlag && checkRndDataCountFlag), randomNumberUsedList);
    }

    // 計算測項結果
    protected TestCaseResult calculateTestCaseResult(BoardGtrResult boardGtrResult, boolean checkRuleMatchFlag, TestCaseResultExtend testCaseResultExtend){
        // 1. 計算亂數長度符合標籤
        boolean checkRndDataCountFlag = this.checkRndDataCountFlag(boardGtrResult.getGameBetLogResult().getRandomNumberUsedList());

        // 2. 計算亂數列表
        List<Integer> randomNumberUsedList = List.copyOf(boardGtrResult.getGameBetLogResult().getRandomNumberUsedList());

        return new TestCaseResult(testCaseConfig, (checkRuleMatchFlag && checkRndDataCountFlag), randomNumberUsedList, testCaseResultExtend);
    }

    // 計算亂數長度符合標籤
    protected boolean checkRndDataCountFlag(List<Integer> randomNumberUsedList) {
        return (randomNumberUsedList.size() <= this.testCaseConfig.getTestCaseConfigExtend().getRndCountTopLimit());
    }
}

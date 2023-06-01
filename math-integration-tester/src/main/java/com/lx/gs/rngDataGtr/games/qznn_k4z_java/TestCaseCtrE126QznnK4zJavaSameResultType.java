package com.lx.gs.rngDataGtr.games.qznn_k4z_java;

import com.lx.gs.mathStr.core.entity.playStr.BoardGtrResult;
import com.lx.gs.mathStr.core.entity.playStr.gameResultExtend.card.GameResultExtend126QznnK4zJava;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigE126QznnK4ZJavaSameResultType;
import com.lx.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;
import com.lx.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.AbstractTestCaseCtr;

// 測項計算器 新看四張搶莊牛牛 相同牌型測項
public class TestCaseCtrE126QznnK4zJavaSameResultType extends AbstractTestCaseCtr {
    private final TestCaseConfigE126QznnK4ZJavaSameResultType testCaseConfigExtend; // 測項計算器設定

    public TestCaseCtrE126QznnK4zJavaSameResultType(TestCaseConfig testCaseConfig) {
        super(testCaseConfig);
        this.testCaseConfigExtend = (TestCaseConfigE126QznnK4ZJavaSameResultType) testCaseConfig.getTestCaseConfigExtend();
    }

    // 計算測項結果
    public TestCaseResult calculateTestCaseResult(BoardGtrResult boardGtrResult){
        return super.calculateTestCaseResult(boardGtrResult, this.calculateIsMatch(boardGtrResult, this.testCaseConfigExtend));
    }

    // 計算符合標籤
    private boolean calculateIsMatch(BoardGtrResult boardGtrResult, TestCaseConfigE126QznnK4ZJavaSameResultType testCaseConfigExtend){
        GameResultExtend126QznnK4zJava gameResultExtend = (GameResultExtend126QznnK4zJava)boardGtrResult.getGameResultExtend();

        // 1. 真人符合指定牌型標誌
        boolean isHumanMatch = (gameResultExtend.getNiuStackArray()[gameResultExtend.getHumanChairIndex()] == testCaseConfigExtend.getNiuTypeEnum().getType());

        // 2. 機器人莊家符合指定牌型標誌
        boolean isRobotBankerMatch = (gameResultExtend.getNiuStackArray()[gameResultExtend.getBankerChairIndex()] == testCaseConfigExtend.getNiuTypeEnum().getType());

        // 3. 回傳
        return (isHumanMatch && isRobotBankerMatch);
    }
}

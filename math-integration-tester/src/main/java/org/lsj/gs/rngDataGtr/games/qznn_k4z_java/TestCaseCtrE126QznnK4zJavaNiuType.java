package org.lsj.gs.rngDataGtr.games.qznn_k4z_java;

import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.card.GameResultExtend126QznnK4zJava;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigE126QznnK4ZJavaNiuType;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.AbstractTestCaseCtr;

// 測項計算器 新看四張搶莊牛牛 牌型測項
public class TestCaseCtrE126QznnK4zJavaNiuType extends AbstractTestCaseCtr {
    private final TestCaseConfigE126QznnK4ZJavaNiuType testCaseConfigExtend; // 測項計算器設定

    public TestCaseCtrE126QznnK4zJavaNiuType(TestCaseConfig testCaseConfig) {
        super(testCaseConfig);
        this.testCaseConfigExtend = (TestCaseConfigE126QznnK4ZJavaNiuType) testCaseConfig.getTestCaseConfigExtend();
    }

    // 計算測項結果
    public TestCaseResult calculateTestCaseResult(BoardGtrResult boardGtrResult){
        return super.calculateTestCaseResult(boardGtrResult, this.calculateIsMatch(boardGtrResult, this.testCaseConfigExtend));
    }

    // 計算符合標籤
    private boolean calculateIsMatch(BoardGtrResult boardGtrResult, TestCaseConfigE126QznnK4ZJavaNiuType testCaseConfigExtend){
        GameResultExtend126QznnK4zJava gameResultExtend = (GameResultExtend126QznnK4zJava)boardGtrResult.getGameResultExtend();

        // 1. 計算真人搶莊符合性
        boolean humanVieBankerRateFlag = (gameResultExtend.getHumanVieBankerRate() == testCaseConfigExtend.getHumanVieBankerRate());

        // 2. 計算真人下注符合性
        boolean humaBetRateFlag = (gameResultExtend.getHumanBetRate() == testCaseConfigExtend.getHumanBetRate());

        // 3. 計算真人牌型
        boolean nieTypeFlag = (gameResultExtend.getNiuStackArray()[gameResultExtend.getHumanChairIndex()] == testCaseConfigExtend.getNiuTypeEnum().getType());

        return (humanVieBankerRateFlag && humaBetRateFlag && nieTypeFlag);
    }
}

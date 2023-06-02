package org.lsj.gs.rngDataGtr.games.lhd_java;

import org.lsj.gs.math.games.lhd_java.entity.ConstLhdJava;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.baiRen.GameResultExtend111LhdJava;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigE111LhdJavaBetAndCardType;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.AbstractTestCaseCtr;

import java.util.Arrays;

// 測項計算器 新龍虎鬥
public class TestCaseCtrE111LhdJavaSpecifyBetAndCardType extends AbstractTestCaseCtr {
    private final TestCaseConfigE111LhdJavaBetAndCardType testCaseConfigExtend; // 測項計算器設定

    public TestCaseCtrE111LhdJavaSpecifyBetAndCardType(TestCaseConfig testCaseConfig) {
        super(testCaseConfig);
        this.testCaseConfigExtend = (TestCaseConfigE111LhdJavaBetAndCardType) testCaseConfig.getTestCaseConfigExtend();
    }

    // 計算測項結果
    public TestCaseResult calculateTestCaseResult(BoardGtrResult boardGtrResult){
        return super.calculateTestCaseResult(boardGtrResult, this.calculateIsMatch(boardGtrResult, this.testCaseConfigExtend));
    }

    // 計算符合標籤
    private boolean calculateIsMatch(BoardGtrResult boardGtrResult, TestCaseConfigE111LhdJavaBetAndCardType testCaseConfigExtend){
        GameResultExtend111LhdJava gameResultExtend = (GameResultExtend111LhdJava)boardGtrResult.getGameResultExtend();

        // 1. 玩家只押注指定區標誌
        boolean onlyBetSpecifyArea =
                (Arrays.stream(gameResultExtend.getPlayerAreaBetArray()).filter(betAmount -> betAmount > 0).count() == 1)
                && (gameResultExtend.getPlayerAreaBetArray()[testCaseConfigExtend.getBetAreaId().getCode()] > 0);

        // 2. 開獎為指定區標誌
        boolean awardSpecifyArea = ConstLhdJava.BetAreaIdLhdJava.calculateWinBetAreaIdLhdJava(gameResultExtend.getStackMap()).equals(testCaseConfigExtend.getResultBetAreaId());

        // 3. 判斷是否滿足押注區與牌型
        return (onlyBetSpecifyArea && awardSpecifyArea);
    }
}

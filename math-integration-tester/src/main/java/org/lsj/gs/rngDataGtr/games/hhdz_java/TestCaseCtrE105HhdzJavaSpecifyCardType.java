package org.lsj.gs.rngDataGtr.games.hhdz_java;

import org.lsj.gs.math.core.card.stackCtr.jhStackCtr.JhStack;
import org.lsj.gs.math.games.hhdz_java.entity.ConstHhdzJava;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.baiRen.GameResultExtend105HhdzJava;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigE105HhdzJavaCardType;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.AbstractTestCaseCtr;

import java.util.Arrays;

// 測項計算器 新紅黑大戰 指定押注區域與牌型
public class TestCaseCtrE105HhdzJavaSpecifyCardType extends AbstractTestCaseCtr {
    private final TestCaseConfigE105HhdzJavaCardType testCaseConfigExtend; // 測項計算器設定

    public TestCaseCtrE105HhdzJavaSpecifyCardType(TestCaseConfig testCaseConfig) {
        super(testCaseConfig);
        this.testCaseConfigExtend = (TestCaseConfigE105HhdzJavaCardType) testCaseConfig.getTestCaseConfigExtend();
    }

    // 計算測項結果
    public TestCaseResult calculateTestCaseResult(BoardGtrResult boardGtrResult){
        return super.calculateTestCaseResult(boardGtrResult, this.calculateIsMatch(boardGtrResult, this.testCaseConfigExtend));
    }

    // 計算符合標籤
    private boolean calculateIsMatch(BoardGtrResult boardGtrResult, TestCaseConfigE105HhdzJavaCardType testCaseConfigExtend){
        GameResultExtend105HhdzJava gameResultExtend = (GameResultExtend105HhdzJava)boardGtrResult.getGameResultExtend();

        // 1. 玩家只押注指定區標誌
        boolean onlyBetSpecifyArea =
                (Arrays.stream(gameResultExtend.getPlayerAreaBetArray()).filter(betAmount -> betAmount > 0).count() == 1)
                && (gameResultExtend.getPlayerAreaBetArray()[testCaseConfigExtend.getBetAreaId().getCode()] > 0);

        // 2. 開獎為指定牌型
        JhStack redStack = gameResultExtend.getStackMap().get(ConstHhdzJava.BetAreaIdHhdzJava.RED.getCode());
        JhStack blackStack = gameResultExtend.getStackMap().get(ConstHhdzJava.BetAreaIdHhdzJava.BLACK.getCode());
        JhStack cardTypeStack = (redStack.compareTo(blackStack) > 0) ? redStack: blackStack;

        boolean awardSpecifyCardType = cardTypeStack.getTypeEnumCommon().equals(testCaseConfigExtend.getStack());

        // 3. 判斷是否滿足押注區與牌型
        return (onlyBetSpecifyArea && awardSpecifyCardType);
    }
}

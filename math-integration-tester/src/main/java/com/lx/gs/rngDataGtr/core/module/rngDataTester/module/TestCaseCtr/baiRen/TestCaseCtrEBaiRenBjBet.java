package com.lx.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.baiRen;

import com.lx.gs.math.core.baiRen.ConstMathBjl;
import com.lx.gs.mathStr.core.entity.playStr.BoardGtrResult;
import com.lx.gs.mathStr.core.entity.playStr.gameResultExtend.baiRen.GameResultExtendBaiRenBj;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigEBaiRenBjBet;
import com.lx.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;
import com.lx.gs.rngDataGtr.core.module.rngDataTester.entity.testCaseResultExtend.TestCaseResultExtendBaiRen;
import com.lx.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.AbstractTestCaseCtr;

import java.util.Arrays;
import java.util.stream.IntStream;

// 測項計算器 百人牛類 指定押注區域與贏分區域
public class TestCaseCtrEBaiRenBjBet extends AbstractTestCaseCtr {
    private final TestCaseConfigEBaiRenBjBet testCaseConfigExtend; // 測項計算器設定

    public TestCaseCtrEBaiRenBjBet(TestCaseConfig testCaseConfig) {
        super(testCaseConfig);
        this.testCaseConfigExtend = (TestCaseConfigEBaiRenBjBet) testCaseConfig.getTestCaseConfigExtend();
    }

    // 計算測項結果
    public TestCaseResult calculateTestCaseResult(BoardGtrResult boardGtrResult){
        return super.calculateTestCaseResult(
                boardGtrResult,
                this.calculateIsMatch(boardGtrResult, this.testCaseConfigExtend),
                new TestCaseResultExtendBaiRen(((GameResultExtendBaiRenBj)boardGtrResult.getGameResultExtend()).getPlayerAreaBetArray()));
    }

    // 計算符合設定標籤
    private boolean calculateIsMatch(BoardGtrResult boardGtrResult, TestCaseConfigEBaiRenBjBet testCaseConfigExtend){
        GameResultExtendBaiRenBj gameResultExtend = (GameResultExtendBaiRenBj)boardGtrResult.getGameResultExtend();

        // 1. 玩家只押注指定區標誌
        boolean betSpecifyArea = this.checkBetSpecifyArea(gameResultExtend.getPlayerAreaBetArray());

        // 2. 開獎為指定區標誌
        boolean targetSpecifyArea = this.checkTargetSpecifyArea(gameResultExtend);

        // 3. 檢查是否有押注
        boolean betFlag = Arrays.stream(gameResultExtend.getPlayerAreaBetArray())
                .anyMatch(betAmount -> betAmount > 0);

        // 4. 判斷是否滿足押注區與牌型
        return (betSpecifyArea && targetSpecifyArea && betFlag);
    }

    // 檢查是否符合設定押注區域
    private boolean checkBetSpecifyArea(int[] playerBetArray) {
        if (Arrays.stream(playerBetArray).filter(betAmount -> betAmount > 0).count() != this.testCaseConfigExtend.getBetAreaList().size()) {
            return false;
        }

        return IntStream.range(0, playerBetArray.length)
                .filter(betAreaId -> playerBetArray[betAreaId] > 0)
                .allMatch(betAreaId -> this.testCaseConfigExtend.getBetAreaList().contains(ConstMathBjl.BetAreaEnum.fromCode(betAreaId)));
    }

    // 檢查是否符合設定返獎區域
    private boolean checkTargetSpecifyArea(GameResultExtendBaiRenBj gameResultExtend) {
        int[] targetArray = (this.testCaseConfigExtend.getWinReturnType() == 0) ? gameResultExtend.getWinAreaArray() : gameResultExtend.getReturnAreaArray();

        if (targetArray.length == 0) {
            return false;
        }

        return this.testCaseConfigExtend.getTargetBetAreaList().stream()
                .mapToInt(ConstMathBjl.BetAreaEnum::getCode)
                .allMatch(betAreaId -> Arrays.stream(targetArray).anyMatch(targetId -> targetId == betAreaId));
    }
}

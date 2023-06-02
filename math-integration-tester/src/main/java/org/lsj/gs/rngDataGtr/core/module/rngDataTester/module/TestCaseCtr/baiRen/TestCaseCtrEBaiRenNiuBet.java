package org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.baiRen;

import org.lsj.gs.math.core.baiRen.ConstMathNiu;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.baiRen.GameResultExtendBaiRenNiu;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigEBaiRenNiuBet;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.entity.testCaseResultExtend.TestCaseResultExtendBaiRen;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.AbstractTestCaseCtr;

import java.util.Arrays;
import java.util.stream.IntStream;

// 測項計算器 百人牛類 指定押注區域與贏分區域
public class TestCaseCtrEBaiRenNiuBet extends AbstractTestCaseCtr {
    private final TestCaseConfigEBaiRenNiuBet testCaseConfigExtend; // 測項計算器設定

    public TestCaseCtrEBaiRenNiuBet(TestCaseConfig testCaseConfig) {
        super(testCaseConfig);
        this.testCaseConfigExtend = (TestCaseConfigEBaiRenNiuBet) testCaseConfig.getTestCaseConfigExtend();
    }

    // 計算測項結果
    public TestCaseResult calculateTestCaseResult(BoardGtrResult boardGtrResult){
        return super.calculateTestCaseResult(
                boardGtrResult,
                this.calculateIsMatch(boardGtrResult, this.testCaseConfigExtend),
                new TestCaseResultExtendBaiRen(((GameResultExtendBaiRenNiu)boardGtrResult.getGameResultExtend()).getPlayerAreaBetArray()));
    }

    // 計算符合設定標籤
    private boolean calculateIsMatch(BoardGtrResult boardGtrResult, TestCaseConfigEBaiRenNiuBet testCaseConfigExtend){
        GameResultExtendBaiRenNiu gameResultExtend = (GameResultExtendBaiRenNiu)boardGtrResult.getGameResultExtend();

        // 1. 玩家只押注指定區標誌
        boolean betSpecifyArea = this.checkBetSpecifyArea(gameResultExtend.getPlayerAreaBetArray());

        // 2. 開獎為指定區標誌
        boolean awardSpecifyArea = this.checkAwardSpecifyArea(gameResultExtend.getWinLossArray());

        // 3. 檢查是否有押注
        boolean betFlag = Arrays.stream(gameResultExtend.getPlayerAreaBetArray())
                .anyMatch(betAmount -> betAmount > 0);

        // 4. 判斷是否滿足押注區與牌型
        return (betSpecifyArea && awardSpecifyArea && betFlag);
    }

    // 檢查是否符合設定押注區域
    private boolean checkBetSpecifyArea(int[] playerBetArray) {
        if (Arrays.stream(playerBetArray).filter(betAmount -> betAmount > 0).count() != this.testCaseConfigExtend.getBetAreaList().size()) {
            return false;
        }

        return IntStream.range(0, playerBetArray.length)
                .filter(betAreaId -> playerBetArray[betAreaId] > 0)
                .allMatch(betAreaId -> this.testCaseConfigExtend.getBetAreaList().contains(ConstMathNiu.BetAreaEnum.fromCode(betAreaId)));
    }

    // 檢查是否符合設定返獎區域
    private boolean checkAwardSpecifyArea(int[] winLossArray) {
        if (Arrays.stream(winLossArray).filter(winLoss -> ConstMathNiu.ChartEnum.WIN.getCode() == winLoss).count() != this.testCaseConfigExtend.getWinBetAreaList().size()) {
            return false;
        }

        return IntStream.range(0, winLossArray.length)
                .filter(betAreaId -> winLossArray[betAreaId] > 0)
                .allMatch(betAreaId -> this.testCaseConfigExtend.getWinBetAreaList().contains(ConstMathNiu.BetAreaEnum.fromCode(betAreaId)));
    }
}

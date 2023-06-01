package org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.baiRen;

import org.lsj.gs.math.core.card.cardWallCtr.ICard;
import org.lsj.gs.math.core.card.stackCtr.bjlStackCtr.BjlStack;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.baiRen.GameResultExtendBaiRenBj;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigEBaiRenBjDeal;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.entity.testCaseResultExtend.TestCaseResultExtendBaiRen;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.AbstractTestCaseCtr;

import java.util.Arrays;

// 測項計算器 百人牛類 指定發牌
public class TestCaseCtrEBaiRenBjDeal extends AbstractTestCaseCtr {
    private final TestCaseConfigEBaiRenBjDeal testCaseConfigExtend; // 測項計算器設定

    public TestCaseCtrEBaiRenBjDeal(TestCaseConfig testCaseConfig) {
        super(testCaseConfig);
        this.testCaseConfigExtend = (TestCaseConfigEBaiRenBjDeal) testCaseConfig.getTestCaseConfigExtend();
    }

    // 計算測項結果
    public TestCaseResult calculateTestCaseResult(BoardGtrResult boardGtrResult){
        return super.calculateTestCaseResult(
                boardGtrResult,
                this.calculateIsMatch(boardGtrResult, this.testCaseConfigExtend),
                new TestCaseResultExtendBaiRen(((GameResultExtendBaiRenBj)boardGtrResult.getGameResultExtend()).getPlayerAreaBetArray()));
    }

    // 計算符合設定標籤
    private boolean calculateIsMatch(BoardGtrResult boardGtrResult, TestCaseConfigEBaiRenBjDeal testCaseConfigExtend){
        GameResultExtendBaiRenBj gameResultExtend = (GameResultExtendBaiRenBj)boardGtrResult.getGameResultExtend();

        // 1. 開獎為指定區標誌
        boolean dealResult = this.checkDealResult(gameResultExtend);

        // 2. 檢查是否有押注
        boolean betFlag = Arrays.stream(gameResultExtend.getPlayerAreaBetArray())
                .anyMatch(betAmount -> betAmount > 0);

        // 3. 判斷是否滿足押注區與牌型
        return (dealResult && betFlag);
    }

    // 檢查是否符合設定押注區域
    private boolean checkDealResult(GameResultExtendBaiRenBj gameResultExtend) {
        BjlStack targetStack = gameResultExtend.getStackMap().get(this.testCaseConfigExtend.getDealAreaEnum().getCode());

        // 1. 檢查張數
        if (targetStack.getHandCardList().size() != this.testCaseConfigExtend.getCardCount()) {
            return false;
        }

        // 2. 檢查點數
        int pointSum = targetStack.getHandCardList().stream().mapToInt(ICard::getPoint).sum();
        return (pointSum % 10) == this.testCaseConfigExtend.getPointSum();
    }

}

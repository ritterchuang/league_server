package org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.baiRen;

import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.NiuStackCommon;
import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.baiRen.GameResultExtendBaiRenNiu;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigEBaiRenNiuType;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.entity.testCaseResultExtend.TestCaseResultExtendBaiRen;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.AbstractTestCaseCtr;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.IntStream;

// 測項計算器 百人牛類 指定押注區域與贏分區域
public class TestCaseCtrEBaiRenNiuType extends AbstractTestCaseCtr {
    private final TestCaseConfigEBaiRenNiuType testCaseConfigExtend; // 測項計算器設定

    public TestCaseCtrEBaiRenNiuType(TestCaseConfig testCaseConfig) {
        super(testCaseConfig);
        this.testCaseConfigExtend = (TestCaseConfigEBaiRenNiuType) testCaseConfig.getTestCaseConfigExtend();
    }

    // 計算測項結果
    public TestCaseResult calculateTestCaseResult(BoardGtrResult boardGtrResult){
        return super.calculateTestCaseResult(
                boardGtrResult,
                this.calculateIsMatch(boardGtrResult, this.testCaseConfigExtend),
                new TestCaseResultExtendBaiRen(((GameResultExtendBaiRenNiu)boardGtrResult.getGameResultExtend()).getPlayerAreaBetArray()));
    }

    // 計算符合設定標籤
    private boolean calculateIsMatch(BoardGtrResult boardGtrResult, TestCaseConfigEBaiRenNiuType testCaseConfigExtend){
        GameResultExtendBaiRenNiu gameResultExtend = (GameResultExtendBaiRenNiu)boardGtrResult.getGameResultExtend();

        // 1. 玩家只押注指定區標誌
        boolean betSpecifyArea = this.checkBetSpecifyArea(gameResultExtend.getPlayerAreaBetArray());

        // 2. 開獎為指定區標誌
        boolean areaSpecifyNiuType = this.checkAreaNiuType(gameResultExtend.getStackMap());

        // 3. 檢查是否有押注
        boolean betFlag = Arrays.stream(gameResultExtend.getPlayerAreaBetArray())
                .anyMatch(betAmount -> betAmount > 0);

        // 4. 判斷是否滿足押注區與牌型
        return (betSpecifyArea && areaSpecifyNiuType && betFlag);
    }

    // 檢查是否符合設定押注區域
    private boolean checkBetSpecifyArea(int[] playerBetArray) {
        return IntStream.range(0, playerBetArray.length)
                .anyMatch(betAreaId -> betAreaId == this.testCaseConfigExtend.getBetAreaEnum().getCode());
    }

    // 檢查是否符合設定牛型
    private boolean checkAreaNiuType(Map<Integer, NiuStackCommon> niuStackCommonMap) {
        return niuStackCommonMap.get(this.testCaseConfigExtend.getBetAreaEnum().getCode())
                .getNiuTypeEnumCommon()
                .equals(this.testCaseConfigExtend.getNiuTypeEnumCommon());
    }
}

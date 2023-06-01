package com.lx.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.slot;

import com.lx.gs.math.core.common.logic.gameBetLogResultCtr.enity.GameBetLogResultSlot;
import com.lx.gs.math.core.slot.animationCtr.enity.result.AnimationResultExtendOddsAnimation;
import com.lx.gs.mathStr.core.entity.playStr.BoardGtrResult;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigESlotOddsWinType;
import com.lx.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;
import com.lx.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.AbstractTestCaseCtr;

import java.util.Objects;

// 測項計算器 虎機倍數表演類型測項
public class TestCaseCtrESlotOddsWinType extends AbstractTestCaseCtr {
    private final TestCaseConfigESlotOddsWinType testCaseConfigExtend; // 測項計算器設定

    public TestCaseCtrESlotOddsWinType(TestCaseConfig testCaseConfig) {
        super(testCaseConfig);
        this.testCaseConfigExtend = (TestCaseConfigESlotOddsWinType) testCaseConfig.getTestCaseConfigExtend();
    }

    // 計算測項結果
    public TestCaseResult calculateTestCaseResult(BoardGtrResult boardGtrResult){
        return super.calculateTestCaseResult(boardGtrResult, this.calculateIsMatch(boardGtrResult, this.testCaseConfigExtend));
    }

    // 計算符合標籤
    private boolean calculateIsMatch(BoardGtrResult boardGtrResult, TestCaseConfigESlotOddsWinType testCaseConfigExtend){
        return Objects.equals(((AnimationResultExtendOddsAnimation)
                ((GameBetLogResultSlot)(boardGtrResult.getGameBetLogResult())).getGameFlowHlrResult()
                        .getAnimationResult().getAnimationResultExtend()).getOddsWinType(), testCaseConfigExtend.getOddsWinType());
    }
}

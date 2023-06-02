package org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr;

import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.mathStr.core.entity.playStr.gameResultExtend.fish.GameResultExtendFishJava;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfigEFishSpecifyHitKillFlag;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;

// 測項計算器 魚機指定打擊方式的擊殺判斷 測項
public class TestCaseCtrEFishSpecifyHitKillFlag extends AbstractTestCaseCtr {
    private final TestCaseConfigEFishSpecifyHitKillFlag testCaseConfigExtend; // 測項計算器設定

    public TestCaseCtrEFishSpecifyHitKillFlag(TestCaseConfig testCaseConfig) {
        super(testCaseConfig);
        this.testCaseConfigExtend = (TestCaseConfigEFishSpecifyHitKillFlag) testCaseConfig.getTestCaseConfigExtend();
    }

    // 計算測項結果
    public TestCaseResult calculateTestCaseResult(BoardGtrResult boardGtrResult){
        return super.calculateTestCaseResult(boardGtrResult, this.calculateIsMatch(boardGtrResult, this.testCaseConfigExtend));
    }

    // 計算符合標籤
    private boolean calculateIsMatch(BoardGtrResult boardGtrResult, TestCaseConfigEFishSpecifyHitKillFlag testCaseConfigExtend){
        return (((GameResultExtendFishJava)boardGtrResult.getGameResultExtend()).getBulletIndex() == testCaseConfigExtend.getBulletId()
        && ((GameResultExtendFishJava)boardGtrResult.getGameResultExtend()).getTargetIndex() == testCaseConfigExtend.getTargetId()
        && ((GameResultExtendFishJava)boardGtrResult.getGameResultExtend()).getHitResult().isKillFlag() == testCaseConfigExtend.isKillFlag()
        );
    }
}

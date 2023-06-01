package com.lx.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr;

import com.lx.gs.mathStr.core.entity.playStr.BoardGtrResult;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import com.lx.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;

// 測項計算器
public class TestCaseCtrDefault extends AbstractTestCaseCtr{
    public TestCaseCtrDefault(TestCaseConfig testCaseConfig) {
        super(testCaseConfig);
    }

    public TestCaseResult calculateTestCaseResult(BoardGtrResult boardGtrResult){
        return null;
    }
}

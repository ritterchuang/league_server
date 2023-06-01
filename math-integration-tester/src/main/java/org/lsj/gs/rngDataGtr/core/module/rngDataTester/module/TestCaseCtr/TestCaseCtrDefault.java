package org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr;

import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;

// 測項計算器
public class TestCaseCtrDefault extends AbstractTestCaseCtr{
    public TestCaseCtrDefault(TestCaseConfig testCaseConfig) {
        super(testCaseConfig);
    }

    public TestCaseResult calculateTestCaseResult(BoardGtrResult boardGtrResult){
        return null;
    }
}

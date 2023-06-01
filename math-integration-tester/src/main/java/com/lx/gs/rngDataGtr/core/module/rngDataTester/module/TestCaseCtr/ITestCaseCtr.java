package com.lx.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr;

import com.lx.gs.mathStr.core.entity.playStr.BoardGtrResult;
import com.lx.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;

public interface ITestCaseCtr {
    TestCaseResult calculateTestCaseResult(BoardGtrResult boardGtrResult); // 計算測項結果
}

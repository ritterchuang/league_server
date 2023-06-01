package org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr;

import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;

public interface ITestCaseCtr {
    TestCaseResult calculateTestCaseResult(BoardGtrResult boardGtrResult); // 計算測項結果
}

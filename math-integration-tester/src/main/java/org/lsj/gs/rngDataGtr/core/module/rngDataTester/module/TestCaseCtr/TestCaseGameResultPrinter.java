package org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr;

import org.lsj.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseGameResult;

import java.util.List;

// 測項遊戲結果列印器
public class TestCaseGameResultPrinter {
    public void print(List<TestCaseGameResult> testCaseGameResultList) {
        System.out.println("[測項結果列表]");
        for(TestCaseGameResult testCaseGameResult: testCaseGameResultList){
            System.out.println(testCaseGameResult.getTestCaseResult().getSystemPrintString());
        }
    }

    public void print(TestCaseGameResult testCaseGameResult) {
        System.out.println("[單一測項結果]" + "\t" + testCaseGameResult.getTestCaseResult().getSystemPrintString());
    }
}

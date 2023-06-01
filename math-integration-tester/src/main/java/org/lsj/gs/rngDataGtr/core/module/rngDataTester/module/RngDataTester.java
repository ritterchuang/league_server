package org.lsj.gs.rngDataGtr.core.module.rngDataTester.module;

import org.lsj.gs.mathStr.core.entity.playStr.BoardGtrResult;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.RngDataTesterConfig;
import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.TestCaseCtrFactory;

// 亂數檢測器
public class RngDataTester {
    private final RngDataTesterConfig rngDataTesterConfig; // 亂數檢測器設定

    public RngDataTester(RngDataTesterConfig rngDataTesterConfig) {
        this.rngDataTesterConfig = rngDataTesterConfig;
    }

    // 計算檢測結果
    public TestCaseResult calculateTestCaseResult(int testCaseIndex, BoardGtrResult boardGtrResult){
        // 1. 防呆
        if(testCaseIndex >= this.rngDataTesterConfig.getTestCaseConfigList().size()){
            return new TestCaseResult();
        }

        // 2. 取得測項設定
        TestCaseConfig testCaseConfig = this.rngDataTesterConfig.getTestCaseConfigList().get(testCaseIndex);

        // 3. 檢測
        return new TestCaseCtrFactory().getTestCaseCtr(testCaseConfig).calculateTestCaseResult(boardGtrResult);
    }

    public RngDataTesterConfig getRngDataTesterConfig() {
        return rngDataTesterConfig;
    }
}

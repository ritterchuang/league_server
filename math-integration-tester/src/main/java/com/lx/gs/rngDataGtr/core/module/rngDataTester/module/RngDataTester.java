package com.lx.gs.rngDataGtr.core.module.rngDataTester.module;

import com.lx.gs.mathStr.core.entity.playStr.BoardGtrResult;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.RngDataTesterConfig;
import com.lx.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import com.lx.gs.rngDataGtr.core.module.rngDataTester.entity.TestCaseResult;
import com.lx.gs.rngDataGtr.core.module.rngDataTester.module.TestCaseCtr.TestCaseCtrFactory;

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

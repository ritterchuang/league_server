package org.lsj.gs.rngDataGtr.config.entity.rngDataTester;

import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;

import java.util.List;

// 亂數檢測器設定
public class RngDataTesterConfig {
    private final List<TestCaseConfig> testCaseConfigList; // 測項設定列表

    public RngDataTesterConfig(List<TestCaseConfig> testCaseConfigList) {
        this.testCaseConfigList = testCaseConfigList;
    }

    public List<TestCaseConfig> getTestCaseConfigList() {
        return testCaseConfigList;
    }
}

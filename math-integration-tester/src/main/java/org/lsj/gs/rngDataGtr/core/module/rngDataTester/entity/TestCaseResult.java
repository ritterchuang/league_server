package org.lsj.gs.rngDataGtr.core.module.rngDataTester.entity;

import org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase.TestCaseConfig;
import org.lsj.gs.rngDataGtr.core.module.rngDataTester.entity.testCaseResultExtend.TestCaseResultExtend;

import java.util.List;

// 測項結果
public class TestCaseResult {
    protected final TestCaseConfig testCaseConfig; // 測項設定
    protected final boolean isMatch; // 符合標籤
    protected final List<Integer> randomNumberUsedList; // 亂數列表
    protected final TestCaseResultExtend testCaseResultExtend;

    public TestCaseResult() {
        this.testCaseConfig = null;
        this.isMatch = false;
        this.randomNumberUsedList = null;
        this.testCaseResultExtend = new TestCaseResultExtend();
    }

    public TestCaseResult(TestCaseConfig testCaseConfig, boolean isMatch, List<Integer> randomNumberUsedList) {
        this.testCaseConfig = testCaseConfig;
        this.isMatch = isMatch;
        this.randomNumberUsedList = randomNumberUsedList;
        this.testCaseResultExtend = new TestCaseResultExtend();
    }

    public TestCaseResult(TestCaseConfig testCaseConfig, boolean isMatch, List<Integer> randomNumberUsedList, TestCaseResultExtend testCaseResultExtend) {
        this.testCaseConfig = testCaseConfig;
        this.isMatch = isMatch;
        this.randomNumberUsedList = randomNumberUsedList;
        this.testCaseResultExtend = testCaseResultExtend;
    }

    public TestCaseConfig getTestCaseConfig() {
        return testCaseConfig;
    }

    public boolean isMatch() {
        return isMatch;
    }

    public List<Integer> getRandomNumberUsedList() {
        return randomNumberUsedList;
    }

    public String getSystemPrintString(){
        return new StringBuilder()
                .append(this.testCaseConfig.getSystemPrintString())
                .append("\t")
                .append(this.testCaseResultExtend.getPrintString())
                .append("\t")
                .append(this.isMatch)
                .append("\t")
                .append(this.randomNumberUsedList.size())
                .append("\t")
                .append(this.randomNumberUsedList)
                .toString();
    }
}

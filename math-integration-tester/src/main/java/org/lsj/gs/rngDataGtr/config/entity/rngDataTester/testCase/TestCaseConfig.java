package org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase;

import org.lsj.gs.rngDataGtr.core.entity.ConstRngDataGtr;

// 測項設定
public class TestCaseConfig implements ITestCaseConfig{
    private final ConstRngDataGtr.TestCaseTypeEnum testCaseTypeEnum; // 測項種類定義
    private final AbstractTestCaseConfigExtend testCaseConfigExtend; // 客製測項設定

    public TestCaseConfig(ConstRngDataGtr.TestCaseTypeEnum testCaseTypeEnum, AbstractTestCaseConfigExtend testCaseConfigExtend) {
        this.testCaseTypeEnum = testCaseTypeEnum;
        this.testCaseConfigExtend = testCaseConfigExtend;
    }

    public ConstRngDataGtr.TestCaseTypeEnum getTestCaseTypeEnum() {
        return testCaseTypeEnum;
    }

    public AbstractTestCaseConfigExtend getTestCaseConfigExtend() {
        return testCaseConfigExtend;
    }

    @Override
    public String getSystemPrintString(){
        return new StringBuilder()
                .append(this.testCaseTypeEnum.name())
                .append("\t")
                .append(this.testCaseConfigExtend.systemPrintString())
                .toString();
    }
}

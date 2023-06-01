package org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase;

// 抽象客製測項設定
public abstract class AbstractTestCaseConfigExtend implements ITestCaseConfigExtend{
    private final int DEFAULT_RND_COUNT_TOP_LIMIT = 1500; // 預設亂數個數上限個數
    private final int rndCountTopLimit; // 亂數個數上限個數

    public AbstractTestCaseConfigExtend(int rndCountTopLimit) {
        this.rndCountTopLimit = rndCountTopLimit;
    }

    public AbstractTestCaseConfigExtend() {
        this.rndCountTopLimit = this.DEFAULT_RND_COUNT_TOP_LIMIT;
    }

    public int getRndCountTopLimit() {
        return rndCountTopLimit;
    }
}

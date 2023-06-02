package org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase;

// 客製測項設定 新牌九 和局測項
public class TestCaseConfigE122QzpjJavaTie extends AbstractTestCaseConfigExtend {
    private final int humanVieBankerRate; // 真人搶莊倍數
    private final int humanBetRate; // 真人搶閒倍數

    public TestCaseConfigE122QzpjJavaTie(int humanVieBankerRate, int humanBetRate) {
        this.humanVieBankerRate = humanVieBankerRate;
        this.humanBetRate = humanBetRate;
    }

    public int getHumanVieBankerRate() {
        return humanVieBankerRate;
    }

    public int getHumanBetRate() {
        return humanBetRate;
    }


    @Override
    public String systemPrintString() {
        return this.humanVieBankerRate + "_" +
                this.humanBetRate;
    }
}

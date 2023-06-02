package org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase;

import org.lsj.gs.math.games.sg_java.entity.ConstSgJava;

// 客製測項設定 新三公 牌型測項
public class TestCaseConfigE117SgJavaSgType extends AbstractTestCaseConfigExtend {
    private final int humanVieBankerRate; // 真人搶莊倍數
    private final int humanBetRate; // 真人搶閒倍數
    private final ConstSgJava.SgTypeEnumSgJava sgTypeEnumSgJava; // 三公牌型定義

    public TestCaseConfigE117SgJavaSgType(int humanVieBankerRate, int humanBetRate, ConstSgJava.SgTypeEnumSgJava sgTypeEnumSgJava) {
        this.humanVieBankerRate = humanVieBankerRate;
        this.humanBetRate = humanBetRate;
        this.sgTypeEnumSgJava = sgTypeEnumSgJava;
    }

    public int getHumanVieBankerRate() {
        return humanVieBankerRate;
    }

    public int getHumanBetRate() {
        return humanBetRate;
    }

    public ConstSgJava.SgTypeEnumSgJava getSgTypeEnumSgJava() {
        return sgTypeEnumSgJava;
    }

    @Override
    public String systemPrintString() {
        return this.humanVieBankerRate + "_" +
                this.humanBetRate + "_" +
                this.sgTypeEnumSgJava.name();
    }
}

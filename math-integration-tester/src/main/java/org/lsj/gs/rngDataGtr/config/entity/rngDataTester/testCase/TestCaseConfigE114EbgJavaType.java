package org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase;

import org.lsj.gs.math.games.ebg_java.entity.ConstEbgJava;

// 客製測項設定 新二八槓 牌型測項
public class TestCaseConfigE114EbgJavaType extends AbstractTestCaseConfigExtend {
    private final int humanVieBankerRate; // 真人搶莊倍數
    private final int humanBetRate; // 真人搶閒倍數
    private final ConstEbgJava.EbgTypeEnumEbgJava ebgTypeEnumEbgJava; // 二八槓牌型定義

    public TestCaseConfigE114EbgJavaType(int humanVieBankerRate, int humanBetRate, ConstEbgJava.EbgTypeEnumEbgJava ebgTypeEnumEbgJava) {
        this.humanVieBankerRate = humanVieBankerRate;
        this.humanBetRate = humanBetRate;
        this.ebgTypeEnumEbgJava = ebgTypeEnumEbgJava;
    }

    public int getHumanVieBankerRate() {
        return humanVieBankerRate;
    }

    public int getHumanBetRate() {
        return humanBetRate;
    }

    public ConstEbgJava.EbgTypeEnumEbgJava getEbgTypeEnumEbgJava() {
        return ebgTypeEnumEbgJava;
    }

    @Override
    public String systemPrintString() {
        return this.humanVieBankerRate + "_" +
                this.humanBetRate + "_" +
                this.ebgTypeEnumEbgJava.name();
    }
}

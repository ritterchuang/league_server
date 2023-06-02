package org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase;

import org.lsj.gs.math.games.qzpj_java.entity.ConstQzpjJava;

// 客製測項設定 新牌九 牌型測項
public class TestCaseConfigE122QzpjJavaType extends AbstractTestCaseConfigExtend {
    private final int humanVieBankerRate; // 真人搶莊倍數
    private final int humanBetRate; // 真人搶閒倍數
    private final ConstQzpjJava.PjTypeEnumQzpjJava pjTypeEnumQzpjJava; // 牌九牌型定義

    public TestCaseConfigE122QzpjJavaType(int humanVieBankerRate, int humanBetRate, ConstQzpjJava.PjTypeEnumQzpjJava pjTypeEnumQzpjJava) {
        this.humanVieBankerRate = humanVieBankerRate;
        this.humanBetRate = humanBetRate;
        this.pjTypeEnumQzpjJava = pjTypeEnumQzpjJava;
    }

    public int getHumanVieBankerRate() {
        return humanVieBankerRate;
    }

    public int getHumanBetRate() {
        return humanBetRate;
    }

    public ConstQzpjJava.PjTypeEnumQzpjJava getPjTypeEnumQzpjJava() {
        return pjTypeEnumQzpjJava;
    }

    @Override
    public String systemPrintString() {
        return this.humanVieBankerRate + "_" +
                this.humanBetRate + "_" +
                this.pjTypeEnumQzpjJava.name();
    }
}

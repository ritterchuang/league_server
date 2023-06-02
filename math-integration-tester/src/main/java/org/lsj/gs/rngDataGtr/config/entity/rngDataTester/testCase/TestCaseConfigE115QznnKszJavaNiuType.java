package org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase;

import org.lsj.gs.math.games.qznn_ksz_java.entity.ConstQznnKszJava;

// 客製測項設定 新看三張搶莊牛牛 牌型測項
public class TestCaseConfigE115QznnKszJavaNiuType extends AbstractTestCaseConfigExtend {
    private final int humanVieBankerRate; // 真人搶莊倍數
    private final int humanBetRate; // 真人搶閒倍數
    private final ConstQznnKszJava.NiuTypeEnumQznnKszJava niuTypeEnum;

    public TestCaseConfigE115QznnKszJavaNiuType(int humanVieBankerRate, int humanBetRate, ConstQznnKszJava.NiuTypeEnumQznnKszJava niuTypeEnum) {
        this.humanVieBankerRate = humanVieBankerRate;
        this.humanBetRate = humanBetRate;
        this.niuTypeEnum = niuTypeEnum;
    }

    public int getHumanVieBankerRate() {
        return humanVieBankerRate;
    }

    public int getHumanBetRate() {
        return humanBetRate;
    }

    public ConstQznnKszJava.NiuTypeEnumQznnKszJava getNiuTypeEnum() {
        return niuTypeEnum;
    }

    @Override
    public String systemPrintString() {
        return this.humanVieBankerRate + "_" +
                this.humanBetRate + "_" +
                this.niuTypeEnum.name();
    }
}

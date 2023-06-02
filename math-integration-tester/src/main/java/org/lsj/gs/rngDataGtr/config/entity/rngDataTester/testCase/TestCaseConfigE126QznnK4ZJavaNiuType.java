package org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase;

import org.lsj.gs.math.games.qznn_k4z_java.entity.ConstQznnK4zJava;

// 客製測項設定 新看四張搶莊牛牛 牌型測項
public class TestCaseConfigE126QznnK4ZJavaNiuType extends AbstractTestCaseConfigExtend {
    private final int humanVieBankerRate; // 真人搶莊倍數
    private final int humanBetRate; // 真人搶閒倍數
    private final ConstQznnK4zJava.NiuTypeEnumQznnK4zJava niuTypeEnum;

    public TestCaseConfigE126QznnK4ZJavaNiuType(int humanVieBankerRate, int humanBetRate, ConstQznnK4zJava.NiuTypeEnumQznnK4zJava niuTypeEnum) {
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

    public ConstQznnK4zJava.NiuTypeEnumQznnK4zJava getNiuTypeEnum() {
        return niuTypeEnum;
    }

    @Override
    public String systemPrintString() {
        return this.humanVieBankerRate + "_" +
                this.humanBetRate + "_" +
                this.niuTypeEnum.name();
    }
}

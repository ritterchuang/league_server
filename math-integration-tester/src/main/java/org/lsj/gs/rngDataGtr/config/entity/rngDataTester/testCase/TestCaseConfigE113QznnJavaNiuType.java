package org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase;

import org.lsj.gs.math.games.qznn_java.entity.ConstQznnJava;

// 客製測項設定 新搶莊牛牛 牌型測項
public class TestCaseConfigE113QznnJavaNiuType extends AbstractTestCaseConfigExtend {
    private final int humanVieBankerRate; // 真人搶莊倍數
    private final int humanBetRate; // 真人搶閒倍數
    private final ConstQznnJava.NiuTypeEnumQznnJava niuTypeEnum; // 玩家牌型列舉

    public TestCaseConfigE113QznnJavaNiuType(int humanVieBankerRate, int humanBetRate, ConstQznnJava.NiuTypeEnumQznnJava niuTypeEnum) {
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

    public ConstQznnJava.NiuTypeEnumQznnJava getNiuTypeEnum() {
        return niuTypeEnum;
    }

    @Override
    public String systemPrintString() {
        return this.humanVieBankerRate + "_" +
                this.humanBetRate + "_" +
                this.niuTypeEnum.name();
    }
}

package org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase;

import org.lsj.gs.math.games.lznn_java.entity.ConstLznnJava;

// 客製測項設定 新賴子牛牛 牌型測項
public class TestCaseConfigE127LznnJavaNiuType extends AbstractTestCaseConfigExtend {
    private final int humanVieBankerRate; // 真人搶莊倍數
    private final int humanBetRate; // 真人搶閒倍數
    private final ConstLznnJava.NiuTypeEnumLznnJava niuTypeEnum; // 牛牌型

    public TestCaseConfigE127LznnJavaNiuType(int humanVieBankerRate, int humanBetRate, ConstLznnJava.NiuTypeEnumLznnJava niuTypeEnum) {
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

    public ConstLznnJava.NiuTypeEnumLznnJava getNiuTypeEnum() {
        return niuTypeEnum;
    }

    @Override
    public String systemPrintString() {
        return this.humanVieBankerRate + "_" +
                this.humanBetRate + "_" +
                this.niuTypeEnum.name();
    }
}

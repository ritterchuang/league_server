package org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase;

import org.lsj.gs.math.games.tbnn_java.entity.ConstTbnnJava;

// 客製測項設定 新通比牛牛 牌型測項
public class TestCaseConfigE125TbnnJavaNiuType extends AbstractTestCaseConfigExtend {
    private final int humanBetRate; // 真人搶閒倍數
    private final ConstTbnnJava.NiuTypeEnumTbnnJava niuTypeEnum;

    public TestCaseConfigE125TbnnJavaNiuType(int humanBetRate, ConstTbnnJava.NiuTypeEnumTbnnJava niuTypeEnum) {
        this.humanBetRate = humanBetRate;
        this.niuTypeEnum = niuTypeEnum;
    }


    public int getHumanBetRate() {
        return humanBetRate;
    }

    public ConstTbnnJava.NiuTypeEnumTbnnJava getNiuTypeEnum() {
        return niuTypeEnum;
    }

    @Override
    public String systemPrintString() {
        return this.humanBetRate + "_" + this.niuTypeEnum.name();
    }
}

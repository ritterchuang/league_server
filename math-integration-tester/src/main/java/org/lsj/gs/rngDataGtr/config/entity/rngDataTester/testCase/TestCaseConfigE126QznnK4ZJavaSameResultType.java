package org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase;

import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;

// 客製測項設定 新看四張搶莊牛牛 相同牌型測項
public class TestCaseConfigE126QznnK4ZJavaSameResultType extends AbstractTestCaseConfigExtend {
    private final ConstNiu.NiuTypeEnumCommon niuTypeEnum;

    public TestCaseConfigE126QznnK4ZJavaSameResultType(ConstNiu.NiuTypeEnumCommon niuTypeEnum) {
        this.niuTypeEnum = niuTypeEnum;
    }

    public ConstNiu.NiuTypeEnumCommon getNiuTypeEnum() {
        return niuTypeEnum;
    }

    @Override
    public String systemPrintString() {
        return this.niuTypeEnum.name();
    }
}

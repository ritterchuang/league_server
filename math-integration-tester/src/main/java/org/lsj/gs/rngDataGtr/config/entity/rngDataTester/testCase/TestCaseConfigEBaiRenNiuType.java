package org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase;

import org.lsj.gs.math.core.baiRen.ConstMathNiu;
import org.lsj.gs.math.core.card.stackCtr.niuStackCtr.ConstNiu;

// 客製測項設定 百人牛類 牌型區
public class TestCaseConfigEBaiRenNiuType extends AbstractTestCaseConfigExtend {
    private final ConstMathNiu.BetAreaEnum betAreaEnum; // 押注區域
    private final ConstNiu.NiuTypeEnumCommon niuTypeEnumCommon; // 牛牌型

    public TestCaseConfigEBaiRenNiuType(ConstMathNiu.BetAreaEnum betAreaEnum, ConstNiu.NiuTypeEnumCommon niuTypeEnumCommon) {
        this.betAreaEnum = betAreaEnum;
        this.niuTypeEnumCommon = niuTypeEnumCommon;
    }

    public ConstMathNiu.BetAreaEnum getBetAreaEnum() {
        return betAreaEnum;
    }

    public ConstNiu.NiuTypeEnumCommon getNiuTypeEnumCommon() {
        return niuTypeEnumCommon;
    }

    @Override
    public String systemPrintString() {
        return  betAreaEnum.name()
                + "_"
                + niuTypeEnumCommon.name();
    }
}

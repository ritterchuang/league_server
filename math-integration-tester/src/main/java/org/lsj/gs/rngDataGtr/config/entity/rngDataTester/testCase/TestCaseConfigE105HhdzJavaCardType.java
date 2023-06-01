package org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase;

import org.lsj.gs.math.core.card.stackCtr.jhStackCtr.ConstJhStack;
import org.lsj.gs.math.games.hhdz_java.entity.ConstHhdzJava;

// 客製測項設定 新紅黑大戰 牌型測項
public class TestCaseConfigE105HhdzJavaCardType extends AbstractTestCaseConfigExtend {
    private final ConstHhdzJava.BetAreaIdHhdzJava betAreaId; // 押注區域識別碼
    private final ConstJhStack.JhTypeEnumCommon stack; // 開牌結果

    public TestCaseConfigE105HhdzJavaCardType(
            ConstHhdzJava.BetAreaIdHhdzJava betAreaId,
            ConstJhStack.JhTypeEnumCommon stack) {
        this.betAreaId = betAreaId;
        this.stack = stack;
    }

    public ConstHhdzJava.BetAreaIdHhdzJava getBetAreaId() {
        return betAreaId;
    }

    public ConstJhStack.JhTypeEnumCommon getStack() {
        return stack;
    }

    @Override
    public String systemPrintString() {
        return this.betAreaId.name() + "_" + this.stack.name();
    }
}

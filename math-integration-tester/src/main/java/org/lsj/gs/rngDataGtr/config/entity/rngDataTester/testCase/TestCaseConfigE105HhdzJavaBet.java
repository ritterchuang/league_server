package org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase;

import org.lsj.gs.math.games.hhdz_java.entity.ConstHhdzJava;

// 客製測項設定 新紅黑大戰 押注區
public class TestCaseConfigE105HhdzJavaBet extends AbstractTestCaseConfigExtend {
    private final ConstHhdzJava.BetAreaIdHhdzJava betAreaId; // 押注區域識別碼
    private final ConstHhdzJava.BetAreaIdHhdzJava resultBetAreaId; // 龍虎開牌結果

    public TestCaseConfigE105HhdzJavaBet(
            ConstHhdzJava.BetAreaIdHhdzJava betAreaId,
            ConstHhdzJava.BetAreaIdHhdzJava resultBetAreaId) {
        this.betAreaId = betAreaId;
        this.resultBetAreaId = resultBetAreaId;
    }

    public ConstHhdzJava.BetAreaIdHhdzJava getBetAreaId() {
        return betAreaId;
    }

    public ConstHhdzJava.BetAreaIdHhdzJava getResultBetAreaId() {
        return resultBetAreaId;
    }

    @Override
    public String systemPrintString() {
        return this.betAreaId.name() + "_" + this.resultBetAreaId.name();
    }
}

package org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase;

import org.lsj.gs.math.games.lhd_java.entity.ConstLhdJava;

// 客製測項設定 新龍虎鬥 押注區與牌型測項
public class TestCaseConfigE111LhdJavaBetAndCardType extends AbstractTestCaseConfigExtend {
    private final ConstLhdJava.BetAreaIdLhdJava betAreaId; // 押注區域識別碼
    private final ConstLhdJava.BetAreaIdLhdJava resultBetAreaId; // 龍虎開牌結果

    public TestCaseConfigE111LhdJavaBetAndCardType(
            ConstLhdJava.BetAreaIdLhdJava betAreaId,
            ConstLhdJava.BetAreaIdLhdJava resultBetAreaId) {
        this.betAreaId = betAreaId;
        this.resultBetAreaId = resultBetAreaId;
    }

    public ConstLhdJava.BetAreaIdLhdJava getBetAreaId() {
        return betAreaId;
    }

    public ConstLhdJava.BetAreaIdLhdJava getResultBetAreaId() {
        return resultBetAreaId;
    }

    @Override
    public String systemPrintString() {
        return this.betAreaId.name() + "_" + this.resultBetAreaId.name();
    }
}

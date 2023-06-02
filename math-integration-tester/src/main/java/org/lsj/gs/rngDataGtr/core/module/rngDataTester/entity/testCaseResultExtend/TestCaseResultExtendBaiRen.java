package org.lsj.gs.rngDataGtr.core.module.rngDataTester.entity.testCaseResultExtend;

import org.lsj.utils.JsonUtil;

public class TestCaseResultExtendBaiRen extends TestCaseResultExtend{
    private final int[] betArray; // 各區域下注列表

    public TestCaseResultExtendBaiRen(int[] betArray) {
        this.betArray = betArray;
    }

    public int[] getBetArray() {
        return betArray;
    }

    public String getPrintString() {
        return JsonUtil.getInstance().writeValueAsStringWithoutException(this.betArray);
    }
}

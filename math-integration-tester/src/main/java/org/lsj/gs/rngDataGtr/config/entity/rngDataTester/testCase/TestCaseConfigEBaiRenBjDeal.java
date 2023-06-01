package org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase;

import org.lsj.gs.math.core.baiRen.ConstMathBjl;

// 客製測項設定 百家類 發牌
public class TestCaseConfigEBaiRenBjDeal extends AbstractTestCaseConfigExtend {
    private final ConstMathBjl.DealAreaEnum dealAreaEnum; // 發牌區
    private final int cardCount; // 發牌數
    private final int pointSum; // 點數和

    public TestCaseConfigEBaiRenBjDeal(ConstMathBjl.DealAreaEnum dealAreaEnum, int cardCount, int pointSum) {
        this.dealAreaEnum = dealAreaEnum;
        this.cardCount = cardCount;
        this.pointSum = pointSum;
    }

    public ConstMathBjl.DealAreaEnum getDealAreaEnum() {
        return dealAreaEnum;
    }

    public int getCardCount() {
        return cardCount;
    }

    public int getPointSum() {
        return pointSum;
    }

    @Override
    public String systemPrintString() {
        return  this.dealAreaEnum.name()
                + "_"
                + this.cardCount
                + "_"
                + this.pointSum;
    }
}

package org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase;

import org.lsj.gs.math.core.card.ConstMathCard;

// 客製測項設定 搶莊全輸贏測項
public class TestCaseConfigEBankerAllWinLoss extends AbstractTestCaseConfigExtend {
    private final int humanVieBankerRate; // 真人搶莊倍數
    private final int humanBetRate; // 真人搶閒倍數
    private final ConstMathCard.WinType winType; // 玩家輸贏列舉

    public TestCaseConfigEBankerAllWinLoss(int humanVieBankerRate, int humanBetRate, ConstMathCard.WinType winType) {
        this.humanVieBankerRate = humanVieBankerRate;
        this.humanBetRate = humanBetRate;
        this.winType = winType;
    }

    public int getHumanVieBankerRate() {
        return humanVieBankerRate;
    }

    public int getHumanBetRate() {
        return humanBetRate;
    }

    public ConstMathCard.WinType getWinType() {
        return winType;
    }

    @Override
    public String systemPrintString() {
        return this.humanVieBankerRate + "_" +
                this.humanBetRate + "_" +
                this.winType.name();
    }
}

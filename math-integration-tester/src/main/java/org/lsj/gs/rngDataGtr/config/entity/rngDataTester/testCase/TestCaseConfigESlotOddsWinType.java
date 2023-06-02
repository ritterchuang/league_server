package org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 客製測項設定 虎機倍數表演類型測項
public class TestCaseConfigESlotOddsWinType extends AbstractTestCaseConfigExtend {
    private final ConstMathSlot.OddsWinType oddsWinType;

    public TestCaseConfigESlotOddsWinType(ConstMathSlot.OddsWinType oddsWinType) {
        this.oddsWinType = oddsWinType;
    }

    public ConstMathSlot.OddsWinType getOddsWinType() {
        return oddsWinType;
    }

    @Override
    public String systemPrintString() {
        return this.oddsWinType.name();
    }
}

package org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 客製測項設定 消除虎機 賠率表連線測項
public class TestCaseConfigESlotCascadeGameState extends AbstractTestCaseConfigExtend {

    private final ConstMathSlot.GameStateType gameStateType; // 遊戲狀態類型

    public TestCaseConfigESlotCascadeGameState(ConstMathSlot.GameStateType gameStateType) {
        this.gameStateType = gameStateType;
    }

    public ConstMathSlot.GameStateType getGameStateType() {
        return gameStateType;
    }

    @Override
    public String systemPrintString() {
        return this.gameStateType.name();
    }
}

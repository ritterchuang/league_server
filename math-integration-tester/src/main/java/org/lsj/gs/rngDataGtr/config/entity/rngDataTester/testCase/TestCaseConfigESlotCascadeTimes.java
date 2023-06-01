package org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 客製測項設定 消除虎機 消除次數測項
public class TestCaseConfigESlotCascadeTimes extends AbstractTestCaseConfigExtend {
    private final int cascadeTimes; // 消除次數

    private final ConstMathSlot.GameStateType gameStateType; // 遊戲狀態類型

    public TestCaseConfigESlotCascadeTimes(int cascadeTimes, ConstMathSlot.GameStateType gameStateType) {
        this.cascadeTimes = cascadeTimes;
        this.gameStateType = gameStateType;
    }

    public int getCascadeTimes() {
        return cascadeTimes;
    }

    public ConstMathSlot.GameStateType getGameStateType() {
        return gameStateType;
    }

    @Override
    public String systemPrintString() {
        return this.cascadeTimes + "_" +
                this.gameStateType.name();
    }
}

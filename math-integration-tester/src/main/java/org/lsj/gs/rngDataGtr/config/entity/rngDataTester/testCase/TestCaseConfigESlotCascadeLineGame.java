package org.lsj.gs.rngDataGtr.config.entity.rngDataTester.testCase;

import org.lsj.gs.math.core.slot.ConstMathSlot;

// 客製測項設定 消除虎機 賠率表連線測項
public class TestCaseConfigESlotCascadeLineGame extends AbstractTestCaseConfigExtend {
    private final int payComboId; // 賠率表識別碼
    private final int hitNumber; // 連線數

    private final ConstMathSlot.GameStateType gameStateType; // 遊戲狀態類型

    public TestCaseConfigESlotCascadeLineGame(int payComboId, int hitNumber, ConstMathSlot.GameStateType gameStateType) {
        this.payComboId = payComboId;
        this.hitNumber = hitNumber;
        this.gameStateType = gameStateType;
    }

    public int getPayComboId() {
        return payComboId;
    }

    public int getHitNumber() {
        return hitNumber;
    }

    public ConstMathSlot.GameStateType getGameStateType() {
        return gameStateType;
    }

    @Override
    public String systemPrintString() {
        return this.payComboId + "_" +
                this.hitNumber + "_" +
                this.gameStateType.name();
    }
}
